package com.yijiacc.canvas.app.bank;

import com.yijiacc.canvas.app.bank.account.Account;
import com.yijiacc.canvas.app.bank.user.Name;

public class CreditCardRequest {
    protected Account account;
    protected Name cardHolderName;

    CreditCardRequest(Account account, Name cardHolderName) {
        this.account = account;
        this.cardHolderName = cardHolderName;
    }
}
