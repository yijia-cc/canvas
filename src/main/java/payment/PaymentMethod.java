package payment;

import exceptions.InsufficientFundException;
import exceptions.PaymentTimeoutException;

import java.math.BigDecimal;

public interface PaymentMethod {
    boolean isAuthorized() throws PaymentTimeoutException;

    boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException, PaymentTimeoutException;

}
