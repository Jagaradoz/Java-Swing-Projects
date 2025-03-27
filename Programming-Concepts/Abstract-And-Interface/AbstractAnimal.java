// Abstract Class:
// It is a class that cannot be instantiated.
// It mainly used to be inherited.
// It can contain concrete methods and abstract methods.
abstract class AbstractAnimal {
    private String name;

    public AbstractAnimal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void makeSound();
}

// Example Implementation:
class Cat extends AbstractAnimal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says Meow!");
    }
}

// Example Implementation:
class Dog extends AbstractAnimal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says Woof!");
    }
}