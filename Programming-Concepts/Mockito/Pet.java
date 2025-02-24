public class Pet {
    private String name;
    private String breed;

    public Pet() {
    }

    public Pet(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public void feed() {
        System.out.println("Feeding " + name + "...");
    }

    public void play() {
        System.out.println("Playing with " + name + "...");
    }

    public void eat(String food) {
        System.out.println("Eating " + food);
    }
}
