package zemberek3.util;

public class Preconditions {
    public static void checkArgument(boolean criteria, String message) {
        if (!criteria)
            throw new IllegalArgumentException(message);
    }
}
