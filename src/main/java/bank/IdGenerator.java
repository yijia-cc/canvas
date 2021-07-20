package bank;

public class IdGenerator {
    private final int minLength;
    private final int maxLength;

    private int currentId;

    public IdGenerator(int startFrom, int length) {
        this(startFrom, length, length);
    }

    public IdGenerator(int startFrom, int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.currentId = startFrom;
    }

    public int[] nextUniqueId() {
        throw new UnsupportedOperationException();
    }
}
