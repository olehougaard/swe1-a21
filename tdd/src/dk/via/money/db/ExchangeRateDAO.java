package dk.via.money.db;

import dk.via.money.model.ExchangeRate;

import java.math.BigDecimal;
import java.sql.*;

public class ExchangeRateDAO implements ExchangeRateData {
    private static ExchangeRateDAO instance;

    private ExchangeRateDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized ExchangeRateDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new ExchangeRateDAO();
        }
        return instance;
    }

    @Override
    public ExchangeRate getExchangeRate(String fromCurrency, String toCurrency) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password")) {
            PreparedStatement statement = connection.prepareStatement("SELECT rate FROM Exchange_Rate WHERE from_currency = ?, to_currency = ?");
            statement.setString(1, fromCurrency);
            statement.setString(2, toCurrency);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new ExchangeRate(fromCurrency, toCurrency, (BigDecimal) rs.getObject(1));
            } else {
                return null;
            }
        }
    }
}
