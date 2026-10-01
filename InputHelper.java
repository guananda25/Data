import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static String required(String message) {
        while (true) {
            String value = readString(message);
            if (!value.isEmpty()) return value;
            System.out.println("Value cannot be empty.");
        }
    }

    public static int readInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(readString(message));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static int positiveInt(String message) {
        while (true) {
            int value = readInt(message);
            if (value > 0) return value;
            System.out.println("Enter a positive number.");
        }
    }

    public static double readDouble(String message) {
        while (true) {
            try {
                return Double.parseDouble(readString(message));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}