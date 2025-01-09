-- Insert users (let the database generate IDs)
INSERT INTO users (username)
VALUES
    ('egemen'),
    ('osman'),
    ('cagatay');

-- Insert accounts linked to users using the generated IDs
INSERT INTO account (user_id, balance, created_at)
SELECT id, 1000, NOW()
FROM users
WHERE username IN ('egemen', 'osman', 'cagatay');