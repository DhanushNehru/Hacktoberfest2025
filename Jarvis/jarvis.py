#!/usr/bin/env python3
"""
Jarvis AI Assistant for Arch Linux + Hyprland
A modular voice assistant with wake word detection, speech recognition, and task execution
"""

import json
import os
import sys
import subprocess
import threading
import queue
import time
import struct
import wave
import numpy as np
from pathlib import Path
from typing import Dict, Any, Optional, List
from dataclasses import dataclass
from enum import Enum

# Import required libraries
try:
    import pvporcupine
    import pyaudio
    from faster_whisper import WhisperModel
    from llama_cpp import Llama

except ImportError as e:
    print(f"Missing dependency: {e}")
    print("Install with: pip install pvporcupine pyaudio faster-whisper llama-cpp-python")
    sys.exit(1)

# Configuration
@dataclass
class Config:
    try:
        with open("config.json", "r") as f:
            data_load = json.load(f)
    except Exception as e:
        print(f"Warning: Could not load config.json ({e}), using defaults.")
        data_load = {}
    # Porcupine wake word settings
    PORCUPINE_ACCESS_KEY = data_load["porcupine"]["access_key"] # Get from .env file
    WAKE_WORD = data_load["porcupine"]["wake_word"]  # e.g., "jarvis"
    SENSITIVITY = data_load["porcupine"]["sensitivity"]  # 0 to 1
    KEYWORD_PATH_PORCUPINE = data_load["porcupine"]["keyword_path"]  # Write your own model path or remove the subsequent line of code
    
    # Audio settings
    SAMPLE_RATE = 16000
    FRAME_LENGTH = 512
    AUDIO_DEVICE_INDEX = None  # None for default device
    
    # Whisper settings
    WHISPER_MODEL = data_load["whisper"]["model"]  # Options: tiny, base, small, medium, large
    WHISPER_DEVICE = data_load["whisper"]["device"]  # or "cuda" if you have GPU
    WHISPER_COMPUTE_TYPE = data_load["whisper"]["compute_type"]  # int8 for CPU, float16 for GPU
    WHISPER_LANGUAGE = data_load["whisper"]["language"]  # e.g., "en"
    
    # Llama settings
    LLAMA_MODEL_PATH = data_load["llama"]["model_path"]     # Write your own model
    LLAMA_N_CTX = 2048
    LLAMA_N_THREADS = 4
    
    # Piper TTS settings
    PIPER_MODEL = data_load["piper"]["voice"]
    PIPER_VOICE_PATH = Path.home() / ".local/share/piper/voices"
    PIPER_SPEED = data_load["piper"]["speed"]  # 1.0 is normal speed
    
    # System settings
    TASKS_DIR = Path.home() / ".config/jarvis/tasks"
    LOG_DIR = Path.home() / ".local/share/jarvis/logs"
    CACHE_DIR = Path.home() / ".cache/jarvis"

class TaskType(Enum):
    SYSTEM = "system"
    APPLICATION = "application"
    WEB = "web"
    CUSTOM = "custom"
    QUERY = "query"

@dataclass
class Task:
    name: str
    type: TaskType
    description: str
    patterns: List[str]
    action: Dict[str, Any]
    response_template: str

