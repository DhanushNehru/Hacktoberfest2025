from PIL import Image

img = Image.open("input.jpg")
rotated = img.rotate(45, expand=True) 
rotated.save("output.jpg")   
