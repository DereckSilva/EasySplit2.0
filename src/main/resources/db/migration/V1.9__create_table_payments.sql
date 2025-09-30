CREATE TABLE IF NOT EXISTS payments(
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT UNSIGNED,
    expense_id BIGINT UNSIGNED,
    intermediary TINYINT DEFAULT 0,
    datePayment DATETIME DEFAULT CURRENT_TIMESTAMP,
    totalPaid FLOAT(10,2) NOT NULL,
    parcelNumber INT DEFAULT 1
);

ALTER TABLE payments
    ADD CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES person(id);

ALTER TABLE payments
    ADD CONSTRAINT fk_expense FOREIGN KEY (expense_id) REFERENCES expense(id);

ALTER TABLE expense
    DROP COLUMN datePayment;