class TaskManager:
    """Manages and executes system tasks"""
    
    def __init__(self, config: Config):
        self.config = config
        self.tasks = {}
        self.load_tasks()
    
    def load_tasks(self):
        """Load task definitions from JSON files"""
        self.config.TASKS_DIR.mkdir(parents=True, exist_ok=True)
        
        # Create default tasks if none exist
        default_tasks_file = self.config.TASKS_DIR / "default_tasks.json"
        if not default_tasks_file.exists():
            self.create_default_tasks(default_tasks_file)
        
        # Load all task files
        for task_file in self.config.TASKS_DIR.glob("*.json"):
            try:
                with open(task_file, 'r') as f:
                    tasks_data = json.load(f)
                    for task_data in tasks_data.get('tasks', []):
                        task = Task(
                            name=task_data['name'],
                            type=TaskType(task_data['type']),
                            description=task_data['description'],
                            patterns=task_data['patterns'],
                            action=task_data['action'],
                            response_template=task_data['response_template']
                        )
                        self.tasks[task.name] = task
            except Exception as e:
                print(f"Error loading task file {task_file}: {e}")
    
    def create_default_tasks(self, filepath: Path):
        """Create default task definitions"""
        default_tasks = {
            "tasks": [
                {
                    "name": "open_terminal",
                    "type": "application",
                    "description": "Open a terminal emulator",
                    "patterns": ["open terminal", "launch terminal", "start console"],
                    "action": {
                        "command": "kitty",
                        "args": []
                    },
                    "response_template": "Opening terminal for you"
                },
                {
                    "name": "open_browser",
                    "type": "application",
                    "description": "Open web browser",
                    "patterns": ["open browser", "launch firefox", "start chrome"],
                    "action": {
                        "command": "firefox",
                        "args": []
                    },
                    "response_template": "Launching web browser"
                },
                {
                    "name": "system_info",
                    "type": "system",
                    "description": "Get system information",
                    "patterns": ["system info", "system status", "how much ram", "cpu usage"],
                    "action": {
                        "command": "custom_function",
                        "function": "get_system_info"
                    },
                    "response_template": "Here's your system information: {result}"
                },
                {
                    "name": "volume_control",
                    "type": "system",
                    "description": "Control system volume",
                    "patterns": ["volume up", "volume down", "mute", "unmute"],
                    "action": {
                        "command": "pactl",
                        "args_template": "set-sink-volume @DEFAULT_SINK@ {volume}"
                    },
                    "response_template": "Volume adjusted"
                },
                {
                    "name": "workspace_switch",
                    "type": "system",
                    "description": "Switch Hyprland workspace",
                    "patterns": ["workspace", "switch to workspace", "go to workspace"],
                    "action": {
                        "command": "hyprctl",
                        "args_template": "dispatch workspace {number}"
                    },
                    "response_template": "Switching to workspace {number}"
                },
                {
                    "name": "take_screenshot",
                    "type": "system",
                    "description": "Take a screenshot",
                    "patterns": ["take screenshot", "capture screen", "screenshot"],
                    "action": {
                        "command": "grim",
                        "args": ["-g", "$(slurp)", "~/Pictures/screenshot_$(date +%Y%m%d_%H%M%S).png"]
                    },
                    "response_template": "Screenshot captured and saved"
                }
            ]
        }
        
        with open(filepath, 'w') as f:
            json.dump(default_tasks, f, indent=2)
    
    def execute_task(self, task_name: str, parameters: Dict[str, Any]) -> str:
        """Execute a specific task"""
        if task_name not in self.tasks:
            return "Task not found"
        
        task = self.tasks[task_name]
        
        try:
            if task.type == TaskType.SYSTEM or task.type == TaskType.APPLICATION:
                result = self._execute_command(task.action, parameters)
            elif task.type == TaskType.CUSTOM:
                result = self._execute_custom_function(task.action, parameters)
            else:
                result = "Task type not implemented"
            
            # Format response
            response = task.response_template.format(**parameters, result=result)
            return response
            
        except Exception as e:
            return f"Error executing task: {str(e)}"
    
    def _execute_command(self, action: Dict, params: Dict) -> str:
        """Execute a system command"""
        command = action['command']
        
        if 'args_template' in action:
            args = action['args_template'].format(**params).split()
        else:
            args = action.get('args', [])
        
        try:
            result = subprocess.run([command] + args, capture_output=True, text=True)
            return result.stdout if result.returncode == 0 else result.stderr
        except Exception as e:
            return str(e)
    
    def _execute_custom_function(self, action: Dict, params: Dict) -> str:
        """Execute a custom Python function"""
        func_name = action.get('function')
        
        if func_name == 'get_system_info':
            return self._get_system_info()
        # Add more custom functions here
        
        return "Function not implemented"
    
    def _get_system_info(self) -> str:
        """Get system information"""
        try:
            # CPU usage
            cpu_info = subprocess.run(['top', '-bn1'], capture_output=True, text=True)
            cpu_line = [l for l in cpu_info.stdout.split('\n') if 'Cpu(s)' in l or '%Cpu' in l][0]
            
            # Memory info
            mem_info = subprocess.run(['free', '-h'], capture_output=True, text=True)
            mem_lines = mem_info.stdout.split('\n')[1:3]
            
            return f"CPU: {cpu_line}\nMemory: {mem_lines[0]}"
        except:
            return "Unable to get system info"

