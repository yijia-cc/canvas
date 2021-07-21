package bank.card;

import bank.user.Name;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankCard {
    private final Name cardHolderName;
    private final BigInteger cardNumber;
    private final LocalDate validThrough;
    private final int cardVerificationValue;

    private final CardNetwork cardNetwork;
    private final String issuer;
    private final BigInteger accountNumber;
}


