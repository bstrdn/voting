DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS restaurant;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1000;

create TABLE users
(
    id       INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name     VARCHAR(100)                          NOT NULL,
    email    VARCHAR(100)                          NOT NULL,
    enabled  BOOL    DEFAULT TRUE                  NOT NULL,
    password VARCHAR(100)                          NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


create TABLE user_role
(
    user_id INTEGER                                NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name VARCHAR(100)                          NOT NULL
);

CREATE TABLE dish
(
    id   INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name          VARCHAR(100)                            NOT NULL,
    added         TIMESTAMP default NOW()                 NOT NULL,
    price         INTEGER                                 NOT NULL,
    restaurant_id INTEGER                                 NOT NULL,
    CONSTRAINT restaurant_id_idx
        FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id  INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    restaurant_id INTEGER,
    voted         TIMESTAMP,
    user_id       INTEGER                               NOT NULL,
    CONSTRAINT restaurant_id_idx_2
        FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
            ON DELETE CASCADE,
    CONSTRAINT user_id_idx
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE
);