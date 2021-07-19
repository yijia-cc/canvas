package bank;

import bank.card.CreditCard;
import bank.card.DebitCard;
import exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public interface PaymentService {
    boolean verifyCreditCard(CreditCard creditCard) throws TimeoutException;

    boolean verifyDebitCard(DebitCard debitCard, int[] pin);

    boolean payWithCreditCard(CreditCard creditCard, BigDecimal amount) throws InsufficientFundException;

    boolean payWithDebitCard(DebitCard debitCard, int[] pin, BigDecimal amount);
}
