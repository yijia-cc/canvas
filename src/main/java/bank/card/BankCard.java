package bank.card;

import bank.user.Name;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class BankCard {
    private final Name cardHolderName;
    private final int[] cardNumber;
    private final LocalDate validThrough;
    private final int[] cardVerificationValue;

    private final CardNetwork cardNetwork;
    private final String issuer;
    private final int[] accountNumber;
}