class IntentProcessor:
    """Process natural language to structured intents using Llama"""
    
    def __init__(self, config: Config, task_manager: TaskManager):
        self.config = config
        self.task_manager = task_manager
        self.llm = None
        self.load_model()
    
    def load_model(self):
        """Load Llama model"""
        try:
            self.llm = Llama(
                model_path=self.config.LLAMA_MODEL_PATH,
                n_ctx=self.config.LLAMA_N_CTX,
                n_threads=self.config.LLAMA_N_THREADS,
                verbose=False
            )
            print("Llama model loaded successfully")
        except Exception as e:
            print(f"Error loading Llama model: {e}")
            self.llm = None
    
    def process_intent(self, text: str) -> Dict[str, Any]:
        """Convert natural language to structured intent"""
        if not self.llm:
            return self._fallback_intent_processing(text)
        
        # Create prompt for Llama
        task_descriptions = "\n".join([
            f"- {task.name}: {task.description} (patterns: {', '.join(task.patterns)})"
            for task in self.task_manager.tasks.values()
        ])
        
        prompt = f"""You are an AI assistant intent classifier. Given a user command, identify the intent and extract parameters.
Available tasks:
{task_descriptions}

User command: "{text}"

Respond with a JSON object containing:
- "task": the task name that best matches the intent
- "parameters": object with any extracted parameters
- "confidence": confidence score between 0 and 1

Response:"""
        
        try:
            response = self.llm(
                prompt,
                max_tokens=256,
                temperature=0.1,
                stop=["User:", "\n\n"]
            )
            
            # Extract JSON from response
            json_str = response['choices'][0]['text'].strip()
            # Clean up the response to ensure valid JSON
            if not json_str.startswith('{'):
                json_str = '{' + json_str.split('{', 1)[-1]
            if not json_str.endswith('}'):
                json_str = json_str.rsplit('}', 1)[0] + '}'
            
            intent = json.loads(json_str)
            return intent
            
        except Exception as e:
            print(f"Error processing intent with Llama: {e}")
            return self._fallback_intent_processing(text)
    
    def _fallback_intent_processing(self, text: str) -> Dict[str, Any]:
        """Simple pattern matching fallback when Llama is not available"""
        text_lower = text.lower()
        
        for task in self.task_manager.tasks.values():
            for pattern in task.patterns:
                if pattern.lower() in text_lower:
                    # Extract basic parameters
                    params = {}
                    
                    # Extract numbers
                    import re
                    numbers = re.findall(r'\d+', text)
                    if numbers:
                        params['number'] = numbers[0]
                    
                    # Extract volume commands
                    if 'up' in text_lower:
                        params['volume'] = '+10%'
                    elif 'down' in text_lower:
                        params['volume'] = '-10%'
                    
                    return {
                        "task": task.name,
                        "parameters": params,
                        "confidence": 0.7
                    }
        
        return {
            "task": None,
            "parameters": {},
            "confidence": 0.0
        }

