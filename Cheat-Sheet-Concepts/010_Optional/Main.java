import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // ========= CREATION =========
        // msg1 -> Needs to be explicitly value
        // msg2 -> Needs to be explicitly empty
        // msg3 -> Can be either value or null
        Optional<String> msg1 = Optional.of("hello world");
        Optional<String> msg2 = Optional.empty();
        Optional<String> msg3 = Optional.ofNullable("hello");

        
        // ========= CHECKING CONDITION =========
        // isPresent()   -> if value is present
        // isEmpty()     -> if value is empty
        // ifPresent()   -> handle with method
        boolean present1 = msg1.isPresent();
        boolean present2 = msg1.isEmpty();
        msg1.ifPresent(System.out::println);

        
        // ========= RETRIEVING VALUE =========
        // .get()           -> Gets Value
        // .orElse()        -> If it's null, return value
        // .orElseGet()     -> If it's null, return by using lambda
        // .orElseThrow()   -> If its' null, throws exceptions
        String str1 = msg2.get();
        String str2 = msg2.orElse("Has been null");
        String str3 = msg2.orElseGet(() -> "Has been null");
        String str4 = msg2.orElseThrow(() -> new IllegalArgumentException("Has been null"));

        
        // ========= CONVERT THE VALUE =========
        // map()        -> Changes value as you want
        // flatMap()    -> Flattens nested value
        // filter()     -> Filters value as you want
        Optional<String> upperMsg = msg3.map(String::toUpperCase);
        Optional<String> upperMsgFlat = msg3.flatMap(n -> Optional.of(n.toUpperCase()));
        Optional<String> shortMsg = msg3.filter(n -> n.length() < 5);
    }
}
