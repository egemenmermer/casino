ALTER TABLE transaction_history_log DROP COLUMN amount;
ALTER TABLE transaction_history_log RENAME COLUMN final_balance TO profit;