SELECT e.name, d.department_name
FROM employees e NATURAL JOIN departments d;

SELECT o.order_id, c.name
FROM orders o NATURAL JOIN customers c;

SELECT s.name, c.course_title
FROM students s NATURAL JOIN enrollments en NATURAL JOIN courses c;

SELECT p.project_name, e.name
FROM projects p NATURAL JOIN project_assignments pa NATURAL JOIN employees e;

SELECT i.invoice_id, p.product_name
FROM invoices i NATURAL JOIN products p;

SELECT b.title, a.name
FROM books b NATURAL JOIN authors a;

SELECT cs.schedule_id, i.name
FROM class_schedules cs NATURAL JOIN instructors i;

SELECT s.name, p.product_name
FROM suppliers s NATURAL JOIN products p;

SELECT c.name, o.order_id, sd.shipping_address
FROM customers c NATURAL JOIN orders o NATURAL JOIN shipping_details sd;

SELECT e.name, j.job_title
FROM employees e NATURAL JOIN jobs j;
