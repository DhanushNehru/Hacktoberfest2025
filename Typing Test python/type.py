from tkinter import *
import time
from tkinter import messagebox
from PIL import Image, ImageTk
from functools import partial
import random


def again(window):
    window.destroy()
    main()


def check(written, words_text):
    count = 0
    for i in range(len(written)):
        if written[i] == words_text[i]:
            count += 1
    acc = (count * 100) // len(written)
    return acc


def countdown(temp, window, mins, sec):
    minute = temp // 60
    seconds = temp % 60
    mins.set(str(minute).zfill(2))
    sec.set(str(seconds).zfill(2))
    if temp > 0:
        window.after(1000, countdown, temp - 1, window, mins, sec)


def check_input(window, canvas1, write, words_text):
    messagebox.showinfo("Timer", "Time's up")
    written = write.get("1.0", "end-1c").split()
    wpm = len(written)
    if wpm != 0:
        accuracy = check(written, words_text)
        canvas1.create_text(200, 380, text=f"{wpm} words/minute", fill="#59E817", font=("Helvetica", 14, "bold"))
        canvas1.create_text(370, 380, text=f"Accuracy = {accuracy}%", fill="#59E817", font=("Helvetica", 14, "bold"))

    try_again = Button(text="Try Again", command=partial(again, window),
                       font=("Helvetica", 13, "bold italic"), bg="#c80815", fg="white", relief="raised")
    canvas1.create_window(530, 420, window=try_again)


def start(mins, sec, window, canvas1, write, words_text):
    temp = int(mins.get()) * 60 + int(sec.get())
    countdown(temp, window, mins, sec)
    window.after(1000 * 61, lambda: check_input(window, canvas1, write, words_text))


def choose_text():
    with open("sentences (1).txt") as fw:
        sen = fw.readlines()
    return random.choice(sen)


def foo(window):
    window.destroy()
    screen = Tk()
    screen.geometry("600x450+300+150")
    screen.title("Typing-Speed-Test")

    canvas1 = Canvas(height=450, width=600)
    canvas1.pack()

    bg = Image.open("bg2ts (1).jpg")
    bg = bg.resize((600, 450))
    bg = ImageTk.PhotoImage(bg)
    canvas1.create_image(0, 0, image=bg, anchor=NW)

    icon = Image.open("iconst (1).png")
    icon = icon.resize((80, 70))
    icon = ImageTk.PhotoImage(icon)
    canvas1.create_image(550, 45, image=icon)

    mins = StringVar()
    sec = StringVar()

    mins.set("01")
    sec.set("01")

    min_text = Label(textvariable=mins, font=("Arial", 18), bg="#1e3d59", fg="#f5f0e1")
    colon = Label(text=":", font=("Arial", 18), bg="#1e3d59", fg="#f5f0e1")
    sec_text = Label(textvariable=sec, font=("Arial", 18), bg="#1e3d59", fg="#f5f0e1")

    canvas1.create_window(50, 40, window=min_text)
    canvas1.create_window(69, 40, window=colon)
    canvas1.create_window(90, 40, window=sec_text)

    selected = choose_text()

    canvas1.create_text(30, 105, text=selected, fill="#E5E4E2", width=550, anchor=NW, font=("Helvetica", 11, "bold"))
    words_text = selected.split()

    write = Text(font=("Arial", 11), bg="#C2DFFF")
    canvas1.create_window(32, 240, window=write, anchor=NW, height=120, width=520)

    start_btn = Button(text="Start", command=partial(start, mins, sec, screen, canvas1, write, words_text),
                       font=("Calibri", 18, "bold"), width=5, bg="#c80815", fg="white", relief="raised")
    canvas1.create_window(300, 50, window=start_btn)

    screen.mainloop()


def main():
    window = Tk()
    window.geometry("600x400+300+150")
    window.title("TypingSpeed Test")
    bg = Image.open("bgst (1).jpg")
    bg = bg.resize((600, 320))
    bg = ImageTk.PhotoImage(bg)
    canvas1 = Canvas(height=400, width=600)
    canvas1.create_image(0, 0, image=bg, anchor=NW)
    canvas1.pack()
    go_btn = Button(text="Proceed", font=("Helvetica", 18, "bold italic"), width=8, bg="#c80815", fg="white",
                    relief="raised", command=partial(foo, window))
    exit_btn = Button(text="Exit", font=("Helvetica", 19, "bold italic"), width=5, bg="#c80815", fg="white",
                      relief="raised", command=window.destroy)
    canvas1.create_window(200, 360, window=go_btn)
    canvas1.create_window(400, 360, window=exit_btn)
    window.mainloop()


main()
