package vending_machine;

import payment.InsufficientFundException;
import payment.PaymentMethod;
import payment.UnauthorizedException;
import ui.VendingMachineUI;

public class VendingMachineAutomator {
    private final VendingMachine vendingMachine;
    private final VendingMachineUI vendingMachineUI;

    public VendingMachineAutomator(VendingMachineUI vendingMachineUI) {
        this.vendingMachineUI = vendingMachineUI;
        this.vendingMachine = new VendingMachine();
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
            PaymentMethod paymentMethod = vendingMachineUI.requestPaymentMethod();
            vendingMachine.usePaymentMethod(paymentMethod);

            String inventoryId = vendingMachineUI.requestInventoryId();
            Inventory selectedInventory = vendingMachine.selectInventory(inventoryId);
            vendingMachineUI.displaySelectedInventory(selectedInventory);

            Item item;

            try {
                item = vendingMachine.makePurchase();
            } catch (InsufficientFundException | UnauthorizedException e) {
                vendingMachineUI.displayPaymentError(e);
                vendingMachine.cancel();
                continue;
            }

            vendingMachineUI.displayPurchasedItem(item);
            vendingMachineUI.issueChange(paymentMethod);
            vendingMachine.reset();
        }
    }
}
