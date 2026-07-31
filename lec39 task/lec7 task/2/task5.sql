SELECT name, salary
FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);

SELECT name
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE name = 'Alice'
);

SELECT product_name, price
FROM products
WHERE price = (SELECT MIN(price) FROM products);

SELECT department_name
FROM departments
WHERE department_id IN (
    SELECT department_id
    FROM employees
    WHERE salary = (SELECT MAX(salary) FROM employees)
);

SELECT m.name
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
WHERE e.hire_date = (SELECT MAX(hire_date) FROM employees);

SELECT name, salary
FROM employees
WHERE salary = (SELECT AVG(salary) FROM employees);

SELECT order_id, order_date
FROM orders
WHERE order_date = (SELECT MIN(order_date) FROM orders);

SELECT name, salary
FROM employees
WHERE salary > (
    SELECT salary
    FROM employees
    WHERE employee_id = 101
);

SELECT name, gpa
FROM students
WHERE gpa = (
    SELECT gpa
    FROM students
    WHERE name = 'John Doe'
);

SELECT b.title, b.price
FROM books b
JOIN categories c ON b.category_id = c.category_id
WHERE b.price = (
    SELECT MAX(b2.price)
    FROM books b2
    JOIN categories c2 ON b2.category_id = c2.category_id
    WHERE c2.name = 'Science'
);
