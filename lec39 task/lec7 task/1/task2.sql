SELECT e.name, d.department_name
FROM employees e JOIN departments d USING (department_id);

SELECT o.order_id, c.name
FROM orders o JOIN customers c USING (customer_id);

SELECT p.product_name, s.name
FROM products p JOIN suppliers s USING (supplier_id);

SELECT s.name, c.course_title
FROM students s
JOIN enrollments en USING (student_id)
JOIN courses c USING (course_id);

SELECT i.invoice_number, p.product_name
FROM invoices i JOIN products p USING (product_id);

SELECT p.project_name, e.name
FROM projects p
JOIN project_assignments pa USING (project_id)
JOIN employees e USING (employee_id);

SELECT a.name, b.title
FROM authors a JOIN books b USING (author_id);

SELECT so.order_id, e.name
FROM sales_orders so JOIN employees e USING (employee_id);

SELECT cs.schedule_id, i.name
FROM course_schedules cs JOIN instructors i USING (instructor_id);

SELECT t.transaction_id, a.account_holder_name
FROM transactions t JOIN accounts a USING (account_id);
