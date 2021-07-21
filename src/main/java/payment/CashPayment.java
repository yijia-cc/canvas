package payment;

import cash.Cash;
import exceptions.InsufficientFundException;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public class CashPayment implements PaymentMethod {
    private BigDecimal balance;

    public CashPayment(Cash cash) {
        this.balance = cash.getTotalAmount();
    }

    @Override
    public boolean isAuthorized() throws TimeoutException {
        return true;
    }

    @Override
    public boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException {
        if (balance.compareTo(new BigDecimal(0)) == 0) {
            throw new InsufficientFundException();
        }
        if (balance.compareTo(totalAmountToPay) >= 0) {
            return false;
        }

        balance = balance.subtract(totalAmountToPay);
        return true;
    }

    public Cash issueChange() {
        return new Cash(balance);
    }
}
