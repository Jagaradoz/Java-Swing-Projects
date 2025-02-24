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
