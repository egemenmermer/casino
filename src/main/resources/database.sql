CREATE TABLE users(
                     user_id SERIAL PRIMARY KEY,
                     username VARCHAR(15) NOT NULL UNIQUE,
                     balance DECIMAL(10,2) NOT NULL
);

CREATE TABLE game(
                     game_id SERIAL PRIMARY KEY,
                     game_name VARCHAR(50) NOT NULL,
                     win_chance DECIMAL(3,2) NOT NULL,
                     min_amount DECIMAL(3,2) NOT NULL
);

CREATE TABLE game_history(
                             id SERIAL PRIMARY KEY,
                             user_id BIGINT NOT NULL,
                             game_id BIGINT NOT NULL,
                             game_name VARCHAR(50) NOT NULL,
                             play_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             bet_amount DECIMAL(10,2) NOT NULL,
                             old_balance DECIMAL(10,2) NOT NULL,
                             new_balance DECIMAL(10,2) NOT NULL,
                             FOREIGN KEY(user_id) REFERENCES users(user_id),
                             FOREIGN KEY(game_id) REFERENCES game(game_id)
);



INSERT INTO users (username, balance)
VALUES ('Osman', 1000),
       ('Cagatay', 1000);

INSERT INTO game (game_name, min_amount, win_chance)
VALUES ('Gates Of Olympus', 0.20, 0.45),
       ('Sweet Bonanza', 0.20, 0.10),
       ('Saray Rüyası', 0.20, 0.75);

SELECT * FROM game;
SELECT * FROM users;
SELECT * FROM game_history;
