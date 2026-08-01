-- 1. Find all employee where the emp_id is within a certain range 100 and 105.
SELECT *
FROM employees
WHERE employee_id BETWEEN 100 AND 105;

-- 2. Find all employee that belong to a specific set of emp_id 151, 152, 153, 154, 155.
SELECT *
FROM employees
WHERE employee_id IN (151, 152, 153, 154, 155);

-- 3. Retrieve all employee where the employee first_name starts with the letter 'P' OR 'p'.
SELECT *
FROM employees
WHERE UPPER(first_name) LIKE 'P%';

-- 4. Retrieve all employee where the employee first_name end with the letter 'A' OR 'a'.
SELECT *
FROM employees
WHERE UPPER(first_name) LIKE '%A';

-- 5. Retrieve all employee where the employee first_name contains the letter 'A' OR 'a'.
SELECT *
FROM employees
WHERE UPPER(first_name) LIKE '%A%';

-- 6. Retrieve all employee where the employee first_name third char is the letter 'E' OR 'e'.
SELECT *
FROM employees
WHERE UPPER(first_name) LIKE '__E%';

-- 7. Retrieve all employees who don't have a manager assigned.
SELECT *
FROM employees
WHERE manager_id IS NULL;

-- 8. Find all employees who have a manager assigned.
SELECT *
FROM employees
WHERE manager_id IS NOT NULL;

-- 9. Insert a new employee without assigning a manager.
INSERT INTO employees (
    employee_id,
    first_name,
    last_name,
    email,
    hire_date,
    job_id,
    manager_id
)
VALUES (207, 'Adam', 'Ali', 'AALI207', SYSDATE, 'IT_PROG', NULL);

-- 10. Find all employees who work either in the 'AD_VP' JOB_ID or the 'IT_PROG' JOB_ID.
SELECT *
FROM employees
WHERE job_id IN ('AD_VP', 'IT_PROG');

-- 11. Retrieve all employees sorted by their last_name in ascending order.
SELECT *
FROM employees
ORDER BY last_name ASC;

-- 12. Retrieve all employees sorted by their hire_date in descending order.
SELECT *
FROM employees
ORDER BY hire_date DESC;

-- 13. Sort employees first by department in ascending order and then by salary in descending order within each department_id.
SELECT *
FROM employees
ORDER BY department_id ASC, salary DESC;

-- 14. Retrieve all employees with their last_name in lowercase.
SELECT LOWER(last_name)
FROM employees;

-- 15. Retrieve all employees with their first_name in uppercase.
SELECT UPPER(first_name)
FROM employees;

-- 16. Retrieve all employees with their first_name and last_name in title case.
SELECT INITCAP(first_name), INITCAP(last_name)
FROM employees;

-- 17. Find employees whose last_name is 'smith', regardless of the case.
SELECT *
FROM employees
WHERE UPPER(last_name) = 'SMITH';
