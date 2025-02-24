public class Main {
    public static void main(String[] args) {
        // Java Memory Management Demonstration

        // 1. Method Area (Stores static and class-level data.)
        // - Stores class-level data such as method code, static variables, and runtime constant pool.
        // - Shared among all threads.
        // Example:
        MyClass.staticMethod();

        // 2. Heap
        // - Used for dynamic memory allocation, i.e., objects and JRE classes.
        // - Divided into Young and Old Generation.
        // Example:
        MyClass obj = new MyClass("Heap Allocation Example");
        obj.instanceMethod();

        // 3. Stack
        // - Stores method call details, local variables, and references to objects in the Heap.
        // - Each thread has its own stack.
        // Example:
        int localVariable = 42;
        System.out.println("Stack: Local variable = " + localVariable);

        // 4. Program Counter (PC) Register
        // - Keeps track of the current JVM instruction being executed.
        // - Specific to the executing thread.

        // 5. Native Method Stack
        // - Used for executing native code (methods written in C/C++).
        // Demonstrating this is beyond the scope of Java itself.

        // Example to trigger Garbage Collection:
        obj = null; // Make the object eligible for garbage collection
        System.gc(); // Suggest JVM to run garbage collection (not guaranteed)
    }
}

