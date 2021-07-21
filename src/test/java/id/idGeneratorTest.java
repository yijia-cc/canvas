package id;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class idGeneratorTest {

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("nextUniqueIdProvider")
    public void nextUniqueIdTest(String testCaseName, IdGenerator idGenerator, int numOfCalls, List<BigInteger> expectedIds) {
        for (int i = 0; i < numOfCalls; i++) {
            BigInteger actualId = idGenerator.nextUniqueId();
            assertEquals(expectedIds.get(i).intValue(), actualId.intValue());
        }
    }

    private static Stream<Arguments> nextUniqueIdProvider() {
        return Stream.of(
                arguments(
                        "Call 1 time",
                        new IdGenerator(new BigInteger("10000"), 5),
                        1,
                        Arrays.asList(new BigInteger("10001"))),
                arguments(
                        "Call 5 times",
                        new IdGenerator(new BigInteger("20000"), 5),
                        1,
                        Arrays.asList(
                                new BigInteger("20001"),
                                new BigInteger("20002"),
                                new BigInteger("20003"),
                                new BigInteger("20004"),
                                new BigInteger("20005")
                        ))
        );
    }
}
