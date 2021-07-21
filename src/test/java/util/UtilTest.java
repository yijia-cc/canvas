package util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static util.Util.*;

@ExtendWith(MockitoExtension.class)
public class UtilTest {
    private int BASE = 10;

    @Mock
    Random random;

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("areCloseEnoughProvider")
    public void areCloseEnoughTest(String testCaseName, BigDecimal num1, BigDecimal num2, BigDecimal error, boolean includeEqual, boolean expectedAreEqual) {
        boolean actualAreEqual = areCloseEnough(num1, num2, error, includeEqual);
        assertEquals(expectedAreEqual, actualAreEqual);
    }

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("generateRandomNumbersProvider")
    public void generateRandomNumbersTest(String testCaseName, int length, Class<? extends Exception> expectedException) {
        if (expectedException == null) {
            int min = lengthMin(BASE, length);
            int max = lengthMax(BASE, length);
            for (int i = 0; i < 100; i++) {
                int randomNum = generateRandomNumbers(random, length);
                assertTrue(min <= randomNum && randomNum <= max);
            }
        } else {
            assertThrows(expectedException, () -> {
                generateRandomNumbers(random, length);
            });
        }
    }

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("lengthMinProvider")
    public void lengthMinTest(String testCaseName, int length, int expected, Class<? extends Exception> expectedException) {
        if (expectedException == null) {
            int actual = lengthMin(BASE, length);
            assertEquals(expected, actual);
        } else {
            assertThrows(expectedException, () -> {
                lengthMin(BASE, length);
            });
        }

    }

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("lengthMaxProvider")
    public void lengthMaxTest(String testCaseName, int length, int expected, Class<? extends Exception> expectedException) {
        if (expectedException == null) {
            int actual = lengthMax(BASE, length);
            assertEquals(expected, actual);
        } else {
            assertThrows(expectedException, () -> {
                lengthMax(BASE, length);
            });
        }

    }

    private static Stream<Arguments> areCloseEnoughProvider() {
        return Stream.of(
                arguments(
                        "Equal to error, not include equal",
                        new BigDecimal("0.00001"),
                        new BigDecimal("0"),
                        new BigDecimal("01e-5"),
                        false,
                        false),
                arguments(
                        "Equal to error, include equal",
                        new BigDecimal("0.00001"),
                        new BigDecimal("0"),
                        new BigDecimal("1e-5"),
                        true,
                        true),
                arguments(
                        "Less than error",
                        new BigDecimal("0.00001"),
                        new BigDecimal("0"),
                        new BigDecimal("1e-4"),
                        true,
                        true),
                arguments(
                        "Greater than error",
                        new BigDecimal("0.00001"),
                        new BigDecimal("0"),
                        new BigDecimal("1e-6"),
                        true,
                        false)
        );
    }

    private static Stream<Arguments> generateRandomNumbersProvider() {
        return Stream.of(
                arguments("Less than 1", 0, IllegalArgumentException.class),
                arguments("Larger than 9 digits", 10, IllegalArgumentException.class),
                arguments("Valid length", 4, null));
    }

    private static Stream<Arguments> lengthMinProvider() {
        return Stream.of(
                arguments("Less than 1", 0, 0, IllegalArgumentException.class),
                arguments("Larger than 9 digits", 10, 0, IllegalArgumentException.class),
                arguments("Case 1", 3, 100, null),
                arguments("Case 2", 5, 10000, null),
                arguments("Case 3", 1, 1, null)
        );
    }

    private static Stream<Arguments> lengthMaxProvider() {
        return Stream.of(
                arguments("Less than 1", 0, 0, IllegalArgumentException.class),
                arguments("Larger than 9 digits", 10, 0, IllegalArgumentException.class),
                arguments("Case 1", 3, 999, null),
                arguments("Case 2", 5, 99999, null),
                arguments("Case 3", 1, 9, null)
        );
    }
}
