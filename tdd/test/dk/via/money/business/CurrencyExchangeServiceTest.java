package dk.via.money.business;

import dk.via.money.model.ExchangeRate;
import dk.via.money.model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyExchangeServiceTest {
    private Money eur100;
    private Money usd120;
    private CurrencyExchangeService service;

    @BeforeEach
    public void setUp() {
        eur100 = new Money(BigDecimal.valueOf(100), "EUR");
        usd120 = new Money(BigDecimal.valueOf(120), "USD");
        ExchangeRateDataFake data = new ExchangeRateDataFake();
        data.addExchangeRate(new ExchangeRate("EUR", "USD", BigDecimal.valueOf(120)));
        service = new CurrencyExchangeService(data);
    }

    @Test
    public void hundredEuroIsHundredFiftyDollars() throws SQLException {
        Money converted = service.exchange(eur100, "USD");
        assertEquals(usd120, converted);
    }
}
