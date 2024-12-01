class MyClass {
    private final String message;
    public static int staticCounter = 0;

    MyClass(String message) {
        this.message = message;
    }

    void instanceMethod() {
        System.out.println("Heap: Instance method called. Message: " + message);
    }

    static void staticMethod() {
        staticCounter++;
        System.out.println("Method Area: Static method called. Static Counter = " + staticCounter);
    }
}
