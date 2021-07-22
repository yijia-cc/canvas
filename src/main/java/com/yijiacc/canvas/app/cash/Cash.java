package com.yijiacc.canvas.app.cash;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.yijiacc.canvas.app.util.Util.areCloseEnough;

public class Cash {
    private static final BigDecimal ONE_CENT = new BigDecimal("0.01");
    private static final Denomination[] denominations = new Denomination[]{
            Note.HUNDRED,
            Note.FIFTY,
            Note.TWENTY,
            Note.TEN,
            Note.FIVE,
            Note.TWO,
            Note.ONE,
            Coin.DOLLAR,
            Coin.QUARTER,
            Coin.DIME,
            Coin.NICKEL,
            Coin.PENNY
    };
    private final Map<Denomination, Integer> cash = new HashMap<>();

    public Cash(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("amount cannot be negative");
        }

        initCash(amount);

    }

    Cash(Map<Denomination, Integer> cash) {
        this.cash.putAll(cash);
    }

    public void addCash(Denomination denomination, int count) {
        cash.put(denomination, count);
    }

    public Map<Denomination, Integer> getCash() {
        return cash;
    }

    public BigDecimal getTotalAmount() {
        return getValueSum(cash);
    }

    private static BigDecimal getValueSum(Map<Denomination, Integer> faceValueCount) {
        if (faceValueCount == null) {
            return new BigDecimal(0);
        }

        BigDecimal res = new BigDecimal(0);
        for (Map.Entry<Denomination, Integer> entry : faceValueCount.entrySet()) {
            Denomination denomination = entry.getKey();
            res = res.add(denomination.getValue().multiply(new BigDecimal(entry.getValue())));
        }
        return res;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(cash);
        result = 31 * result + Arrays.hashCode(denominations);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cash cash1 = (Cash) o;
        return Objects.equals(cash, cash1.cash);
    }

    private static List<Integer> countCash(BigDecimal amount, List<Integer> cashCounts, int currDominationIndex, int totalCount) {
        if (currDominationIndex == denominations.length) {
            if (areCloseEnough(amount, new BigDecimal(0), ONE_CENT, false)) {
                return new ArrayList<>(cashCounts);
            }
        }

        BigDecimal domination = denominations[currDominationIndex].getValue();
        int maxCount = amount.divide(domination, RoundingMode.DOWN).intValue();
        for (int count = maxCount; count >= 0; count--) {
            cashCounts.add(count);
            List<Integer> solution = countCash(amount.subtract(domination.multiply(new BigDecimal(count))),
                    cashCounts,
                    currDominationIndex + 1,
                    totalCount + count);
            cashCounts.remove(cashCounts.size() - 1);
            if (solution != null) {
                return solution;
            }
        }
        return null;
    }

    private void initCash(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal(0)) == 0) {
            return;
        }

        List<Integer> minCashCounts = countCash(amount, new ArrayList<>(), 0, 0);
        if (minCashCounts == null) {
            throw new IllegalArgumentException("amount cannot be converted to cash");
        }

        for (int i = 0; i < minCashCounts.size(); i++) {
            if (minCashCounts.get(i) == 0) {
                continue;
            }
            cash.put(denominations[i], minCashCounts.get(i));
        }
    }
}

