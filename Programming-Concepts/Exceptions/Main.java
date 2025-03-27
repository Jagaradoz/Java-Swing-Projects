import java.io.*;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Handling Exceptions Syntax:
        // Try          -> Code inside that may throw an exception.
        // Catch        -> Handles the exception.
        // Finally      -> Code inside that will always run.
        try {
            // Code...
        } catch (Exception e) {
            // Handles the exception...
        } finally {
            // Code...
        }

        // Throw and Throws:
        // Throw                                      -> Used for creating a new exception and throw it.
        // Throws                                     -> Used for telling method that this method might have exception.
        throw new ArithmeticException("Error occurred");
        public void method() throws NullPointerException { ... }


        // Type of Exceptions:
        // ArithmeticException                      -> An invalid arithmetic operation occurs (e.g., divide by zero).
        // NullPointerException                     -> Trying to access an object or method on a null reference.
        // ArrayIndexOutOfBoundsException           -> Accessing an invalid index of an array.
        // ClassCastException                       -> An object is cast to a type it's not compatible with.
        // IllegalArgumentException                 -> A method receives an illegal or inappropriate argument.
        // IllegalStateException                    -> A method is called at an illegal or inappropriate time.
        // IOException                              -> An I/O operation fails or is interrupted.
        // FileNotFoundException                    -> A file with the specified pathname cannot be found.
        // SQLException                             -> Database access errors occur.
        // NumberFormatException                    -> A string cannot be converted to a number.
        // TimeoutException                         -> A blocking operation times out.
        // InterruptedException                     -> A thread is interrupted while waiting, sleeping, or performing some other operation.
        // IndexOutOfBoundsException                -> Trying to access an index outside the valid range of a list or array.
        // NoSuchElementException                   -> One tries to access an element that is absent in a collection.
        // UnsupportedOperationException            -> An unsupported operation is attempted on an object.
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


        // Custom Exceptions
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
