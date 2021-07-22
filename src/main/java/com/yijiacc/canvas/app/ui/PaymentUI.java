package com.yijiacc.canvas.app.ui;

import com.yijiacc.canvas.app.payment.PaymentMethod;

public interface PaymentUI {
    PaymentMethod requestPaymentMethod();

    void finishPayment();
}
