CREATE DATABASE demojpa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS employee
(
    id   INT     NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    dept VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

insert into employee (name, dept) values ("Foo Bar", "Tropic");
insert into employee (name, dept) values ("John Doe", "Wood");

CREATE TABLE IF NOT EXISTS company
(
    id   INT     NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customer
(
    id   INT     NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    status   INT NULL,
    company_id   INT     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company (id)
);

insert into company (name) values ("Dollar Exchange");
insert into company (name) values ("Euro Exchange");

insert into customer (name, company_id, status) values ("Boo Stoo",1,1);
insert into customer (name, company_id, status) values ("Roo Kaa",1,2);
insert into customer (name, company_id, status) values ("Koko Baba",2,3);
