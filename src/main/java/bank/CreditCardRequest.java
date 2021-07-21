package bank;

import bank.account.Account;
import bank.user.Name;

public class CreditCardRequest {
    protected Account account;
    protected Name cardHolderName;

    CreditCardRequest(Account account, Name cardHolderName) {
        this.account = account;
        this.cardHolderName = cardHolderName;
    }
}
