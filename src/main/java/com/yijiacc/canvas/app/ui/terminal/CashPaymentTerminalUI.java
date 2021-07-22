package com.yijiacc.canvas.app.ui.terminal;

import com.yijiacc.canvas.app.payment.CashPayment;
import com.yijiacc.canvas.app.payment.PaymentMethod;
import com.yijiacc.canvas.app.ui.PaymentUI;

import java.math.BigDecimal;

class CashPaymentTerminalUI implements PaymentUI {
    private CashPayment cashPayment;

    public void issueChange(BigDecimal totalAmountToPay) {
        System.out.println("Here is your change");

        cashPayment.issueChange();
    }

    @Override
    public PaymentMethod requestPaymentMethod() {
        return null;
    }

    @Override
    public void finishPayment() {

    }
}
