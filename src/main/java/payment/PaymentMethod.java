package payment;

public interface PaymentMethod {
    boolean isAuthorized() throws TimeoutException;
    boolean pay(float amount) throws InsufficientFundException, TimeoutException;

}
