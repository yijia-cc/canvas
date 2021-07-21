package payment;

import exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public interface PaymentMethod {
    boolean isAuthorized() throws TimeoutException;

    boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException, TimeoutException;

}
