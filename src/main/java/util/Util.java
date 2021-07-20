package util;

import java.math.BigDecimal;
import java.util.Random;

public class Util {
    private static final Random RANDOM = new Random();

    public static boolean areCloseEnough(BigDecimal num1, BigDecimal num2, BigDecimal error, boolean includeEqual) {
        int compared = num1.subtract(num2).abs().compareTo(error);
        if (includeEqual) {
            return
                    compared <= 0;
        } else {
            return compared < 0;
        }
    }

    public static int[] generateRandomNumber(int length, int min, int max) {
        if (length == 0) {
            return new int[0];
        }

        int[] randomNumbers = new int[length];
        for (int i = 0; i < length; i++) {
            randomNumbers[i] = min + RANDOM.nextInt(max - min + 1);
        }
        return randomNumbers;
    }
}
