import java.util.Arrays;
import java.lang.reflect.*;
import java.lang.annotation.*;

public class Main {
    @StaticField(value = "COUNT")
    private static int COUNT = 0;

    @StaticField(value = "AGE")
    private static int AGE = 0;

    @InvokeTimes(times = 3)
    private static void print() {
        System.out.println("Invoked!");
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        // gets declared fields array and declared methods array
        Field[] fields = Main.class.getDeclaredFields();
        Method[] methods = Main.class.getDeclaredMethods();

        // loops through fields
        for (Field field : fields) {
            // if field's annotation is StaticField , print it!
            if (field.isAnnotationPresent(StaticField.class)) {
                Annotation[] annotations = field.getAnnotations();
                System.out.println("Annotations: " + Arrays.toString(annotations));
            }
        }

        System.out.println();

        // loops through methods
        for (Method method : methods) {
            // if method's annotation is InvokeTimes
            if (method.isAnnotationPresent(InvokeTimes.class)) {
                // gets times from method's annotation
                InvokeTimes invokeTimesAnnotation = method.getAnnotation(InvokeTimes.class);

                // loops times
                for (int i = 0; i < invokeTimesAnnotation.times(); i++) {
                    method.invoke(null);
                }
            }
        }
    }
}


// ========= CREATION =========
// @Target          -> can apply to selected values (field,method,class)
// @Retention       -> available at
//                  -> SOURCE   - be got rid of before compile time
//                  -> CLASS    - stays in compile time but be got rid of before runtime
//                  -> RUNTIME  - stays throughout program
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface StaticField {
    String value() default "UNDEFINED";
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface InvokeTimes {
    int times();
}