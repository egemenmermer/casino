ALTER TABLE tokens
ALTER COLUMN expires_at TYPE DATE USING expires_at::DATE;