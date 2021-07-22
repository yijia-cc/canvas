package com.yijiacc.canvas.app.bank;

import com.yijiacc.canvas.app.bank.account.Account;
import com.yijiacc.canvas.app.bank.user.Name;

final public class DebitCardRequest extends CreditCardRequest {
    int[] pin;

    DebitCardRequest(Account account, Name cardHolderName, int[] pin) {
        super(account, cardHolderName);
        this.pin = pin;
    }
}
