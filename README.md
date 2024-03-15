# UniversityDatabase
Project of the "Introduction to Databases" course

Authors: Irene Avezzù and Ivana Nworah Bortot

This repository contains the database design and implementation for a university management system, developed as part of the "Introduction to Databases" course. The database represents the internal structure of a university, including its faculties, study programs, courses, exams, personnel, and events.

## Database Structure

### Entities
1. **Faculty**: Represents the faculties within the university, characterized by a unique name and location.
2. **StudyProgram**: Represents study programs offered by faculties, with attributes such as name, degree class, maximum number of students, duration, location, and yearly fees.
3. **Course**: Represents courses associated with study programs, identified by code, year, and additional attributes such as CFU (crediti formativi universitari), language, lecture details, semester, and syllabus.
4. **Exam**: Represents exams associated with courses, uniquely identified by course, date, time, and location.
5. **Person**: Represents individuals associated with the university, with attributes such as name, last name, phone number, and email. Can be categorized as internal (worker or student) or guest.
6. **Worker**: Represents employees of the university, with attributes including contract dates, department affiliation, and office hours for professors.
7. **Student**: Represents students enrolled in study programs, with attributes including student ID and previous degrees.
8. **Event**: Represents public events hosted by the university, with attributes such as name, date, time, and location.

### Relationships
- **Faculty-StudyProgram**: One-to-many relationship between faculties and study programs.
- **StudyProgram-Course**: One-to-many relationship between study programs and courses.
- **Course-Exam**: One-to-many relationship between courses and exams.
- **Person-Worker**: One-to-one relationship between persons and workers.
- **Person-Student**: One-to-one relationship between persons and students.
- **Course-Professor**: Many-to-many relationship between courses and professors.
- **Event-Person**: Many-to-many relationship between events and persons.

## Organized Requirements
The database design adheres to structured and organized requirements as outlined in the project report. Key components include the management of faculties, study programs, courses, exams, personnel, and events, ensuring data integrity and consistency throughout the system.

For further details on the database schema and implementation, please refer to the project documentation and database files within the repository.
