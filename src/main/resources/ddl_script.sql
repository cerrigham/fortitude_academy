CREATE DATABASE fortitude;

CREATE TABLE IF NOT EXISTS fortitude.public.human_resource (
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
email VARCHAR(150) NOT NULL,
phone_number VARCHAR(20) NOT NULL,
vat_code CHAR(16) NOT NULL,
flag_ceo BOOLEAN,
flag_cda BOOLEAN
);

CREATE TABLE IF NOT EXISTS fortitude.public.company (
id SERIAL PRIMARY KEY,
name VARCHAR (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS fortitude.public.company_location (
id SERIAL PRIMARY KEY,
city VARCHAR(50) NOT NULL,
address VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS fortitude.public.customer (
id SERIAL PRIMARY KEY,
name VARCHAR(100)  NOT NULL,
email VARCHAR(150) NOT NULL,
phone_number VARCHAR(20) NOT NULL,
detail VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS fortitude.public.technology (
id SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS fortitude.public.project (
id SERIAL PRIMARY KEY,
name VARCHAR(200) NOT NULL,
end_date DATE,
reporting_id VARCHAR(50) NOT NULL,
customer_id INT,
FOREIGN KEY(customer_id) REFERENCES customer(id)
);


CREATE TABLE IF NOT EXISTS fortitude.public.job_description (
id SERIAL PRIMARY KEY,
technology_id INT,
project_id INT,
FOREIGN KEY(technology_id) REFERENCES technology(id),
FOREIGN KEY(project_id) REFERENCES project(id)
);

CREATE TABLE IF NOT EXISTS fortitude.public.allocation (
id SERIAL PRIMARY KEY,
human_resource_id INT,
project_id INT,
FOREIGN KEY(human_resource_id) REFERENCES human_resource(id),
FOREIGN KEY(project_id) REFERENCES project(id)
);

ALTER TABLE fortitude.public.allocation RENAME COLUMN human_resource_id TO humanresource_id;
ALTER TABLE fortitude.public.allocation DROP CONSTRAINT allocation_human_resource_id_fkey;
ALTER TABLE fortitude.public.allocation ADD FOREIGN KEY (humanresource_id) REFERENCES human_resource(id);
