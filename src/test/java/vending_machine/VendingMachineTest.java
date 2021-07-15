package vending_machine;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.InvalidInventoryIdException;
import payment.PaymentMethod;
import payment.TimeoutException;
import payment.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
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
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }},
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }}));
    }

    private static Stream<Arguments> simpleConstructorProvider() {
        return Stream.of(
                arguments("Inventory is null", null, IllegalArgumentException.class),
                arguments("No Inventory", new ArrayList<>(), null),
                arguments(
                        "Has Inventories",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }},
                        null)
        );
    }

    private static Stream<Arguments> usePaymentProvider() {
        return Stream.of(
                arguments("Unauthorized",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }},
                        false,
                        null,
                        UnauthorizedException.class),
                arguments("Authorization timeout",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }},
                        false,
                        TimeoutException.class,
                        TimeoutException.class),
                arguments(
                        "Authorized",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }},
                        true,
                        null,
                        null));
    }

    private static Stream<Arguments> selectInventoryProvider() {
        return Stream.of(
                arguments("No inventory",
                        new ArrayList<>() {},
                        "004",
                        InvalidInventoryIdException.class,
                        null),
                arguments("Inventory ID not found",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }},
                        "004",
                        InvalidInventoryIdException.class,
                        null),
                arguments("Inventory ID found",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", 3));
                            add(new Inventory("002", "Pepsi", 3));
                            add(new Inventory("003", "Kind", 2));
                        }},
                        "001",
                        null,
                        new Inventory("001", "Coke", 3)));
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

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("selectInventoryProvider")
    public void selectInventoryTest(
            String testCaseName,
            List<Inventory> inputInventories,
            String selectedInventoryId,
            Class<? extends Exception> expectedException,
            Inventory expectedInventory
    ) {
        VendingMachine vendingMachine = new VendingMachine(inputInventories);
        if (expectedException == null) {
            Inventory selectedInventory = null;
            try {
                selectedInventory = vendingMachine.selectInventory(selectedInventoryId);
            } catch (Exception e) {
                fail(e);
            }
            assertEquals(expectedInventory, selectedInventory);
        } else {
            assertThrows(expectedException, () -> {
                vendingMachine.selectInventory(selectedInventoryId);
            });
        }
    }
}
