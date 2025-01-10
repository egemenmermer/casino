-- V6__update_sequence.sql

-- USERS TABLE
-- Mevcut sequence'i yeniden adlandır ve başlat
DO $$ BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'users_id_seq') THEN
CREATE SEQUENCE users_id_seq OWNED BY users.id;
END IF;
END $$;

-- Sequence değerini sıfırla
SELECT setval('users_id_seq', COALESCE((SELECT MAX(id) FROM users), 0) + 1);

-- ACCOUNT TABLE
DO $$ BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'account_id_seq') THEN
CREATE SEQUENCE account_id_seq OWNED BY account.id;
END IF;
END $$;

SELECT setval('account_id_seq', COALESCE((SELECT MAX(id) FROM account), 0) + 1);

-- TRANSACTION TABLE
DO $$ BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'transaction_id_seq') THEN
CREATE SEQUENCE transaction_id_seq OWNED BY transaction.id;
END IF;
END $$;

SELECT setval('transaction_id_seq', COALESCE((SELECT MAX(id) FROM transaction), 0) + 1);

-- GAME TABLE
DO $$ BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'game_id_seq') THEN
CREATE SEQUENCE game_id_seq OWNED BY game.id;
END IF;
END $$;

SELECT setval('game_id_seq', COALESCE((SELECT MAX(id) FROM game), 0) + 1);

-- GAME_HISTORY_LOG TABLE
DO $$ BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'game_history_log_id_seq') THEN
CREATE SEQUENCE game_history_log_id_seq OWNED BY game_history_log.id;
END IF;
END $$;

SELECT setval('game_history_log_id_seq', COALESCE((SELECT MAX(id) FROM game_history_log), 0) + 1);