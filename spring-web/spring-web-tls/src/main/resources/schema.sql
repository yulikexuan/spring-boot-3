DROP TABLE IF EXISTS spring_data_jdbc.buyers CASCADE;
CREATE TABLE spring_data_jdbc.buyers (id serial PRIMARY KEY, active boolean, name varchar(32) UNIQUE NOT NULL, email varchar(32) NOT NULL, level integer, created timestamp NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.contacts CASCADE;
CREATE TABLE spring_data_jdbc.contacts (id serial PRIMARY KEY , street VARCHAR(30) NOT NULL, city VARCHAR(20) NOT NULL);

DROP TABLE IF EXISTS spring_data_jdbc.buyers_contacts CASCADE;
CREATE TABLE spring_data_jdbc.buyers_contacts (buyer_id BIGINT, contact_id BIGINT, FOREIGN KEY (buyer_id) REFERENCES spring_data_jdbc.buyers(id), FOREIGN KEY (contact_id) REFERENCES spring_data_jdbc.contacts(id));
