package payment;

import exceptions.InsufficientFundException;
import exceptions.PaymentTimeoutException;

public interface PaymentMethod {
    boolean isAuthorized() throws PaymentTimeoutException;

    boolean pay(float amount) throws InsufficientFundException, PaymentTimeoutException;
}
