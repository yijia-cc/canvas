package vending_machine;

import payment.InsufficientFundException;
import payment.PaymentMethod;
import payment.TimeoutException;
import payment.UnauthorizedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private final Map<String, Inventory> idToInventory;
    private Inventory selectedInventory;
    private PaymentMethod providedPaymentMethod;

    public VendingMachine(List<Inventory> initialInventories) {
        if (initialInventories == null) {
            throw new IllegalArgumentException("initialInventories cannot be null");
        }

        idToInventory = new HashMap<>();
        for (Inventory initialInventory : initialInventories) {
            idToInventory.put(initialInventory.getId(), initialInventory);
        }
    }

    /**
     * List all the inventories in the vending machine.
     * Each inventory contains a list of items which users can purchase.
     * @return All inventories in the vending machine.
     */
    public List<Inventory> listInventories() {
        return new ArrayList<>(idToInventory.values());
    }

    public void addItem(String inventoryId, int amount) {
        throw new UnsupportedOperationException();
    }

    public void removeItem() {
        throw new UnsupportedOperationException();
    }

    public void usePaymentMethod(PaymentMethod paymentMethod) throws UnauthorizedException, TimeoutException {
        if (!paymentMethod.isAuthorized()) {
            throw new UnauthorizedException();
        }
        this.providedPaymentMethod = paymentMethod;
    }

    public Inventory selectInventory(String selectedInventoryId) {
        throw new UnsupportedOperationException();
    }

    public Item makePurchase() throws InsufficientFundException, TimeoutException {
        throw new UnsupportedOperationException();
    }

    public void cancelTransaction() {
        selectedInventory = null;
        providedPaymentMethod = null;
    }

    public void finishTransaction() {
        cancelTransaction();
    }

    public PaymentMethod getPaymentMethod() {
        throw new UnsupportedOperationException();
    }
}
