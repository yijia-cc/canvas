package bank.card;

import bank.user.Name;
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CreditCard that = (CreditCard) o;
//        return Objects.equals(creditLimit, that.creditLimit);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(creditLimit);
//    }
}