class TTSEngine:
    """Text-to-Speech using Piper"""
    
    def __init__(self, config: Config):
        self.config = config
        self.speaking = False
    
    def speak(self, text: str):
        """Convert text to speech and play"""
        self.speaking = True
        try:
            # Use piper-tts command line tool
            cmd = [
                'piper',
                '--model', self.config.PIPER_MODEL,
                '--output-raw',
                '--length-scale', str(1.0 / self.config.PIPER_SPEED)
            ]
            
            # Pipe text to piper and play with aplay
            piper_proc = subprocess.Popen(
                cmd,
                stdin=subprocess.PIPE,
                stdout=subprocess.PIPE,
                stderr=subprocess.DEVNULL
            )
            
            aplay_proc = subprocess.Popen(
                ['aplay', '-r', '22050', '-f', 'S16_LE', '-c', '1'],
                stdin=piper_proc.stdout,
                stderr=subprocess.DEVNULL
            )
            
            piper_proc.communicate(input=text.encode())
            aplay_proc.wait()
            
        except Exception as e:
            print(f"TTS Error: {e}")
        finally:
            self.speaking = False

class JarvisAssistant:
    """Main Jarvis Assistant class"""
    
    def __init__(self, config: Config):
        self.config = config
        self.running = False
        self.audio_queue = queue.Queue()
        
        # Initialize components
        self.task_manager = TaskManager(config)
        self.intent_processor = IntentProcessor(config, self.task_manager)
        self.tts = TTSEngine(config)
        
        # Initialize audio
        self.pa = pyaudio.PyAudio()
        
        # Initialize Porcupine
        self.porcupine = None
        self.init_porcupine()
        
        # Initialize Whisper
        self.whisper_model = None
        self.init_whisper()
    
    def init_porcupine(self):
        """Initialize Porcupine wake word detection"""
        try:
            self.porcupine = pvporcupine.create(
            access_key=self.config.PORCUPINE_ACCESS_KEY,
            keyword_paths=[self.config.KEYWORD_PATH_PORCUPINE],  # load your custom ppn
            sensitivities=[self.config.SENSITIVITY]
)
            print(f"Porcupine initialized for wake word: {self.config.WAKE_WORD}")
        except Exception as e:
            print(f"Error initializing Porcupine: {e}")
            print("Please set your Porcupine access key in the config")
    
    def init_whisper(self):
        """Initialize Whisper speech recognition"""
        try:
            self.whisper_model = WhisperModel(
                self.config.WHISPER_MODEL,
                device=self.config.WHISPER_DEVICE,
                compute_type=self.config.WHISPER_COMPUTE_TYPE
            )
            print(f"Whisper model '{self.config.WHISPER_MODEL}' loaded")
        except Exception as e:
            print(f"Error loading Whisper model: {e}")
    
    def listen_for_wake_word(self):
        """Listen for the wake word"""
        if not self.porcupine:
            return False
        
        audio_stream = self.pa.open(
            rate=self.porcupine.sample_rate,
            channels=1,
            format=pyaudio.paInt16,
            input=True,
            frames_per_buffer=self.porcupine.frame_length,
            input_device_index=self.config.AUDIO_DEVICE_INDEX
        )
        
        print("Listening for wake word...")
        
        try:
            while self.running:
                pcm = audio_stream.read(self.porcupine.frame_length, exception_on_overflow=False)
                pcm = struct.unpack_from("h" * self.porcupine.frame_length, pcm)
                
                keyword_index = self.porcupine.process(pcm)
                
                if keyword_index >= 0:
                    print("Wake word detected!")
                    audio_stream.close()
                    return True
                    
        except Exception as e:
            print(f"Error in wake word detection: {e}")
        finally:
            try:
                if audio_stream.is_active():
                    audio_stream.close()
            except Exception:
                # stream might already be closed
                pass

        
        return False
    
    def record_command(self, duration: float = 5.0) -> np.ndarray:
        """Record audio command"""
        print("Listening for command...")
        
        audio_stream = self.pa.open(
            rate=self.config.SAMPLE_RATE,
            channels=1,
            format=pyaudio.paInt16,
            input=True,
            frames_per_buffer=self.config.FRAME_LENGTH,
            input_device_index=self.config.AUDIO_DEVICE_INDEX
        )
        
        frames = []
        num_frames = int(self.config.SAMPLE_RATE * duration / self.config.FRAME_LENGTH)
        
        try:
            for _ in range(num_frames):
                data = audio_stream.read(self.config.FRAME_LENGTH, exception_on_overflow=False)
                frames.append(data)
        finally:
            audio_stream.close()
        
        # Convert to numpy array
        audio_data = b''.join(frames)
        audio_np = np.frombuffer(audio_data, dtype=np.int16).astype(np.float32) / 32768.0
        
        return audio_np
    
    def transcribe_audio(self, audio: np.ndarray) -> str:
        """Transcribe audio to text using Whisper"""
        if not self.whisper_model:
            return ""
        
        try:
            segments, _ = self.whisper_model.transcribe(audio, beam_size=5, language=self.config.WHISPER_LANGUAGE)
            text = " ".join([segment.text for segment in segments]).strip()
            print(f"Transcribed: {text}")
            return text
        except Exception as e:
            print(f"Transcription error: {e}")
            return ""
    
    def process_command(self, command_text: str):
        """Process and execute command"""
        # Get intent from text
        intent = self.intent_processor.process_intent(command_text)
        
        if intent['task'] and intent['confidence'] > 0.5:
            # Execute task
            response = self.task_manager.execute_task(
                intent['task'],
                intent['parameters']
            )
        else:
            response = "I didn't understand that command. Please try again."
        
        # Speak response
        self.tts.speak(response)
    
    def run(self):
        """Main assistant loop"""
        self.running = True
        
        print("Jarvis Assistant started!")
        print(f"Say '{self.config.WAKE_WORD}' to activate")
        
        try:
            while self.running:
                # Listen for wake word
                if self.listen_for_wake_word():
                    # Greet and ask for command
                    self.tts.speak("Yes sir, how can I help you?")
                    
                    # Record command
                    audio = self.record_command()
                    
                    # Transcribe audio
                    command_text = self.transcribe_audio(audio)
                    
                    if command_text:
                        # Process command
                        self.process_command(command_text)
                    else:
                        self.tts.speak("I didn't catch that. Please try again.")
                
        except KeyboardInterrupt:
            print("\nShutting down...")
        finally:
            self.cleanup()
    
    def cleanup(self):
        """Clean up resources"""
        self.running = False
        
        if self.porcupine:
            self.porcupine.delete()
        
        self.pa.terminate()
        print("Jarvis Assistant stopped")

def main():
    """Main entry point"""
    # Create config
    config = Config()
    
    # Check for required paths
    print("Jarvis AI Assistant")
    print("===================")
    
    # Create necessary directories
    config.TASKS_DIR.mkdir(parents=True, exist_ok=True)
    config.LOG_DIR.mkdir(parents=True, exist_ok=True)
    config.CACHE_DIR.mkdir(parents=True, exist_ok=True)
    
    # Check for model files
    if not Path(config.LLAMA_MODEL_PATH).exists():
        print(f"Warning: Llama model not found at {config.LLAMA_MODEL_PATH}")
        print("The assistant will work with limited intent recognition")
        print("Download the model from: https://huggingface.co/TheBloke/Llama-2-7B-Chat-GGUF")
    
    # Check for Porcupine access key
    if config.PORCUPINE_ACCESS_KEY == "YOUR_PORCUPINE_ACCESS_KEY":
        print("\nIMPORTANT: Please set your Porcupine access key!")
        print("Get one free at: https://console.picovoice.ai/")
        print("Then update the PORCUPINE_ACCESS_KEY in the config")
        return
    
    # Start assistant
    assistant = JarvisAssistant(config)
    assistant.run()

if __name__ == "__main__":
    main()