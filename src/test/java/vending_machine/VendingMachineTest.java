package vending_machine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static tools.Assertions.assertSameElements;

public class VendingMachineTest {
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

    @Test
    public void simpleConstructorTest() {
        List<Inventory> inventories = new ArrayList<>() {{
            new Inventory("001", "Coke", 3);
            new Inventory("002", "Pepsi", 3);
            new Inventory("003", "Kind", 2);
        }};
        new VendingMachine(inventories);
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
}
