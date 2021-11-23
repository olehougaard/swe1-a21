package dk.via.money.business;

import dk.via.money.db.ExchangeRateData;
import dk.via.money.model.ExchangeRate;
import org.opentest4j.AssertionFailedError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ExchangeRateDataMock implements ExchangeRateData {

    private static class Call {
        private final String fromCurrency;
        private final String toCurrency;
        private final ExchangeRate returnValue;

        public Call(String fromCurrency, String toCurrency, ExchangeRate returnValue) {
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.returnValue = returnValue;
        }

        public boolean validate(String fromCurrency, String toCurrency) {
            return fromCurrency.equals(this.fromCurrency) && toCurrency.equals(this.toCurrency);
        }

        public ExchangeRate getReturnValue() {
            return returnValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Call call = (Call) o;
            return Objects.equals(fromCurrency, call.fromCurrency) && Objects.equals(toCurrency, call.toCurrency) && Objects.equals(returnValue, call.returnValue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fromCurrency, toCurrency, returnValue);
        }
    }

    private final ArrayList<Call> expectedCalls;
    private final ArrayList<Call> actualCalls;

    public ExchangeRateDataMock() {
        this.expectedCalls = new ArrayList<>();
        this.actualCalls = new ArrayList<>();
    }

    public void expect(String fromCurrency, String toCurrency, ExchangeRate returnValue) {
        expectedCalls.add(new Call(fromCurrency, toCurrency, returnValue));
    }

    @Override
    public ExchangeRate getExchangeRate(String fromCurrency, String toCurrency) {
        if (actualCalls.size() >= expectedCalls.size()) throw new AssertionFailedError("Unexpected Call");
        Call expectedCall = expectedCalls.get(actualCalls.size());
        if (!expectedCall.validate(fromCurrency, toCurrency)) throw new AssertionFailedError("Unexpected parameters");
        ExchangeRate returnValue = expectedCall.getReturnValue();
        actualCalls.add(new Call(fromCurrency, toCurrency, returnValue));
        return returnValue;
    }

    public boolean validate() {
        return actualCalls.equals(expectedCalls);
    }
}
