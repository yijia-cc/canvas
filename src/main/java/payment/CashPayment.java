package payment;

public class CashPayment implements PaymentMethod {
    private float availableCash;

    public CashPayment (float availableCash) {
        this.availableCash = availableCash;
    }

    @Override
    public boolean pay(float amount) {
        throw new UnsupportedOperationException();
    }
}
