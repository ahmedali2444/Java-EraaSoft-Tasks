CREATE TABLE task6_language (
    id NUMBER,
    name VARCHAR2(100),
    CONSTRAINT task6_language_pk PRIMARY KEY (id)
);

CREATE TABLE task6_teacher (
    id NUMBER,
    name VARCHAR2(100),
    salary NUMBER,
    language_id NUMBER NOT NULL,
    CONSTRAINT task6_teacher_pk PRIMARY KEY (id),
    CONSTRAINT task6_teacher_language_fk FOREIGN KEY (language_id) REFERENCES task6_language (id)
);
