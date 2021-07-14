package vending_machine;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.PaymentMethod;
import payment.TimeoutException;
import payment.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;
import static tools.Assertions.assertSameElements;

@ExtendWith(MockitoExtension.class)
public class VendingMachineTest {

    @Mock
    static PaymentMethod stubPaymentMethod;

    private static Stream<Arguments> listInventoriesProvider() {
        return Stream.of(
                arguments("No Inventory", new ArrayList<>(), new ArrayList<>()),
                arguments(
                        "Has Inventories",
                        new ArrayList<>() {{
                            new Inventory("001", "Coke", 3);
                            new Inventory("002", "Pepsi", 3);
                            new Inventory("003", "Kind", 2);
                        }},
                        new ArrayList<>() {{
                            new Inventory("002", "Pepsi", 3);
                            new Inventory("001", "Coke", 3);
                            new Inventory("003", "Kind", 2);
                        }}));
    }

    private static Stream<Arguments> simpleConstructorProvider() {
        return Stream.of(
                arguments("Inventory is null", null, IllegalArgumentException.class),
                arguments("No Inventory", new ArrayList<>(), null),
                arguments(
                        "Has Inventories",
                        new ArrayList<>() {{
                            new Inventory("001", "Coke", 3);
                            new Inventory("002", "Pepsi", 3);
                            new Inventory("003", "Kind", 2);
                        }},
                        null)
        );
    }

    private static Stream<Arguments> usePaymentProvider() {
        return Stream.of(
                arguments("Unauthorized", new ArrayList<>() {{
                            new Inventory("001", "Coke", 3);
                            new Inventory("002", "Pepsi", 3);
                            new Inventory("003", "Kind", 2);
                        }},
                        false,
                        null,
                        UnauthorizedException.class),
                arguments("Authorization timeout", new ArrayList<>() {{
                            new Inventory("001", "Coke", 3);
                            new Inventory("002", "Pepsi", 3);
                            new Inventory("003", "Kind", 2);
                        }},
                        false,
                        TimeoutException.class,
                        TimeoutException.class),
                arguments(
                        "Authorized",
                        new ArrayList<>() {{
                            new Inventory("001", "Coke", 3);
                            new Inventory("002", "Pepsi", 3);
                            new Inventory("003", "Kind", 2);
                        }},
                        true,
                        null,
                        null));
    }


    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("simpleConstructorProvider")
    public void simpleConstructorTest(String testCaseName, List<Inventory> inventories, Class<Exception> expectedException) {
        if (expectedException == null) {
            new VendingMachine(inventories);
        } else {
            assertThrows(expectedException, () -> new VendingMachine(inventories));
        }
    }

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("listInventoriesProvider")
    public void listInventoriesTest(
            String testCaseName,
            List<Inventory> inputInventories,
            List<Inventory> expectedInventories) {
        VendingMachine vendingMachine = new VendingMachine(inputInventories);
        List<Inventory> actualInventories = vendingMachine.listInventories();
        assertSameElements(expectedInventories, actualInventories, testCaseName);

    }

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("usePaymentProvider")
    public void usePaymentMethodTest(
            String testCaseName,
            List<Inventory> inputInventories,
            boolean paymentMethodIsAuthorized,
            Class<? extends Exception> paymentMethodIsAuthorizedException,
            Class<? extends Exception> expectedException
    ) {
        try {
            if (paymentMethodIsAuthorizedException == null) {
                when(stubPaymentMethod.isAuthorized())
                        .thenReturn(paymentMethodIsAuthorized);
            } else {
                when(stubPaymentMethod.isAuthorized())
                        .thenThrow(paymentMethodIsAuthorizedException);
            }
        } catch (Exception e) {
            fail(e);
        }

        VendingMachine vendingMachine = new VendingMachine(inputInventories);
        if (expectedException == null) {
            try {
                vendingMachine.usePaymentMethod(stubPaymentMethod);
            } catch (Exception e) {
                fail(e);
            }

        } else {
            assertThrows(expectedException, () -> {
                vendingMachine.usePaymentMethod(stubPaymentMethod);
            });
        }
    }
}
