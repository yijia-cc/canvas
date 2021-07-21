package cash;

import java.math.BigDecimal;

public enum Coin implements Denomination {
    PENNY(new BigDecimal("0.01")),
    NICKEL(new BigDecimal("0.05")),
    DIME(new BigDecimal("0.1")),
    QUARTER(new BigDecimal("0.25")),
    DOLLAR(new BigDecimal("1"));

    public final BigDecimal value;

    Coin(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

}
