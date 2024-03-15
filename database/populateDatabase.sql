--To execute all these queries values need to be added and the whole query saved in a String object. 
--Then each query must be executed in java with the comand stmt.executeUpdate(queryString)

--create new faculty
INSERT INTO Faculty (name, location) VALUES ('...', '...');
INSERT INTO StudyProgram (name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...');

--create new study program
--bachelors
INSERT INTO Bachelor (accepted_high_school_qualification, name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...', '...');
INSERT INTO StudyProgram (name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...');
INSERT INTO IncludedStudyProgram (faculty_name, study_program_name, study_program_degree_code) 
    VALUES ('...', '...', '...');

--master
INSERT INTO Master (accepted_bachelor, name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...', '...');
INSERT INTO StudyProgram (name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...');
INSERT INTO IncludedStudyProgram (faculty_name, study_program_name, study_program_degree_code) 
    VALUES ('...', '...', '...');

--PhD
INSERT INTO PhD (accepted_master, topic_of_research, name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...', '...', '...');
INSERT INTO StudyProgram (name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...');
INSERT INTO IncludedStudyProgram (faculty_name, study_program_name, study_program_degree_code) 
    VALUES ('...', '...', '...');

--create new courses
INSERT INTO Course (name, code, year, cfu, language, timetable, semester_of_teaching, mandatory_or_not, syllabus) 
    VALUES ('...', '...', '...', '...', '...', '...', '...', '...', '...');
INSERT INTO OfferedCourses (study_program_name, study_program_degree_code, course_name, course_code, course_year) 
    VALUES ('...', '...', '...', '...', '...');
INSERT INTO TaughtCourses(professor_name, professor_lastname, course_name, course_code, course_year) 
    VALUES ('...', '...', '...', '...', '...');

--create new exams
INSERT INTO Exam (date_time, room,  course_name, course_code, course_year)
    VALUES ('...', '...', '...', '...', '...');

--create new persons
INSERT INTO Person (name, lastname, phone, personal_email) 
    VALUES ('...', '...', '...', '...');
--guest
INSERT INTO (name, lastname, phone, personal_email) 
    VALUES ('...', '...', '...', '...');
INSERT INTO Person (name, lastname, phone, personal_email) 
    VALUES ('...', '...', '...', '...');

--internal
INSERT INTO Internal (name, lastname, phone, personale_emeil, academic_email) 
    VALUES ('...', '...', '...', '...', '...');
INSERT INTO Person (name, lastname, phone, personal_email) 
    VALUES ('...', '...', '...', '...');

--worker
INSERT INTO Worker (name, lastname, phone, personal_email, academic_email, department, begin_of_contract, end_of_contract) 
    VALUES ('...', '...', '...', '...', '...', '...', '...', '...');
INSERT INTO Internal (name, lastname, phone, personale_emeil, academic_email) 
    VALUES ('...', '...', '...', '...', '...');
INSERT INTO Person (name, lastname, phone, personal_email) 
    VALUES ('...', '...', '...', '...');

--professor
INSERT INTO Professor (name, lastname, phone, personal_email, academic_email, department, begin_of_contract, end_of_contract, professor_id, office_hours) 
    VALUES ('...', '...', '...', '...', '...', '...', '...', '...', '...', '...');
INSERT INTO Worker (name, lastname, phone, personal_email, academic_email, department, begin_of_contract, end_of_contract) 
    VALUES ('...', '...', '...', '...', '...', '...', '...', '...');
INSERT INTO Internal (name, lastname, phone, personale_emeil, academic_email) 
    VALUES ('...', '...', '...', '...', '...');
INSERT INTO Person (name, lastname, phone, personal_email) 
    VALUES ('...', '...', '...', '...');
INSERT INTO TaughtCourses (professor_name, professor_lastname, course_name, course_code, course_year) 
    VALUES ('...', '...', '...', '...', '...');

--student
INSERT INTO Student (name, lastname, phone, personal_email, academic_email, student_id, degree) 
    VALUES ('...', '...', '...', '...', '...', '...', '...');
INSERT INTO Internal (name, lastname, phone, personale_emeil, academic_email) 
    VALUES ('...', '...', '...', '...', '...');
INSERT INTO Person (name, lastname, phone, personal_email) 
    VALUES ('...', '...', '...', '...');

--create new public events
INSERT INTO PublicEvent (name, date_time, location) 
    VALUES ('...', '...', '...');
INSERT INTO HostingEvent (person_name, person_lastname, event_name, event_data_time)

--create relationships
--person partecipatin in an event
INSERT INTO PartecipationInEvent (person_name, person_lastname, event_name, event_data_time)
    VALUES ('...', '...', '...', '...');

--person hosting an event
INSERT INTO HostingEvent (person_name, person_lastname, event_name, event_data_time)
    VALUES ('...', '...', '...', '...');

--assign a professo to a course
INSERT INTO TaughtCourses (professor_name, professor_lastname, course_name, course_code, course_year) 
    VALUES ('...', '...', '...', '...', '...');

--make a student take an exam
INSERT INTO TakenExams(student_id, student_name, student_lastname, grade, status, exam_data_time, course_name, course_code, course_year)
    VALUES ('...', '...', '...', '...', '...', '...', '...', '...', '...');

--enroll a student to a study program
--if no tuple with the same student_id and year_of_enrollment exists
INSERT INTO StudentsEnrolled(student_id, student_name, student_lastname, year_of_enrollment, study_program_name, study_program_degree_code)
    VALUES ('...', '...', '...', '...', '...', '...');

--assign a course to a study program
--if there is 1 or less tuple witht he same course_name, course_code, course_year
INSERT INTO OfferedCourses (study_program_name, study_program_degree_code, course_name, course_code, course_year) 
    VALUES ('...', '...', '...', '...', '...');

--include a study program in a faculty
INSERT INTO StudyProgram (name, degree_code, number_of_students, duration, location, yearly_fee) 
    VALUES ('...', '...', '...', '...', '...', '...');

--assign a director to a study program
--comment: if there is already a director for the study program then the standard means the old one is replaced (even if they have the same begin year)
INSERT INTO Directors (study_program_name, study_program_degree_code, professor_id, begin_year_of_direction) 
    VALUES ('...', '...', '...', '...');

--evaluate a course with an exam
INSERT INTO Exam (date_time, room) 
    VALUES ('...', '...');