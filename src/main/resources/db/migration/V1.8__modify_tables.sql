ALTER TABLE user
    ADD COLUMN person_id BIGINT UNSIGNED,
    ADD CONSTRAINT fk_person_id FOREIGN KEY(person_id) REFERENCES person(id);

ALTER TABLE person
    ADD COLUMN expense_id BIGINT UNSIGNED,
    ADD CONSTRAINT fk_expense_id FOREIGN KEY (expense_id) REFERENCES expense(id);

ALTER TABLE expense
    DROP FOREIGN KEY expense_ibfk_1;

 ALTER TABLE expense
    ADD CONSTRAINT fk_payee_id FOREIGN KEY (payee_id) REFERENCES person(id)