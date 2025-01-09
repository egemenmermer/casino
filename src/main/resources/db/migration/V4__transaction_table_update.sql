ALTER TABLE transaction ADD COLUMN account_id FOREIGN KEY (account_id) REFERENCES account(id);
