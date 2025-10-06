import pyautogui  # pip install pyautogui
from PIL import Image, ImageGrab  # pip install pillow
# from numpy import asarray
import time


def hit(key):
    pyautogui.keyDown(key)
    return


def isCollide(data):
    # Draw the rectangle for birds
    for i in range(285, 315):
        for j in range(310, 340):
            if data[i, j] < 100:
                hit("down")
                return

    for i in range(285, 325):
        for j in range(372, 450):
            if data[i, j] < 100:
                hit("up")
                return
    return


if __name__ == "__main__":
    print("Hey.. Dino game about to start in 3 seconds")
    time.sleep(2)
    # hit('up')

    while True:
        image = ImageGrab.grab().convert('L')
        data = image.load()
        isCollide(data)

        # print(asarray(image))

        # Draw the rectangle for cactus
        '''for i in range(285, 315):
            for j in range(372, 450):
                data[i, j] = 0

        # raw the rectangle for birds
        for i in range(285, 315):
            for j in range(310, 340):
                data[i, j] = 171

        image.show()
        break


'''
