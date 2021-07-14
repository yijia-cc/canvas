package payment;

public class CreditCardPayment implements PaymentMethod{

    @Override
    public boolean isAuthorized() throws TimeoutException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean pay(float amount) throws InsufficientFundException {
        return false;
    }
}
