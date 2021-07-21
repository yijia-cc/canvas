package bank;

import bank.account.Account;
import bank.user.Name;

final public class DebitCardRequest extends CreditCardRequest {
    int[] pin;

    DebitCardRequest(Account account, Name cardHolderName, int[] pin) {
        super(account, cardHolderName);
        this.pin = pin;
    }
}
