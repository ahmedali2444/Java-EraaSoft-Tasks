SELECT c.name, o.order_id
FROM customers c
FULL OUTER JOIN orders o ON c.customer_id = o.customer_id;

SELECT e.name, p.project_name
FROM employees e
FULL OUTER JOIN project_assignments pa ON e.employee_id = pa.employee_id
FULL OUTER JOIN projects p ON pa.project_id = p.project_id;

SELECT p.product_name, s.name
FROM products p
FULL OUTER JOIN suppliers s ON p.supplier_id = s.supplier_id;

SELECT s.name, c.course_title
FROM students s
FULL OUTER JOIN enrollments en ON s.student_id = en.student_id
FULL OUTER JOIN courses c ON en.course_id = c.course_id;

SELECT a.name, b.title
FROM authors a
FULL OUTER JOIN books b ON a.author_id = b.author_id;

SELECT e.name, d.department_name
FROM employees e
FULL OUTER JOIN departments d ON e.department_id = d.department_id;

SELECT t.transaction_id, pm.method_name
FROM transactions t
FULL OUTER JOIN payment_methods pm ON t.payment_method_id = pm.payment_method_id;

SELECT c1.name AS region1_customer, c2.name AS region2_customer
FROM customers_region1 c1
FULL OUTER JOIN customers_region2 c2 ON c1.customer_id = c2.customer_id;
