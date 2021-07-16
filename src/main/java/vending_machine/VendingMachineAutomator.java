package vending_machine;

import exceptions.*;
import payment.PaymentMethod;
import retry.RetryStrategy;
import ui.VendingMachineUI;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class VendingMachineAutomator {
    private static final int PAYMENT_RETRY_LIMIT = 3;
    private static final int INVENTORY_ID_RETRY_LIMIT = 3;

    private final VendingMachine vendingMachine;
    private final VendingMachineUI vendingMachineUI;

    public VendingMachineAutomator(VendingMachineUI vendingMachineUI, List<Inventory> initialInventories) {
        this.vendingMachineUI = vendingMachineUI;
        this.vendingMachine = new VendingMachine(initialInventories);
    }

    public void run() {
        /**
         * Requirements:
         * 1. user purchase items multiple times
         * 2. user can cancel transaction at any time
         * 3. purchase item
         *      a) user can view what items are available
         *      b) user pay first (provide fund), enter code, then confirm to get the item
         * 4. terminate condition - user cancel transaction
         * 5. a new user can start a different transaction.
         * 6. the machine can be powered on and powered off
         */
        // process multiple transactions
        while (true) {
            vendingMachineUI.displayInventories(vendingMachine.listInventories());

            boolean succeed = RetryStrategy.instant(() -> {
                PaymentMethod paymentMethod = vendingMachineUI.requestPaymentMethod();
                vendingMachine.usePaymentMethod(paymentMethod);
            }, PAYMENT_RETRY_LIMIT);
            if (!succeed) {
                cancelTransaction();
                continue;
            }

            AtomicReference<Inventory> inventory = new AtomicReference<>();

            succeed = RetryStrategy.instant(() -> {
                String inventoryId = vendingMachineUI.requestInventoryId();
                inventory.set(vendingMachine.selectInventory(inventoryId));
            }, INVENTORY_ID_RETRY_LIMIT);
            if (!succeed) {
                cancelTransaction();
                continue;
            }

            vendingMachineUI.displaySelectedInventory(inventory.get());

            Item item;
            try {
                item = vendingMachine.makePurchase();
            } catch (InsufficientFundException |
                    PaymentTimeoutException |
                    InsufficientInventoryException |
                    NoInventorySelectedException |
                    NoPaymentMethodException e) {
                vendingMachineUI.displayPaymentError(e);
                cancelTransaction();
                continue;
            }

            vendingMachineUI.displayPurchasedItem(item);
            vendingMachineUI.issueChange(vendingMachine.getPaymentMethod());
            vendingMachine.finishTransaction();
        }
    }

    private void cancelTransaction() {
        vendingMachineUI.displayCancel();
        vendingMachine.cancelTransaction();
    }
}
