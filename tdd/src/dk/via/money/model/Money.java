package dk.via.money.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {
    private static final BigDecimal ZERO_AMOUNT = new BigDecimal("0.00");
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        if (amount.equals(ZERO_AMOUNT) && other.amount.equals(ZERO_AMOUNT)) return true;
        return currency.equals(other.currency) && amount.equals(other.amount);
    }

    public Money add(Money other) {
        if (!currency.equals(other.currency)) throw new IllegalArgumentException();
        return new Money(amount.add(other.amount), other.getCurrency());
    }

    public Money multiply(BigDecimal factor) {
        return new Money(amount.multiply(factor), currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public Money divide(BigDecimal denominator) {
        return new Money(amount.divide(denominator, RoundingMode.HALF_UP), currency);
    }
}
