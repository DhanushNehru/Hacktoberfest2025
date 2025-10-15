public interface Engine {

    static final int PRICE = 2000000;
    void start();
    void stop();
    void acc();
}

public interface Media {
    void start();
    void stop();

}

public interface Brake {

    void brake();

}

public class Car implements Engine, Media, Brake {

    int power;

    @Override
    public void brake() {
        System.out.println("Brakes are pulled");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopped");
    }

    @Override
    public void start() {
        System.out.println("Start the car");
    }

    @Override
    public void acc() {
        System.out.println("increase the speed");
    }

}

public class MediaPlayer implements Media {

    @Override
    public void start() {
        System.out.println("MUsic Start");
    }

    @Override
    public void stop() {
        System.out.println("Music Stop");
    }

}

public class NewCar  {
    private Engine engine;
    private Media player;
    private Brake brakes;

    public NewCar(){
        engine = new PowerEngine();
        player = new MediaPlayer();
        brakes = new PowerBreaks();
    }
    public void start(){
        engine.start();
    }

    public void startMusic(){
        player.start();
    }

    public void brake(){
        brakes.brake();
    }

}

public class PowerBreaks implements Brake {

    @Override
    public void brake() {
        System.out.println("BRAKE!!!!");
    }

}

public class PowerEngine implements Engine{

    @Override
    public void start() {
        System.out.println("Engine Start");
    }

    @Override
    public void stop() {
        System.out.println("Engine Stop");
    }

    @Override
    public void acc() {
        System.out.println("Speed up");
    }

}


public class Main {
    public static void main(String[] args) {
        Car car = new Car();

        car.acc();
        car.brake();
        car.start();
        car.stop();

        NewCar newCar = new NewCar();

        newCar.start();
        newCar.startMusic();
        newCar.brake();

    }
    // interface doesn't care whether 2 classes are related or not 

    // Engine car = new Car();
    // car.a - we can't access it because the variable access is defined by the Engine here
    // interface can also inherited
    //static interface method should always have a body
    // methods that are overridden should change the access specifier but should be less restricted compared to the previous one 
    //in the nested interface the super interface should be public 
}
