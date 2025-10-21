mod nn_model;
mod utils;

use csv::ReaderBuilder;
use nn_model::NeuralNetwork;
use ndarray::{Array1, Array2};
use std::error::Error;
use utils::{one_hot_encode, train_test_split};

fn load_mnist_data(path: &str) -> Result<(Array2<f32>, Array1<u8>), Box<dyn Error>> {
    let mut reader = ReaderBuilder::new().has_headers(false).from_path(path)?;
    let mut images = Vec::new();
    let mut labels = Vec::new();

    for result in reader.records() {
        let record = result?;
        let label: u8 = record[0].parse()?;
        let image: Vec<f32> = record
            .iter()
            .skip(1)
            .map(|x| x.parse::<f32>().unwrap() / 255.0)
            .collect();
        labels.push(label);
        images.push(image);
    }

    let images_array = Array2::from_shape_vec((images.len(), 784), images.into_iter().flatten().collect())?;
    let labels_array = Array1::from_vec(labels);

    Ok((images_array, labels_array))
}

fn main() -> Result<(), Box<dyn Error>> {
    let (images, labels) = load_mnist_data("data/mnist_train.csv")?;
    let (x_train, x_test, y_train, y_test) = train_test_split(&images, &labels, 0.2);

    let y_train_oh = one_hot_encode(&y_train, 10);
    let y_test_oh = one_hot_encode(&y_test, 10);

    let mut nn = NeuralNetwork::new(784, 64, 10);

    let epochs = 10;
    let learning_rate = 0.01;

    for epoch in 0..epochs {
        for (x, y) in x_train.outer_iter().zip(y_train_oh.outer_iter()) {
            let (z1, a1, _z2, a2) = nn.forward(&x);
            let (dw1, db1, dw2, db2) = nn.backward(&x, &y, &z1, &a1, &a2);
            nn.update(&dw1, &db1, &dw2, &db2, learning_rate);
        }
        println!("Epoch {}/{} completed", epoch + 1, epochs);
    }

    let mut correct = 0;
    for (x, &y) in x_test.outer_iter().zip(y_test.iter()) {
        let (predicted_class, _) = nn.predict_single(&x);
        if predicted_class as u8 == y {
            correct += 1;
        }
    }
    let accuracy = correct as f32 / y_test.len() as f32;
    println!("Test Accuracy: {:.2}%", accuracy * 100.0);

    Ok(())
}