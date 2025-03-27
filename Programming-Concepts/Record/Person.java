// Record:
// It's a special class introduced in Java to represent immutable data objects.
// Key features of records:
// - All fields are `final` by default (cannot be changed once set).
// - Automatically provides the constructor, `toString`, `equals`, and `hashCode` methods.
// - Simplifies the creation of data-carrying classes by reducing boilerplate code.
public record Person(String name, int age) {

    // Static fields are allowed in records (shared by all instances).
    private static int number = 5;

    // Custom methods can be defined inside a record.
    // This method returns a greeting message using the record's fields.
    public String greet() {
        return "Hello, my name is " + name + " and I am " + age + " years old.";
    }

    // Static methods are allowed and can access static fields.
    public static int getStaticNumber() {
        return number;
    }

    // Compact constructor example (optional, modifies default constructor behavior).
    // This validates input fields before initializing the record.
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative!");
        }
    }
}
