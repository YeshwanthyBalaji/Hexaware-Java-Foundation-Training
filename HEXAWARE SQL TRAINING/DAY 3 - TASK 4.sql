USE SISDB;

SELECT 
    AVG(student_count) AS average_students_per_course
FROM (
    SELECT 
        course_id,
        COUNT(student_id) AS student_count
    FROM Enrollments
    GROUP BY course_id
) avg_enrollment;

SELECT 
    Students.student_id,
    Students.first_name,
    Students.last_name,
    Payments.amount,
    Payments.payment_date
FROM 
    Students 
JOIN 
    Payments ON Students.student_id = Payments.student_id
WHERE 
    Payments.amount = (
        SELECT MAX(amount)
        FROM Payments
    );
    
SELECT 
    c.course_id,
    c.course_name,
    COUNT(e.enrollment_id) AS enrollment_count
FROM 
    Courses c
JOIN 
    Enrollments e ON c.course_id = e.course_id
GROUP BY 
    c.course_id, c.course_name
HAVING 
    COUNT(e.enrollment_id) = (
        SELECT 
            MAX(course_enroll_count)
        FROM (
            SELECT 
                COUNT(enrollment_id) AS course_enroll_count
            FROM Enrollments
            GROUP BY course_id
        ) AS enrollment_counts
    );

SELECT 
    t.teacher_id,
    t.first_name,
    t.last_name,
    SUM(p.amount) AS total_payments
FROM 
    Teacher t
JOIN 
    Courses c ON t.teacher_id = c.teacher_id
JOIN 
    Enrollments e ON c.course_id = e.course_id
JOIN 
    Payments p ON e.student_id = p.student_id
GROUP BY 
    t.teacher_id, t.first_name, t.last_name;

SELECT 
    s.student_id,
    s.first_name,
    s.last_name
FROM 
    Students s
WHERE 
    (
        SELECT COUNT(DISTINCT e.course_id)
        FROM Enrollments e
        WHERE e.student_id = s.student_id
    ) = (
        SELECT COUNT(*) FROM Courses
    );

SELECT 
    t.teacher_id,
    t.first_name,
    t.last_name
FROM 
    Teacher t
WHERE 
    t.teacher_id NOT IN (
        SELECT DISTINCT teacher_id FROM Courses
    );

SELECT 
    AVG(student_age) AS average_age
FROM (
    SELECT 
        TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS student_age
    FROM 
        Students
) AS age_table;

SELECT 
    c.course_id,
    c.course_name
FROM 
    Courses c
WHERE 
    c.course_id NOT IN (
        SELECT DISTINCT course_id
        FROM Enrollments
    );

SELECT 
    s.student_id,
    s.first_name,
    s.last_name,
    e.course_id,
    c.course_name,
    (
        SELECT SUM(p.amount)
        FROM Payments p
        WHERE p.student_id = s.student_id
        AND p.student_id IN (
            SELECT e2.student_id
            FROM Enrollments e2
            WHERE e2.course_id = e.course_id
        )
    ) AS total_payments
FROM 
    Students s
JOIN 
    Enrollments e ON s.student_id = e.student_id
JOIN 
    Courses c ON e.course_id = c.course_id;

SELECT 
    s.student_id,
    s.first_name,
    s.last_name
FROM 
    Students s
WHERE 
    (
        SELECT COUNT(*)
        FROM Payments p
        WHERE p.student_id = s.student_id
    ) > 1;

SELECT 
    s.student_id,
    s.first_name,
    s.last_name,
    SUM(p.amount) AS total_payments
FROM 
    Students s
JOIN 
    Payments p ON s.student_id = p.student_id
GROUP BY 
    s.student_id, s.first_name, s.last_name;

SELECT 
    c.course_name,
    COUNT(e.student_id) AS enrollment_count
FROM 
    Courses c
JOIN 
    Enrollments e ON c.course_id = e.course_id
GROUP BY 
    c.course_name;

SELECT 
    s.student_id,
    s.first_name,
    s.last_name,
    AVG(p.amount) AS average_payment
FROM 
    Students s
JOIN 
    Payments p ON s.student_id = p.student_id
GROUP BY 
    s.student_id, s.first_name, s.last_name;

SELECT * FROM Students;
SELECT * FROM Enrollments;
SELECT * FROM Courses;
SELECT * FROM Teacher;
SELECT * FROM Payments;
