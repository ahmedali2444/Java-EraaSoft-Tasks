-- Natural Join
SELECT country_name, city
FROM countries NATURAL JOIN locations;

-- Join Using
SELECT country_name, city
FROM countries JOIN locations USING (country_id);

-- Join On
SELECT c.country_name, l.city
FROM countries c JOIN locations l ON c.country_id = l.country_id;

-- Inner Join
SELECT c.country_name, l.city
FROM countries c INNER JOIN locations l ON c.country_id = l.country_id;

-- Left Join
SELECT c.country_name, l.city
FROM countries c LEFT JOIN locations l ON c.country_id = l.country_id;

-- Right Join
SELECT c.country_name, l.city
FROM countries c RIGHT JOIN locations l ON c.country_id = l.country_id;

-- Full Join
SELECT c.country_name, l.city
FROM countries c FULL OUTER JOIN locations l ON c.country_id = l.country_id;
