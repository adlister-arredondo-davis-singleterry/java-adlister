USE team_adlister;

TRUNCATE TABLE ad_category;
DELETE
FROM categories
WHERE TRUE;
DELETE
FROM ads
WHERE TRUE;
DELETE
FROM users
WHERE TRUE;

INSERT INTO users (id, username, email, password, is_admin)
VALUES (1, 'admin-user', 'adminuser@gmail.com', '$2a$12$qx.cAY4W4rnQI2ZqS.RC4eTAyiQI9m6lNuI6jWYn7icnHOpqn14NW', 1),
       (2, 'test-user', 'testuser@gmail.com', '$2a$12$qx.cAY4W4rnQI2ZqS.RC4eTAyiQI9m6lNuI6jWYn7icnHOpqn14NW', 0);

INSERT INTO ads (id, user_id, title, description)
VALUES (1, 2, 'Holding the Door', 'I guess people can''t hold the door open anymore? Have we turned into barbarians?'),
       (2, 2, 'Slow Drivers', 'Why can''t slow drivers stay on the slow lane??'),
       (3, 1, 'Dad Jokes', 'It bugs me when I have to hear my Dad''s jokes all day'),
       (4, 1, 'Awkward Group Dinners', 'Not a big fan of groups dinners, especially trying to decide on how to split the tab');

INSERT INTO categories (id, name)
VALUES (1, 'Adulting'),
       (2, 'Grammar'),
       (3, 'Everybody''s Bad at Driving'),
       (4, 'Social Gatherings'),
       (5, 'Basic Etiquette'),
       (6, 'Family'),
       (7, 'Miscellaneous');


INSERT INTO ad_category (ad_id, category_id)
VALUES (1, 1),
       (1, 4),
       (2, 3);