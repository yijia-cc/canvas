package com.yijiacc.canvas.app.bank;

import com.yijiacc.canvas.app.bank.account.Account;
import com.yijiacc.canvas.app.bank.user.Recipient;

import java.math.BigDecimal;

public interface TransferService {
    boolean transferMoney(Account fromAccount, Account toAccount, BigDecimal amount);

    boolean transferMoney(Account senderAccount, Recipient recipient, BigDecimal amount);
}
