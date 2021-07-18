package ui.terminal;

import payment.PaymentMethod;
import ui.PaymentUI;

public class CreditCardPaymentTerminalUI implements PaymentUI {
    @Override
    public PaymentMethod requestPaymentMethod() {
        return null;
    }

    @Override
    public void finishPayment() {

    }
}
