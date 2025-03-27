// Generics:
// It provides <...> that can put wrapper classes.
// The wrapper class will be the data type for the class (T).
public class Box<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}