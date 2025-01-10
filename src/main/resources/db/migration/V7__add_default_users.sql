-- V7__add_default_users.sql

-- Users ve Account tablolarına veri ekleme
-- Eğer zaten eklenmişse aynı kullanıcıları tekrar eklemeyecek şekilde kontrol ekliyoruz
DO $$ BEGIN
   IF NOT EXISTS (SELECT 1 FROM users WHERE username = 'egemen') THEN
      INSERT INTO users (username) VALUES ('egemen');
END IF;

   IF NOT EXISTS (SELECT 1 FROM users WHERE username = 'osman') THEN
      INSERT INTO users (username) VALUES ('osman');
END IF;

   IF NOT EXISTS (SELECT 1 FROM users WHERE username = 'cagatay') THEN
      INSERT INTO users (username) VALUES ('cagatay');
END IF;
END $$;

-- Account'ları kullanıcılarla ilişkilendirip ekle
DO $$ BEGIN
   IF NOT EXISTS (SELECT 1 FROM account WHERE user_id = (SELECT id FROM users WHERE username = 'egemen')) THEN
      INSERT INTO account (user_id, balance, created_at)
      VALUES ((SELECT id FROM users WHERE username = 'egemen'), 1000, NOW());
END IF;

   IF NOT EXISTS (SELECT 1 FROM account WHERE user_id = (SELECT id FROM users WHERE username = 'osman')) THEN
      INSERT INTO account (user_id, balance, created_at)
      VALUES ((SELECT id FROM users WHERE username = 'osman'), 1000, NOW());
END IF;

   IF NOT EXISTS (SELECT 1 FROM account WHERE user_id = (SELECT id FROM users WHERE username = 'cagatay')) THEN
      INSERT INTO account (user_id, balance, created_at)
      VALUES ((SELECT id FROM users WHERE username = 'cagatay'), 1000, NOW());
END IF;
END $$;