CREATE TABLE users (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       username VARCHAR(15)
);

CREATE TABLE account (
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         user_id BIGINT,
                         balance NUMERIC(10,2),
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE transaction (
                             id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             amount BIGINT NOT NULL,
                             kind VARCHAR, -- DEPOSIT, WITHDRAW, WIN, BET
                             created_at TIMESTAMP NOT NULL
);

CREATE TABLE game (
                      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      name VARCHAR(50),
                      win_chance NUMERIC(3,2),
                      min_amount NUMERIC(3,2),
                      created_at TIMESTAMP NOT NULL,
                      updated_at TIMESTAMP
);

CREATE TABLE game_history_log (
                                  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                  play_date TIMESTAMP,
                                  bet_amount NUMERIC(10,2),
                                  old_balance NUMERIC(10,2),
                                  new_balance NUMERIC(10,2),
                                  status VARCHAR,
                                  account_id BIGINT,
                                  game_id BIGINT,
                                  FOREIGN KEY (account_id) REFERENCES account(id),
                                  FOREIGN KEY (game_id) REFERENCES game(id)
);