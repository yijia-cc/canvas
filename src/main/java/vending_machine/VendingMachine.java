package vending_machine;

import payment.*;

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

    public void selectInventory(String selectedInventoryId) throws InvalidInventoryIdException {
        if (!idToInventory.containsKey(selectedInventoryId)) {
            throw new InvalidInventoryIdException();
        }
        this.selectedInventory = idToInventory.get(selectedInventoryId);
    }

    public Item makePurchase() throws InsufficientFundException, TimeoutException {
        throw new UnsupportedOperationException();
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void reset() {
        throw new UnsupportedOperationException();
    }

    public PaymentMethod getPaymentMethod() {
        throw new UnsupportedOperationException();
    }

    public Inventory getSelectedInventory() {
        throw new UnsupportedOperationException();
    }
}
