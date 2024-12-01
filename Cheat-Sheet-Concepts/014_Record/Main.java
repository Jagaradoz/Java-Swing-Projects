public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alice", 25);

        System.out.println("Name: " + person.name());
        System.out.println("Age: " + person.age());

        System.out.println(person.greet());

        Person anotherPerson = new Person("Alice", 25);
        System.out.println("Are they the same? " + person.equals(anotherPerson));
    }
}
