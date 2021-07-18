package payment;

import exceptions.InsufficientFundException;
import exceptions.PaymentTimeoutException;

import java.math.BigDecimal;

public class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean isAuthorized() throws PaymentTimeoutException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException {
        return false;
    }
}
