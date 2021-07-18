package vending_machine;

import exceptions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static tools.Assertions.assertSameElements;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VendingMachineTest {

    @Mock
    static PaymentMethod stubPaymentMethod;

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

    @ParameterizedTest(name = "{displayName} : {0}")
    @MethodSource("purchaseProvider")
    public void purchaseTest(
            String testCaseName,
            List<Inventory> inputInventories,
            String inventoryId,
            boolean usePaymentMethod,
            Class<? extends Exception> paymentMethodPayException,
            Class<? extends Exception> expectedException,
            Item expectedItem
    ) {

        VendingMachine vendingMachine = new VendingMachine(inputInventories);

        configurePaymentMethod(vendingMachine, usePaymentMethod, paymentMethodPayException);
        configureInventoryId(vendingMachine, inventoryId);

        if (expectedException == null) {
            try {
                Item item = vendingMachine.makePurchase();
                assertEquals(expectedItem, item);
            } catch (Exception e) {
                fail(e);
            }
        } else {
            assertThrows(expectedException, vendingMachine::makePurchase);
        }
    }

    private static void configurePaymentMethod(
            VendingMachine vendingMachine,
            boolean usePaymentMethod,
            Class<? extends Exception> paymentMethodPayException) {
        if (!usePaymentMethod) {
            return;
        }

        try {
            when(stubPaymentMethod.isAuthorized())
                    .thenReturn(true);

            if (paymentMethodPayException == null) {
                when(stubPaymentMethod.pay(any()))
                        .thenReturn(true);
            } else {
                when(stubPaymentMethod.pay(any()))
                        .thenThrow(paymentMethodPayException);
            }
        } catch (Exception e) {
            fail(e);
        }

        try {
            vendingMachine.usePaymentMethod(stubPaymentMethod);
        } catch (Exception e) {
            fail(e);
        }
    }

    private static void configureInventoryId(VendingMachine vendingMachine, String inventoryId) {
        if (inventoryId == null) {
            return;
        }

        try {
            vendingMachine.selectInventory(inventoryId);
        } catch (Exception e) {
            fail(e);
        }
    }

    private static Stream<Arguments> listInventoriesProvider() {
        return Stream.of(
                arguments("No Inventory", new ArrayList<>(), new ArrayList<>()),
                arguments(
                        "Has Inventories",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }}));
    }

    private static Stream<Arguments> simpleConstructorProvider() {
        return Stream.of(
                arguments("Inventory is null", null, IllegalArgumentException.class),
                arguments("No Inventory", new ArrayList<>(), null),
                arguments(
                        "Has Inventories",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        null)
        );
    }

    private static Stream<Arguments> usePaymentProvider() {
        return Stream.of(
                arguments("Unauthorized",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        false,
                        null,
                        UnauthorizedException.class),
                arguments("Authorization timeout",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        false,
                        PaymentTimeoutException.class,
                        PaymentTimeoutException.class),
                arguments(
                        "Authorized",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        true,
                        null,
                        null));
    }

    private static Stream<Arguments> selectInventoryProvider() {
        return Stream.of(
                arguments("No inventory",
                        new ArrayList<>() {
                        },
                        "004",
                        InvalidInventoryIdException.class,
                        null),
                arguments("Inventory ID not found",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        "004",
                        InvalidInventoryIdException.class,
                        null),
                arguments("Inventory ID found",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        "001",
                        null,
                        new Inventory("001", "Coke", new BigDecimal("3"))));
    }

    private static Stream<Arguments> purchaseProvider() {
        return Stream.of(
                arguments(
                        "No payment method provided",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        "003",
                        false,
                        null,
                        NoPaymentMethodException.class,
                        null
                ),
                arguments(
                        "No inventory selected",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }} /* inputInventories */,
                        null /* inventoryId */,
                        true /* usePaymentMethod */,
                        null /* paymentMethodPayException */,
                        NoInventorySelectedException.class /* expectedException */,
                        null /* expectedItem */),
                arguments(
                        "Inventory out of stock",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        "001",
                        true,
                        null,
                        InsufficientInventoryException.class,
                        null),
                arguments(
                        "Insufficient fund for item",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        "001",
                        true,
                        InsufficientFundException.class,
                        InsufficientFundException.class,
                        null
                ),
                arguments(
                        "Payment time out",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3")));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        "001",
                        true,
                        PaymentTimeoutException.class,
                        PaymentTimeoutException.class,
                        null
                ),
                arguments(
                        "Purchase item successfully",
                        new ArrayList<>() {{
                            add(new Inventory("001", "Coke", new BigDecimal("3")));
                            add(new Inventory("002", "Pepsi", new BigDecimal("3"),
                                    new ArrayList<>() {{
                                        add(new Item(1, "PepsiZeroSugar"));
                                        add(new Item(2, "PepsiZeroSugar"));
                                    }}));
                            add(new Inventory("003", "Kind", new BigDecimal("2")));
                        }},
                        "002",
                        true,
                        null,
                        null,
                        new Item(2, "PepsiZeroSugar")
                )
        );
    }
}
