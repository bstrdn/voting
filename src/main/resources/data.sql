INSERT INTO users (name, email, password)
VALUES ('User0', 'user0@ya.ru', 'p'),
       ('User1', 'user1@ya.ru', 'p'),
       ('User2', 'user2@ya.ru', 'p'),
       ('Admin3', 'admin3@ya.ru', 'p');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 1000),
       ('USER', 1001),
       ('USER', 1002),
       ('ADMIN', 1003),
       ('USER', 1003);

INSERT INTO restaurant (name)
VALUES ('first'),
       ('second'),
       ('third'),
       ('fourth');

INSERT INTO dish (name, price, restaurant_id)
VALUES ('breakfast', 100, 1004),
       ('dinner', 200, 1004),
       ('dinner4', 250, 1006),
       ('dinner4', 205, 1006);

