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
        // Gets declared fields array and declared methods array
        Field[] fields = Main.class.getDeclaredFields();
        Method[] methods = Main.class.getDeclaredMethods();

        // Loops through fields:
        // If field's annotation is StaticField , print it!
        for (Field field : fields) {            
            if (field.isAnnotationPresent(StaticField.class)) {
                Annotation[] annotations = field.getAnnotations();
                System.out.println("Annotations: " + Arrays.toString(annotations));
            }
        }

        System.out.println();

        // Loops through methods:
        // If method's annotation is InvokeTimes, invoke the method multiple times! 
        for (Method method : methods) {
            
            if (method.isAnnotationPresent(InvokeTimes.class)) {
                
                InvokeTimes invokeTimesAnnotation = method.getAnnotation(InvokeTimes.class);

                for (int i = 0; i < invokeTimesAnnotation.times(); i++) {
                    method.invoke(null);
                }
            }
        }
    }
}

// @Target          -> Can apply to selected values (field,method,class)
// @Retention       -> Available at
//                  -> SOURCE   - Be got rid of before compile time
//                  -> CLASS    - Stays in compile time but be got rid of before runtime
//                  -> RUNTIME  - Stays throughout program
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
