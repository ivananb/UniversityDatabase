--To execute all these queries values need to be added and the whole query saved in a String object. 
--Then each query must be executed in java with the comand stmt.executeUpdate(queryString)

--entities tables
DROP TABLE IF EXISTS Faculty;
CREATE TABLE Faculty (
    name CHARACTER(50) PRIMARY KEY,
    location CHARACTER(40) NOT NULL
);

DROP TABLE IF EXISTS StudyProgram;
CREATE TABLE StudyProgram (
    name VARCHAR(40) NOT NULL,
    degree_code VARCHAR(9) NOT NULL,
    number_of_students VARCHAR(4) NOT NULL,
    duration VARCHAR(10) NOT NULL,
    location VARCHAR(40) NOT NULL,
    yearly_fee VARCHAR(6) NOT NULL,
    PRIMARY KEY (name, degree_code)
);

DROP TABLE IF EXISTS Bachelor;
CREATE TABLE Bachelor (
    accepted_high_school_qualification VARCHAR(40) NOT NULL,
    name VARCHAR(40) NOT NULL,
    degree_code VARCHAR(9) NOT NULL,
    number_of_student VARCHAR(4) NOT NULL,
    duration VARCHAR(10) NOT NULL,
    location VARCHAR(40) NOT NULL,
    yearly_fee VARCHAR(6) NOT NULL,
    PRIMARY KEY (name, degree_code)
);

DROP TABLE IF EXISTS Master;
CREATE TABLE Master (
    accepted_bachelor VARCHAR(40) NOT NULL,
    name VARCHAR(40) NOT NULL,
    degree_code VARCHAR(9) NOT NULL,
    number_of_student VARCHAR(4) NOT NULL,
    duration VARCHAR(10) NOT NULL,
    location VARCHAR(40) NOT NULL,
    yearly_fee VARCHAR(6) NOT NULL,
    PRIMARY KEY (name, degree_code)
);

DROP TABLE IF EXISTS PhD;
CREATE TABLE PhD (
    accepted_master VARCHAR(40) NOT NULL,
    topic_of_research VARCHAR(40) NOT NULL,
    name VARCHAR(40) NOT NULL,
    degree_code VARCHAR(9) NOT NULL,
    number_of_student VARCHAR(4) NOT NULL,
    duration VARCHAR(10) NOT NULL,
    location VARCHAR(40) NOT NULL,
    yearly_fee VARCHAR(6) NOT NULL,
    PRIMARY KEY (name, degree_code)
);

DROP TABLE IF EXISTS Course;
CREATE TABLE Course (
    name VARCHAR(40) PRIMARY KEY,
    code VARCHAR(40) NOT NULL,
    year VARCHAR(40) NOT NULL,
    cfu VARCHAR(3) NOT NULL,
    language VARCHAR(40) NOT NULL,
    timetable VARCHAR(500) NOT NULL,
    semester_of_teaching VARCHAR(40) NOT NULL,
    mandatory_or_not VARCHAR(20) NOT NULL,
    syllabus VARCHAR(200) NOT NULL,
    PRIMARY KEY (code, year)
);

DROP TABLE IF EXISTS Exam;
CREATE TABLE Exam (
    date_time TIMESTAMP(40) PRIMARY KEY,
    room VARCHAR(10) NOT NULL,
    course_name VARCHAR(20) NOT NULL,
    course_code VARCHAR(9) NOT NULL,
    course_year VARCHAR(9) NOT NULL,
    FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year)
);

DROP TABLE IF EXISTS Person;
CREATE TABLE Person (
	name VARCHAR(20) NOT NULL,
	lastname VARCHAR(20) NOT NULL,
	phone VARCHAR(15),
	personal_email VARCHAR(9) DEFAULT 0,
    PRIMARY KEY(name, lastname)
);

DROP TABLE IF EXISTS Internal;
CREATE TABLE Internal (
    name VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    personal_email VARCHAR(30) NOT NULL,
    academic_email VARCHAR(30) PRIMARY KEY,
    UNIQUE (name, lastname)
);

DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
    student_id VARCHAR(9) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)  NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    personal_email VARCHAR(30) NOT NULL,
    academic_email VARCHAR(30) NOT NULL,
    degree VARCHAR(9),
    UNIQUE (name, lastname, accademic_email)
);

DROP TABLE IF EXISTS Worker;
CREATE TABLE Worker (
    name VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    personal_email VARCHAR(30) NOT NULL,
    academic_email VARCHAR(30) NOT NULL,
    department VARCHAR(20) NOT NULL,
    begin_of_contract VARCHAR(9) NOT NULL,
    end_of_contract VARCHAR(9) NOT NULL
    UNIQUE (name, lastname, accademic_email)
);

DROP TABLE IF EXISTS Professor;
CREATE TABLE Professor (
    professor_id VARCHAR(9) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    personal_email VARCHAR(30) NOT NULL,
    academic_email VARCHAR(30) NOT NULL,
    department VARCHAR(20) NOT NULL,
    begin_of_contract VARCHAR(9) NOT NULL,
    end_of_contract VARCHAR(9) NOT NULL,
    office_hours VARCHAR(9) NOT NULL,
    UNIQUE (name, lastname, accademic_email)
);
	
