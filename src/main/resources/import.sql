INSERT INTO users (name, email, password) VALUES ('User', 'user@yandex.ru', 'password'), ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('USER', 1000), ('ADMIN', 1001), ('USER', 1001);

INSERT INTO restaurant (name) VALUES ('first'), ('second');


-- INSERT INTO menu () VALUES ();


INSERT INTO dish (name, price, restaurant_id) VALUES ('breakfast', 100, 1002), ('dinner', 200, 1002);

