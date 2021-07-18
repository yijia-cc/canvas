package util;

public class Util {
    public static boolean areCloseEnough(double num1, double num2, double error, boolean includeEqual) {
        if (includeEqual) {
            return (Math.abs(num1 - num2) <= error);
        } else {
            return (Math.abs(num1 - num2) < error);
        }
    }
}
