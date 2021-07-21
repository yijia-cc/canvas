package bank;

import bank.card.BankCard;
import exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public interface PaymentService {
    boolean verifyBankCard(BankCard bankCard) throws TimeoutException;
    boolean payWithBankCard(BankCard bankCard, BigDecimal amount) throws InsufficientFundException;
}
