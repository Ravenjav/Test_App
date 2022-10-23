BEGIN;

DROP TABLE IF EXISTS sensors CASCADE;
DROP type IF EXISTS sensor_unit;
DROP type IF EXISTS sensor_type;

create type sensor_type as enum ('Pressure', 'Voltage', 'Temperature', 'Humidity');
create type sensor_unit as enum ('Bar', 'Voltage', 'Celsius', 'Percent');

CREATE TABLE sensors(
    id bigserial PRIMARY KEY,
    title varchar(30) NOT NULL,
    model varchar(15) NOT NULL,
    range_from smallint NOT NULL,
    range_to smallint NOT NULL CHECK(range_to >= range_from),
    type sensor_type,
    unit sensor_unit,
    location varchar(40),
    description varchar(200)
);

INSERT INTO sensors (title, model, range_from, range_to) VALUES
('Sensor1', 'XM', 10, 15),
('Sensor 2', 'XM', 50, 75),
('Sensor 3', 'XMD', -5, 10);
INSERT INTO sensors (title, model, range_from, range_to, type) VALUES
('Sensor 4', 'XK', 10, 15, 'Pressure'),
('Sensor 5', 'XK', 25, 40, 'Voltage');
INSERT INTO sensors (title, model, range_from, range_to, type, unit) VALUES
('Sensor 6', 'XJD', -30, 44, 'Pressure', 'Bar'),
('Sensor 7', 'XJ', 30, 44, 'Pressure', 'Celsius'),
('Sensor 7.2', 'XJH', 90, 110, 'Humidity', 'Percent'),
('Sensor 7.2', 'XJH', 90, 110, 'Humidity', 'Bar');
INSERT INTO sensors (title, model, range_from, range_to, type, unit, location) VALUES
('Sensor 8', 'XL', 10, 50, 'Temperature', 'Percent', 'Belarus'),
('Sensor 9', 'XLH', 90, 110, 'Humidity', 'Voltage', 'Russia');
INSERT INTO sensors (title, model, range_from, range_to, type, unit, location, description) VALUES
('Sensor 8', 'XLH', 90, 110, 'Temperature', 'Voltage', 'Belarus', 'description 1'),
('Sensor 9', 'XL', 30, 60, 'Humidity', 'Percent', 'Russia', 'description 2');

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;
CREATE TABLE users (
  username VARCHAR(50) primary key,
  password VARCHAR(100) NOT NULL,
  enabled boolean NOT NULL
);

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO users (username, password, enabled) values ('user', '{noop}user', true);
INSERT INTO authorities (username, authority) values ('user', 'ROLE_VIEWER');

INSERT INTO users (username, password, enabled) values ('admin', '{noop}admin', true);
INSERT INTO authorities (username, authority) values ('admin', 'ROLE_ADMIN');
END;


