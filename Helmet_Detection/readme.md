# Helmet Detection Model

This repository contains the implementation of a Helmet Detection Model using TensorFlow and Keras. The model is designed to detect whether a person is wearing a helmet or not in images. The dataset used includes images annotated with bounding boxes and labels indicating whether a helmet is present.

## **Table of Contents**
- [Introduction](#introduction)
- [Requirements](#requirements)
- [Data Preparation](#data-preparation)
- [Model Architecture](#model-architecture)
- [Training](#training)
- [Evaluation](#evaluation)
- [Usage](#usage)
- [Result](#Result)

## **Introduction**

Helmet detection is crucial for ensuring safety standards are met in various environments, such as construction sites and roadways. This project leverages a Faster R-CNN approach with a ResNet50 backbone to accurately detect helmets in images.

## **Requirements**

- Python 
- TensorFlow 
- NumPy
- Matplotlib
- lxml

Install the required libraries using:

```bash
pip install tensorflow numpy matplotlib lxml

```


# Data Preparation

The dataset consists of images and their corresponding XML annotation files.

- **Annotations Directory:** `/content/drive/MyDrive/annotations`
- **Images Directory:** `/content/drive/MyDrive/images`

## Parsing XML Annotations

The annotations are in XML format. The `parse_annotation` function extracts object names and bounding boxes from the XML files.

```python
def parse_annotation(annotation_file):
    tree = etree.parse(annotation_file)
    root = tree.getroot()
    objects = []
    for obj in root.findall('object'):
        obj_struct = {}
        obj_struct['name'] = obj.find('name').text
        bbox = obj.find('bndbox')
        obj_struct['bbox'] = [int(bbox.find('xmin').text), int(bbox.find('ymin').text),
                              int(bbox.find('xmax').text), int(bbox.find('ymax').text)]
        objects.append(obj_struct)
    return objects
```

# Loading Data

The `load_data` function loads and preprocesses the images and annotations for training.

```python
def load_data(annotations_dir, images_dir):
    X, y = [], []
    for annotation_file in os.listdir(annotations_dir):
        if annotation_file.endswith('.xml'):
            annotation_path = os.path.join(annotations_dir, annotation_file)
            objects = parse_annotation(annotation_path)
            image_path = os.path.join(images_dir, annotation_file.replace('.xml', '.png'))
            image = load_img(image_path, target_size=(224, 224))
            image = img_to_array(image)
            X.append(preprocess_input(image))
            labels = [0] * len(classes)
            for obj in objects:
                label_idx = classes.index(obj['name'])
                labels[label_idx] = 1
            y.append(labels)
    return np.array(X), np.array(y)
```

# Model Architecture

The model is based on Faster R-CNN with a ResNet50 backbone, pre-trained on ImageNet.

```python
def create_faster_rcnn():
    base_model = ResNet50(include_top=False, weights='imagenet', input_shape=(224, 224, 3))
    for layer in base_model.layers:
        layer.trainable = False
    x = base_model.output
    x = layers.GlobalAveragePooling2D()(x)
    x = layers.Dense(128, activation='relu')(x)
    output = layers.Dense(len(classes), activation='sigmoid')(x)
    model = models.Model(inputs=base_model.input, outputs=output)
    return model
```

# Training

The model is compiled and trained using the following parameters:

- **Optimizer:** Adam
- **Loss Function:** Binary Crossentropy
- **Metrics:** Accuracy

```python
model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])
model.fit(X_train, y_train, epochs=10, batch_size=32, validation_split=0.2)
```

# Evaluation

The model can be evaluated on a separate test set. Here, we demonstrate how to load a saved model and make predictions on new images.


## Preprocess Input Image

```python
def preprocess_image(image_path):
    image = load_img(image_path, target_size=(224, 224))
    image = img_to_array(image)
    image = np.expand_dims(image, axis=0)
    image = tf.keras.applications.resnet50.preprocess_input(image)
    return image
```


## Interpret Predictions
```python
def interpret_prediction(prediction, threshold=0.6):
   if prediction > threshold:
        return "With Helmet"
    else:
        return "Without Helmet"
```

# Usage
To use the model for predicting helmet presence on new images, follow these steps:

## Load the saved model:
```python
loaded_model = tf.keras.models.load_model("/content/drive/MyDrive/helmet_detection_model.h5")
```
## Make Predictions:
```python
prediction = predict_image(image_path, loaded_model)
result = interpret_prediction(prediction[0][0])
```

## Display Image with Prediction:
```python
display_image_with_prediction(image_path, result)
```

# Result 

The model is working fine with accuracy of 81% and is able to successfully detect the images With Helmets and Without Helmets.


# Dataset 
The dataset is taken from kaggle.

Here's the reference: https://www.kaggle.com/datasets/andrewmvd/helmet-detection/data

# Deployment 
This project is deployed on streamlit server.
Steps to run this project are:

- Clone the repo.
- First install streamlit package "pip install streamlit"
- Then go to terminal, then type "cd Helmet_Detection".
- Then run this command "streamlit run app.py"

If the above steps doesn't work, you may use this deployed link 
https://helmetdetection-twxcyn5veugnr4ojqqbgrd.streamlit.app/





