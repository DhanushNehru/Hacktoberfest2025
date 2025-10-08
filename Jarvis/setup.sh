#!/bin/bash

# Jarvis AI Assistant Setup Script for Arch Linux
# This script installs all dependencies and sets up the environment

set -e

echo "=========================================="
echo "   Jarvis AI Assistant Setup for Arch    "
echo "=========================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${GREEN}[+]${NC} $1"
}

print_error() {
    echo -e "${RED}[!]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[*]${NC} $1"
}

# Check if running on Arch Linux
if [ ! -f /etc/arch-release ]; then
    print_error "This script is designed for Arch Linux"
    exit 1
fi

# Update system
print_status "Updating system packages..."
sudo pacman -Syu --noconfirm

# Install system dependencies
print_status "Installing system dependencies..."
sudo pacman -S --needed --noconfirm \
    python \
    python-pip \
    python-virtualenv \
    portaudio \
    git \
    base-devel \
    cmake \
    ffmpeg \
    espeak-ng \
    alsa-utils \
    pulseaudio-alsa \
    grim \
    slurp \
    jq

# Create project directory
PROJECT_DIR="$HOME/jarvis-assistant"
print_status "Creating project directory at $PROJECT_DIR..."
mkdir -p "$PROJECT_DIR"
cd "$PROJECT_DIR"

# Create Python virtual environment
print_status "Creating Python virtual environment..."
python -m venv venv
source venv/bin/activate

# Upgrade pip
print_status "Upgrading pip..."
pip install --upgrade pip wheel setuptools

# Install Python dependencies
print_status "Installing Python dependencies..."
pip install \
    pvporcupine \
    pyaudio \
    faster-whisper \
    llama-cpp-python \
    numpy \
    SpeechRecognition 

# Install Piper TTS
print_status "Installing Piper TTS..."
pip install piper-tts

# Download Piper voice model
PIPER_VOICE_DIR="$HOME/.local/share/piper/voices"
mkdir -p "$PIPER_VOICE_DIR"
print_status "Download Piper TTS voice model and store in $HOME/.local/share/piper/voices..."

# Download a good quality English voice
# cd "$PIPER_VOICE_DIR"
# if [ ! -f "en_US-amy-medium.onnx" ]; then
#     wget https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_US/amy/medium/en_US-amy-medium.onnx
#     wget https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_US/amy/medium/en_US-amy-medium.onnx.json
# fi
cd "$PROJECT_DIR"

# Download Whisper model
print_status "Download Whisper model and store in $PROJECT_DIR/models. Run this command:"
echo "python -c \"from faster_whisper import WhisperModel; WhisperModel('base', device='cpu', compute_type='int8')\""

# Create directory structure
print_status "Creating directory structure..."
mkdir -p "$HOME/.config/jarvis/tasks"
mkdir -p "$HOME/.local/share/jarvis/logs"
mkdir -p "$HOME/.cache/jarvis"
mkdir -p "$PROJECT_DIR/models"

# Download Llama 2 model
print_status "Download Llama model and store it in $PROJECT_DIR/models/"


# if [ ! -f "llama-2-7b-chat.Q4_K_M.gguf" ]; then
#     print_warning "Downloading Llama 2 7B model (3.8GB)..."
#     wget https://huggingface.co/TheBloke/Llama-2-7B-Chat-GGUF/resolve/main/llama-2-7b-chat.Q4_K_M.gguf
# else
#     print_status "Llama 2 model already exists, skipping download"
# fi

cd "$PROJECT_DIR"

# Create systemd service file
print_status "Creating systemd service file..."
cat > jarvis.service << 'EOF'
[Unit]
Description=Jarvis AI Assistant
After=network.target sound.target

[Service]
Type=simple
User=%i
WorkingDirectory=/home/%i/jarvis-assistant
Environment="PATH=/home/%i/jarvis-assistant/venv/bin:/usr/local/bin:/usr/bin:/bin"
ExecStart=/home/%i/jarvis-assistant/venv/bin/python /home/%i/jarvis-assistant/jarvis.py
Restart=on-failure
RestartSec=5

