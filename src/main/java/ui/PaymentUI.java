package ui;

import payment.PaymentMethod;

public interface PaymentUI {
    PaymentMethod requestPaymentMethod();

    void finishPayment();
}
