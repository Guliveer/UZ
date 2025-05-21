-- Zadanie 03


-- region
INSERT INTO region (id, name) VALUES
    (1, 'North America'),
    (2, 'Europe'),
    (3, 'Asia'),
    (4, 'South America'),
    (5, 'Australia');


-- dept
INSERT INTO dept (id, name, region_id) VALUES
    (1, 'Sales', 1),
    (2, 'Marketing', 1),
    (3, 'Engineering', 2),
    (4, 'Support', 3),
    (5, 'Finance', 1),
    (6, 'IT', 2),
    (7, 'Legal', 1),
    (8, 'Operations', 4),
    (9, 'Research', 2),
    (10, 'Logistics', 4),
    (11, 'HR', 3),
    (12, 'Customer Success', 1),
    (13, 'Procurement', 3),
    (14, 'Analytics', 2),
    (15, 'QA', 2);


-- emp
-- Styl 1: pełna lista kolumn
INSERT INTO emp (id, last_name, first_name, userid, start_date, comments, manager_id, title, dept_id, salary, commission_pct) VALUES
    (1, 'Smith', 'John', 'jsmith', '2020-01-15', NULL, NULL, 'CEO', 1, 150000.00, NULL),
    (2, 'Taylor', 'Alice', 'ataylor', '2021-03-22', NULL, 1, 'CTO', 1, 130000.00, NULL),
    (3, 'Brown', 'Charlie', 'cbrown', '2022-07-10', NULL, 2, 'Manager', 2, 95000.00, NULL),
    (4, 'White', 'Emily', 'ewhite', '2023-01-05', NULL, 3, 'Engineer', 1, 85000.00, 0.05),
    (5, 'Green', 'Daniel', 'dgreen', '2021-06-19', NULL, 3, 'Engineer', 1, 87000.00, 0.07),
    (6, 'Black', 'Sophia', 'sblack', '2020-11-03', NULL, 3, 'Marketing Specialist', 2, 65000.00, NULL),
    (7, 'King', 'Oliver', 'oking', '2023-04-28', NULL, 3, 'Intern', 1, 20000.00, NULL),
    (8, 'Wright', 'Mia', 'mwright', '2021-09-15', NULL, 2, 'HR Specialist', 4, 60000.00, NULL),
    (9, 'Scott', 'Jacob', 'jscott', '2022-05-20', NULL, 2, 'Engineer', 1, 88000.00, NULL),
    (10, 'Bell', 'Emma', 'ebell', '2019-08-13', NULL, 3, 'Sales Representative', 3, 62000.00, 0.1);

-- Styl 2: insert bez nazw kolumn (kolejność musi odpowiadać tabeli!)
INSERT INTO emp VALUES
    (11, 'Adams', 'Grace', 'gadams', '2020-02-11', NULL, 3, 'Engineer', 1, 83000.00, NULL),
    (12, 'Baker', 'Henry', 'hbaker', '2021-10-01', NULL, 3, 'Engineer', 1, 81000.00, 0.05),
    (13, 'Carter', 'Liam', 'lcarter', '2020-12-20', NULL, 3, 'Engineer', 1, 80000.00, NULL),
    (14, 'Davis', 'Ella', 'edavis', '2022-06-08', NULL, 3, 'Engineer', 1, 79000.00, NULL),
    (15, 'Evans', 'Noah', 'nevans', '2023-02-15', NULL, 3, 'Engineer', 1, 77000.00, NULL),
    (16, 'Foster', 'Ava', 'afoster', '2022-09-25', NULL, 3, 'Marketing Specialist', 2, 63000.00, NULL),
    (17, 'Graham', 'Mason', 'mgraham', '2020-10-30', NULL, 3, 'Sales Representative', 3, 61000.00, 0.12),
    (18, 'Hall', 'Isabella', 'ihall', '2021-04-18', NULL, 3, 'Sales Representative', 3, 60000.00, 0.09),
    (19, 'Irwin', 'James', 'jirwin', '2020-07-07', NULL, 3, 'Engineer', 1, 75000.00, NULL),
    (20, 'Jones', 'Lily', 'ljones', '2023-03-12', NULL, 3, 'Intern', 1, 18000.00, NULL);


-- customer
INSERT INTO customer (id, name, region_id) VALUES
    (1, 'Beisbol Si!', 4),
    (2, 'Sweet Rock Sports', 1),
    (3, 'Sporta Russia', 3),
    (4, 'Womansport', 2),
    (5, 'Hamada Sport', 3);