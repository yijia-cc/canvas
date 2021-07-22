package com.yijiacc.canvas.app.payment;

import com.yijiacc.canvas.app.exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public interface PaymentMethod {
    boolean isAuthorized() throws TimeoutException;

    boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException, TimeoutException;

}
