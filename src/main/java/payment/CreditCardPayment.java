package payment;

import exceptions.InsufficientFundException;
import exceptions.PaymentTimeoutException;

public class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean isAuthorized() throws PaymentTimeoutException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean pay(float amount) throws InsufficientFundException {
        return false;
    }
}
