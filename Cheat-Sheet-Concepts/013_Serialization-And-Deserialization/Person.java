import java.io.Serial;
import java.io.Serializable;

class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private final int age;
    private final transient String password; // transient are not serialized

    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }
}
