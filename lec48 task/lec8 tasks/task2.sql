-- Natural Join
SELECT job_title, employee_id
FROM jobs NATURAL JOIN job_history;

-- Join Using
SELECT job_title, employee_id
FROM jobs JOIN job_history USING (job_id);

-- Join On
SELECT j.job_title, jh.employee_id
FROM jobs j JOIN job_history jh ON j.job_id = jh.job_id;

-- Inner Join
SELECT j.job_title, jh.employee_id
FROM jobs j INNER JOIN job_history jh ON j.job_id = jh.job_id;

-- Left Join
SELECT j.job_title, jh.employee_id
FROM jobs j LEFT JOIN job_history jh ON j.job_id = jh.job_id;

-- Right Join
SELECT j.job_title, jh.employee_id
FROM jobs j RIGHT JOIN job_history jh ON j.job_id = jh.job_id;

-- Full Join
SELECT j.job_title, jh.employee_id
FROM jobs j FULL OUTER JOIN job_history jh ON j.job_id = jh.job_id;
