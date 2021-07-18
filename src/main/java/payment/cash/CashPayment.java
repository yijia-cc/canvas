package payment.cash;

import exceptions.InsufficientFundException;
import exceptions.PaymentTimeoutException;
import payment.PaymentMethod;

import java.math.BigDecimal;

public class CashPayment implements PaymentMethod {
    private BigDecimal balance;

    public CashPayment(Cash cash) {
        this.balance = cash.getTotalAmount();
    }

    @Override
    public boolean isAuthorized() throws PaymentTimeoutException {
        return true;
    }

    @Override
    public boolean pay(BigDecimal totalAmountToPay) throws InsufficientFundException, PaymentTimeoutException {
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
