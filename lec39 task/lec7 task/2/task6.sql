SELECT name, salary
FROM employees
WHERE salary > ANY (
    SELECT salary
    FROM employees
    WHERE department_id = 10
);

SELECT name, salary
FROM employees
WHERE salary < ALL (
    SELECT salary
    FROM employees
    WHERE department_id = 20
);

SELECT product_name, price
FROM products
WHERE price IN (
    SELECT p.price
    FROM products p
    JOIN categories c ON p.category_id = c.category_id
    WHERE c.name = 'Electronics'
);

SELECT c.name
FROM customers c
WHERE c.customer_id IN (
    SELECT o.customer_id
    FROM orders o
    JOIN order_details od ON o.order_id = od.order_id
    JOIN products p ON od.product_id = p.product_id
    WHERE p.price > 1000
);

SELECT name, job_title
FROM employees
WHERE job_title IN (
    SELECT job_title
    FROM employees
    GROUP BY job_title
    HAVING COUNT(*) > 1
);

SELECT department_name
FROM departments
WHERE department_id IN (
    SELECT department_id
    FROM employees
    GROUP BY department_id
    HAVING COUNT(*) > 1
);

SELECT o.order_id, c.name, c.city
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
WHERE c.city IN (
    SELECT c2.city
    FROM customers c2
    WHERE EXISTS (
        SELECT 1
        FROM orders o2
        WHERE o2.customer_id = c2.customer_id
    )
    GROUP BY c2.city
    HAVING COUNT(DISTINCT c2.customer_id) > 1
);

SELECT b.title, a.name
FROM books b
JOIN authors a ON b.author_id = a.author_id
WHERE b.author_id IN (
    SELECT author_id
    FROM books
    GROUP BY author_id
    HAVING COUNT(*) > 1
);

SELECT s.name
FROM students s
WHERE s.student_id IN (
    SELECT en.student_id
    FROM enrollments en
    JOIN courses c ON en.course_id = c.course_id
    JOIN instructors i ON c.instructor_id = i.instructor_id
    WHERE i.name = 'Dr. Smith'
);

SELECT name, salary
FROM employees
WHERE salary IN (
    SELECT salary
    FROM employees
    WHERE department_id = 30
);
