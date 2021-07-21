package id;

import java.math.BigInteger;

public class IdGenerator {
    private final int minLength;
    private final int maxLength;

    private BigInteger currentId;

    public IdGenerator(BigInteger startFrom, int length) {
        this(startFrom, length, length);
    }

    public IdGenerator(BigInteger startFrom, int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.currentId = startFrom;
    }

    public BigInteger nextUniqueId() {
        currentId = currentId.add(BigInteger.ONE);
        return currentId;
    }
}
