
CREATE SEQUENCE IF NOT EXISTS Toad_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE Toad
(
    id          BIGINT  NOT NULL,
    age         INTEGER NOT NULL,
    name        VARCHAR(255),
    weight      INTEGER NOT NULL,
    gender      CHAR    NOT NULL,
    description VARCHAR(1000),
    birthday    date,
    CONSTRAINT pk_toad PRIMARY KEY (id)
);

DROP TABLE Toad;

