package retry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RetryStrategyTest {
    private static Stream<Arguments> instantRetryProvider() {
        return Stream.of(
                arguments("Succeed at 1st try", 0, 3, true),
                arguments("Succeed at 2nd try", 1, 3, true),
                arguments("Succeed at 3rd try", 2, 3, true),
                arguments("Fail with all retries", 5, 3, false)
        );
    }

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("instantRetryProvider")
    public void instantTest(
            String testCaseName,
            int succeedAt,
            int retryLimit,
            boolean expectedSucceed

    ) {
        AtomicInteger count = new AtomicInteger();
        assertEquals(expectedSucceed, RetryStrategy.instant(() -> {
            try {
                if(count.get() < succeedAt) {
                    throw new Exception();
                }
            } finally {
                count.getAndIncrement();
            }
        }, retryLimit));
    }

}
