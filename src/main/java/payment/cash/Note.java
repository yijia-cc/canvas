package payment.cash;

import java.math.BigDecimal;

public enum Note implements Denomination {
    ONE(new BigDecimal("1")),
    TWO(new BigDecimal("2")),
    FIVE(new BigDecimal("5")),
    TEN(new BigDecimal("10")),
    TWENTY(new BigDecimal("20")),
    FIFTY(new BigDecimal("50")),
    HUNDRED(new BigDecimal("100"));

    public final BigDecimal value;

    Note(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }
}
