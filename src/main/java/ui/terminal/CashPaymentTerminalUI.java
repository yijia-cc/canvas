package ui.terminal;

import payment.CashPayment;
import payment.PaymentMethod;
import ui.PaymentUI;

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
