# app.py
from flask import Flask, render_template, request
from flask import send_file
import pywhatkit as kt

app = Flask(__name__, static_url_path='/static')

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/upload", methods=["POST"])
def upload():
    if "imageInput" in request.files:
        image = request.files["imageInput"]
        
        image_path = "uploaded_image.jpg"
        image.save(image_path)

        ascii_art_base = "ascii_art"
        kt.image_to_ascii_art(image_path, ascii_art_base)

        ascii_art_path = ascii_art_base + ".txt"

        # Read ASCII content
        with open(ascii_art_path, "r") as file:
            ascii_content = file.read()

        return render_template("result.html", image_path=image_path, ascii_art_content=ascii_content)
    else:
        return "No image received."


@app.route("/download_ascii")
def download_ascii():
    ascii_art_path = "ascii_art.txt"
    return send_file(ascii_art_path, as_attachment=True)

if __name__ == "__main__":
    app.run(debug=True, port=4747)

# app = Flask(__name__, static_url_path='/static')


print("hi")
