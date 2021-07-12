package payment;

public interface PaymentMethod {
    boolean pay(float amount) throws InsufficientFundException, UnauthorizedException;

}
