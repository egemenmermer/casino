-- Add new columns to existing tables
ALTER TABLE users ADD COLUMN email VARCHAR(100);

-- Add new table if necessary
CREATE TABLE rewards (
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         user_id BIGINT,
                         points INT DEFAULT 0,
                         FOREIGN KEY (user_id) REFERECES users(id)
);