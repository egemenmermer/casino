ALTER TABLE transaction ADD COLUMN account_id BIGINT;

ALTER TABLE transaction ADD CONSTRAINT fk_transaction_account
    FOREIGN KEY (account_id) REFERENCES account(id);