CREATE TABLE task2_doctor (
    id NUMBER,
    name VARCHAR2(100),
    salary NUMBER,
    CONSTRAINT task2_doctor_pk PRIMARY KEY (id)
);

CREATE TABLE task2_patient (
    id NUMBER,
    name VARCHAR2(100),
    age NUMBER,
    CONSTRAINT task2_patient_pk PRIMARY KEY (id)
);

CREATE TABLE task2_doctor_patient (
    doctor_id NUMBER,
    patient_id NUMBER,
    CONSTRAINT task2_doctor_patient_pk PRIMARY KEY (doctor_id, patient_id),
    CONSTRAINT task2_doctor_patient_doctor_fk FOREIGN KEY (doctor_id) REFERENCES task2_doctor (id),
    CONSTRAINT task2_doc_pat_patient_fk FOREIGN KEY (patient_id) REFERENCES task2_patient (id)
);
