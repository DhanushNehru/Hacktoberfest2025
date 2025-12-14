 import tkinter as tk
import time

# Pomodoro Settings
WORK_MIN = 25
BREAK_MIN = 5

class PomodoroTimer:
    def __init__(self, root):
        self.root = root
        self.root.title("Pomodoro Timer")
        self.root.geometry("300x200")
        self.root.configure(bg="#f7f5dd")

        # Timer variables
        self.time_left = WORK_MIN * 60
        self.is_running = False
        self.timer_type = "Work"  # Can be "Work" or "Break"

        # UI Setup
        self.label = tk.Label(root, text="Pomodoro Timer", font=("Helvetica", 18), bg="#f7f5dd")
        self.label.pack(pady=10)

        self.timer_label = tk.Label(root, text="25:00", font=("Helvetica", 24), bg="#f7f5dd")
        self.timer_label.pack(pady=5)

        # Buttons
        self.start_button = tk.Button(root, text="Start", command=self.start_timer)
        self.start_button.pack(side="left", padx=10, pady=10)

        self.stop_button = tk.Button(root, text="Stop", command=self.stop_timer)
        self.stop_button.pack(side="left", padx=10, pady=10)

        self.reset_button = tk.Button(root, text="Reset", command=self.reset_timer)
        self.reset_button.pack(side="left", padx=10, pady=10)

    def start_timer(self):
        if not self.is_running:
            self.is_running = True
            self.update_timer()

    def stop_timer(self):
        self.is_running = False

    def reset_timer(self):
        self.is_running = False
        self.timer_type = "Work"
        self.time_left = WORK_MIN * 60
        self.update_display()

    def update_timer(self):
        if self.is_running:
            if self.time_left > 0:
                self.time_left -= 1
            else:
                # Switch between Work and Break
                if self.timer_type == "Work":
                    self.timer_type = "Break"
                    self.time_left = BREAK_MIN * 60
                else:
                    self.timer_type = "Work"
                    self.time_left = WORK_MIN * 60
                self.notify()

            self.update_display()
            # Only call update_timer again if the timer is still running
            if self.is_running:
                self.root.after(1000, self.update_timer)

    def update_display(self):
        minutes = self.time_left // 60
        seconds = self.time_left % 60
        self.timer_label.config(text=f"{minutes:02d}:{seconds:02d}")
        self.label.config(text=f"{self.timer_type} Time")

    def notify(self):
        if self.timer_type == "Break":
            print("Time for a break!")
        else:
            print("Time to get back to work!")

# Run the Pomodoro Timer
root = tk.Tk()
app = PomodoroTimer(root)
root.mainloop()
