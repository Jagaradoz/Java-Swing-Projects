import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        // ========= CREATION =========
        // person1      -> instantiates normally
        // person2      -> uses reflection
        // person3      -> uses reflection
        Person person1 = new Person();
        Person person2 = Person.class.getDeclaredConstructor().newInstance();
        Class<?> person3 = Class.forName("Person");

        // ========= GET FIELDS =========
        // .getDeclaredField(name)      -> gives public private protected default field
        // .getFields(name)             -> gives public field
        // .getClass()                  -> happens at runtime
        // Class.class                  -> happens at compile time
        // .getGenericType()            -> gets generic type of the field
        Field field1 = person1.getClass().getDeclaredField("count");
        Field[] fields1 = person1.getClass().getDeclaredFields();
        Field field2 = Person.class.getField("name");
        Field[] fields2 = Person.class.getFields();
        Type type = Person.class.getDeclaredField("list").getGenericType();


        // ========= GET METHODS =========
        // .getDeclaredMethod(name)      -> gives public private protected default method
        // .getMethods(name)             -> gives public method
        // .invoke(object,args...)       -> invoke methods
        Method method1 = person1.getClass().getDeclaredMethod("printCount", String.class);
        Method[] methods1 = person1.getClass().getDeclaredMethods();
        Method method2 = Person.class.getMethod("printName", String.class);
        Method[] methods2 = Person.class.getMethods();
        method1.invoke(person1, "Hello");
        method2.invoke(person1, "Hello");


        // ========= ADDITIONAL METHODS =========
        // .getConstructors()               -> gets constructor (public)
        // .getDeclaredConstructors()       -> gets constructor from class (public private protected default)
        // .getParameterTypes()             -> gets parameter types
        // .getModifiers()                  -> gets modifiers
        // .getAnnotations()                -> gets annotations
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

