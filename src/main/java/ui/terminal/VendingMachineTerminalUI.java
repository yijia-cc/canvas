package ui.terminal;

import payment.PaymentMethod;
import ui.VendingMachineUI;
import vending_machine.Inventory;
import vending_machine.Item;

import java.util.List;

public class VendingMachineTerminalUI implements VendingMachineUI {
    private final PaymentTerminalUI paymentTerminalUI = new PaymentTerminalUI();

    @Override
    public void displayInventories(List<Inventory> inventories) {
        System.out.println("*********************************************");
        System.out.println("    WELCOME TO THE VENDING MACHINE           ");
        System.out.println("*********************************************");
        System.out.println("             Items available:                ");
        System.out.println();
        for (Inventory inventory : inventories) {
            if (inventory != null) {
                System.out.printf("     %s, %s - Price: %s.%n", inventory.getId(), inventory.getLabel(), inventory.getPrice());
            }
        }
        System.out.println();
    }

    @Override
    public PaymentMethod requestPaymentMethod() {
        return paymentTerminalUI.requestPaymentMethod();
    }

    @Override
    public String requestInventoryId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void displaySelectedInventory(Inventory selectedInventory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void displayPaymentError(Exception e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void displayPurchasedItem(Item selectedInventory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void issueChange(PaymentMethod paymentMethod) {
        throw new UnsupportedOperationException();
    }
}

