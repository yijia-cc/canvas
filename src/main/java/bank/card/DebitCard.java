package bank.card;

import bank.user.Name;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DebitCard extends BankCard {
    private int[] pin;

    public DebitCard(
            Name cardHolderName,
            BigInteger cardNumber,
            LocalDate validThrough,
            int cardVerificationValue,
            CardNetwork cardNetwork,
            String issuer,
            BigInteger accountNumber) {
        super(cardHolderName, cardNumber, validThrough, cardVerificationValue, cardNetwork, issuer, accountNumber);
    }
}
