package retry;

@FunctionalInterface
public interface RetryCallback {
    void execute() throws Exception;
}