[Install]
WantedBy=default.target
EOF

# Create configuration file
print_status "Creating configuration file..."
cat > config.json << 'EOF'
{
  "porcupine": {
    "access_key": "YOUR_PORCUPINE_ACCESS_KEY",
    "wake_word": "jarvis",
    "keyword_path": "YOUR_PORCUPINE_KEYWORD_PATH",
    "sensitivity": 0.8
  },
  "whisper": {
    "model": "base",
    "device": "cpu",
    "compute_type": "int8",
    "language": "en"
  },
  "llama": {
    "model_path": "YOUR_LLAMA_MODEL_PATH",
    "n_ctx": 2048,
    "n_threads": 4,
    "temperature": 0.1
  },
  "piper": {
    "voice": "YOUR_PIPER_VOICE",
    "speed": 1.0
  },
  "audio": {
    "sample_rate": 16000,
    "channels": 1,
    "chunk_size": 512,
    "record_seconds": 5
  }
}
EOF

# Create launcher script
print_status "Creating launcher script..."
cat > launch_jarvis.sh << 'EOF'
#!/bin/bash
# Jarvis Assistant Launcher

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

# Activate virtual environment
source venv/bin/activate

# Check for Porcupine access key
if grep -q "YOUR_PORCUPINE_ACCESS_KEY" config.json; then
    echo "=================================================="
    echo "  IMPORTANT: Porcupine Access Key Required!"
    echo "=================================================="
    echo ""
    echo "1. Get your free access key from:"
    echo "   https://console.picovoice.ai/"
    echo ""
    echo "2. Update the access_key, model paths and piper voice in config.json"
    echo ""
    echo "=================================================="
    exit 1
fi

# Launch Jarvis
echo "Starting Jarvis Assistant..."
python jarvis.py
EOF

chmod +x launch_jarvis.sh

#Copying task files
echo "Copying example task files..."
cp -r tasks/* "$HOME/.config/jarvis/tasks/"
echo "Task files copied to $HOME/.config/jarvis/tasks/"


# Create setup completion script
print_status "Creating setup completion script..."
cat > complete_setup.sh << 'EOF'
#!/bin/bash

echo "=================================================="
echo "       Jarvis Assistant Setup Complete!"
echo "=================================================="
echo ""
echo "Next steps:"
echo ""
echo "1. Get your Porcupine access key (free):"
echo "   https://console.picovoice.ai/"
echo ""
echo "2. Edit config.json and add your access key"
echo ""
echo "3. Copy the jarvis.py file to this directory"
echo ""
echo "4. Run the assistant:"
echo "   ./launch_jarvis.sh"
echo ""
echo "Optional: Install as systemd service:"
echo "   sudo cp jarvis.service /etc/systemd/system/jarvis@$USER.service"
echo "   systemctl --user enable jarvis@$USER"
echo "   systemctl --user start jarvis@$USER"
echo ""
echo "=================================================="
echo ""
echo "Directory structure created:"
echo "  ~/.config/jarvis/tasks/    - Task definitions"
echo "  ~/.local/share/jarvis/logs/ - Log files"
echo "  ~/.cache/jarvis/           - Cache files"
echo ""
echo "Download models and store in the following locations:"
echo "   models/                 - Llama 2 and porcupine model"
echo "  ~/.cache/whisper/           - Whisper base model"
echo "  ~/.local/share/piper/voices/ - Piper TTS voice"
echo ""
echo "=================================================="
EOF

chmod +x complete_setup.sh

# Test audio input
print_status "Testing audio input..."
if arecord -l | grep -q "card"; then
    print_status "Audio input devices found"
else
    print_warning "No audio input devices found. Please check your microphone setup."
fi

# Test audio output
print_status "Testing audio output..."
if aplay -l | grep -q "card"; then
    print_status "Audio output devices found"
else
    print_warning "No audio output devices found. Please check your speaker setup."
fi

# Run completion script
./complete_setup.sh

print_status "Setup complete! Project directory: $PROJECT_DIR"
