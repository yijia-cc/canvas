package com.yijiacc.canvas.app.bank;

import com.yijiacc.canvas.app.bank.card.BankCard;
import com.yijiacc.canvas.app.exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public interface PaymentService {
    boolean verifyBankCard(BankCard bankCard) throws TimeoutException;
    boolean payWithBankCard(BankCard bankCard, BigDecimal amount) throws InsufficientFundException;
}
