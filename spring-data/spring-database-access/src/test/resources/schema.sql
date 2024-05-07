\connect yulikexuan;
DROP SCHEMA IF EXISTS date4u CASCADE;
CREATE SCHEMA IF NOT EXISTS date4u;
SET search_path TO date4u,public;
CREATE TABLE IF NOT EXISTS date4u.profile (id serial PRIMARY KEY, birthdate date NOT NULL, nickname varchar(32) UNIQUE NOT NULL, manelength smallint NOT NULL, gender smallint NOT NULL, attracted_to_gender smallint, description varchar(2048) NOT NULL, lastseen timestamp NOT NULL);
CREATE TABLE IF NOT EXISTS date4u.unicorn (id serial PRIMARY KEY, email varchar(64) UNIQUE NOT NULL, password varchar(128) NOT NULL, profile_fk bigint REFERENCES date4u.profile (id));
CREATE TABLE IF NOT EXISTS date4u.photo (id serial PRIMARY KEY, name varchar(256) NOT NULL, is_profile_photo boolean NOT NULL, created timestamp NOT NULL, profile_fk bigint NOT NULL REFERENCES date4u.profile (id));
CREATE TABLE IF NOT EXISTS date4u.likes (liker_fk bigint NOT NULL REFERENCES profile (id), likee_fk bigint NOT NULL REFERENCES profile (id));
