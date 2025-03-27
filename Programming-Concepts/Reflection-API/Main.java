import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        // Creation:
        // person1      -> Instantiates normally.
        // person2      -> Uses reflection.
        // person3      -> Uses reflection.
        Person person1 = new Person();
        Person person2 = Person.class.getDeclaredConstructor().newInstance();
        Class<?> person3 = Class.forName("Person");

        // Get fields:
        // .getDeclaredField(name)      -> Gives public, private, protected, default field.
        // .getFields(name)             -> Gives public field.
        // .getClass()                  -> Happens at runtime.
        // Class.class                  -> Happens at compile time.
        // .getGenericType()            -> Gets generic type of the field.
        Field field1 = person1.getClass().getDeclaredField("count");
        Field[] fields1 = person1.getClass().getDeclaredFields();
        Field field2 = Person.class.getField("name");
        Field[] fields2 = Person.class.getFields();
        Type type = Person.class.getDeclaredField("list").getGenericType();

        // Get methods:
        // .getDeclaredMethod(name)      -> Gives public, private, protected, default method.
        // .getMethods(name)             -> Gives public method.
        // .invoke(object, args...)      -> Invoke methods.
        Method method1 = person1.getClass().getDeclaredMethod("printCount", String.class);
        Method[] methods1 = person1.getClass().getDeclaredMethods();
        Method method2 = Person.class.getMethod("printName", String.class);
        Method[] methods2 = Person.class.getMethods();
        method1.invoke(person1, "Hello");
        method2.invoke(person1, "Hello");

        // Additional methods:
        // .getConstructors()               -> Gets constructor (public).
        // .getDeclaredConstructors()       -> Gets constructor from class (public, private, protected, default).
        // .getParameterTypes()             -> Gets parameter types.
        // .getModifiers()                  -> Gets modifiers.
        // .getAnnotations()                -> Gets annotations.
    }
}

class Person {
    private int count = 50;
    private int[] numbers = {1, 3, 5, 7, 9};
    private ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 4, 6, 8));

    public String name = "Mike";
    public boolean gender = false;

    public Person() {
        System.out.println("Default constructor called!");
    }

    private void printCount(String test) {
        System.out.println("Count : " + count);
    }

    public void printName(String test) {
        System.out.println("Name : " + name);
    }
}
