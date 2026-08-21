SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.department_id;

SELECT p.product_name, c.name
FROM products p
LEFT JOIN categories c ON p.category_id = c.category_id;

SELECT s.name, c.course_title
FROM students s
LEFT JOIN enrollments en ON s.student_id = en.student_id
LEFT JOIN courses c ON en.course_id = c.course_id;

SELECT o.order_id, c.name
FROM orders o
LEFT JOIN customers c ON o.customer_id = c.customer_id;

SELECT d.department_name, m.name
FROM departments d
LEFT JOIN employees m ON d.manager_id = m.employee_id;

SELECT b.title, a.name
FROM books b
LEFT JOIN authors a ON b.author_id = a.author_id;

SELECT i.invoice_id, p.payment_status
FROM invoices i
LEFT JOIN payments p ON i.invoice_id = p.invoice_id;

SELECT e.name, p.project_name
FROM employees e
LEFT JOIN projects_assigned pa ON e.employee_id = pa.employee_id
LEFT JOIN projects p ON pa.project_id = p.project_id;
