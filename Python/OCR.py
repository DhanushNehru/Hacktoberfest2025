import cv2
import pytesseract
import os
import pyttsx3

# Path to Tesseract executable (change it to match your system)
pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

# Initialize the Text-to-Speech engine
engine = pyttsx3.init()

# Directory to save images
image_dir = r'C:\\Users\\rajee\\OneDrive\\Desktop\\project'

# Start webcam
cap = cv2.VideoCapture(0)

processing_image = False
current_text = ""

while True:
    # Capture frame from webcam
    ret, frame = cap.read()
    if not ret:
        break

    # Display the frame
    if processing_image:
        try:
            # Convert frame to grayscale
            gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
            # Perform OCR
            text = pytesseract.image_to_string(gray)
            print("OCR Result:", text)
            current_text = text

            # Convert OCR result to speech
            engine.say(text)
            engine.runAndWait()
        except pytesseract.TesseractError as e:
            print("Tesseract Error:", e)

        # Reset processing_image flag
        processing_image = False

    # Overlay text on the frame if OCR has been performed
    if current_text:
        text_position = (10, 50)  # Adjust the position of the text
        text_color = (0, 0, 255)  # Red color
        cv2.putText(frame, current_text, text_position, cv2.FONT_HERSHEY_SIMPLEX, 1, text_color, 2)

    cv2.imshow('OCR', frame)

    # Check for key press events
    key = cv2.waitKey(1)
    if key & 0xFF == ord('q'):
        break
    elif key & 0xFF == ord(' '):  # Check for spacebar press
        processing_image = True

# Release the video capture object and close all windows
cap.release()
cv2.destroyAllWindows()
