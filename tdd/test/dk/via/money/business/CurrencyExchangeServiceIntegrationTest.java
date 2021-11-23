package dk.via.money.business;

import dk.via.money.db.ExchangeRateDAO;
import dk.via.money.model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyExchangeServiceIntegrationTest {
    private Money eur100;
    private Money usd120;
    private CurrencyExchangeService service;

    @BeforeEach
    public void setUp() throws SQLException {
        eur100 = new Money(BigDecimal.valueOf(100), "USD");
        usd120 = new Money(BigDecimal.valueOf(120), "EUR");
        service = new CurrencyExchangeService(ExchangeRateDAO.getInstance());
    }

    @Test
    public void hundredEuroIsHundredFiftyDollars() throws SQLException {
        Money converted = service.exchange(eur100, "USD");
        assertEquals(usd120, converted);
    }
}
