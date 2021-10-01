DELETE FROM auth_codes;
DELETE FROM cards;
DELETE FROM users;

INSERT INTO users (id, login, password)
VALUES (1, "vasya", "password");

INSERT INTO users (id, login, password)
VALUES (2, "petya", "password");

INSERT INTO cards (id, user_id, number, balance_in_kopecks)
VALUES (1, 1, "5559000000000001", 1000000),
       (2, 1, "5559000000000002", 1000000);

INSERT INTO card_transactions (source, target, amount_in_kopecks)
VALUES ("5559000000000001", "5559000000000002", 10000);

UPDATE cards
SET balance_in_kopecks = balance_in_kopecks - 10000
WHERE number = "5559000000000001";

UPDATE cards
SET balance_in_kopecks = balance_in_kopecks + 10000
WHERE number = "5559000000000002";

DELETE FROM auth_codes WHERE created < NOW() - INTERVAL 3 SECOND ;

DELETE FROM cards WHERE user_id = "7570dea8-4417-425f-a99d-3ef075fbde62" ;
DELETE FROM users WHERE login = "vasya";

DELETE * FROM auth_codes;



DELETE FROM cards WHERE user_id = "697c71f0-1514-40f8-b396-1b7b47e73f2e" ;
DELETE FROM users WHERE login = "petya";

DELETE FROM cards WHERE created < NOW() - INTERVAL 5 MINUTE ;
DELETE FROM cards WHERE user_id = "0bf52e04-bd24-487a-aa41-032243bc8773" ;

DELETE FROM cards WHERE number = "5559000000000002" ;

DELETE FROM cards WHERE number IS NOT NULL;
DELETE FROM users WHERE login IS NOT NULL;


-- выборка всех столбцов и всех строк из таблицы users (будьте осторожны на больших таблицах)
SELECT * FROM users;
-- выборка только определённых столбцов
SELECT id, login FROM users;
-- выборка по условию
SELECT balance_in_kopecks FROM cards WHERE number = "5559000000000002";
-- вычисляемые столбцы
SELECT balance_in_kopecks / 100 AS balance_in_rub FROM cards WHERE number = "5559000000000002";
SELECT id FROM users WHERE login = 'vasya' ;
SELECT max(cards.balance_in_kopecks) FROM cards;

SELECT sum(balance_in_kopecks) FROM cards WHERE user_id = 1;

SELECT count(*), user_id FROM cards GROUP BY user_id;