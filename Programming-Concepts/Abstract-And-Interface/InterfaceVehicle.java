// Interface:
// It is inherited by using `implement` keyword.
// One class can implement multiple interfaces.
// It contain only abstract methods (abstract keyword hidden).
public interface InterfaceVehicle {
    void start();
    void stop();
}

class Car implements InterfaceVehicle {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    @Override
    public void start() {
        System.out.println(model + " is starting.");
    }

    @Override
    public void stop() {
        System.out.println(model + " is stopping.");
    }
}

// Example Implementation:
class Bike implements InterfaceVehicle {
    private String model;

    public Bike(String model) {
        this.model = model;
    }

    @Override
    public void start() {
        System.out.println(model + " is starting.");
    }

    @Override
    public void stop() {
        System.out.println(model + " is stopping.");
    }
}
