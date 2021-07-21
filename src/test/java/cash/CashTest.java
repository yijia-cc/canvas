package cash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CashTest {

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("cashConstructorProvider")
    public void cashConstructorTest(String testCaseName, BigDecimal amount, List<Cash> expectedCash, Class<Exception> expectedException) {
        if (expectedException == null) {
            Cash actualCash = new Cash(amount);
            assertAny(expectedCash, actualCash);
        } else {
            assertThrows(expectedException, () -> new Cash(amount));
        }
    }

    private static <Item> void assertAny(List<Item> expectedItems, Item actualItem) {
        for (Item expectedItem : expectedItems) {
            if (expectedItem.equals(actualItem)) {
                return;
            }
        }

        fail();
    }

    private static Stream<Arguments> cashConstructorProvider() {
        return Stream.of(
                arguments("Amount is negative", new BigDecimal("-1"), null, IllegalArgumentException.class),
                arguments("Amount is 0", new BigDecimal("0"), Arrays.asList(new Cash(new HashMap<>())), null),
                arguments("Amount is 5.2", new BigDecimal("5.02"), Arrays.asList(
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Note.FIVE, 1);
                            put(Coin.PENNY, 2);
                        }})), null),
                arguments("Amount is 0.3", new BigDecimal("0.3"), Arrays.asList(
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Coin.QUARTER, 1);
                            put(Coin.NICKEL, 1);
                        }})), null),
                arguments("Amount is 0.17", new BigDecimal("0.17"), Arrays.asList(
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Coin.DIME, 1);
                            put(Coin.NICKEL, 1);
                            put(Coin.PENNY, 2);
                        }})), null),
                arguments("Amount is 0.07", new BigDecimal("0.07"), Arrays.asList(
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Coin.NICKEL, 1);
                            put(Coin.PENNY, 2);
                        }})), null),
                arguments("Amount is 0.09", new BigDecimal("0.09"), Arrays.asList(
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Coin.NICKEL, 1);
                            put(Coin.PENNY, 4);
                        }})), null),
                arguments("Amount is 0.03", new BigDecimal("0.03"), Arrays.asList(
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Coin.PENNY, 3);
                        }})), null),
                arguments("Amount is 13.8", new BigDecimal("13.8"), Arrays.asList(
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Note.TEN, 1);
                            put(Note.TWO, 1);
                            put(Coin.DOLLAR, 1);
                            put(Coin.QUARTER, 3);
                            put(Coin.NICKEL, 1);
                        }}),
                        new Cash(new HashMap<Denomination, Integer>() {{
                            put(Note.TEN, 1);
                            put(Note.TWO, 1);
                            put(Note.ONE, 1);
                            put(Coin.QUARTER, 3);
                            put(Coin.NICKEL, 1);
                        }})
                ), null)
        );
    }
}
