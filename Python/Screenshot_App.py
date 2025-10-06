import time
import pyautogui
import tkinter as tk
def screenshot():
    name = int(round(time.time()*1000)) #To avoid generating files with same names
    name = '{}.png'.format(name) #can also add a location to store screenshots 
    img = pyautogui.screenshot(name)
    img.show()
root = tk.Tk()
frame = tk.Frame(root)
frame.pack()
button = tk.Button(
    frame,
    text = "Take ScreenShot",
    command = screenshot)
button.pack(side = tk.LEFT)
close = tk.Button(
    frame,
    text = "Cancel",
    command = root.destroy)
close.pack(side = tk.LEFT)
root.mainloop()
