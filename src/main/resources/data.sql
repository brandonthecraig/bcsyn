CREATE TABLE IF NOT EXISTS employee_info (
                              id VARCHAR(16)  PRIMARY KEY NOT NULL,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL,
                              email VARCHAR(250) NOT NULL,
                              mobile_number VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_pin (
                              id VARCHAR(16)  PRIMARY KEY NOT NULL,
                              pin int NOT NULL,
                              CHECK (pin>=0 AND pin <= 9999)

);

