//Title: Add Java OOP example for abstraction
//Author: hirushi1999
//Date: 15.10.2025

abstract class Vehicle {
    private String brand;

    // Constructor
    public Vehicle(String brand) {
        this.brand = brand;
    }

    // Abstract method (no implementation)
    abstract void startEngine();

    // Concrete method (has implementation)
    public void displayBrand() {
        System.out.println("Brand: " + brand);
    }
}

class Car extends Vehicle {

    public Car(String brand) {
        super(brand); // calls the constructor of Vehicle
    }

    @Override
    void startEngine() {
        System.out.println("Car engine starts with a key ignition.");
    }
}

class ElectricScooter extends Vehicle {

    public ElectricScooter(String brand) {
        super(brand);
    }

    @Override
    void startEngine() {
        System.out.println("Scooter motor starts silently with a button press.");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        // You can't do this:
        // Vehicle v = new Vehicle("Generic");

        // But you can use subclasses:
        Vehicle car = new Car("Toyota");
        Vehicle scooter = new ElectricScooter("Tesla");

        car.displayBrand();
        car.startEngine();

        System.out.println();

        scooter.displayBrand();
        scooter.startEngine();
    }
}
