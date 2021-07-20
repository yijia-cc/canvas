package bank.card;

import bank.user.Name;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreditCard extends BankCard {
    private BigDecimal creditLimit;

    public CreditCard(
            Name cardHolderName,
            int[] cardNumber,
            LocalDate validThrough,
            int[] cardVerificationValue,
            CardNetwork cardNetwork,
            String issuer,
            int[] accountNumber) {
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
