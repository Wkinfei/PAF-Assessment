drop database  if exists acem_bank;
CREATE database acem_bank;
use acem_bank;

drop table if exists accounts;

create table accounts(
	id int auto_increment,
	account_id varchar(128),
    name varchar(64),
    balance decimal(18,2),
    constraint pk_id primary key (id)
);

INSERT INTO accounts (account_id,name,balance) VALUES ("V9L3Jd1BBI", "fred",100);
INSERT INTO accounts (account_id,name,balance) VALUES ("fhRq46Y6vB", "barney",300);
INSERT INTO accounts (account_id,name,balance) VALUES ("uFSFRqUpJy", "wilma",1000);
INSERT INTO accounts (account_id,name,balance) VALUES ("ckTV56axff", "betty",1000);
INSERT INTO accounts (account_id,name,balance) VALUES ("Qgcnwbshbh", "pebbles",50);
INSERT INTO accounts (account_id,name,balance) VALUES ("if9l185l18", "bambam",50);