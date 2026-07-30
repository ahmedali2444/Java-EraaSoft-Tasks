CREATE TABLE task3_language (
    id NUMBER,
    name VARCHAR2(100),
    CONSTRAINT task3_language_pk PRIMARY KEY (id)
);

CREATE TABLE task3_teacher (
    id NUMBER,
    name VARCHAR2(100),
    salary NUMBER,
    language_id NUMBER NOT NULL,
    CONSTRAINT task3_teacher_pk PRIMARY KEY (id),
    CONSTRAINT task3_teacher_language_fk FOREIGN KEY (language_id) REFERENCES task3_language (id)
);
