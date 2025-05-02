USE SISDB;

SELECT 
    Students.first_name, 
    Students.last_name,
    SUM(Payments.amount) AS total_amount
FROM 
    Payments
JOIN 
    Students ON Students.student_id = Payments.student_id
WHERE 
    Students.student_id = 4
GROUP BY 
    Students.first_name, Students.last_name;

SELECT 
    Courses.course_id,
    Courses.course_name,
    COUNT(Enrollments.student_id) AS student_count
FROM 
    Courses
JOIN 
    Enrollments ON Courses.course_id = Enrollments.course_id
GROUP BY 
    Courses.course_id, Courses.course_name;
    
    SELECT 
    Students.first_name, 
    Students.last_name
FROM 
    Students
LEFT JOIN 
    Enrollments ON Students.student_id = Enrollments.student_id
WHERE 
    Enrollments.course_id IS NULL;
    
    SELECT 
    Students.first_name,
    Students.last_name,
    Courses.course_name
FROM 
    Students
JOIN 
    Enrollments ON Students.student_id = Enrollments.student_id
JOIN 
    Courses ON Enrollments.course_id = Courses.course_id;

SELECT 
    Teacher.first_name,
    Teacher.last_name,
    Courses.course_name
FROM 
    Teacher
JOIN 
    Courses ON Teacher.teacher_id = Courses.teacher_id;

SELECT 
    Students.first_name,
    Students.last_name,
    Enrollments.enrollment_date,
    Courses.course_name
FROM 
    Students
JOIN 
    Enrollments ON Students.student_id = Enrollments.student_id
JOIN 
    Courses ON Enrollments.course_id = Courses.course_id
WHERE 
    Courses.course_id = 3;
    
SELECT 
    Students.first_name,
    Students.last_name
FROM 
    Students
LEFT JOIN 
    Payments ON Students.student_id = Payments.student_id
WHERE 
    Payments.payment_id IS NULL;

SELECT 
    Courses.course_id,
    Courses.course_name
FROM 
    Courses
LEFT JOIN 
    Enrollments ON Courses.course_id = Enrollments.course_id
WHERE 
    Enrollments.student_id IS NULL;

SELECT 
    e1.student_id
FROM 
    Enrollments e1
JOIN 
    Enrollments e2 
    ON e1.student_id = e2.student_id 
    AND e1.course_id <> e2.course_id;

SELECT 
    Teacher.first_name,
    Teacher.last_name
FROM 
    Teacher
LEFT JOIN 
    Courses ON Teacher.teacher_id = Courses.teacher_id
WHERE 
    Courses.course_id IS NULL;

SELECT * FROM Students;
SELECT * FROM Enrollments;
SELECT * FROM Courses;
SELECT * FROM Teacher;
SELECT * FROM Payments;


