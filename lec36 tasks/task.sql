-- 1. CHECK Constraints
CREATE TABLE employees (
    employee_id NUMBER CONSTRAINT pk_employees PRIMARY KEY,
    age NUMBER CONSTRAINT chk_employee_age CHECK (age >= 18),
    salary NUMBER CONSTRAINT chk_salary CHECK (salary > 0),
    department_id NUMBER
);

CREATE TABLE staff (
    staff_id NUMBER CONSTRAINT pk_staff PRIMARY KEY,
    salary NUMBER CONSTRAINT chk_staff_salary CHECK (salary BETWEEN 3000 AND 10000)
);

CREATE TABLE products (
    product_id NUMBER CONSTRAINT pk_products PRIMARY KEY,
    price NUMBER
);

ALTER TABLE products ADD CONSTRAINT chk_product_price CHECK (price > 0);

CREATE TABLE students (
    student_id NUMBER CONSTRAINT pk_students PRIMARY KEY,
    age NUMBER CONSTRAINT chk_age CHECK (age >= 18),
    grade VARCHAR2(1) CONSTRAINT chk_grade CHECK (grade IN ('A', 'B', 'C', 'D', 'E', 'F'))
);

-- 2. Adding Constraints via ALTER TABLE
CREATE TABLE customers (
    id NUMBER CONSTRAINT pk_customers PRIMARY KEY,
    email VARCHAR2(100)
);

ALTER TABLE customers MODIFY (email CONSTRAINT customers_email_nn NOT NULL);

CREATE TABLE users (
    id NUMBER CONSTRAINT pk_users PRIMARY KEY,
    username VARCHAR2(50),
    email VARCHAR2(100) CONSTRAINT uk_users_email UNIQUE
);

ALTER TABLE users ADD CONSTRAINT uk_username UNIQUE (username);

CREATE TABLE orders (
    order_id NUMBER CONSTRAINT pk_orders PRIMARY KEY,
    customer_id NUMBER
);

ALTER TABLE orders ADD CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers (id);

CREATE TABLE accounts (
    account_id NUMBER CONSTRAINT pk_accounts PRIMARY KEY,
    balance NUMBER
);

ALTER TABLE accounts ADD CONSTRAINT chk_balance CHECK (balance >= 0);

CREATE TABLE departments (
    dept_id NUMBER
);

ALTER TABLE departments ADD CONSTRAINT pk_departments PRIMARY KEY (dept_id);

-- 3. Dropping Constraints
ALTER TABLE employees DROP CONSTRAINT chk_salary;

ALTER TABLE users DROP CONSTRAINT uk_users_email;

ALTER TABLE products DROP CONSTRAINT pk_products;

ALTER TABLE orders DROP CONSTRAINT fk_order_customer;

CREATE TABLE contacts (
    contact_id NUMBER CONSTRAINT pk_contacts PRIMARY KEY,
    phone VARCHAR2(30) NOT NULL
);

ALTER TABLE contacts MODIFY (phone NULL);

-- 4. Renaming Constraints
ALTER TABLE students RENAME CONSTRAINT chk_age TO check_min_age;

ALTER TABLE employees ADD CONSTRAINT fk_emp_dept FOREIGN KEY (department_id) REFERENCES departments (dept_id);

ALTER TABLE employees RENAME CONSTRAINT fk_emp_dept TO fk_employee_department;

ALTER TABLE users RENAME CONSTRAINT pk_users TO pk_users_id;

ALTER TABLE users RENAME CONSTRAINT uk_username TO uk_user_name;

-- SQL Server: EXEC sp_rename 'students.chk_age', 'check_min_age', 'OBJECT';
-- PostgreSQL: ALTER TABLE students RENAME CONSTRAINT chk_age TO check_min_age;

-- 5. Disabling Constraints
ALTER TABLE orders ADD CONSTRAINT fk_customer_order FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE orders DISABLE CONSTRAINT fk_customer_order;

ALTER TABLE products DISABLE ALL CONSTRAINTS;

ALTER TABLE accounts DISABLE CONSTRAINT chk_balance;

ALTER TABLE departments DISABLE CONSTRAINT pk_departments CASCADE;

-- 6. Enabling Constraints
ALTER TABLE orders ENABLE CONSTRAINT fk_customer_order;

ALTER TABLE products ENABLE ALL CONSTRAINTS;

ALTER TABLE staff ENABLE CONSTRAINT chk_staff_salary;

ALTER TABLE departments ENABLE CONSTRAINT pk_departments;

DECLARE
    constraint_status USER_CONSTRAINTS.STATUS%TYPE;
BEGIN
    SELECT status
    INTO constraint_status
    FROM user_constraints
    WHERE constraint_name = 'CHK_BALANCE';

    IF constraint_status = 'DISABLED' THEN
        EXECUTE IMMEDIATE 'ALTER TABLE accounts ENABLE CONSTRAINT chk_balance';
    END IF;
END;
/
