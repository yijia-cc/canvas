package bank.card;

import bank.user.Name;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DebitCard extends BankCard {
    private int[] pin;

    public DebitCard(
            Name cardHolderName,
            int[] cardNumber,
            LocalDate validThrough,
            int[] cardVerificationValue,
            CardNetwork cardNetwork,
            String issuer,
            int[] accountNumber) {
        super(cardHolderName, cardNumber, validThrough, cardVerificationValue, cardNetwork, issuer, accountNumber);
    }
}