DROP TABLE IF EXISTS Guest;
CREATE TABLE Guest (
    guest_id VARCHAR(9) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    personal_email VARCHAR(9) DEFAULT 0,
);

DROP TABLE IF EXISTS PublicEvent;
CREATE TABLE PublicEvent(
    name VARCHAR(50) PRIMARY KEY NOT NULL,
    date_time TIMESTAMP () NOT NULL,
    location VARCHAR(40) NOT NULL,
    UNIQUE (name, date_time)
);

--relationship tables
DROP TABLE IF EXISTS PartecipationInEvent;
CREATE TABLE PartecipationInEvent (
    person_name VARCHAR(20) NOT NULL,
    person_lastname VARCHAR(20) NOT NULL,
    event_name VARCHAR(50) NOT NULL,
    event_date_time TIMESTAMP () NOT NULL,
    PRIMARY KEY (person_name, person_lastname, event_name, event_date_time),
    FOREIGN KEY (person_name, person_lastname) REFERENCES Person(name, lastname),
    FOREIGN KEY (event_name, event_date_time) REFERENCES PublicEvent(name, date_time)
);

DROP TABLE IF EXISTS HostingEvent;
CREATE TABLE HostingEvent(
    person_name VARCHAR(20) NOT NULL,
    person_lastname VARCHAR(20) NOT NULL,
    event_name VARCHAR(50) NOT NULL,
    event_date_time TIMESTAMP () NOT NULL,
    PRIMARY KEY (person_name, person_lastname, event_name, event_date_time),
    FOREIGN KEY (person_name, person_lastname) REFERENCES Person(name, lastname),
    FOREIGN KEY (event_name, event_date_time) REFERENCES PublicEvent(name, date_time)
);

DROP TABLE IF EXISTS Teaching;
CREATE TABLE TaughtCourses (
    professor_id VARCHAR(9) NOT NULL,
    professor_name VARCHAR(20) NOT NULL,
    professor_lastname VARCHAR(20) NOT NULL,
    course_name VARCHAR(20) NOT NULL,
    course_code VARCHAR(9) NOT NULL,
    course_year VARCHAR(9) NOT NULL,
    PRIMARY KEY (professor_id, course_code, course_year),
    FOREIGN KEY (professor_id) REFERENCES Professor(professor_id),
    FOREIGN KEY (professor_name, professor_lastname) REFERENCES Professor(name, lastname)
    FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year)
);

DROP TABLE IF EXISTS TakenExams;
CREATE TABLE TakenExams (
    student_id VARCHAR(9) NOT NULL,
    student_name VARCHAR(20) NOT NULL,
    student_lastname VARCHAR(20) NOT NULL,
    grade VARCHAR(9) NOT NULL,
    status VARCHAR(9) NOT NULL,
    exam_date_time TIMESTAMP() NOT NULL,
    course_name VARCHAR(20) NOT NULL,
    course_code VARCHAR(9) NOT NULL,
    course_year VARCHAR(9) NOT NULL,
    PRIMARY KEY (student_id, course_code, course_year),
    FOREIGN KEY (student_id, student_name, student_lastname) REFERENCES Student(student_id, name, lastname),
    FOREIGN KEY (exam_date_time) REFERENCES Exam(date_time),
    FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year)
);

DROP TABLE IF EXISTS StudentsEnrolled;
CREATE TABLE StudentsEnrolled (
    student_id VARCHAR(9) NOT NULL,
    student_name VARCHAR(20) NOT NULL,
    student_lastname VARCHAR(20) NOT NULL,
    year_of_enrollment VARCHAR(4) NOT NULL,
    study_program_name VARCHAR(40) NOT NULL,
    study_program_degree_code VARCHAR(9) NOT NULL,
    PRIMARY KEY (student_id, year_of_enrollment),
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code)
);

DROP TABLE IF EXISTS OfferedCourses;
CREATE TABLE OfferedCourses (
    course_name VARCHAR(40) NOT NULL,
    course_code VARCHAR(40) NOT NULL,
    course_year VARCHAR(40) NOT NULL,
    study_program_name VARCHAR(40) NOT NULL,
    study_program_degree_code VARCHAR(9) NOT NULL,
    PRIMARY KEY (course_code, course_year),
    FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year),
    FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code)

);

DROP TABLE IF EXISTS IncludedStudyProgram;
CREATE TABLE IncludedStudyProgram (
    faculty_name VARCHAR(50) NOT NULL,
    study_program_name VARCHAR(40) NOT NULL,
    study_program_degree_code VARCHAR(9) NOT NULL,
    PRIMARY KEY (faculty_name, study_program_name, study_program_degree_code),
    FOREIGN KEY (faculty_name) REFERENCES Faculty(name),
    FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code)

    
);

DROP TABLE IF EXISTS Directors;
CREATE TABLE Directors (
    study_program_name VARCHAR(40) NOT NULL,
    study_program_degree_code VARCHAR(9) NOT NULL,
    professor_id VARCHAR(20) NOT NULL,
    begin_year_of_direction VARCHAR(4) NOT NULL,
    PRIMARY KEY (study_program_name, study_program_degree_code, professor_id),
    FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code),
    FOREIGN KEY (professor_id) REFERENCES Professor(professor_id)
);


