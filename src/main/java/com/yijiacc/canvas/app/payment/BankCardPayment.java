package com.yijiacc.canvas.app.payment;

import com.yijiacc.canvas.app.bank.PaymentService;
import com.yijiacc.canvas.app.bank.card.BankCard;
import com.yijiacc.canvas.app.exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public class BankCardPayment implements PaymentMethod {
    private final PaymentService paymentService;
    private final BankCard bankCard;

    public BankCardPayment(PaymentService paymentService, BankCard bankCard) {
        this.paymentService = paymentService;
        this.bankCard = bankCard;
    }

    @Override
    public boolean isAuthorized() throws TimeoutException {
        return paymentService.verifyBankCard(bankCard);
    }

    @Override
    public boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException {
        return paymentService.payWithBankCard(bankCard, totalAmountToPay);
    }
}
