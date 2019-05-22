CREATE DATABASE MMS;

CREATE TABLE Motorway (
  name varchar(255),
  PRIMARY KEY (name)
);

CREATE TABLE UnitRate (
  id varchar(1) NOT NULL AUTO_INCREMENT,
  rate float,
  PRIMARY KEY (id)
);

CREATE TABLE Tollbooth (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255),
  km int,
  PRIMARY KEY (id)
);

CREATE TABLE Vehicle (
  plate_number varchar(7) NOT NULL,
  brand varchar(255),
  model varchar(255),
  type boolean,
  unitRate varchar(1),
  year int,
  axis int,
  height int,
  weight int,
  noise_pollution int,
  environmental_class int,
  PRIMARY KEY (plate_number)
);

CREATE TABLE Toll (
  id int NOT NULL AUTO_INCREMENT,
  vehicle_id varchar(7),
  tollbooth_in int,
  tollbooth_out int,
  cost float,
  PRIMARY KEY (id),
  FOREIGN KEY (`vehicle_id`) REFERENCES `Vehicle` (`plate_number`),
  FOREIGN KEY (`tollbooth_in`) REFERENCES `Tollbooth` (`id`),
  FOREIGN KEY (`tollbooth_out`) REFERENCES `Tollbooth` (`id`)
);
