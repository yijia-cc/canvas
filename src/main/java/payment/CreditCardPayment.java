package payment;

import bank.PaymentService;
import bank.card.CreditCard;
import exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public class CreditCardPayment implements PaymentMethod {

    private final PaymentService paymentService;
    private final CreditCard creditCard;

    public CreditCardPayment(PaymentService paymentService, CreditCard creditCard) {
        this.paymentService = paymentService;
        this.creditCard = creditCard;
    }

    @Override
    public boolean isAuthorized() throws TimeoutException {
        return paymentService.verifyCreditCard(creditCard);
    }

    @Override
    public boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException {
        return paymentService.payWithCreditCard(creditCard, totalAmountToPay);
    }
}
