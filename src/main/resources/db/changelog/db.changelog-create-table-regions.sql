--liquibase formatted sql

--changeset max:1
CREATE TABLE IF NOT EXISTS regions (
    iso VARCHAR(32) PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
    );


