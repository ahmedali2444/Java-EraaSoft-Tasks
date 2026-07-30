CREATE TABLE task5_doctor (
    id NUMBER,
    name VARCHAR2(100),
    salary NUMBER,
    CONSTRAINT task5_doctor_pk PRIMARY KEY (id)
);

CREATE TABLE task5_patient (
    id NUMBER,
    name VARCHAR2(100),
    age NUMBER,
    CONSTRAINT task5_patient_pk PRIMARY KEY (id)
);

CREATE TABLE task5_doctor_patient (
    doctor_id NUMBER,
    patient_id NUMBER,
    CONSTRAINT task5_doctor_patient_pk PRIMARY KEY (doctor_id, patient_id),
    CONSTRAINT task5_doctor_patient_doctor_fk FOREIGN KEY (doctor_id) REFERENCES task5_doctor (id),
    CONSTRAINT task5_doc_pat_patient_fk FOREIGN KEY (patient_id) REFERENCES task5_patient (id)
);
