
use ndarray::{Array1, Array2, Axis};
use rand::seq::SliceRandom;
use std::fmt::Write;

pub fn one_hot_encode(label: u8, num_classes: usize) -> Array1<f32> {
    let mut arr = Array1::<f32>::zeros(num_classes);
    arr[label as usize] = 1.0;
    arr
}

pub fn train_test_split(
    images: &Array2<f32>,
    labels: &Array1<u8>,
    test_ratio: f32,
) -> (Array2<f32>, Array2<f32>, Array1<u8>, Array1<u8>) {
    let n = images.nrows();
    let test_size = (n as f32 * test_ratio).round() as usize;
    let mut idxs: Vec<usize> = (0..n).collect();
    let mut rng = rand::thread_rng();
    idxs.shuffle(&mut rng);
    let (test_idxs, train_idxs) = idxs.split_at(test_size);
    let x_test = images.select(Axis(0), test_idxs);
    let x_train = images.select(Axis(0), train_idxs);
    let y_test = labels.select(Axis(0), test_idxs);
    let y_train = labels.select(Axis(0), train_idxs);
    (x_train, x_test, y_train, y_test)
}

pub fn accuracy(preds: &[usize], labels: &[usize]) -> f32 {
    let correct = preds.iter().zip(labels.iter()).filter(|(a, b)| a == b).count();
    correct as f32 / preds.len() as f32
}

pub fn display_image(x: &Array1<f32>) {
    let mut s = String::new();
    for (i, v) in x.iter().enumerate() {
        if i % 28 == 0 {
            let _ = writeln!(s);
        }
        let ch = match *v {
            v if v > 0.7 => '#',
            v if v > 0.3 => '+',
            _ => '.',
        };
        let _ = write!(s, "{}", ch);
    }
    println!("{}", s);
}