USE team_adlister;

TRUNCATE TABLE ad_category;
DELETE FROM categories WHERE TRUE;
DELETE FROM ads WHERE TRUE;
DELETE FROM users WHERE TRUE;

INSERT INTO users (id, username, email, password, is_admin)
VALUES 	(1, 'admin-user', 'adminuser@gmail.com', '$2a$12$qx.cAY4W4rnQI2ZqS.RC4eTAyiQI9m6lNuI6jWYn7icnHOpqn14NW', 1),
        (2, 'test-user', 'testuser@gmail.com', '$2a$12$qx.cAY4W4rnQI2ZqS.RC4eTAyiQI9m6lNuI6jWYn7icnHOpqn14NW', 0);

INSERT INTO ads (id, user_id, title, description)
VALUES	(1, 2, 'Test Ad', 'descript1'),
        (2, 2, 'ad2', 'descript1');

INSERT INTO categories (id, name)
VALUES 	(1, 'for sale - general'),
        (2, 'wanted'),
        (3, 'lost and found'),
        (4, 'furniture'),
        (5, 'tools');


INSERT INTO ad_category (ad_id, category_id)
VALUES (1, 1),
       (1, 4),
       (2, 3);