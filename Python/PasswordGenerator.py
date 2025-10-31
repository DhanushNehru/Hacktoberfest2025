# ---------------------------------------------------------------
# PASSWORD GENERATOR APP (Enhanced Version)
# Visit: codewithcurious.com for more projects
# ---------------------------------------------------------------

from tkinter import *
import random
import pyperclip

# ---------------------------------------------------------------
#  Create main window
# ---------------------------------------------------------------
root = Tk()
root.title("Password Generator")
root.geometry("400x400")
root.configure(background="#f4f4f4")  # Light gray background

# ---------------------------------------------------------------
#  Define variables for user input and generated password
# ---------------------------------------------------------------
password_var = StringVar()  # Stores the generated password
length_var = IntVar()       # Stores the user-selected length

# ---------------------------------------------------------------
#  Options for including characters
# ---------------------------------------------------------------
include_upper = BooleanVar(value=True)
include_lower = BooleanVar(value=True)
include_digits = BooleanVar(value=True)
include_symbols = BooleanVar(value=True)

# ---------------------------------------------------------------
#  Function: Generate Password
# ---------------------------------------------------------------
def generate_password():
    """Generates a random password based on user preferences."""
    
    # Character sets
    upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    lower = "abcdefghijklmnopqrstuvwxyz"
    digits = "0123456789"
    symbols = "!@#$%^&*()-_=+[]{}|;:,.<>?/"
    
    # Combine allowed character sets
    characters = ""
    if include_upper.get():
        characters += upper
    if include_lower.get():
        characters += lower
    if include_digits.get():
        characters += digits
    if include_symbols.get():
        characters += symbols
    
    # Validation: Password length must be at least 8
    if length_var.get() < 8:
        password_var.set("⚠️ Minimum length should be 8!")
        return
    
    # Validation: At least one character set should be selected
    if not characters:
        password_var.set("⚠️ Select at least one character type!")
        return
    
    # Generate random password
    password = ''.join(random.choice(characters) for _ in range(length_var.get()))
    
    # Set the generated password to the text box
    password_var.set(password)

# ---------------------------------------------------------------
#  Function: Copy to Clipboard
# ---------------------------------------------------------------
def copy_to_clipboard():
    """Copies the generated password to the clipboard."""
    pyperclip.copy(password_var.get())
    copied_label.config(text="✅ Copied to clipboard!", fg="green")

# ---------------------------------------------------------------
#  UI Layout
# ---------------------------------------------------------------

Label(root, text="🔐 Random Password Generator", font=("Arial", 14, "bold"), bg="#f4f4f4", fg="#333").pack(pady=10)

# Entry for password length
frame_length = Frame(root, bg="#f4f4f4")
frame_length.pack(pady=5)
Label(frame_length, text="Password Length:", bg="#f4f4f4", font=("Arial", 11)).pack(side=LEFT, padx=5)
Entry(frame_length, textvariable=length_var, width=8, font=("Arial", 11)).pack(side=LEFT)

# Checkboxes for options
Label(root, text="Include:", bg="#f4f4f4", font=("Arial", 11, "bold")).pack(pady=(10, 0))

Checkbutton(root, text="Uppercase Letters (A-Z)", variable=include_upper, bg="#f4f4f4").pack(anchor=W, padx=60)
Checkbutton(root, text="Lowercase Letters (a-z)", variable=include_lower, bg="#f4f4f4").pack(anchor=W, padx=60)
Checkbutton(root, text="Digits (0-9)", variable=include_digits, bg="#f4f4f4").pack(anchor=W, padx=60)
Checkbutton(root, text="Symbols (!@#$%^&*)", variable=include_symbols, bg="#f4f4f4").pack(anchor=W, padx=60)

# Generate button
Button(root, text="Generate Password", command=generate_password, bg="#333", fg="white", font=("Arial", 11, "bold"), padx=10, pady=5).pack(pady=10)

# Display generated password
Entry(root, textvariable=password_var, font=("Arial", 12), width=30, justify='center').pack(pady=5)

# Copy button
Button(root, text="Copy to Clipboard", command=copy_to_clipboard, bg="#0066cc", fg="white", font=("Arial", 11, "bold")).pack(pady=5)

# Label to show "Copied" message
copied_label = Label(root, text="", bg="#f4f4f4", font=("Arial", 10))
copied_label.pack()

# ---------------------------------------------------------------
#  Run the GUI loop
# ---------------------------------------------------------------
root.mainloop()
