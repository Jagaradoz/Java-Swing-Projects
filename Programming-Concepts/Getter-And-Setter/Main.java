public class Main {
    public static void main(String[] args) {
        // Create an instance of Person (new Person(...))
        Person person = new Person("John Doe", 25, false);

        // Set new values
        person.setName("Mikey");
        person.setAge(30);
        person.setGender(true);

        // Get values and print 
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Gender: " + (person.isGender() ? "Male" : "Female"));
    }
}