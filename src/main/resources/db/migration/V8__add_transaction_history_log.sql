CREATE TABLE IF NOT EXISTS transaction_history_log (
                                                        id SERIAL PRIMARY KEY,
                                                        kind VARCHAR(20) NOT NULL,
                                                        account_id BIGINT,
                                                        amount NUMERIC(10, 2) NOT NULL,
                                                        final_balance NUMERIC(10,2),
                                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                        FOREIGN KEY (account_id) REFERENCES account(id)
    );