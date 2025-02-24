public class Person {
    private String name;
    private int age;
    private Pet pet;
    private Work work;

    public Person() {
    }

    public Person(String name, int age, Pet pet, Work work) {
        this.name = name;
        this.age = age;
        this.pet = pet;
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Pet getPet() {
        return pet;
    }

    public Work getWork() {
        return work;
    }
}
