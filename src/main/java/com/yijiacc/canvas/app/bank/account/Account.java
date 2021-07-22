package com.yijiacc.canvas.app.bank.account;

import lombok.Value;

import java.math.BigInteger;

@Value
public class Account {
    BigInteger accountNumber;

    public Account (BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }
}
