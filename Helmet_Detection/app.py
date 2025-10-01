import streamlit as st
import numpy as np
import tensorflow as tf
from PIL import Image
from tensorflow.keras.preprocessing.image import img_to_array

# Load the saved model
loaded_model = tf.keras.models.load_model("./helmet_detection_model.h5")

# Function to preprocess the input image
def preprocess_image(image):
    image = image.resize((224, 224))  # Resize the image
    image = image.convert("RGB")        # Convert image to RGB (discard alpha channel if exists)
    image = img_to_array(image)         # Convert to array
    image = np.expand_dims(image, axis=0)  # Add batch dimension
    image = tf.keras.applications.resnet50.preprocess_input(image)  # Preprocess
    return image

# Function to make predictions on images
def predict_image(image, model):
    preprocessed_image = preprocess_image(image)
    prediction = model.predict(preprocessed_image)
    return prediction

# Function to interpret the prediction
def interpret_prediction(prediction, threshold=0.6):
    if prediction > threshold:
        return "With Helmet"
    else:
        return "Without Helmet"

# Streamlit UI
st.title("Helmet Detection")
uploaded_file = st.file_uploader("Choose an image...", type=["jpg", "jpeg", "png", "bmp", "gif"])

if uploaded_file is not None:
    image = Image.open(uploaded_file)  # Open the uploaded image
    st.image(image, caption="Uploaded Image", use_column_width=True)  # Display the image

    # Predict
    prediction = predict_image(image, loaded_model)
    result = interpret_prediction(prediction[0][0])  # Assuming the model output is in the form [[probability]]
    
    # Display the prediction result
    st.write("Prediction:", result)
