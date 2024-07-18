DROP SCHEMA IF EXISTS spring_data_jdbc CASCADE;
CREATE SCHEMA IF NOT EXISTS spring_data_jdbc;
SET search_path TO spring_data_jdbc,public;
DROP TABLE IF EXISTS spring_data_jdbc.users CASCADE;
CREATE TABLE spring_data_jdbc.users (id serial PRIMARY KEY, active boolean, username varchar(32) UNIQUE NOT NULL, email varchar(32), level integer, created timestamp NOT NULL);