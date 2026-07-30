CREATE TABLE task1_player (
    id NUMBER NOT NULL,
    name VARCHAR2(100) UNIQUE,
    age NUMBER,
    CONSTRAINT task1_player_pk PRIMARY KEY (id)
);

CREATE TABLE task1_manager_composite (
    id NUMBER NOT NULL,
    name VARCHAR2(100) NOT NULL,
    salary NUMBER,
    CONSTRAINT task1_manager_composite_uq UNIQUE (id, name)
);

CREATE TABLE task1_manager_primary (
    id NUMBER,
    name VARCHAR2(100),
    age NUMBER,
    CONSTRAINT task1_manager_primary_pk PRIMARY KEY (id)
);
