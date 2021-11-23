package dk.via.money.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRateTest {

    private ExchangeRate rate;

    @BeforeEach
    void setUp() {
        rate = new ExchangeRate("EUR", "USD", BigDecimal.valueOf(150));
    }

    @Test
    public void getFromCurrencyGetsTheGivenCurrency() {
        assertEquals("EUR", rate.getFromCurrency());
    }

    @Test
    public void getToCurrencyGetsTheGivenCurrency() {
        assertEquals("USD", rate.getToCurrency());
    }

    @Test
    public void getRateGetsTheGivenRate() {
        assertEquals(BigDecimal.valueOf(150), rate.getRate());
    }

    @Test
    public void exchangeRatesAreEqualIfAllElementsAreEquals() {
        assertEquals(new ExchangeRate("EUR", "USD", BigDecimal.valueOf(150)), rate);
    }

    @Test
    public void exchangeRatesAreDifferentIfFromCurrencyIsDifferent() {
        assertNotEquals(new ExchangeRate("DKK", "USD", BigDecimal.valueOf(150)), rate);
    }

    @Test
    public void exchangeRatesAreDifferentIfToCurrencyIsDifferent() {
        assertNotEquals(new ExchangeRate("EUR", "DKK", BigDecimal.valueOf(150)), rate);
    }

    @Test
    public void exchangeRatesAreDifferentIfRateIsDifferent() {
        assertNotEquals(new ExchangeRate("EUR", "USD", BigDecimal.valueOf(100)), rate);
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void exchangeRatesAreDifferentFromObjectsOfOtherClasses() {
        assertNotEquals(rate, "150");
    }
}
