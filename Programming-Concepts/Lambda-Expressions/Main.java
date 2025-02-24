// LAMBDA EXPRESSIONS
// Convert an anonymous function to a shorter form: () -> {}
// Functional Interface:
// - Only one abstract method is needed
// - Default,static methods/variables are ignored
public class Main {
    public static void main(String[] args) {
        Addition addition = (a, b) -> a + b;
        Subtraction subtraction = (a, b) -> a - b;

        int additionResult = addition.operate(5, 3);
        System.out.println("Addition Result: " + additionResult);

        int subtractionResult = subtraction.operate(5, 3);
        System.out.println("Subtraction Result: " + subtractionResult);
    }
}

@FunctionalInterface
interface Addition {
    int operate(int a, int b);
}

@FunctionalInterface
interface Subtraction {
    int operate(int a, int b);
}
