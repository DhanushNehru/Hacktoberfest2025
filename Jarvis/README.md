# Jarvis — Arch + Hyprland AI Assistant

[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/Mr-Mysterious001/Jarvis?include_prereleases)](https://github.com/Mr-Mysterious001/Jarvis/releases)
[![License](https://img.shields.io/github/license/Mr-Mysterious001/Jarvis)](LICENSE)
[![Contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg)](https://github.com/Mr-Mysterious001/Jarvis/issues)

---

## 🎯 About This Project

Jarvis began as a **vibe coding project** — something I hacked together for fun, experimenting with voice recognition, TTS, and local LLMs on Linux.  

What started as a personal side project is now being **opened up to the community**.  
The vision is to evolve Jarvis into a **useful, extensible AI assistant** designed specifically for **Arch Linux + Hyprland**, built by and for the open-source community.  

I want Jarvis to grow beyond “just for fun” — to become a tool that genuinely makes using Linux smoother, smarter, and more accessible.  

---

## ✨ Features (v0.1.0 Alpha)

- 🔊 **Wake word detection** with Porcupine (default: `jarvis`)  
- 🎙️ **Speech-to-text** via Faster-Whisper (supports `tiny → large-v3` models)  
- 🧠 **Local intent parsing** powered by llama.cpp (default tested with `llama-2-7b-chat.Q4_K_M.gguf`)  
- 🗣️ **Text-to-speech** using Piper (default: `en_US-amy-medium`)  
- 🧩 **Modular task system**: create JSON task files and drop them into `~/.config/jarvis/tasks/`  
- ⚙️ **Single config file** (`~/.config/jarvis/config.json`) for all models and options  
- 📝 **Logging support** for debugging (`~/.local/share/jarvis/logs/`)  
- 📂 **Example tasks and config** included to get you started  

---

## 🚧 Why It’s Still Alpha

This is an early build. It works, but:  
- The **LLM command recognition is weak** when using small models like `"base"`  
- Models are **not bundled** (users must download manually and edit `config.json`)  
- The **task library is tiny** — Jarvis can only do a few example actions right now  
- There’s **no CLI yet** for managing tasks or configs  

That’s where the community comes in. The foundation is here — now we can build together.  

---

## ⚡ Requirements

To run Jarvis, you’ll need:

- **Operating System**: Linux (tested on **Arch Linux + Hyprland**)  
- **Python**: Version 3.10 or higher  
- **Dependencies**: Installed automatically via `setup.sh` (pip + required libraries)  
- **Models**: Must be downloaded manually and paths added in `~/.config/jarvis/config.json`  

### Required Models

1. **Porcupine (Wake Word Detection)**  
   - Access key from [Picovoice Console](https://console.picovoice.ai/)  
   - Wake word file (`jarvis.ppn` or any custom `.ppn`)  

2. **Whisper (Speech-to-Text)**  
   - Choose from: `tiny`, `base`, `small`, `medium`, `large-v3`  
   - Default: `"base"` (fast but limited recognition)  

3. **LLaMA (Intent Parsing)**  
   - Example model: `llama-2-7b.Q4_K_M.gguf`  
   - Runs locally via **llama.cpp**  
   - Path must be specified in `config.json`  

4. **Piper (Text-to-Speech)**  
   - Example: `en_US-amy-medium`  
   - Download from the [Piper voices repo](https://github.com/rhasspy/piper#voices)  

---

## 📦 Installation

Clone the repo and run the setup script:

```bash
git clone https://github.com/Mr-Mysterious001/Jarvis.git
cd Jarvis
chmod +x setup.sh
./setup.sh
```
---

## 🚀 Usage

Once installed and configured:

### 1. Start Jarvis
```bash
python3 jarvis.py
```
---

## 🙌 Contributing

Jarvis started as a vibe coding project, but the goal now is to turn it into a **useful, community-driven AI assistant**.  
Contributions of all kinds are welcome — whether you’re fixing bugs, writing docs, or adding new tasks.

### Ways to Contribute
- 🧩 **Add tasks**: Create JSON task files for system actions, Hyprland workflows, or custom automations.  
- 🧠 **Improve intent recognition**: Test smaller instruction-tuned LLMs, refine prompts, or suggest hybrid rule-based + LLM parsing.  
- ⚙️ **Polish setup**: Add AUR packaging, Dockerfiles, or improve `setup.sh` with auto-download of models.  
- 📝 **Documentation**: Tutorials, troubleshooting guides, and better examples.  
- 🐛 **Bug reports & fixes**: Found an issue? Report it under [Issues](https://github.com/Mr-Mysterious001/Jarvis/issues).  
- 🎨 **Enhancements**: Code refactoring, performance optimizations, or UI/UX polish (CLI, logging, etc.).  

### Contribution Process
1. Fork this repo  
2. Create a new branch:  
   ```bash
   git checkout -b feature-name
   ```
3. Make your changes and commit:
    ```bash
    git commit -m "Add: feature description"
    ```
4. Push to your fork and create a pull request

---

## 🔮 Roadmap

Jarvis is still in its early stages. The long-term vision is to transform it from a **vibe coding project** into a **full-featured AI assistant for Arch + Hyprland**.  
Here’s what’s planned for the journey ahead:

---

### 🟢 Current (Alpha Stage)
- Basic pipeline working: **Porcupine → Whisper → LLaMA → Piper**  
- Configurable via `config.json`  
- Tasks loadable from `~/.config/jarvis/tasks/`  
- Logging support  

---

### 🟡 Short-Term Goals (Next Releases)
- 📂 Expand **default task library** with more Linux + Hyprland workflows  
- ⚙️ Add **config validation** to prevent errors when paths/models are missing  
- ⬇️ Implement **auto-download helpers** for Whisper, Piper, and LLaMA models  
- 🐛 Fix recognition issues with smaller Whisper/LLM models  
- 📖 Improve documentation (setup, troubleshooting, examples)  

---

### 🟠 Mid-Term Goals
- 💻 Build a **CLI tool** for easier interaction:
  - `jarvis tasks --list` → show available tasks  
  - `jarvis tasks --reload` → reload tasks without restart  
  - `jarvis devices` → list audio devices  
- 🧠 Smarter **intent classification** using:
  - Small instruction-tuned models  
  - Hybrid LLM + regex/pattern-based parsing  
- 📦 Package support:
  - Arch Linux AUR  
  - Debian/Ubuntu `.deb` package  
- 🐧 Extend support beyond Hyprland (GNOME, KDE, Sway)  

---

### 🔴 Long-Term Vision
- 🔌 **Plugin system**: Allow developers to add custom modules beyond JSON tasks  
- 🌐 **Optional online extensions** (weather, news, APIs) while keeping the core offline-first  
- 📊 **GUI/Dashboard(optional)** to manage tasks, view logs, and tweak configs  
- 🎙️ Smarter multi-turn conversations with context retention  
- 🤝 Grow into the **go-to open-source AI assistant** for Linux desktops  

---

Jarvis is built to evolve **with the community**.  
This roadmap isn’t fixed — it’s a living plan that will grow based on contributions, feedback, and new ideas. 🚀


