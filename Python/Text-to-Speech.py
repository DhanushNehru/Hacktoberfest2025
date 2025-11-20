"""
Text-to-Speech Converter

Description: A Python text-to-speech converter using pyttsx3 for offline conversion and gTTS for online Google TTS.
Supports multiple voices, speech rate adjustment, file saving, and batch text processing.

Time Complexity: O(n) - Linear time based on text length
Space Complexity: O(1) - Constant space for operations
"""

import pyttsx3
import os
from gtts import gTTS
import tempfile
from playsound import playsound
import threading

class TextToSpeechConverter:
    def __init__(self):
        """Initialize the TTS engine and set default properties"""
        try:
            self.engine = pyttsx3.init()
            self.setup_engine()
            self.voices = self.get_available_voices()
        except Exception as e:
            print(f"Error initializing TTS engine: {e}")
            self.engine = None

    def setup_engine(self):
        """Setup the TTS engine with default properties"""
        if self.engine:
            # Set default rate (speech speed)
            self.engine.setProperty('rate', 150)
            # Set default volume
            self.engine.setProperty('volume', 1.0)

    def get_available_voices(self):
        """Get list of available voices"""
        if not self.engine:
            return []
        
        voices = []
        for voice in self.engine.getProperty('voices'):
            voices.append({
                'id': voice.id,
                'name': voice.name,
                'gender': 'Male' if 'male' in voice.name.lower() else 'Female'
            })
        return voices

    def list_voices(self):
        """Display available voices"""
        print("\nAvailable Voices:")
        for i, voice in enumerate(self.voices):
            print(f"{i + 1}. {voice['name']} ({voice['gender']})")

    def set_voice(self, voice_index):
        """Set the voice by index"""
        if self.engine and 0 <= voice_index < len(self.voices):
            self.engine.setProperty('voice', self.voices[voice_index]['id'])
            return True
        return False

    def set_speech_rate(self, rate):
        """Set speech rate (words per minute)"""
        if self.engine:
            self.engine.setProperty('rate', rate)

    def set_volume(self, volume):
        """Set volume level (0.0 to 1.0)"""
        if self.engine:
            self.engine.setProperty('volume', max(0.0, min(1.0, volume)))

    def speak_text(self, text, wait=True):
        """
        Convert text to speech using offline engine
        
        Args:
            text (str): Text to convert to speech
            wait (bool): Whether to wait for speech to complete
        """
        if not self.engine:
            print("TTS engine not available!")
            return False

        try:
            self.engine.say(text)
            if wait:
                self.engine.runAndWait()
            return True
        except Exception as e:
            print(f"Error in speech synthesis: {e}")
            return False

    def speak_text_async(self, text):
        """Speak text asynchronously"""
        thread = threading.Thread(target=self.speak_text, args=(text, True))
        thread.daemon = True
        thread.start()

    def save_to_file_offline(self, text, filename):
        """
        Save speech to file using offline engine
        
        Args:
            text (str): Text to convert
            filename (str): Output filename
        """
        if not self.engine:
            return False

        try:
            # Ensure file has .wav extension
            if not filename.endswith('.wav'):
                filename += '.wav'

            self.engine.save_to_file(text, filename)
            self.engine.runAndWait()
            print(f"Audio saved to: {filename}")
            return True
        except Exception as e:
            print(f"Error saving audio file: {e}")
            return False

    def save_to_file_online(self, text, filename, lang='en'):
        """
        Save speech to file using Google TTS (requires internet)
        
        Args:
            text (str): Text to convert
            filename (str): Output filename
            lang (str): Language code
        """
        try:
            # Ensure file has .mp3 extension
            if not filename.endswith('.mp3'):
                filename += '.mp3'

            tts = gTTS(text=text, lang=lang, slow=False)
            tts.save(filename)
            print(f"Audio saved to: {filename}")
            return True
        except Exception as e:
            print(f"Error with Google TTS: {e}")
            return False

    def play_audio_file(self, filename):
        """Play an audio file"""
        try:
            if os.path.exists(filename):
                playsound(filename)
                return True
            else:
                print(f"File not found: {filename}")
                return False
        except Exception as e:
            print(f"Error playing audio: {e}")
            return False

    def get_engine_status(self):
        """Get status of TTS engines"""
        status = {
            'offline_engine': bool(self.engine),
            'available_voices': len(self.voices),
            'online_engine': 'Available (requires internet)'
        }
        return status

