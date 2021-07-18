package util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static util.Util.areCloseEnough;


public class UtilTest {

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("areCloseEnoughProvider")
    public void areCloseEnoughTest(String testCaseName, double num1, double num2, double error, boolean includeEqual, boolean expectedAreEqual) {
        boolean actualAreEqual = areCloseEnough(num1, num2, error, includeEqual);
        assertEquals(expectedAreEqual, actualAreEqual);
    }


    private static Stream<Arguments> areCloseEnoughProvider() {
        return Stream.of(
                arguments("Equal to error, not include equal", 0.00001, 0, 1e-5, false, false),
                arguments("Equal to error, include equal", 0.00001, 0, 1e-5, true, true),
                arguments("Less than error", 0.00001, 0, 1e-4, true, true),
                arguments("Greater than error", 0.00001, 0, 1e-6, true, false)
        );
    }
}
