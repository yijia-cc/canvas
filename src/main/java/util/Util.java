package util;

import java.math.BigDecimal;

public class Util {
    public static boolean areCloseEnough(BigDecimal num1, BigDecimal num2, BigDecimal error, boolean includeEqual) {
        if (includeEqual) {
            return num1.subtract(num2).abs().compareTo(error) <= 0;
        } else {
            return num1.subtract(num2).abs().compareTo(error) < 0;
        }
    }
}
