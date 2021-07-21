package bank;

import bank.account.Account;
import bank.user.Recipient;

import java.math.BigDecimal;

public interface TransferService {
    boolean transferMoney(Account fromAccount, Account toAccount, BigDecimal amount);

    boolean transferMoney(Account senderAccount, Recipient recipient, BigDecimal amount);
}
