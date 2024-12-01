import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Person person = new Person("John Doe", 30, "secret123");

        // Serialize the object to a file
        String filename = "data/person.ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)))) {
            System.out.println("Serializing object: " + person);
            oos.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filename)))) {
            Person deserializedPerson = (Person) ois.readObject();
            System.out.println("Deserialized object: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

