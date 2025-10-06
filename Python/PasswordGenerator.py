# import all the required packages
# Visit : codewithcurious.com for more projects
from tkinter import *
import random
import pyperclip

# To create a root window of GUI in python
tk=Tk()
tk.geometry('300x300')
tk.configure(background='yellow')

# To store/retrieve the string value entered by user
pswd=StringVar()

# To store/retrieve the Integer value entered by user
passlen=IntVar()
passlen.set('Enter Length')

# Function to generate a random password
def password_generator():
    characters='abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 !@#$%^&*()'
    password=''
    if passlen.get()>=8:
        for i in range(passlen.get()):
            password+=random.choice(characters)
        pswd.set(password)

# Function to copy generated password to clipboard
def copyclipboard():
    random_password = pswd.get()
    pyperclip.copy(random_password)
    Label(tk,text="Copied to Clipboard",bg="red").pack(pady=6)

# Label to display the primary instruction to user to enter the length of passwod he requires
Label(tk, text="Enter the number to get password \n (Minimum length should be 8)",bg='Blue',fg='white').pack(pady=3)

# To store the entry of user
Entry(tk, textvariable=passlen).pack(pady=3)

# To generate Random password and confirmation by the button click
Button(tk, text="Generate Password", command=password_generator,bg='black',fg='white').pack(pady=7)
Entry(tk, textvariable=pswd).pack(pady=3)

Button(tk, text="Copy to clipboard", command=copyclipboard,bg='black',fg='white').pack()
# To initiate and display the root window we created
tk.mainloop()
