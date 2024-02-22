INSERT INTO department (dept_name, create_date, dept_head_id) VALUES('Engineering', '2024-01-01', NULL),('Sales', '2024-01-01', NULL),('Marketing', '2024-01-01', NULL);

INSERT INTO employee (emp_name, dob, salary, department_id, title, joining_date, bonus_percentage, manager_id) VALUES ('John Doe', '1990-05-15', 60000, 1, 'Engineering Manager', '2020-01-01', 5.00, NULL),('Jane Smith', '1988-09-20', 55000, 1, 'Senior Engineer', '2019-02-01', 6.00, 1),('Michael Johnson', '1992-03-10', 62000, 1, 'Software Developer', '2021-03-01', 4.50, 1),('Emily Davis', '1995-11-25', 58000, 1, 'Software Engineer', '2021-04-15', 4.00, 1),('Robert Brown', '1993-07-12', 59000, 1, 'Junior Engineer', '2022-05-01', 3.50, 1),('Jennifer Wilson', '1991-02-18', 63000, 2, 'Sales Manager', '2018-06-01', 7.00, NULL),('William Taylor', '1987-12-05', 60000, 2, 'Sales Executive', '2020-07-01', 6.50, 6),('Jessica Martinez', '1994-08-30', 57000, 2, 'Sales Representative', '2021-08-15', 5.50, 6),('David Anderson', '1989-06-08', 61000, 2, 'Sales Associate', '2019-04-01', 5.00, 6),('Linda Thomas', '1990-04-03', 62000, 2, 'Senior Sales Executive', '2017-09-01', 6.00, 6),('Christopher Harris', '1992-10-22', 59000, 3, 'Marketing Manager', '2018-01-01', 6.50, NULL),('Amanda Clark', '1986-01-17', 58000, 3, 'Marketing Specialist', '2019-03-01', 5.50, 11),('Daniel Rodriguez', '1993-09-15', 56000, 3, 'Marketing Coordinator', '2020-02-15', 5.00, 11),('Melissa Lee', '1985-07-08', 54000, 3, 'Marketing Executive', '2016-05-01', 4.50, 11),('Steven Walker', '1991-03-27', 57000, 3, 'Marketing Assistant', '2017-08-01', 4.00, 11),('Sarah Johnson', '1994-06-20', 65000, 1, 'Senior Software Engineer', '2020-10-01', 6.50, 1),('Andrew Wilson', '1993-09-15', 63000, 1, 'Software Engineer', '2021-02-15', 5.00, 1),('Emma Thompson', '1991-05-02', 60000, 1, 'Software Developer', '2019-08-01', 4.50, 1),('Olivia Martinez', '1990-08-18', 62000, 1, 'Software Engineer', '2021-04-01', 4.00, 1),('James Clark', '1988-03-10', 59000, 1, 'Junior Engineer', '2022-01-01', 3.50, 1),('Sophia Walker', '1995-01-27', 64000, 2, 'Sales Executive', '2018-09-01', 6.00, NULL),('Ethan Harris', '1992-11-14', 62000, 2, 'Sales Representative', '2020-05-15', 5.50, NULL),('Isabella Rodriguez', '1989-07-08', 61000, 2, 'Sales Associate', '2019-03-01', 5.00, NULL),('Mia King', '1993-04-25', 59000, 2, 'Senior Sales Executive', '2017-07-01', 6.50, NULL),('Alexander Young', '1990-12-12', 60000, 2, 'Sales Manager', '2016-04-01', 7.00, NULL);

UPDATE department SET dept_head_id = 1 WHERE dept_name = 'Engineering';
UPDATE department SET dept_head_id = 6 WHERE dept_name = 'Sales';
UPDATE department SET dept_head_id = 11 WHERE dept_name = 'Marketing';

UPDATE employee SET manager_id = 22 WHERE emp_id = 21;
UPDATE employee SET manager_id = 23 WHERE emp_id = 22;
UPDATE employee SET manager_id = 24 WHERE emp_id = 23;
UPDATE employee SET manager_id = 25 WHERE emp_id = 24;