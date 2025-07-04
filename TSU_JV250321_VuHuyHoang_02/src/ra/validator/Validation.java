package ra.validator;

public class Validation {
    public static boolean isEmpty(String string) {
        if (string == null || string.isEmpty()) {
            return true;
        }
        return false;
    }
}
