public class Java26PrimitivePattern {

    public static void main(String[] args) {
        // Feature: Primitive Pattern Matching (Java 26 Preview)
        Object value = 1000;

        // In Java 21, you had to check for 'Integer' then cast.
        // In Java 26, you can check for 'int' directly!
        if (value instanceof int i) {
            System.out.println("This is a primitive int: " + i); // if is passed and it will be printed
        }

        if (value instanceof float i) {  //if fails
            System.out.println("This is a primitive float: " + i);
        }

        // Testing safe casting with switch
        //double d = 42.5; // floating value
        double d = 42.0; // integer value
        String result = switch (d) {
            case int i -> "It's exactly the integer: " + i;
            case double db -> "It's a floating point: " + db;
        };
        System.out.println(result);


        processScore(5);


    }

    public static void processScore(Object score) {
        // No more checking for (score instanceof Integer) then casting to (int)
        switch (score) {
            case int i when i > 90 -> System.out.println("High Confidence: " + i);
            case double d -> System.out.println("Floating precision: " + d);
            case boolean b -> System.out.println("Binary signal: " + b);
            default -> System.out.println("Unknown format");
        }
    }
}
