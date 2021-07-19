package bank;

import bank.account.Account;
import bank.account.AccountType;
import bank.alert.Alert;
import bank.card.BankCard;
import bank.card.BankCardType;
import bank.transaction.Transaction;
import bank.user.User;
import cash.Cash;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account openAccount(AccountType accountType);

    boolean closeAccount(Account account);

    List<Account> listAccounts(User user);

    BigDecimal showBalance(Account account);

    List<Transaction> viewTransactions(Account account);

    BankCard requestBankCard(Account account, BankCardType bankCardType);

    void setUpAlert(Account account, Alert alert);

    boolean deposit(Cash cash, Account account);

    Cash withdrawCash(BigDecimal amount, Account account);
}
