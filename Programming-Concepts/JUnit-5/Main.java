package application;

public class Main {
    // Addition method
    // Subtraction method
    // Multiplication method
    // Division method

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public String getString() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        Main main = new Main();

        int n1 = main.add(15, 20);
        int n2 = main.subtract(30, 10);
        int n3 = main.multiply(5, 6);
        int n4 = main.divide(20, 5);

        System.out.println("Addition result (15 + 20): " + n1);
        System.out.println("Subtraction result (30 - 10): " + n2);
        System.out.println("Multiplication result (5 * 6): " + n3);
        System.out.println("Division result (20 / 5): " + n4);

        System.out.println(main.getString());
    }
}
