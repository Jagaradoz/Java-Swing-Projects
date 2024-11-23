abstract class AbstractAnimal {
    private String name;

    // CONSTRUCTOR
    public AbstractAnimal(String name) {
        this.name = name;
    }

    // GETTER
    public String getName() {
        return name;
    }

    // SETTER
    public void setName(String name) {
        this.name = name;
    }

    // ABSTRACT METHOD
    public abstract void makeSound();
}



// CHILD-CLASS
class Cat extends AbstractAnimal {
    // CONSTRUCTOR
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says Meow!");
    }
}

// CHILD-CLASS
class Dog extends AbstractAnimal {
    // CONSTRUCTOR
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says Woof!");
    }
}