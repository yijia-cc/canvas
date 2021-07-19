package ui.terminal;

import exceptions.NoPaymentMethodException;
import payment.PaymentMethod;
import ui.PaymentUI;
import ui.VendingMachineUI;
import vending_machine.Inventory;
import vending_machine.Item;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class VendingMachineTerminalUI implements VendingMachineUI {
    private PaymentUI paymentUI;

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
        System.out.println("Please choose your payment method");
        System.out.println("Cash[1], Credit Card[2], Cancel[9]");
        Scanner scanner = new Scanner(System.in);
        int actionCode = scanner.nextInt();

        if (actionCode == 9) {
            throw new CancellationException();
        }

        paymentUI = makePaymentUI(actionCode);
        return paymentUI.requestPaymentMethod();
    }

    private static PaymentUI makePaymentUI(int actionCode) {
        switch (actionCode) {
            case 2:
                return new CreditCardPaymentTerminalUI();
            default:
                return new CashPaymentTerminalUI();
        }
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
    public void displayPurchaseError(Exception e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void displayPurchasedItem(Item selectedInventory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void finishPayment() throws NoPaymentMethodException {
        if (paymentUI == null) {
            throw new NoPaymentMethodException();
        }
        paymentUI.finishPayment();
    }

    @Override
    public void displayCancel() {

    }
}

