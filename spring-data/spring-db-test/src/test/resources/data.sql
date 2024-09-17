CREATE SCHEMA IF NOT EXISTS spring_db_test;
SET schema 'spring_db_test';
SET search_path TO spring_db_test,public;

CREATE TABLE IF NOT EXISTS spring_db_test.buyers(id serial PRIMARY KEY, active boolean, name varchar(32) UNIQUE NOT NULL, email varchar(32) NOT NULL, level integer, created timestamp NOT NULL);
CREATE TABLE IF NOT EXISTS spring_db_test.contacts (id serial PRIMARY KEY , street VARCHAR(30) NOT NULL, city VARCHAR(20) NOT NULL);
CREATE TABLE IF NOT EXISTS spring_db_test.buyers_contacts (buyer_id BIGINT, contact_id BIGINT, FOREIGN KEY (buyer_id) REFERENCES spring_db_test.buyers(id), FOREIGN KEY (contact_id) REFERENCES spring_db_test.contacts(id));

INSERT INTO spring_db_test.contacts (street, city) VALUES ('New York City', '11, 5th Avenue');
INSERT INTO spring_db_test.contacts (street, city) VALUES ('New York City', '22, 5th Avenue');
INSERT INTO spring_db_test.contacts (street, city) VALUES ('New York City', '33, 5th Avenue');

INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('john', 'john@somedomain.com', 1, true,'2020-04-13 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('mike', 'mike@somedomain.com', 3, true,'2020-01-18 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('james', 'james@somedomain.com', 3, false,'2020-03-11 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('katie', 'katie@somedomain.com', 5, true,'2021-01-05 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('beth', 'beth@somedomain.com', 2, true,'2020-08-03 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('julius', 'julius@somedomain.com', 4, true,'2021-02-09 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('darren', 'darren@somedomain.com', 2, true,'2020-12-11 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('marion', 'marion@somedomain.com', 2, false,'2020-09-23 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('stephanie', 'stephanie@somedomain.com', 4, true,'2020-01-18 00:00:00');
INSERT INTO spring_db_test.buyers (name, email, level, active, created) VALUES ('burk', 'burk@somedomain.com', 1, true,'2020-11-28 00:00:00');

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (1, 1);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (1, 2);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (2, 1);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (2, 3);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (3, 2);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (3, 3);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (4, 1);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (4, 2);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (5, 1);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (5, 3);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (6, 2);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (6, 3);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (7, 1);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (7, 2);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (8, 1);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (8, 3);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (9, 2);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (9, 3);

INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (10, 1);
INSERT INTO spring_db_test.buyers_contacts (buyer_id, contact_id) VALUES (10, 2);
