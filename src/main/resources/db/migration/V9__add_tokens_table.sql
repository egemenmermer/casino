CREATE TABLE tokens (
                        id SERIAL PRIMARY KEY,
                        user_id BIGINT,
                        token TEXT NOT NULL,
                        created_at TIMESTAMP DEFAULT NOW(),
                        expires_at TIMESTAMP,
                        is_active BOOLEAN DEFAULT TRUE,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);



