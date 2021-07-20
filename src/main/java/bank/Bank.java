package bank;

import bank.account.Account;
import bank.account.AccountType;
import bank.alert.Alert;
import bank.authentication.Credential;
import bank.card.*;
import bank.transaction.Transaction;
import bank.user.*;
import cash.Cash;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Util.generateRandomNumber;

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
    private static final int ACCOUNT_ID_LENGTH = 10;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int USER_ID_LENGTH = 10;
    private static final int CVV_LENGTH = 3;
    private static final CardNetwork DEFAULT_CARD_NETWORK = CardNetwork.VISA;
    private static final String BANK_NAME = "Future Digital";

    // Key: accountId, Value: Account
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<String, BankCard> bankCards = new HashMap<>();

    private final IdGenerator userIdGenerator = new IdGenerator(0, USER_ID_LENGTH);
    private final IdGenerator accountIdGenerator =  new IdGenerator(0, ACCOUNT_ID_LENGTH);
    private final IdGenerator cardNumberGenerator = new IdGenerator(0, CARD_NUMBER_LENGTH);

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
    public DebitCard requestCreditCard(DebitCardRequest request) {
        int[] cardNumber = cardNumberGenerator.nextUniqueId();
        LocalDate today = LocalDate.now();
        Period cardValidPeriod = decideCardValidPeriod(request.account, BankCardType.CREDIT_CARD);
        LocalDate expiredAt = today.plus(cardValidPeriod);
        int[] cardVerificationValue = generateRandomNumber(CVV_LENGTH, 0, 9);

        return new DebitCard(
                request.cardHolderName,
                cardNumber,
                expiredAt,
                cardVerificationValue,
                DEFAULT_CARD_NETWORK,
                BANK_NAME,
                request.account.getAccountNumber());
    }

    @Override
    public CreditCard requestDebitCard(CreditCardRequest request) {
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
    public boolean verifyBankCard(BankCard bankCard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean payWithBankCard(BankCard bankCard, BigDecimal amount) {
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

    private Period decideCardValidPeriod(Account account, BankCardType bankCardType) {
        throw new UnsupportedOperationException();
    }
}
