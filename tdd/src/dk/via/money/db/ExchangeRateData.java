package dk.via.money.db;

import dk.via.money.model.ExchangeRate;

import java.sql.SQLException;

public interface ExchangeRateData {
    ExchangeRate getExchangeRate(String fromCurrency, String toCurrency) throws SQLException;
}
