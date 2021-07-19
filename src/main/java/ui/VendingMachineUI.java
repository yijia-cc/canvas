package ui;

import exceptions.NoPaymentMethodException;
import payment.PaymentMethod;
import vending_machine.Inventory;
import vending_machine.Item;

import java.util.List;

public interface VendingMachineUI {
    void displayInventories(List<Inventory> inventories);

    PaymentMethod requestPaymentMethod();

    String requestInventoryId();

    void displaySelectedInventory(Inventory selectedInventory);

    void displayPurchaseError(Exception e);

    void displayPurchasedItem(Item selectedInventory);

    void finishPayment() throws NoPaymentMethodException;

    void displayCancel();
}
