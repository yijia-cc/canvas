package com.yijiacc.canvas.app.bank;

import com.yijiacc.canvas.app.bank.account.Account;
import com.yijiacc.canvas.app.bank.account.AccountType;
import com.yijiacc.canvas.app.bank.alert.Alert;
import com.yijiacc.canvas.app.bank.card.CreditCard;
import com.yijiacc.canvas.app.bank.card.DebitCard;
import com.yijiacc.canvas.app.bank.transaction.Transaction;
import com.yijiacc.canvas.app.bank.user.User;
import com.yijiacc.canvas.app.cash.Cash;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account openAccount(AccountType accountType);

    boolean closeAccount(Account account);

    List<Account> listAccounts(User user);

    BigDecimal showBalance(Account account);

    List<Transaction> viewTransactions(Account account);

    CreditCard requestCreditCard(CreditCardRequest request);

    DebitCard requestDebitCard(DebitCardRequest request);

    void setUpAlert(Account account, Alert alert);

    boolean deposit(Cash cash, Account account);

    Cash withdrawCash(BigDecimal amount, Account account);
}
