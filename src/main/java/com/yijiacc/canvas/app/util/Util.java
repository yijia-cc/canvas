package com.yijiacc.canvas.app.util;

import java.math.BigDecimal;
import java.util.Random;

public class Util {

    public static boolean areCloseEnough(BigDecimal num1, BigDecimal num2, BigDecimal error, boolean includeEqual) {
        int compared = num1.subtract(num2).abs().compareTo(error);
        if (includeEqual) {
            return
                    compared <= 0;
        } else {
            return compared < 0;
        }
    }

    public static int generateRandomNumbers(Random random, int length) {
        if (length == 0) {
           throw new IllegalArgumentException("length must be at least 1");
        }

        if (length > 9) {
            throw new IllegalArgumentException("length must be less than 9");
        }

        int base = 10;

        int min = lengthMin(base, length);
        int max = lengthMax(base, length);
        int addition = random.nextInt(max - min + 1);
        return (int) Math.floor(addition + min);
    }

    public static int lengthMin(int base, int length) {
        if (length == 0) {
            throw new IllegalArgumentException("length must be at least 1");
        }

        if (length > 9) {
            throw new IllegalArgumentException("length must be less than 9");
        }
        return (int)Math.pow(base, length - 1);
    }

    public static int lengthMax(int base, int length) {
        if (length == 0) {
            throw new IllegalArgumentException("length must be at least 1");
        }

        if (length > 9) {
            throw new IllegalArgumentException("length must be less than 9");
        }
        return (int) Math.pow(base, length) - 1;
    }
}
