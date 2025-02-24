// GENERICS
// provides <...> that can put wrapper classes
// to specify data type in (classes,methods)
public class Main {
    public static void main(String[] args) {
        // NORMAL GENERICS
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();

        integerBox.set(10);
        stringBox.set("Hello Generics");

        System.out.println("Integer Box contains: " + integerBox.get());
        System.out.println("String Box contains: " + stringBox.get());


        // MULTIPLE GENERICS
        Pair<Integer, String> pair = new Pair<>(1, "One");
        System.out.println("Key: " + pair.getKey() + ", Value: " + pair.getValue());


        // METHOD GENERICS
        printArray(new Integer[]{1, 2, 3});
        printArray(new String[]{"A", "B", "C"});


        // BOUNDED TYPE PARAMETER
        printNumber(42);
        printNumber(3.14);

        List<?> list = new ArrayList<String>();
        List<? extends Number> list = new ArrayList<Integer>();
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static <T extends Number> void printNumber(T number) {
        System.out.println("Number: " + number);
    }
}
