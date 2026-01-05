// A program to demonstrate inheritance and polymorphism using geometric shapes
public class ShapeCalculator {
    // Abstract base class
    abstract static class Shape {
        protected String name;
        
        public Shape(String name) {
            this.name = name;
        }
        
        // Abstract method that subclasses must implement
        public abstract double calculateArea();
        
        public void display() {
            System.out.println(name + " area: " + calculateArea());
        }
    }
    
    // Circle class extending Shape
    static class Circle extends Shape {
        private double radius;
        
        public Circle(double radius) {
            super("Circle");
            this.radius = radius;
        }
        
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }
    
    // Rectangle class extending Shape
    static class Rectangle extends Shape {
        private double width;
        private double height;
        
        public Rectangle(double width, double height) {
            super("Rectangle");
            this.width = width;
            this.height = height;
        }
        
        @Override
        public double calculateArea() {
            return width * height;
        }
    }
    
    // Triangle class extending Shape
    static class Triangle extends Shape {
        private double base;
        private double height;
        
        public Triangle(double base, double height) {
            super("Triangle");
            this.base = base;
            this.height = height;
        }
        
        @Override
        public double calculateArea() {
            return 0.5 * base * height;
        }
    }
    
    public static void main(String[] args) {
        // Creating an array of different shapes
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(5);
        shapes[1] = new Rectangle(4, 6);
        shapes[2] = new Triangle(3, 4);
        
        // Demonstrating polymorphism by calling the same method on different objects
        System.out.println("Calculating areas of different shapes:");
        System.out.println("------------------------------------");
        
        for (Shape shape : shapes) {
            shape.display();
        }
    }
}