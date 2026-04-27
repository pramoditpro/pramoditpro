import java.lang.reflect.Field;

public class FinalTest {
    // A standard private final field
    private final String secretCode = "ORIGINAL_123";

    public static void main(String[] args) throws Exception {
        FinalTest example = new FinalTest();
        System.out.println("Before Mutation: " + example.secretCode);

        // 1. Get the field via reflection
        Field field = FinalTest.class.getDeclaredField("secretCode");

        // 2. Break encapsulation
        field.setAccessible(true);

        try {
            // 3. Attempt to modify the 'final' value
            field.set(example, "HACKED_999");
            System.out.println("After Mutation: " + example.secretCode);
        } catch (IllegalAccessException e) {
            System.err.println("FAILED: Reflection was blocked! " + e.getMessage());
        }
    }
}