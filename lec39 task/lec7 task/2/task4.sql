SELECT name
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);

SELECT c.name
FROM customers c
WHERE (SELECT COUNT(*) FROM orders o WHERE o.customer_id = c.customer_id) = (
    SELECT MAX(order_count)
    FROM (
        SELECT COUNT(*) AS order_count
        FROM orders
        GROUP BY customer_id
    )
);

SELECT p.product_name, p.price
FROM products p
WHERE p.price > ANY (
    SELECT p2.price
    FROM products p2
    JOIN categories c ON p2.category_id = c.category_id
    WHERE c.name = 'Accessories'
);

SELECT name
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE name = 'John Smith'
);

SELECT order_id
FROM orders
WHERE customer_id IN (
    SELECT customer_id
    FROM customers
    WHERE city = 'New York'
);

SELECT d.department_name
FROM departments d
WHERE NOT EXISTS (
    SELECT 1
    FROM employees e
    WHERE e.department_id = d.department_id
);

SELECT s.name
FROM students s
WHERE NOT EXISTS (
    SELECT 1
    FROM enrollments en
    WHERE en.student_id = s.student_id
);

SELECT MAX(salary) AS second_highest_salary
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);

SELECT product_name, price
FROM products
WHERE price > (SELECT AVG(price) FROM products);

SELECT c.name
FROM customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM products p
    JOIN categories ct ON p.category_id = ct.category_id
    WHERE ct.name = 'A'
      AND NOT EXISTS (
          SELECT 1
          FROM orders o
          JOIN order_details od ON o.order_id = od.order_id
          WHERE o.customer_id = c.customer_id
            AND od.product_id = p.product_id
      )
);
