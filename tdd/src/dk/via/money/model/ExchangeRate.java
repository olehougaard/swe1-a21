package dk.via.money.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ExchangeRate {
    private final String fromCurrency;
    private final String toCurrency;
    private final BigDecimal rate;

    public ExchangeRate(String fromCurrency, String toCurrency, BigDecimal rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExchangeRate)) return false;
        ExchangeRate other = (ExchangeRate) obj;
        return fromCurrency.equals(other.fromCurrency) && toCurrency.equals(other.toCurrency) && rate.equals(other.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromCurrency, toCurrency, rate);
    }
}
