package util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static util.Util.areCloseEnough;


public class UtilTest {

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("areCloseEnoughProvider")
    public void areCloseEnoughTest(String testCaseName, BigDecimal num1, BigDecimal num2, BigDecimal error, boolean includeEqual, boolean expectedAreEqual) {
        boolean actualAreEqual = areCloseEnough(num1, num2, error, includeEqual);
        assertEquals(expectedAreEqual, actualAreEqual);
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
}
