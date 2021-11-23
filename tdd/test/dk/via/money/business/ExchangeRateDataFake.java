package dk.via.money.business;

import dk.via.money.db.ExchangeRateData;
import dk.via.money.model.ExchangeRate;

import java.util.ArrayList;

public class ExchangeRateDataFake implements ExchangeRateData {
    private final ArrayList<ExchangeRate> rates;

    public ExchangeRateDataFake() {
        rates = new ArrayList<>();
    }

    public void addExchangeRate(ExchangeRate rate) {
        rates.add(rate);
    }

    @Override
    public ExchangeRate getExchangeRate(String fromCurrency, String toCurrency) {
        for (ExchangeRate rate: rates) {
            if (fromCurrency.equals(rate.getFromCurrency()) && toCurrency.equals(rate.getToCurrency())) {
                return rate;
            }
        }
        return null;
    }
}
