package dk.via.money.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {
    private Money usd0, usd100, eur100;
    private BigDecimal dec100;
    private BigDecimal dec0;

    @BeforeEach
    void setUp() {
        dec100 = new BigDecimal(100).setScale(2, RoundingMode.HALF_UP);
        dec0 = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        usd0 = new Money(dec0, "USD");
        usd100 = new Money(dec100, "USD");
        eur100 = new Money(dec100, "EUR");
    }

    // Zero dollars - amount
    @Test
    public void zeroDollarsHasAnAmountOfZero() {
        assertEquals(dec0, usd0.getAmount());
    }

    // 100 dollars - amount
    @Test
    public void hundredDollarsHasAnAmountOfOneHundred() {
        assertEquals(dec100, usd100.getAmount());
    }

    // Zero dollars - currency
    @Test
    public void zeroDollarsHasACurrencyOfDollars() {
        assertEquals("USD", usd0.getCurrency());
    }

    // 100 Euro - currency
    @Test
    public void hundredEurosHasACurrencyOfEuro() {
        assertEquals("EUR", eur100.getCurrency());
    }

    // 100 dollars equals 100 dollars
    @Test
    public void hundredDollarsEquals100Dollars() {
        assertEquals(new Money(dec100, "USD"), usd100);
    }

    // 100 dollars doesn't equals 100 euros
    @Test
    public void hundredDollarsDoesntEqual100Euros() {
        assertNotEquals(new Money(dec100, "USD"), eur100);
    }

    // 100 dollars doesn't equal 0 dollars
    @Test
    public void hundredDollarsDoesntEquals0Dollars() {
        assertNotEquals(new Money(dec100, "USD"), usd0);
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void moneyDoesntEqualNonMoney() {
        assertNotEquals(new Money(dec100, "USD"), "100 USD");
    }

    // 0 dollars equals 0 euro
    @Test
    public void zeroDollarsEquals0Euro() {
        assertEquals(new Money(dec0, "EUR"), usd0);
    }

    // 100 dollars + 100 dollars == 200 dollars
    @Test
    public void hundredDollarsPlusHundredDollarsIs200Dollars() {
        assertEquals(new Money(new BigDecimal(200), "USD"), usd100.add(usd100));
    }

    // 100 dollars + 100 euro -> Exception
    @Test
    public void hundredDollarsPlusHundredEurosThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> usd100.add(eur100));
    }

    // 100 dollars * .25 == 25 dollars
    @Test
    public void hundredDollarsTimesAQuarterIs25Dollars() {
        assertEquals(new Money(new BigDecimal(25), "USD"), usd100.multiply(new BigDecimal(".25")));
    }

    // 100 dollars / 4 == 25 dollars
    @Test
    public void hundredDollarsDividedBy4Is25Dollars() {
        assertEquals(new Money(new BigDecimal(25), "USD"), usd100.divide(new BigDecimal(4)));
    }

    // 100 dollars / 0 -> Exception
    @Test
    public void divideByZeroThrowsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> usd100.divide(dec0));
    }
}