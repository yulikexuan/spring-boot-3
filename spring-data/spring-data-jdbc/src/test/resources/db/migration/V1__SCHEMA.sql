DROP SCHEMA IF EXISTS spring_data_jdbc CASCADE;
CREATE SCHEMA IF NOT EXISTS spring_data_jdbc;
SET search_path TO spring_data_jdbc,public;

DROP TABLE IF EXISTS spring_data_jdbc.users CASCADE;
CREATE TABLE spring_data_jdbc.users (id serial PRIMARY KEY, active boolean, username varchar(32) UNIQUE NOT NULL, email varchar(32), level integer, created timestamp NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.clients CASCADE;
CREATE TABLE spring_data_jdbc.clients (id serial PRIMARY KEY, active boolean, name varchar(32) UNIQUE NOT NULL, email varchar(32), level integer, created timestamp NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.addresses CASCADE;
-- All references in an aggregate result in a foreign key relationship in the opposite direction in the database.
-- By default, the name of the foreign key column is the table name of the referencing entity.
CREATE TABLE spring_data_jdbc.addresses (clients INTEGER, street VARCHAR(30) NOT NULL, city VARCHAR(20) NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.members CASCADE;
CREATE TABLE spring_data_jdbc.members (id serial PRIMARY KEY, team VARCHAR(16) DEFAULT 'itopia-app', active boolean, name VARCHAR(32) UNIQUE NOT NULL, email VARCHAR(32) NOT NULL, level INTEGER, created TIMESTAMP NOT NULL, street VARCHAR(30) NOT NULL, city VARCHAR(20) NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.customers CASCADE;
CREATE TABLE spring_data_jdbc.customers (id serial PRIMARY KEY, active boolean, name varchar(32) UNIQUE NOT NULL, email varchar(32) NOT NULL, level integer, created timestamp NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.deliveries CASCADE;
CREATE TABLE spring_data_jdbc.deliveries (id serial PRIMARY KEY , customer_id BIGINT, street VARCHAR(30) NOT NULL, city VARCHAR(20) NOT NULL, FOREIGN KEY (customer_id) REFERENCES spring_data_jdbc.customers(id));

DROP TABLE IF EXISTS spring_data_jdbc.buyers CASCADE;
CREATE TABLE spring_data_jdbc.buyers (id serial PRIMARY KEY, active boolean, name varchar(32) UNIQUE NOT NULL, email varchar(32) NOT NULL, level integer, created timestamp NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.contacts CASCADE;
CREATE TABLE spring_data_jdbc.contacts (id serial PRIMARY KEY , street VARCHAR(30) NOT NULL, city VARCHAR(20) NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.buyers_contacts CASCADE;
CREATE TABLE spring_data_jdbc.buyers_contacts (buyer_id BIGINT, contact_id BIGINT, FOREIGN KEY (buyer_id) REFERENCES spring_data_jdbc.buyers(id), FOREIGN KEY (contact_id) REFERENCES spring_data_jdbc.contacts(id));

INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('yulikexuan', true, 'yulikexuan@yahoo.com', 1, '2024-06-15 22:57:55');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('john', true, 'john@somedomain.com', 1, '2020-04-13 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('mike', true, 'mike@somedomain.com', 3, '2020-01-18 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('james', false, 'james@someotherdomain.com', 3, '2020-03-11 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('katie', true, 'katie@somedomain.com', 5, '2021-01-05 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('beth', true, 'beth@somedomain.com', 2, '2020-08-03 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('julius', true, 'julius@someotherdomain.com', 4, '2021-02-09 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('darren', true, 'darren@somedomain.com', 2, '2020-12-11 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('marion', false, 'marion@someotherdomain.com', 2, '2020-09-23 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('stephanie', true, 'stephanie@someotherdomain.com', 4, '2020-01-18 00:00:00');
INSERT INTO spring_data_jdbc.users (username, active, email, level, created) VALUES ('burk', true, 'burk@somedomain.com', 1, '2020-11-28 00:00:00');