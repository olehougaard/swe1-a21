CREATE TABLE Exchange_Rate (
  from_currency CHAR(3),
  to_currency CHAR(3),
  rate DECIMAL(8, 2) NOT NULL CHECK(rate > 0),
  PRIMARY KEY(from_currency, to_currency)
);

INSERT INTO Exchange_Rate VALUES ("EUR", "USD", 120), ("USD", "EUR", 83);
