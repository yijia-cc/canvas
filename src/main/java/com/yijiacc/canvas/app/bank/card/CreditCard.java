package com.yijiacc.canvas.app.bank.card;

import com.yijiacc.canvas.app.bank.user.Name;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreditCard extends BankCard {
    private BigDecimal creditLimit;

    public CreditCard(
            Name cardHolderName,
            BigInteger cardNumber,
            LocalDate validThrough,
            int cardVerificationValue,
            CardNetwork cardNetwork,
            String issuer,
            BigInteger accountNumber) {
        super(
                cardHolderName,
                cardNumber,
                validThrough,
                cardVerificationValue,
                cardNetwork,
                issuer,
                accountNumber);
    }

}
