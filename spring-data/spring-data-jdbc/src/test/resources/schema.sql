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
