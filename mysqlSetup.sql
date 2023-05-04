-- table creation code
CREATE TABLE instructor (
                            instructor_id INT PRIMARY KEY,
                            instructor_name VARCHAR(50),
                            instructor_email VARCHAR(50)
);


CREATE TABLE course (
                        course_id INT PRIMARY KEY,
                        course_name VARCHAR(50),
                        instructor_id INT,
                        max_num_students INT,
                        FOREIGN KEY (instructor_id) REFERENCES instructor(instructor_id)
);


CREATE TABLE student (
                         student_id INT PRIMARY KEY,
                         student_name VARCHAR(50),
                         student_email VARCHAR(50),
                         student_dob DATE
);


CREATE TABLE enrollment (
                            course_id INT,
                            student_id INT,
                            date_of_enrollment DATE,
                            PRIMARY KEY (course_id, student_id),
                            FOREIGN KEY (course_id) REFERENCES course(course_id),
                            FOREIGN KEY (student_id) REFERENCES student(student_id)
);


CREATE TABLE assignment (
                            assignment_id INT PRIMARY KEY,
                            assignment_name VARCHAR(50),
                            assignment_deadline DATE,
                            assignment_weightage FLOAT
);


CREATE TABLE submission (
                            assignment_id INT,
                            student_id INT,
                            date_of_submission DATE,
                            grade FLOAT,
                            PRIMARY KEY (assignment_id, student_id),
                            FOREIGN KEY (assignment_id) REFERENCES assignment(assignment_id),
                            FOREIGN KEY (student_id) REFERENCES student(student_id)
);

ALTER TABLE assignment
    ADD COLUMN course_id INT,
ADD CONSTRAINT fk_assignment_course
  FOREIGN KEY (course_id)
  REFERENCES course(course_id);

--data insertion
-- instructor table
INSERT INTO instructor VALUES
                           (1, 'John Smith', 'john.smith@example.com'),
                           (2, 'Jane Doe', 'jane.doe@example.com'),
                           (3, 'David Lee', 'david.lee@example.com'),
                           (4, 'Sarah Johnson', 'sarah.johnson@example.com'),
                           (5, 'Michael Brown', 'michael.brown@example.com'),
                           (6, 'Karen Davis', 'karen.davis@example.com'),
                           (7, 'Robert White', 'robert.white@example.com'),
                           (8, 'Jennifer Lee', 'jennifer.lee@example.com'),
                           (9, 'William Wilson', 'william.wilson@example.com'),
                           (10, 'Emily Jones', 'emily.jones@example.com');

-- course table
INSERT INTO course VALUES
                       (1, 'Introduction to Programming', 1, 50),
                       (2, 'Database Systems', 2, 30),
                       (3, 'Web Development', 3, 40),
                       (4, 'Data Structures and Algorithms', 4, 35),
                       (5, 'Operating Systems', 5, 25),
                       (6, 'Computer Networks', 6, 30),
                       (7, 'Software Engineering', 7, 40),
                       (8, 'Artificial Intelligence', 8, 20),
                       (9, 'Machine Learning', 9, 25),
                       (10, 'Computer Graphics', 10, 30);

-- student table
INSERT INTO student VALUES
                        (1, 'Amy Johnson', 'amy.johnson@example.com', '2000-01-01'),
                        (2, 'Benjamin Lee', 'benjamin.lee@example.com', '2001-02-02'),
                        (3, 'Catherine Davis', 'catherine.davis@example.com', '2002-03-03'),
                        (4, 'Daniel Smith', 'daniel.smith@example.com', '2003-04-04'),
                        (5, 'Evelyn Brown', 'evelyn.brown@example.com', '2004-05-05'),
                        (6, 'Frank Wilson', 'frank.wilson@example.com', '2005-06-06'),
                        (7, 'Grace Lee', 'grace.lee@example.com', '2006-07-07'),
                        (8, 'Henry Johnson', 'henry.johnson@example.com', '2007-08-08'),
                        (9, 'Isabella Davis', 'isabella.davis@example.com', '2008-09-09'),
                        (10, 'Jacob Brown', 'jacob.brown@example.com', '2009-10-10');

-- enrollment table
INSERT INTO enrollment VALUES
                           (1, 1, '2021-05-01'),
                           (2, 2, '2021-04-02'),
                           (3, 3, '2021-03-03'),
                           (4, 4, '2021-08-04'),
                           (5, 5, '2021-07-05'),
                           (6, 6, '2021-05-06'),
                           (7, 7, '2021-06-07'),
                           (8, 8, '2021-01-08'),
                           (9, 9, '2021-02-09'),
                           (10, 10, '2021-01-10'),
                           (1, 10, '2021-05-01'),
                           (2, 9, '2021-04-02'),
                           (3, 8, '2021-03-03'),
                           (4, 7, '2021-08-04'),
                           (5, 6, '2021-07-05'),
                           (6, 5, '2021-05-06'),
                           (7, 4, '2021-06-07'),
                           (8, 3, '2021-01-08'),
                           (9, 2, '2021-02-09'),
                           (10, 1, '2021-01-10');

INSERT INTO assignment VALUES
                           (1, 'Assignment 1', '2021-08-15', 10, 1),
                           (2, 'Assignment 1', '2021-09-15', 15, 2),
                           (3, 'Assignment 1', '2021-10-15', 20, 3),
                           (4, 'Assignment 1', '2021-11-15', 25, 4),
                           (5, 'Assignment 1', '2021-12-15', 30, 5),
                           (6, 'Assignment 1', '2021-08-31', 35, 6),
                           (7, 'Assignment 1', '2021-09-30', 40, 7),
                           (8, 'Assignment 1', '2021-10-31', 45, 8),
                           (9, 'Assignment 1', '2021-11-30', 50, 9),
                           (10, 'Assignment 1', '2021-12-31', 55, 10),
                           (11, 'Assignment 2', '2022-01-15', 10, 1),
                           (12, 'Assignment 2', '2022-02-15', 15, 2),
                           (13, 'Assignment 2', '2022-03-15', 20, 3),
                           (14, 'Assignment 2', '2022-04-15', 25, 4),
                           (15, 'Assignment 2', '2022-05-15', 30, 5),
                           (16, 'Assignment 2', '2022-01-31', 35, 6),
                           (17, 'Assignment 2', '2022-02-28', 40, 7),
                           (18, 'Assignment 2', '2022-03-31', 45, 8),
                           (19, 'Assignment 2', '2022-04-30', 50, 9),
                           (20, 'Assignment 2', '2022-05-31', 55, 10);

INSERT INTO submission VALUES
                           (1, 1, '2021-08-10', 85),
                           (2, 2, '2021-09-03', 70),
                           (3, 3, '2021-10-18', 90),
                           (4, 4, '2021-11-03', 80),
                           (5, 5, '2021-12-20', 95),
                           (6, 6, '2021-08-05', 75),
                           (7, 7, '2021-09-16', 60),
                           (8, 8, '2021-10-01', 70),
                           (9, 9, '2021-11-20', 90),
                           (10, 10, '2021-12-28', 85),
                           (11, 10, '2022-01-10', 85),
                           (12, 9, '2022-02-03', 70),
                           (13, 8, '2022-03-18', 90),
                           (14, 7, '2022-04-03', 80),
                           (15, 6, '2022-05-20', 95),
                           (16, 5, '2022-01-05', 75),
                           (17, 4, '2022-02-16', 60),
                           (18, 3, '2022-03-01', 70),
                           (19, 2, '2022-04-20', 90),
                           (20, 1, '2022-05-28', 85);

