SELECT e.employee_id,
       e.name AS employee_name,
       m.employee_id AS manager_id,
       m.name AS manager_name
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id;

SELECT c.name AS customer_name, e.name AS salesperson_name
FROM customers c
JOIN employees e ON c.salesperson_id = e.employee_id;

SELECT o.order_id, od.product_id
FROM orders o
JOIN order_details od ON o.order_id = od.order_id;

SELECT s.name AS student_name, i.name AS instructor_name
FROM students s
JOIN instructors i ON s.instructor_id = i.instructor_id;

SELECT e.name AS employee_name, e.salary, d.budget
FROM employees e
JOIN departments d ON e.department_id = d.department_id;

SELECT p.name AS project_name, t.name AS task_name
FROM projects p
JOIN tasks t ON p.project_id = t.project_id;

SELECT c.course_title, c.start_date AS course_date, e.exam_date
FROM courses c
JOIN exams e ON c.course_id = e.course_id;

SELECT p.name AS product_name, c.name AS category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id;

SELECT b.title AS book_title, p.name AS publisher_name
FROM books b
JOIN publishers p ON b.publisher_id = p.publisher_id;

SELECT e.name AS employee_name,
       e.location AS employee_location,
       d.location AS department_location
FROM employees e
JOIN departments d ON e.department_id = d.department_id;
