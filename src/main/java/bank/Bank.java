package bank;

import bank.account.Account;
import bank.account.AccountType;
import bank.alert.Alert;
import bank.authentication.Credential;
import bank.card.BankCard;
import bank.card.BankCardType;
import bank.card.CreditCard;
import bank.card.DebitCard;
import bank.transaction.Transaction;
import bank.user.*;
import cash.Cash;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yijia-cc
 * @version 07/18/2021
 * <p>
 * 1) Deposit money
 * - Cash
 * - Check
 * 2) Withdraw money
 * 3) Open account
 * 4) Close account
 * 5) Verify credit/debit card
 * 6) Issue credit/debit
 * 7) View transactions
 * 8) Transfer money between accounts
 * 9) Setup email & text alert when paying money
 */
public class Bank implements AuthenticationService, AccountService, PaymentService, TransferService {

    @Override
    public Account openAccount(AccountType accountType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean closeAccount(Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Account> listAccounts(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal showBalance(Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Transaction> viewTransactions(Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BankCard requestBankCard(Account account, BankCardType bankCardType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUpAlert(Account account, Alert alert) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deposit(Cash cash, Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Cash withdrawCash(BigDecimal amount, Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean registerUser(Documents documents, ContactInfo contactInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean confirmPhone(String verificationCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean confirmEmail(String verificationCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User verifyIdentity(Credential credential) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verifyUser(User user, GovernmentId governmentId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verifyCreditCard(CreditCard creditCard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verifyDebitCard(DebitCard debitCard, int[] pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean payWithCreditCard(CreditCard creditCard, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean payWithDebitCard(DebitCard debitCard, int[] pin, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean transferMoney(Account fromAccount, Account toAccount, BigDecimal amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean transferMoney(Account senderAccount, Recipient recipient, BigDecimal amount) {
        throw new UnsupportedOperationException();
    }
}
