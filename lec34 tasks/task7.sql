CREATE TABLE task7_phone (
    id NUMBER,
    phone_number VARCHAR2(30),
    CONSTRAINT task7_phone_pk PRIMARY KEY (id)
);

CREATE TABLE task7_employee (
    id NUMBER,
    name VARCHAR2(100),
    age NUMBER,
    phone_id NUMBER NOT NULL,
    CONSTRAINT task7_employee_pk PRIMARY KEY (id),
    CONSTRAINT task7_employee_phone_uq UNIQUE (phone_id),
    CONSTRAINT task7_employee_phone_fk FOREIGN KEY (phone_id) REFERENCES task7_phone (id)
);
