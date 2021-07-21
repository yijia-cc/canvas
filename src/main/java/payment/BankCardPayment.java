package payment;

import bank.PaymentService;
import bank.card.BankCard;
import exceptions.InsufficientFundException;

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
