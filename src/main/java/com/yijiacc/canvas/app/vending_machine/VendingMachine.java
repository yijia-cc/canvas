package com.yijiacc.canvas.app.vending_machine;

import com.yijiacc.canvas.app.exceptions.*;
import com.yijiacc.canvas.app.payment.PaymentMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class VendingMachine {
    private final Map<String, Inventory> idToInventory;
    private Inventory selectedInventory;
    private PaymentMethod providedPaymentMethod;

    public VendingMachine(List<Inventory> initialInventories) {
        if (initialInventories == null) {
            throw new IllegalArgumentException("initialInventories cannot be null");
        }

        idToInventory = new HashMap<>();
        for (Inventory inventory : initialInventories) {
            idToInventory.put(inventory.getId(), inventory);
        }
    }

    /**
     * List all the inventories in the vending machine.
     * Each inventory contains a list of items which users can purchase.
     *
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

    public Inventory selectInventory(String selectedInventoryId) throws InvalidInventoryIdException {
        if (!idToInventory.containsKey(selectedInventoryId)) {
            throw new InvalidInventoryIdException();
        }
        this.selectedInventory = idToInventory.get(selectedInventoryId);
        return selectedInventory;
    }

    public Item makePurchase() throws
            InsufficientFundException,
            TimeoutException,
            InsufficientInventoryException,
            NoInventorySelectedException,
            NoPaymentMethodException {
        if (selectedInventory == null) {
            throw new NoInventorySelectedException();
        }
        if (providedPaymentMethod == null) {
            throw new NoPaymentMethodException();
        }
        providedPaymentMethod.pay(selectedInventory.getPrice());
        return selectedInventory.removeItem();
    }

    public void finishTransaction() {
        cancelTransaction();
    }

    public void cancelTransaction() {
        selectedInventory = null;
        providedPaymentMethod = null;
    }

    public PaymentMethod getPaymentMethod() {
        return providedPaymentMethod;
    }
}
