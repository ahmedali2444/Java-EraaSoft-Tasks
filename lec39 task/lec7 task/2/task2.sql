SELECT d.department_name, e.name
FROM employees e
RIGHT JOIN departments d ON e.department_id = d.department_id;

SELECT c.name, o.order_id
FROM orders o
RIGHT JOIN customers c ON o.customer_id = c.customer_id;

SELECT c.course_title, s.name
FROM students s
RIGHT JOIN enrollments en ON s.student_id = en.student_id
RIGHT JOIN courses c ON en.course_id = c.course_id;

SELECT p.project_name, e.name
FROM employees e
RIGHT JOIN project_assignments pa ON e.employee_id = pa.employee_id
RIGHT JOIN projects p ON pa.project_id = p.project_id;

SELECT pm.method_name, t.transaction_id
FROM transactions t
RIGHT JOIN payment_methods pm ON t.payment_method_id = pm.payment_method_id;

SELECT a.name, b.title
FROM books b
RIGHT JOIN authors a ON b.author_id = a.author_id;

SELECT c.name, p.product_name
FROM products p
RIGHT JOIN categories c ON p.category_id = c.category_id;

SELECT d.room_number, s.name
FROM students s
RIGHT JOIN dorm_rooms d ON s.dorm_id = d.dorm_id;
