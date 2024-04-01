--liquibase formatted sql

--changeset max:1
CREATE TABLE IF NOT EXISTS regions (
    iso VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(64) NOT NULL UNIQUE
    );


