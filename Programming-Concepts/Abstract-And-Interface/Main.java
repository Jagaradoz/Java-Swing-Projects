public class Main {
    public static void abstractClass(){
        // Can't create an instance of AbstractAnimal.
        AbstractAnimal abstractAnimal = new AbstractAnimal();

        // Create an instance of Cat and Dog
        Cat cat = new Cat("Whiskers");
        Dog dog = new Dog("Buddy");

        // Call completed method
        cat.makeSound();
        dog.makeSound();
    }

    public static void interfaceClass(){
        // Interface can't be created
        InterfaceVehicle vehicle = new InterfaceVehicle();

        // Create an instance of Car and Bike
        InterfaceVehicle car = new Car("Toyota");
        InterfaceVehicle bike = new Bike("Canyon");

        // Call completed method
        car.start();
        car.stop();

        // Call completed method
        bike.start();
        bike.stop();
    }

    public static void main(String[] args) {
        abstractClass();
        interfaceClass();
    }
}