def display_menu():
    """Display the main menu"""
    print("\n" + "="*60)
    print("           TEXT-TO-SPEECH CONVERTER")
    print("="*60)
    print("1. Speak Text (Offline)")
    print("2. Speak Text (Online - Google TTS)")
    print("3. Save to Audio File (Offline - WAV)")
    print("4. Save to Audio File (Online - MP3)")
    print("5. Change Voice")
    print("6. Adjust Speech Rate")
    print("7. Adjust Volume")
    print("8. List Available Voices")
    print("9. Engine Status")
    print("10. Exit")

def main():
    """Main function to run the Text-to-Speech converter"""
    tts = TextToSpeechConverter()
    
    if not tts.engine:
        print("Failed to initialize TTS engine. Please check dependencies.")
        return

    print("Text-to-Speech Converter initialized successfully!")
    
    while True:
        display_menu()
        choice = input("\nEnter your choice (1-10): ").strip()

        if choice == '1':
            text = input("Enter text to speak: ")
            if text.strip():
                tts.speak_text_async(text)
                print("Speaking...")
            else:
                print("Please enter some text!")
                
        elif choice == '2':
            text = input("Enter text to speak (Online): ")
            if text.strip():
                with tempfile.NamedTemporaryFile(suffix='.mp3', delete=False) as temp_file:
                    temp_filename = temp_file.name
                
                if tts.save_to_file_online(text, temp_filename):
                    tts.play_audio_file(temp_filename)
                    os.unlink(temp_filename)  # Clean up temp file
            else:
                print("Please enter some text!")
                
        elif choice == '3':
            text = input("Enter text to save: ")
            filename = input("Enter output filename (without extension): ")
            if text.strip() and filename.strip():
                tts.save_to_file_offline(text, filename)
            else:
                print("Please enter both text and filename!")
                
        elif choice == '4':
            text = input("Enter text to save: ")
            filename = input("Enter output filename (without extension): ")
            lang = input("Enter language code (en, es, fr, etc.) [default: en]: ") or 'en'
            if text.strip() and filename.strip():
                tts.save_to_file_online(text, filename, lang)
            else:
                print("Please enter both text and filename!")
                
        elif choice == '5':
            tts.list_voices()
            try:
                voice_index = int(input("Select voice number: ")) - 1
                if tts.set_voice(voice_index):
                    print("Voice changed successfully!")
                else:
                    print("Invalid voice selection!")
            except ValueError:
                print("Please enter a valid number!")
                
        elif choice == '6':
            try:
                rate = int(input("Enter speech rate (50-300, default 150): "))
                tts.set_speech_rate(rate)
                print(f"Speech rate set to {rate}")
            except ValueError:
                print("Please enter a valid number!")
                
        elif choice == '7':
            try:
                volume = float(input("Enter volume (0.0 to 1.0): "))
                tts.set_volume(volume)
                print(f"Volume set to {volume}")
            except ValueError:
                print("Please enter a valid number!")
                
        elif choice == '8':
            tts.list_voices()
            
        elif choice == '9':
            status = tts.get_engine_status()
            print("\nEngine Status:")
            for key, value in status.items():
                print(f"  {key.replace('_', ' ').title()}: {value}")
                
        elif choice == '10':
            print("Goodbye!")
            break
            
        else:
            print("Invalid choice! Please enter 1-10.")

def demonstrate_features():
    """Demonstrate various TTS features"""
    tts = TextToSpeechConverter()
    
    if not tts.engine:
        return

    print("Demonstrating TTS Features...")
    
    # Basic speech
    print("1. Basic speech:")
    tts.speak_text("Hello! This is a text to speech demonstration.")
    
    # Different voices
    print("2. Different voices:")
    if len(tts.voices) > 1:
        tts.set_voice(0)
        tts.speak_text("This is the first available voice.")
        
        if len(tts.voices) > 1:
            tts.set_voice(1)
            tts.speak_text("This is the second available voice.")
    
    # Different speech rates
    print("3. Different speech rates:")
    tts.set_speech_rate(100)
    tts.speak_text("This is slow speech.")
    
    tts.set_speech_rate(200)
    tts.speak_text("This is fast speech.")
    
    # Reset to default
    tts.set_speech_rate(150)
    tts.speak_text("Back to normal speed.")

if __name__ == "__main__":
    # Install required packages first
    required_packages = ['pyttsx3', 'gtts', 'playsound']
    
    print("Text-to-Speech Converter")
    print("Required packages: pyttsx3, gtts, playsound")
    print("Install with: pip install pyttsx3 gtts playsound")
    
    # Check if we can demonstrate features
    try:
        demonstrate_features()
        main()
    except ImportError as e:
        print(f"Missing dependency: {e}")
        print("Please install required packages using: pip install pyttsx3 gtts playsound")
