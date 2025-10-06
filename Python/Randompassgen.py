import random
import string
import tkinter as tk
from tkinter import ttk

class RandomCodeGenerator:
    def __init__(self, master):
        self.master = master
        master.title("Random Code Generator")
        master.geometry("300x150")

        self.label = ttk.Label(master, text="Generated Code:")
        self.label.pack(pady=10)

        self.code_label = ttk.Label(master, text="", font=("Courier", 24))
        self.code_label.pack(pady=10)

        self.generate_button = ttk.Button(master, text="Generate Code", command=self.generate_code)
        self.generate_button.pack(pady=10)

    def generate_code(self):
        characters = string.ascii_lowercase + string.ascii_uppercase + string.digits
        code = ''.join(random.choice(characters) for _ in range(8))
        self.code_label.config(text=code)
def main():
    root = tk.Tk()
    app = RandomCodeGenerator(root)
    root.mainloop()

if __name__ == "__main__":
    main()