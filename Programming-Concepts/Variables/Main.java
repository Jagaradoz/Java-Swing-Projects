public class Main {
    public static void main(String[] args) {
        // PRIMITIVE TYPES
        boolean var1 = true;
        byte var2 = 'A';
        char var3 = 'A';
        short var4 = 1;
        int var5 = 1;
        long var6 = 1L;
        float var7 = 1.0F;
        double var8 = 1.0D;

        // WRAPPER CLASSES
        Boolean var9 = true;
        Byte var10 = 'A';
        Character va11 = 'A';
        Short var12 = 1;
        Integer var13 = 1;
        Long var14 = 1L;
        Float var15 = 1.0F;
        Double var16 = 1.0D;

        // STRING (IMMUTABLE)
        String str1 = "Hello World";
        String str2 = new String("Hello World");

        // STRING (MUTABLE)
        // StringBuilder used for multi-threaded (asynchronously)
        // StringBuffer used for single-threaded (synchronously)
        StringBuilder stringBuilder = new StringBuilder("Hello");
        StringBuffer stringBuffer = new StringBuffer("Hello");

        // METHODS
        // .append()            -> Adds texts at the end
        // .insert()            -> Inserts " Java" at index 5
        // .replace()           -> Replaces characters from index 0 to 4 with "Hi"
        // .delete()            -> Deletes characters from index 0 to 2
        // .deleteCharAt()      -> Deletes the character at index 2
        // .reverse()           -> Reverses the content of sb
        // .setLength()        -> Adjusts the length to 10
        stringBuilder.append("World ").append(123);
        stringBuilder.insert(5, " Java");
        stringBuilder.replace(0, 5, "Hi");
        stringBuffer.delete(0, 3);
        stringBuffer.deleteCharAt(2);
        stringBuffer.reverse();
        stringBuffer.setLength(10);
    }
}