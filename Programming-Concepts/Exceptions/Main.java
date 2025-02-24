import java.io.*;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws Exception {
        // ========= HANDLING EXCEPTIONS SYNTAX =========
        // try          -> Code inside that may throw an exception
        // catch        -> Handles the exception
        // finally      -> Code inside that will always run
        try {
            // code...
        } catch (Exception e) {
            // handles the exception...
        } finally {
            // code...
        }


        // ========= THROW VS THROWS =========
        // throw                                      -> used for creating a new exception and throw it.
        // throws                                     -> used for telling method that this method might have exception
        throw new ArithmeticException("Error occurred");
        public void method() throws NullPointerException { ... }


        // ========= TYPE OF EXCEPTIONS =========
        // ArithmeticException                      -> an invalid arithmetic operation occurs (e.g., divide by zero).
        // NullPointerException                     -> trying to access an object or method on a null reference.
        // ArrayIndexOutOfBoundsException           -> accessing an invalid index of an array.
        // ClassCastException                       -> an object is cast to a type it's not compatible with.
        // IllegalArgumentException                 -> a method receives an illegal or inappropriate argument.
        // IllegalStateException                    -> a method is called at an illegal or inappropriate time.
        // IOException                              -> an I/O operation fails or is interrupted.
        // FileNotFoundException                    -> a file with the specified pathname cannot be found.
        // SQLException                             -> database access errors occur.
        // NumberFormatException                    -> a string cannot be converted to a number.
        // TimeoutException                         -> a blocking operation times out.
        // InterruptedException                     -> a thread is interrupted while waiting, sleeping, or performing some other operation.
        // IndexOutOfBoundsException                -> trying to access an index outside the valid range of a list or array.
        // NoSuchElementException                   -> one tries to access an element that is absent in a collection.
        // UnsupportedOperationException            -> an unsupported operation is attempted on an object.
        throw new ArithmeticException("Cannot divide by zero");
        throw new NullPointerException("Object is null");
        throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        throw new ClassCastException("Cannot cast to the specified type");
        throw new IllegalArgumentException("Invalid argument provided");
        throw new IllegalStateException("Invalid state");
        throw new IOException("I/O operation failed");
        throw new FileNotFoundException("File not found");
        throw new SQLException("Database error occurred");
        throw new NumberFormatException("Invalid number format");
        throw new TimeoutException("Operation timed out");
        throw new InterruptedException("Thread was interrupted");
        throw new IndexOutOfBoundsException("Index is out of bounds");
        throw new NoSuchElementException("Element not found");
        throw new UnsupportedOperationException("Operation not supported");


        // ========= CUSTOM EXCEPTIONS =========
        try {
            validateAge(15);
        } catch (InvalidAgeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        } else {
            System.out.println("Valid age.");
        }
    }
}
