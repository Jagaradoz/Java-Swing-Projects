import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        // ========= CREATION =========
        // numbers1 -> Generates numbers 1-9 (inclusive of 1, exclusive of 10)
        // numbers2 -> Creates IntStream from primitive array
        // numbers3 -> Converts List<T> into stream
        // numbers4 -> Creates a stream from given elements
        // numbers5 -> Randomizes numbers between 0-99 but limits to 5 values
        IntStream numbers1 = IntStream.range(1, 10);
        IntStream numbers2 = Arrays.stream(new int[]{1, 2, 3});
        Stream<Integer> numbers3 = Arrays.asList(1, 2, 3).stream();
        Stream<Integer> numbers4 = Stream.of(1, 2, 3);
        Stream<Integer> numbers5 = Stream.generate(() -> (int) (Math.random() * 100)).limit(5);


        // ========= INTERMEDIATE OPERATIONS =========
        // filter       -> Filters elements based on a predicate
        // map          -> Transforms each element using a function
        // mapToObj     -> Converts a primitive stream (IntStream) into an object stream (Stream<Integer>)
        // flatMap      -> Flattens a stream of collections into a single stream ({{1,2,3},{4,5,6}} -> {1,2,3,4,5,6})
        // distinct     -> Removes duplicates
        // sorted       -> Sorts elements
        // peek         -> Performs an action on each element without consuming the stream (for debugging or inspecting)
        numbers1.filter((n) -> n % 2 == 0);
        numbers1.map((n) -> n + 1);
        numbers1.mapToObj(n -> n);
        numbers1.distinct();
        numbers1.sorted();
        numbers1.peek(System.out::println);
        

        // ========= TERMINAL OPERATIONS =========
        // forEach -> Iterates over each element
        // collect -> Collects the stream into a collection (List, Set, etc.)
        // reduce  -> Performs an accumulation on the stream elements (e.g., sum)
        // anyMatch -> Checks if any element matches a condition (return boolean)
        // allMatch -> Checks if all elements match a condition (return boolean)
        // noneMatch -> Checks if no element matches a condition (return boolean)
        // count   -> Counts the number of elements in the stream
        // findFirst -> Finds the first element in the stream
        numbers1.forEach(System.out::println);
        numbers1.collect(Collectors.toList());
        numbers1.reduce(0, (a, b) -> a + b);
        numbers1.anyMatch(n -> n % 2 == 0);
        numbers1.allMatch(n -> n > 0);
        numbers1.noneMatch(n -> n > 10);
        numbers1.count();
        numbers1.findFirst();
    }
}