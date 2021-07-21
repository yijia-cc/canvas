package bank;

import bank.account.Account;
import bank.account.AccountType;
import bank.alert.Alert;
import bank.authentication.Credential;
import bank.card.*;
import bank.transaction.Transaction;
import bank.user.*;
import cash.Cash;
import id.IdGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static util.Util.generateRandomNumbers;

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

    private final Random random;
    private final Clock clock;

    // Key: accountId, Value: Account
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<String, BankCard> bankCards = new HashMap<>();

    private final IdGenerator userIdGenerator = new IdGenerator(
            new BigInteger(String.valueOf((long) Math.pow(10, USER_ID_LENGTH - 1))),
            USER_ID_LENGTH);
    private final IdGenerator accountIdGenerator =  new IdGenerator(
            new BigInteger(String.valueOf((long) Math.pow(10, ACCOUNT_ID_LENGTH - 1))),
            ACCOUNT_ID_LENGTH);
    private final IdGenerator cardNumberGenerator = new IdGenerator(
            new BigInteger(String.valueOf((long) Math.pow(10, CARD_NUMBER_LENGTH - 1))),
            CARD_NUMBER_LENGTH);

    Bank(Random random, Clock clock) {
        this.random = random;
        this.clock = clock;
    }

    public Bank() {
        this.random = new Random();
        this.clock = Clock.systemDefaultZone();
    }

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
    public CreditCard requestCreditCard(CreditCardRequest request) {
        if (request == null || 
            request.account == null || 
            request.cardHolderName == null) {
            throw new IllegalArgumentException();
        }

        BigInteger cardNumber = cardNumberGenerator.nextUniqueId();
        LocalDate today = LocalDate.now(clock);
        Period cardValidPeriod = decideCardValidPeriod(request.account, BankCardType.CREDIT_CARD);
        LocalDate expiredAt = today.plus(cardValidPeriod);
        int cardVerificationValue = generateRandomNumbers(random, CVV_LENGTH);

        return new CreditCard(
                request.cardHolderName,
                cardNumber,
                expiredAt,
                cardVerificationValue,
                DEFAULT_CARD_NETWORK,
                BANK_NAME,
                request.account.getAccountNumber());
    }

    @Override
    public DebitCard requestDebitCard(DebitCardRequest request) {
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
        return Period.of(3,0,0);
    }
}
