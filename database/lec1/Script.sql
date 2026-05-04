-- point 1) create table Manger contain
CREATE TABLE manager (
	id NUMBER PRIMARY KEY,
	name VARCHAR2(100),
	age NUMBER,
	birth_date DATE ,
	address VARCHAR2(200)
);

------------------------------------------
-- point 2) alter table manger drop address column & alter table manger add column (city_address, street) 

ALTER TABLE manager DROP COLUMN address;

ALTER TABLE manager ADD (
	city_address VARCHAR2(100),
	street VARCHAR2(100)
);

------------------------------------
-- point 3) modify column name to full_name

ALTER TABLE MANAGER RENAME COLUMN name TO full_name;

--------------------------------------
-- point 4) make this table just for read

ALTER TABLE manager READ ONLY;

----------------------
-- point 5) create table same as  Manger with name Owner just has colum id, name, birth_date 

CREATE TABLE owner AS
SELECT 
    id,
    full_name AS name,
    birth_date
FROM manager;

--------------------------------
-- point 6) rename manger table name to Master

RENAME manager TO master;
--------------
-- point 7) drop all tables

DROP TABLE master;
DROP TABLE owner;





