DROP TABLE IF EXISTS VOTE;
DROP TABLE IF EXISTS USER_ROLE;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS DISH;
DROP TABLE IF EXISTS RESTAURANT;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1000;

create table USERS
(
    ID       INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    NAME     VARCHAR(100)                          not null,
    EMAIL    VARCHAR(100)                          not null,
    ENABLED  BOOL    default TRUE                  not null,
    PASSWORD VARCHAR(100)                          not null
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


create table USER_ROLE
(
    USER_ID INTEGER                                not null,
    ROLE    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

create table RESTAURANT
(
    ID   INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    NAME VARCHAR(100)                          not null
);

create table DISH
(
    ID   INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    NAME          VARCHAR(100)                            not null,
    ADDED         TIMESTAMP default NOW()                 not null,
    PRICE         INTEGER                                 not null,
    RESTAURANT_ID INTEGER                                 not null,
    constraint restaurant_id_idx
        foreign key (RESTAURANT_ID) references RESTAURANT (ID) ON DELETE CASCADE
);

create table VOTE
(
    ID  INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    RESTAURANT_ID INTEGER,
    VOTED         TIMESTAMP,
    USER_ID       INTEGER                               not null,
    constraint restaurant_id_idx_2
        foreign key (RESTAURANT_ID) references RESTAURANT (ID)
            on delete cascade,
    constraint user_id_idx
        foreign key (USER_ID) references USERS (ID)
            on delete cascade
);