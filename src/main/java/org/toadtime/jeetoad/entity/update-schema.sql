
CREATE SEQUENCE IF NOT EXISTS Toad_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE Toad
(
    id          BIGINT NOT NULL,
    age         INTEGER default 0,
    name        VARCHAR(255) default '',
    weight      INTEGER default 0,
    gender      CHAR default '',
    description VARCHAR(1000) default '',
    birthday    date default
    CONSTRAINT pk_toad PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS Toad_seq START WITH 1 INCREMENT BY 50;

ALTER TABLE Toad
    ALTER COLUMN age SET NOT NULL;

ALTER TABLE Toad
    ALTER COLUMN weight SET NOT NULL;