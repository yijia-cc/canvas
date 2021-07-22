package com.yijiacc.canvas.app.ui;

import com.yijiacc.canvas.app.exceptions.NoPaymentMethodException;
import com.yijiacc.canvas.app.payment.PaymentMethod;
import com.yijiacc.canvas.app.vending_machine.Inventory;
import com.yijiacc.canvas.app.vending_machine.Item;

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
