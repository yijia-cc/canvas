package vending_machine;

import payment.InsufficientFundException;
import payment.PaymentMethod;
import payment.UnauthorizedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private Map<String, Inventory> idToInventory = new HashMap<>();
    private Inventory selectedInventory;
    private PaymentMethod providedPaymentMethod;

    public VendingMachine() {
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public List<Inventory> listInventories() {
        throw new UnsupportedOperationException();
    }

    public Inventory selectInventory(String selectedInventory) {
        throw new UnsupportedOperationException();
    }

    public void usePaymentMethod(PaymentMethod paymentMethod) {
        throw new UnsupportedOperationException();
    }

    public Item makePurchase() throws InsufficientFundException, UnauthorizedException {
        throw new UnsupportedOperationException();
    }

    public void reset() {
        throw new UnsupportedOperationException();
    }
}
