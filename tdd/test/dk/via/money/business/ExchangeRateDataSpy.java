package dk.via.money.business;

import dk.via.money.db.ExchangeRateData;
import dk.via.money.model.ExchangeRate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExchangeRateDataSpy implements ExchangeRateData {
    public ArrayList<String> fromCurrencies = new ArrayList<>();
    public ArrayList<String> toCurrencies = new ArrayList<>();

    public ExchangeRateDataSpy() {
    }

    @Override
    public ExchangeRate getExchangeRate(String fromCurrency, String toCurrency) {
        fromCurrencies.add(fromCurrency);
        toCurrencies.add(toCurrency);
        return new ExchangeRate(fromCurrency, toCurrency, BigDecimal.valueOf(120));
    }
}
