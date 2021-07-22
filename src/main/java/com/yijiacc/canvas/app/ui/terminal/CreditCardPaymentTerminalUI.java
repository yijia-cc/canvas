package com.yijiacc.canvas.app.ui.terminal;

import com.yijiacc.canvas.app.payment.PaymentMethod;
import com.yijiacc.canvas.app.ui.PaymentUI;

public class CreditCardPaymentTerminalUI implements PaymentUI {
    @Override
    public PaymentMethod requestPaymentMethod() {
        return null;
    }

    @Override
    public void finishPayment() {

    }
}
