use ndarray::{Array1, Array2};
use rand::distributions::{Distribution,Uniform};


pub struct NeuralNetwork {
    pub w1: Array2<f32>,
    pub b1: Array1<f32>,
    pub w2: Array2<f32>,
    pub b2: Array1<f32>,
}

impl NeuralNetwork {
    pub fn new(input_size: usize, hidden_size: usize, output_size: usize) -> Self {
        let mut rng = rand::thread_rng();
        let uniform = Uniform::new(-0.5f32, 0.5f32);

        let w1 = Array2::from_shape_fn((input_size, hidden_size), |_| uniform.sample(&mut rng));

        let b1 = Array1::from_shape_fn(hidden_size, |_| uniform.sample(&mut rng));

        let w2 = Array2::from_shape_fn((hidden_size, output_size), |_| uniform.sample(&mut rng));
        
        let b2 = Array1::from_shape_fn(output_size, |_| uniform.sample(&mut rng));

        Self { w1, b1, w2, b2 }
    }
}

impl NeuralNetwork {
    pub fn forward(&self, x: &Array1<f32>) -> (Array1<f32>, Array1<f32>, Array1<f32>, Array1<f32>) {
        let z1 = x.dot(&self.w1) + &self.b1;
        let a1 = z1.mapv(relu);
        let z2 = a1.dot(&self.w2) + &self.b2;
        let a2 = softmax(&z2);
        (z1, a1, z2, a2)
    }
}

fn softmax(z: &Array1<f32>) -> Array1<f32> {
    let max_z = z.fold(f32::NEG_INFINITY, |acc, &x| acc.max(x));
    let mut exp_z = z.mapv(|x| (x - max_z).exp());
    let sum_exp_z = exp_z.sum();
    exp_z.mapv_inplace(|x| x / sum_exp_z);
    exp_z
}

fn relu(x: f32) -> f32 {
    x.max(0.0)
}

impl NeuralNetwork {
    pub fn backward(
        &self,
        x: &Array1<f32>,
        y_true: &Array1<f32>,
        z1: &Array1<f32>,
        a1: &Array1<f32>,
        a2: &Array1<f32>,
    ) -> (Array2<f32>, Array1<f32>, Array2<f32>, Array1<f32>) {
        let dz2 = a2 - y_true;
        let dw2 = a1.view().insert_axis(ndarray::Axis(1)).dot(&dz2.view().insert_axis(ndarray::Axis(0)).t());
        let db2 = dz2.clone();
        let da1 = self.w2.dot(&dz2);
        let dz1 = da1 * &z1.mapv(|v| if v > 0.0 { 1.0 } else { 0.0 });
        let dw1 = x.view().insert_axis(ndarray::Axis(1)).dot(&dz1.view().insert_axis(ndarray::Axis(0)).t());
        let db1 = dz1.clone();
        (dw1, db1, dw2, db2)
    }
}

impl NeuralNetwork {
    pub fn update(
        &mut self,
        dw1: &Array2<f32>,
        db1: &Array1<f32>,
        dw2: &Array2<f32>,
        db2: &Array1<f32>,
        lr: f32,
    ) {
        self.w1 = &self.w1 - &(dw1 * lr);
        self.b1 = &self.b1 - &(db1 * lr);
        self.w2 = &self.w2 - &(dw2 * lr);
        self.b2 = &self.b2 - &(db2 * lr);
    }
}

impl NeuralNetwork {
    pub fn predict_single(&self, x: &Array1<f32>) -> (usize, Array1<f32>) {
        let (_, _, _, a2) = self.forward(x);
        let predicted_class = a2
            .iter()
            .enumerate()
            .max_by(|(_, a), (_, b)| a.partial_cmp(b).unwrap())
            .unwrap()
            .0;
        (predicted_class, a2)
    }
}
