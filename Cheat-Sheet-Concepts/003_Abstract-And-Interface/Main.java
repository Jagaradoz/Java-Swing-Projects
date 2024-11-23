public class Main {
    public static void main(String[] args) {
        // ====================================================
        // ===================== ABSTRACT =====================

        // Can't create an instance of AbstractAnimal (fix it)
        AbstractAnimal abstractAnimal = new AbstractAnimal();

        // Create an instance of Cat and Dog
        Cat cat = new Cat("Whiskers");
        Dog dog = new Dog("Buddy");

        // call (not abstract anymore) method
        cat.makeSound();
        dog.makeSound();

        // ====================================================
        // ==================== INTERFACE ====================

        // Interface can't be created (fix it)
        InterfaceVehicle vehicle = new InterfaceVehicle();

        // Create an instance of Car and Bike
        InterfaceVehicle car = new Car("Toyota");
        InterfaceVehicle bike = new Bike("Canyon");

        // call (not interface anymore) method
        car.start();
        car.stop();

        // call (not interface anymore) method
        bike.start();
        bike.stop();
    }
}