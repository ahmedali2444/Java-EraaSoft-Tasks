CREATE USER student_user IDENTIFIED BY Student123;

GRANT CREATE SESSION, CREATE TABLE TO student_user;

ALTER USER student_user QUOTA UNLIMITED ON USERS;

CONNECT student_user/Student123

CREATE TABLE student (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100)
);

GRANT INSERT, SELECT, UPDATE, DELETE ON student TO hr;

CONNECT hr/hr

INSERT INTO student_user.student (id, name) VALUES (1, 'Student');

SELECT *
FROM student_user.student;

UPDATE student_user.student
SET name = 'New Student'
WHERE id = 1;

DELETE FROM student_user.student
WHERE id = 1;

REVOKE INSERT, SELECT, UPDATE, DELETE ON student_user.student FROM hr;

REVOKE CREATE SESSION, CREATE TABLE FROM student_user;
