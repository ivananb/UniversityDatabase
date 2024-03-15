import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DatabaseApp {
    static Date currentDate = new Date(); // This object contains the current date value
    static SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // This object formats the date in the format dd-MM-yyyy HH:mm:ss
    static Scanner scan = new Scanner(System.in);
    static Connection conn;
    static Statement stmt;
    static final String url = "jdbc:postgresql://localhost/idb_project";
    static final String user = "postgres";
    static final String password = "";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        connectToDatabase();
        stmt = conn.createStatement();

        createTables(); 

        boolean tf = true;
        while (tf){
            execute();
            System.out.println("Do you want to continue? (y/n)");
            String input = scan.nextLine();  
            if (input.equals("n")){
                tf = false;
            }
        }
    }

    public static Connection connectToDatabase() {
        try {
        conn = DriverManager.getConnection(url, user, password); System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTables() throws SQLException {
        //entities
        String sqlQuery = "DROP TABLE IF EXISTS Faculty;"
            + "CREATE TABLE Faculty ("
            + "name CHARACTER(50) PRIMARY KEY, "
            + "location CHARACTER(40) NOT NULL)";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS StudyProgram;"
        + "CREATE TABLE StudyProgram ("
            + "name VARCHAR(40) NOT NULL," 
            + "degree_code VARCHAR(9) NOT NULL,"
            + "number_of_students VARCHAR(4) NOT NULL,"
            + "duration VARCHAR(10) NOT NULL,"
            + "location VARCHAR(40) NOT NULL,"
            + "yearly_fee VARCHAR(6) NOT NULL,"
            + "PRIMARY KEY (name, degree_code))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Bachelor;"
        + "CREATE TABLE Bachelor ("
            + "accepted_high_school_qualification VARCHAR(40) NOT NULL,"
            + "name VARCHAR(40) NOT NULL," 
            + "degree_code VARCHAR(9) NOT NULL," 
            + "number_of_student VARCHAR(4) NOT NULL," 
            + "duration VARCHAR(10) NOT NULL, " 
            + "location VARCHAR(40) NOT NULL, " 
            + "yearly_fee VARCHAR(6) NOT NULL," 
            + "PRIMARY KEY (name, degree_code))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Master;"
        + "CREATE TABLE Master ("
            + "accepted_bachelor VARCHAR(40) NOT NULL,"
            + "name VARCHAR(40) NOT NULL,"
            + "degree_code VARCHAR(9) NOT NULL,"
            + "number_of_student VARCHAR(4) NOT NULL,"
            + "duration VARCHAR(10) NOT NULL,"
            + "location VARCHAR(40) NOT NULL,"
            + "yearly_fee VARCHAR(6) NOT NULL,"
            + "PRIMARY KEY (name, degree_code))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS PhD;"
        + "CREATE TABLE PhD ("
            + "accepted_master VARCHAR(40) NOT NULL,"
            + "topic_of_research VARCHAR(40) NOT NULL,"
            + "name VARCHAR(40) NOT NULL,"
            + "degree_code VARCHAR(9) NOT NULL,"
            + "number_of_student VARCHAR(4) NOT NULL,"
            + "duration VARCHAR(10) NOT NULL,"
            + "location VARCHAR(40) NOT NULL,"
            + "yearly_fee VARCHAR(6) NOT NULL,"
            + "PRIMARY KEY (name, degree_code))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Course;"
        + "CREATE TABLE Course ("
            + "name VARCHAR(40) PRIMARY KEY,"
            + "code VARCHAR(40) NOT NULL,"
            + "year VARCHAR(40) NOT NULL,"
            + "cfu VARCHAR(3) NOT NULL,"
            + "language VARCHAR(40) NOT NULL,"
            + "timetable VARCHAR(500) NOT NULL,"
            + "semester_of_teaching VARCHAR(40) NOT NULL,"
            + "mandatory_or_not VARCHAR(20) NOT NULL,"
            + "syllabus VARCHAR(200) NOT NULL,"
            + "PRIMARY KEY (code, year))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Exam;"
        + "CREATE TABLE Exam ("
            + "date_time TIMESTAMP(40) PRIMARY KEY,"
            + "course_name VARCHAR(20) NOT NULL," 
            + "course_code VARCHAR(9) NOT NULL,"
            + "course_year VARCHAR(9) NOT NULL,"
            + "room VARCHAR(10) NOT NULL)"
            + "FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year)";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Person;"
        + "CREATE TABLE Person ("
            + "name VARCHAR(20) NOT NULL,"
            + "lastname VARCHAR(20) NOT NULL,"
            + "phone VARCHAR(15),"
            + "personal_email VARCHAR(9) DEFAULT 0,"
            + "PRIMARY KEY(name, lastname))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Internal;"
        + "CREATE TABLE Internal ("
            + "name VARCHAR(20) NOT NULL,"
            + "lastname VARCHAR(20) NOT NULL,"
            + "phone VARCHAR(15) NOT NULL,"
            + "personal_email VARCHAR(30) NOT NULL,"
            + "academic_email VARCHAR(30) PRIMARY KEY,"
            + "UNIQUE (name, lastname))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Student;"
        + "CREATE TABLE Student ("
            + "student_id VARCHAR(9) PRIMARY KEY AUTO_INCREMENT,"
            + "name VARCHAR(20)  NOT NULL,"
            + "lastname VARCHAR(20) NOT NULL,"
            + "phone VARCHAR(15) NOT NULL,"
            + "personal_email VARCHAR(30) NOT NULL,"
            + "academic_email VARCHAR(30) NOT NULL,"
            + "degree VARCHAR(9),"
            + "UNIQUE (name, lastname, accademic_email))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Worker;"
        + "CREATE TABLE Worker ("
            + "name VARCHAR(20) NOT NULL,"
            + "lastname VARCHAR(20) NOT NULL,"
            + "phone VARCHAR(15) NOT NULL,"
            + "personal_email VARCHAR(30) NOT NULL,"
            + "academic_email VARCHAR(30) NOT NULL,"
            + "department VARCHAR(20) NOT NULL,"
            + "begin_of_contract VARCHAR(9) NOT NULL,"
            + "end_of_contract VARCHAR(9) NOT NULL"
            + "UNIQUE (name, lastname, accademic_email))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Professor;"
        + "CREATE TABLE Professor ("
            + "professor_id VARCHAR(9) PRIMARY KEY AUTO_INCREMENT,"
            + "name VARCHAR(20) NOT NULL,"
            + "lastname VARCHAR(20) NOT NULL,"
            + "phone VARCHAR(15) NOT NULL,"
            + "personal_email VARCHAR(30) NOT NULL,"
            + "academic_email VARCHAR(30) NOT NULL,"
            + "department VARCHAR(20) NOT NULL,"
            + "begin_of_contract VARCHAR(9) NOT NULL,"
            + "end_of_contract VARCHAR(9) NOT NULL,"
            + "office_hours VARCHAR(9) NOT NULL,"
            + "UNIQUE (name, lastname, accademic_email))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Guest;"
        + "CREATE TABLE Guest ("
            + "guest_id VARCHAR(9) PRIMARY KEY AUTO_INCREMENT,"
            + "name VARCHAR(20) NOT NULL,"
            + "lastname VARCHAR(20) NOT NULL,"
            + "phone VARCHAR(15) NOT NULL,"
            + "ersonal_email VARCHAR(9) DEFAULT 0,)";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS PublicEvent;"
        + "CREATE TABLE PublicEvent("
            + "name VARCHAR(50) PRIMARY KEY NOT NULL,"
            + "date_time TIMESTAMP () NOT NULL,"
            + "location VARCHAR(40) NOT NULL,"
            + "UNIQUE (name, data_time))";
        stmt.executeUpdate(sqlQuery);

        //relationships
        sqlQuery = "DROP TABLE IF EXISTS PartecipationInEvent;"
        + "CREATE TABLE PartecipationInEvent ("
            + "person_name VARCHAR(20) NOT NULL,"
            + "person_lastname VARCHAR(20) NOT NULL,"
            + "event_name VARCHAR(50) NOT NULL,"
            + "event_date_time TIMESTAMP () NOT NULL,"
            + "PRIMARY KEY (person_name, person_lastname, event_name, event_date_time),"
            + "FOREIGN KEY (person_name, person_lastname) REFERENCES Person(name, lastname),"
            + "FOREIGN KEY (event_name, event_date_time) REFERENCES PublicEvent(name, date_time))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS HostingEvent;"
        + "CREATE TABLE HostingEvent ("
            + "person_name VARCHAR(20) NOT NULL,"
            + "person_lastname VARCHAR(20) NOT NULL,"
            + "event_name VARCHAR(50) NOT NULL,"
            + "event_date_time TIMESTAMP () NOT NULL,"
            + "PRIMARY KEY (person_name, person_lastname, event_name, event_date_time),"
            + "FOREIGN KEY (person_name, person_lastname) REFERENCES Person(name, lastname),"
            + "FOREIGN KEY (event_name, event_date_time) REFERENCES PublicEvent(name, date_time))";
        stmt.executeUpdate(sqlQuery);
        
        sqlQuery = "DROP TABLE IF EXISTS Teaching;"
        + "CREATE TABLE TaughtCourses ("
            + "professor_id VARCHAR(9) NOT NULL,"
            + "professor_name VARCHAR(20) NOT NULL,"
            + "professor_lastname VARCHAR(20) NOT NULL,"
            + "course_name VARCHAR(20) NOT NULL,"
            + "course_code VARCHAR(9) NOT NULL,"
            + "course_year VARCHAR(9) NOT NULL,"
            + "PRIMARY KEY (professor_id, course_code, course_year),"
            + "FOREIGN KEY (professor_id) REFERENCES Professor(professor_id),"
            + "FOREIGN KEY (professor_name, professor_lastname) REFERENCES Professor(name, lastname)"
            + "FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS TakenExams;"
        + "CREATE TABLE TakenExam ("
            + "student_id VARCHAR(9) NOT NULL,"
            + "student_name VARCHAR(20) NOT NULL,"
            + "student_lastname VARCHAR(20) NOT NULL,"
            + "grade VARCHAR(9) NOT NULL,"
            + "status VARCHAR(9) NOT NULL,"
            + "exam_date_time TIMESTAMP() NOT NULL,"
            + "course_name VARCHAR(20) NOT NULL,"
            + "course_code VARCHAR(9) NOT NULL,"
            + "course_year VARCHAR(9) NOT NULL,"
            + "PRIMARY KEY (student_id, course_code, course_year),"
            + "FOREIGN KEY (student_id, student_name, student_lastname) REFERENCES Student(student_id, name, lastname),"
            + "FOREIGN KEY (exam_date_time) REFERENCES Exam(date_time),"
            + "FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year))";
        stmt.executeUpdate(sqlQuery);
        
        sqlQuery = "DROP TABLE IF EXISTS StudentsEnrolled;"
        + "CREATE TABLE StudentsEnrolled ("
            + "student_id VARCHAR(9) NOT NULL,"
            + "student_name VARCHAR(20) NOT NULL,"
            + "student_lastname VARCHAR(20) NOT NULL,"
            + "year_of_enrollment VARCHAR(4) NOT NULL,"
            + "study_program_name VARCHAR(40) NOT NULL,"
            + "study_program_degree_code VARCHAR(9) NOT NULL,"
            + "PRIMARY KEY (student_id, year_of_enrollment),"
            + "FOREIGN KEY (student_id) REFERENCES Student(student_id),"
            + "FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS OfferedCourses;"
        + "CREATE TABLE OfferedCourses ("
            + "course_name VARCHAR(40) NOT NULL,"
            + "course_code VARCHAR(40) NOT NULL,"
            + "course_year VARCHAR(40) NOT NULL,"
            + "study_program_name VARCHAR(40) NOT NULL,"
            + "study_program_degree_code VARCHAR(9) NOT NULL,"
            + "PRIMARY KEY (course_code, course_year),"
            + "FOREIGN KEY (course_name, course_code, course_year) REFERENCES Course(name, code, year),"
            + "FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code))";
        stmt.executeUpdate(sqlQuery);


        sqlQuery = "DROP TABLE IF EXISTS IncludedStudyProgram;"
        + "CREATE TABLE IncludedStudyProgram ("
            + "faculty_name VARCHAR(50) NOT NULL,"
            + "study_program_name VARCHAR(40) NOT NULL,"
            + "study_program_degree_code VARCHAR(9) NOT NULL,"
            + "PRIMARY KEY (faculty_name, study_program_name, study_program_degree_code),"
            + "FOREIGN KEY (faculty_name) REFERENCES Faculty(name),"
            + "FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code))";
        stmt.executeUpdate(sqlQuery);

        sqlQuery = "DROP TABLE IF EXISTS Directors;"
        + "CREATE TABLE Directors ("
            + "study_program_name VARCHAR(40) NOT NULL,"
            + "study_program_degree_code VARCHAR(9) NOT NULL,"
            + "professor_id VARCHAR(20) NOT NULL,"
            + "begin_year_of_direction VARCHAR(4) NOT NULL,"
            + "PRIMARY KEY (study_program_name, study_program_degree_code, professor_id),"
            + "FOREIGN KEY (study_program_name, study_program_degree_code) REFERENCES StudyProgram(name, degree_code),"
            + "FOREIGN KEY (professor_id) REFERENCES Professor(professor_id))";
        stmt.executeUpdate(sqlQuery);

    }

    public static void execute() throws SQLException {
        System.out.println("Insert comand: " +
                                "\n1 for Top 10 students (in the whole uni or in a study program" +
                                "\n2 to edit personal information (of a prof., a student or a guest)" +
                                "\n3 to add a new user (a prof., a student or a guest)" +
                                "\n4 to get the average grade of a student" +
                                "\n5 to get the timetable of a course" +
                                "\n6 to get mor informations on upcoming events" +
                                "\n7 to sign up for an event" +
                                "\n8 to enroll in an exam" +
                                "\n9 to get the list of students enrolled in a course" +
                                "\n10 to get all avielabl courses for a student" );
        
        String input = scan.nextLine();  
        
        switch (input){
            case "1":{
                getTopTen();
                break;
            }
            case "2":{
                editPersonalInfo();
                break;
            }
            case "3":{
                addNewUser();
                break;
            }
            case "4":{
                getAverageGrade();
                break;
            }
            case "5":{
                getTimetable();
                break;
            }
            case "6":{
                getUpcomingEvents();
                break;
            }
            case "7":{
                signUpForEvent();
                break;
            }
            case "8":{
                enrollInExam();
                break;
            }
            case "9":{
                getPassedStudents();
                break;
            }
            case "10":{
                getAvailableCourses();
                break;
            }
            default:{
                System.out.println("Invalid input");
                break;
            }
        }
    }

    //1 get top ten students, inputs indicates wheather in the whole uni or of a single study program
    static void getTopTen () throws SQLException{
        System.out.println("Insert comand: " +
                                    "\n1 for top 10 in the whole uni" +
                                    "\n2 for top 10 in a study program\n");
        String input = scan.nextLine();  
        if (input.equals("1")){
            getTopTenUni();
        }
        else if (input.equals("2")){
            getTopTenStudyProgram();
        }
        else{
            System.out.println("Invalid input");
        }
    }
    static void getTopTenUni () throws SQLException{
        String sqlQuery = "SELECT student_id, average_grade FROM (" +
                                "(SELECT student_id, AVG(grade) AS average_grade FROM" +
                                    "((SELECT * FROM TakenExams WHERE status = 'passed') GROUP BY student_id)" +
                                ") ORDER BY grade LIMIT 10" +
                            ");" ;

        ResultSet rs = executeQuery(sqlQuery);
        System.out.println("These are the top 10 students in the whole uni: ");
        while (rs.next()){
            System.out.println(rs.getString("student_id") + " " + rs.getString("average_grade"));        }
        rs.close();
    }
    static void getTopTenStudyProgram () throws SQLException{
        //TO DO TUTTO
        System.out.println("Select a faculty from: \n" + getFaculty());
        String faculty = scan.nextLine(); 
        System.out.println("Select a study program from: \n" + getStudyProgram(faculty));
        String studyProgram = scan.nextLine();


        
        String sqlQuery = "";
        
        /*
        SELECT student_id, AVG(grade) AS average_grade FROM
            ((SELECT * FROM TakenExams WHERE status = "passed" AND name = "course_name" AND year = "course_year") GROUP BY student_id)
        ORDER BY grade LIMIT 10 
        */

        ResultSet rs = executeQuery(sqlQuery);
        System.out.println("These are the ids of the top 10 students in the study program " + studyProgram + ":" + rs.getString("studentID"));
        rs.close();
    }

    //2 edid the personal information of a user (user = professor/student/guest)
    static void editPersonalInfo ( ) throws SQLException{
        String user = typeOfUser();
        System.out.println("Insert your ID:");
        String ID = scan.nextLine();  
        if (user.equals("1")){
            editPersonalInfoProfessor(ID);
        }
        else if (user.equals("2")){
            editPersonalInfoStudent(ID);
        }
        else if (user.equals("3")){
            editPersonalInfoGuest(ID);
        }
        else{
            System.out.println("Invalid input");
        }
    }
    static void editPersonalInfoProfessor (String professorID) throws SQLException{
        System.out.println("Which field do you want to edit:" +
                            "\n1 office hour" +
                            "\n2 personal email" +
                            "\n3 phone number");
        String cathegory = scan.nextLine();  
        System.out.println("Insert the new value");
        String newValue = scan.nextLine();  
        String sqlQuery = null;
        String sqlQuery2 = "";
        String sqlQuery3 = "";
        String sqlQuery4 = "";
        String sqlQuery5 = "SELECT name, lastname FROM Professor WHERE professor_id = '" + professorID + "' ;";
        ResultSet rs = stmt.executeQuery(sqlQuery5);
        String name = rs.getString("name");
        String lastname = rs.getString("lastname");
        switch (cathegory){
            case("1"):{
                sqlQuery = "UPDATE Professor SET office_hour = '" + newValue + "' WHERE professor_id = '" + professorID + "'';";
                break;
            }
            case ("2"):{
                sqlQuery = "UPDATE Professor SET personal_email = '" + newValue + "' WHERE professor_id = '" + professorID + "' ';";
                sqlQuery2 = "UPDATE Worker SET personal_email = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                sqlQuery3 = "UPDATE Internal SET personal_email = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                sqlQuery4 = "UPDATE Person SET personal_email = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                break;
            }
            case ("3"):{
                sqlQuery = "UPDATE Professor SET phone_number = '" + newValue + "' WHERE professor_id = '" + professorID + "' ';";
                sqlQuery2 = "UPDATE Worker SET phone_number = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                sqlQuery3 = "UPDATE Internal SET phone_number = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                sqlQuery4 = "UPDATE Person SET phone_number = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                break;
            }
            default:{
                System.out.println("Invalid input for field to edit");
                break;
            }
        }
        if (sqlQuery != null){
            stmt.executeUpdate(sqlQuery);
            stmt.executeUpdate(sqlQuery2);
            stmt.executeUpdate(sqlQuery3);
            stmt.executeUpdate(sqlQuery4);
            System.out.println("The new value has been updated");
        }
    }
    static void editPersonalInfoStudent (String studentID) throws SQLException{
        System.out.println("Which field do you want to edit:" +
                            "\n1 degree" +
                            "\n2 phone number" +
                            "\n3 personal email");
        String cathegory = scan.nextLine(); 
        System.out.println("Insert the new value");
        String newValue = scan.nextLine();  
        String sqlQuery = null;
        String sqlQuery2 = "";
        String sqlQuery3 = "";
        String sqlQuery4 = "SELECT name, lastname FROM Student WHERE student_id = '" + studentID + "' ;";
        ResultSet rs = stmt.executeQuery(sqlQuery4);
        String name = rs.getString("name");
        String lastname = rs.getString("lastname");
        switch (cathegory){
            case("1"):{
                sqlQuery = "UPDATE Student SET degree = '" + newValue + "' WHERE student_id = ' " + studentID + "' ';";
                break;
            }
            case ("2"):{
                sqlQuery = "UPDATE Student SET personal_email = '" + newValue + "' WHERE student_id = ' " + studentID + "' ';";
                //TODO aggiungere get name e lastname
                sqlQuery2 = "UPDATE Internal SET personal_email = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                sqlQuery3 = "UPDATE Person SET personal_email = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                break;
            }
            case ("3"):{
                sqlQuery = "UPDATE Student SET phone_number = '" + newValue + "' WHERE student_id = ' " + studentID + "' ';";
                //TODO aggiungere get name e lastname
                sqlQuery2 = "UPDATE Internal SET phone_number = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                sqlQuery3 = "UPDATE Person SET phone_number = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                break;
            }
            default:{
                System.out.println("Invalid input for field to edit");
                break;
            }
        }
        if (sqlQuery != null){
            stmt.executeUpdate(sqlQuery);
            stmt.executeUpdate(sqlQuery2);
            stmt.executeUpdate(sqlQuery3);
            System.out.println("The new value has been updated");
        }
    }
    static void editPersonalInfoGuest (String guestID) throws SQLException{
        System.out.println("Which field do you want to edit:" +
                            "\n1 phone number" +
                            "\n2 personal email");
        String cathegory = scan.nextLine();  
        System.out.println("Insert the new value");
        String newValue = scan.nextLine();  
        String sqlQuery = null;
        String sqlQuery2 = "";
        String sqlQuery3 = "SELECT name, lastname FROM Guest WHERE guest_id = '" + guestID + "' ;";
        ResultSet rs = stmt.executeQuery(sqlQuery3);
        String name = rs.getString("name");
        String lastname = rs.getString("lastname");
        switch (cathegory){
            case("1"):{
                sqlQuery = "UPDATE Guest SET phone_number = '" + newValue + "' WHERE guest_id = '" + guestID + "' ';";
                sqlQuery2 = "UPDATE Person SET phone_number = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                break;
            }
            case("2"):{
                sqlQuery = "UPDATE Guest SET personal_email = '" + newValue + "' WHERE guest_id = '" + guestID + "' ';";
                sqlQuery2 = "UPDATE Person SET personal_email = '" + newValue + "' WHERE name = '" + name + "', lastname = '" + lastname + "' ;";
                break;
            }
            default:{
                System.out.println("Invalid input for field to edit");
                break;
            }
        }
        if (sqlQuery != null){
            stmt.executeUpdate(sqlQuery);
            stmt.executeUpdate(sqlQuery2);
            System.out.println("The new value has been updated");
        }
    }

    //3 add a new user (user = professor/student/guest)
    static void addNewUser () throws SQLException {
        String user = typeOfUser();
        if (user.equals("1")){
            System.out.println("Insert the following information:");
            System.out.println("Name:");
            String name = scan.nextLine();  
            System.out.println("Last name:");
            String lastName = scan.nextLine(); 
            System.out.println("Personal email:");
            String personalEmail = scan.nextLine();  
            System.out.println("Phone number:");
            String phoneNumber = scan.nextLine();  
            System.out.println("Accademic email:");
            String accademicEmail = scan.nextLine();  
            System.out.println("Department:");
            String department = scan.nextLine(); 
            System.out.println("Begin of contract:");
            String beginOfContract = scan.nextLine();  
            System.out.println("End of contract:");
            String endOfContract = scan.nextLine(); 
            System.out.println("Office hour:");
            String officeHour = scan.nextLine();  

            String sqlQuery = "INSERT INTO Professor (name, last_name, personal_email, phone_number, accademic_email, department, begin_of_contract, end_of_contract, office_hour) VALUES (' " +
                                name + "' ', '" + lastName + "' , '" + personalEmail + "' , '" + phoneNumber + "' , '" + accademicEmail + "' , '" + department + "' , '" + beginOfContract + "' , '" + endOfContract + "', '" + officeHour + "');";
            stmt.executeUpdate(sqlQuery);
            
            sqlQuery = "INSERT INTO Worker (name, last_name, personal_email, phone_number, accademic_email, department, begin_of_contract, end_of_contract) VALUES (' " +
            name + "' ', '" + lastName + "' , '" + personalEmail + "' , '" + phoneNumber + "' , '" + accademicEmail + "' , '" + department + "' , '" + beginOfContract + "' , '" + endOfContract + ";" ;
            stmt.executeUpdate(sqlQuery);

            sqlQuery = "INSERT INTO Internal (name, last_name, personal_email, phone_number, accademic_email) VALUES (' " +
                name + "' ', '" + lastName + "' , '" + personalEmail + "' , '" + phoneNumber + "' , '" + accademicEmail + ";";
            stmt.executeUpdate(sqlQuery);

            sqlQuery = "INSERT INTO Person (name, last_name, personal_email, phone_number) VALUES (' " + 
                name + "' ', '" + lastName + "' , '" + personalEmail + "' , '" + phoneNumber + ";" ;
            stmt.executeUpdate(sqlQuery);
            
            sqlQuery = "SELECT * FROM Professor WHERE name= '" + name +"' AND last_name = '" + lastName + "';" ;
            ResultSet rs = executeQuery(sqlQuery);
            System.out.println("The new professor has been added with id: " + rs.getString("professor_id"));
            rs.close();
        }
        else if (user.equals("2")){
            System.out.println("Insert the following information:");
            System.out.println("Name:");
            String name = scan.nextLine();  
            System.out.println("Last name:");
            String lastName = scan.nextLine();  
            System.out.println("Personal email:");
            String personalEmail = scan.nextLine();  
            System.out.println("Phone number:");
            String phoneNumber = scan.nextLine();  
            System.out.println("Accademic email:");
            String accademicEmail = scan.nextLine(); 
            System.out.println("Degree:");
            String degree = scan.nextLine();  

            String sqlQuery = "INSERT INTO Student (name, lastname, personal_email, phone_number, accademic_email, degree) VALUES (" 
                                + name + ", " + lastName + ", " + personalEmail + ", " + phoneNumber + ", " + accademicEmail + ", " + degree + ");";
            stmt.executeUpdate(sqlQuery);

            sqlQuery = "INSERT INTO Internal (name, last_name, personal_email, phone_number, accademic_email) VALUES (' " +
                name + "' ', '" + lastName + "' , '" + personalEmail + "' , '" + phoneNumber + "' , '" + accademicEmail + ";";
            stmt.executeUpdate(sqlQuery);

            sqlQuery = "INSERT INTO Person (name, last_name, personal_email, phone_number) VALUES (' " + 
                name + "' ', '" + lastName + "' , '" + personalEmail + "' , '" + phoneNumber + ";" ;
            stmt.executeUpdate(sqlQuery);

            sqlQuery = "SELECT * FROM Student WHERE Name= '" + name +"' AND last_name = '" + lastName + "';" ;
            ResultSet rs = executeQuery(sqlQuery);
            System.out.println("The new student has been added with id: " + rs.getString("student_id"));
            rs.close();
        }
        else if (user.equals("3")){
            System.out.println("Insert the following information:");
            System.out.println("Name:");
            String name = scan.nextLine();  
            System.out.println("Last name:");
            String lastName = scan.nextLine();  
            System.out.println("Personal email:");
            String personalEmail = scan.nextLine();  
            System.out.println("Phone number:");
            String phoneNumber = scan.nextLine();  

            String sqlQuery = "INSERT INTO Guest (name, lastname, personal_email, phone_number) VALUES ('" 
                                + name + "', '" + lastName + "', '" + personalEmail + "', '" + phoneNumber + "');";
            stmt.executeUpdate(sqlQuery);

            sqlQuery = "INSERT INTO Person (name, last_name, personal_email, phone_number) VALUES (' " + 
                name + "' ', '" + lastName + "' , '" + personalEmail + "' , '" + phoneNumber + ";" ;
            stmt.executeUpdate(sqlQuery);

            sqlQuery = "SELECT * FROM Guest WHERE name= '" + name +"' AND last_name = '" + lastName + "';" ;
            ResultSet rs = executeQuery(sqlQuery);
            System.out.println("The new guest has been added with id: " + rs.getString("guest_id"));
            rs.close();
        }
        else{
            System.out.println("Invalid input");
        }
    }

    //4
    static void getAverageGrade () throws SQLException{
        System.out.println("Insert student id");
        String studentID = scan.nextLine();  
        String sqlQuery = "SELECT AVG(grade) AS average_gade FROM TakenExam WHERE grade >= 18 AND student_id = '" + studentID + "';";
        ResultSet rs = executeQuery(sqlQuery);
        System.out.println("The average grade for the student with id " + studentID + " is: " + rs.getString("average_grade") + "/30"); 
        rs.close();
    }

    //5
    static void getTimetable () throws SQLException{
        System.out.println("Select a faculty from: \n" + getFaculty());
        String faculty = scan.nextLine(); 
        System.out.println("Select a study program from: \n" + getStudyProgram(faculty));
        String studyProgram = scan.nextLine();  
        System.out.println("Select a course from: " + getCourses(studyProgram));
        String course = scan.nextLine();  
        System.out.println("Select a year from: \n" + getYear(course));
        String year = scan.nextLine();

        String slqQuery = "SELECT timetable FROM Course WHERE name = " + course + " AND year = " + year + ";";
        ResultSet rs = executeQuery(slqQuery);

        System.out.println("The timetable for the course " + course + "is: \n" + rs.getString("timetable"));
        rs.close();
    }

    //6
    static void getUpcomingEvents () throws SQLException{
        String sqlQuery = "SELECT * FROM Event WHERE date > '" + currentDate + "';";
        ResultSet rs = executeQuery(sqlQuery);

        System.out.println("Upcoming events:\n" );
        while (rs.next()){
            System.out.println(rs.getString("name") + " " + rs.getString("date_time") + " " + rs.getString("location"));
        }
        rs.close();
    }

    //7 Method that allow a person to sign up to an event (it needs only name and lastname)
    static void signUpForEvent () throws SQLException{
        //get personal info
        System.out.println("Insert your name:");
        String name = scan.nextLine();
        System.out.println("Insert your lastname:");
        String lastname = scan.nextLine();

        //get and print upcoming events
        getUpcomingEvents();

        //choose the event
        System.out.println("Insert the name of the event you want to sign up for:");
        String eventName = scan.nextLine();
        String sqlQuery = "INSERT INTO EventRegistration (name, lastname, event_name) VALUES ('"
                + name + "', '" + lastname + "', '" + eventName + "');";
        stmt.executeUpdate(sqlQuery);

        //get date of event
        sqlQuery = "SELECT date FROM Event WHERE name = '" + eventName + "';";
        ResultSet rs = executeQuery(sqlQuery);

        //display message of successful registration
        System.out.println("You have been registered to the event '" + eventName + "'in date " + rs.getDate("date"));
    }

    //8 Method that enroll a student into an exam
    static void enrollInExam () throws SQLException{
        String studentID = getUserID();
        //shows all available exams for the student
        String sqlQuery = "SELECT course_name FROM TakenExam WHERE status = 'notpassed' AND  student_id = '" + studentID + "');";
        ResultSet rs = executeQuery(sqlQuery);
        System.out.println("Available exams:\n" + rs.getString("course"));
        System.out.println("Insert the name of the exam you want to enroll in:");
        String examName = scan.nextLine();
        sqlQuery = "INSERT INTO TakenExam (student_id, exam_name) VALUES ('" + studentID + "', '" + examName + "');";

        stmt.executeUpdate(sqlQuery);

        System.out.println("You have been enrolled in the exam " + examName);
    }
    static void setGrade () throws SQLException{
        System.out.println("Insert your professor ID:");
        String profID = scan.nextLine();
        //check if the id is a professor
        String sqlQuery = "SELECT * FROM Professor WHERE id = '" + profID + "';";
        ResultSet rs = executeQuery(sqlQuery);
        if (rs.next() == false){
            System.out.println("The ID you inserted is not a professor ID");
            return;
        }
        System.out.println("Insert the name of the exam you want to set the grade for:");
        String examName = scan.nextLine();
        System.out.println("Insert the year of the exam you want to set the grade for:");
        String examYear = scan.nextLine();
        System.out.println("Insert the code of the exam you want to set the grade for:");
        String examCode = scan.nextLine();
        System.out.println("Insert the student ID:");
        String studentID = scan.nextLine();
        System.out.println("Insert the grade:");
        String grade = scan.nextLine();
        int gradeInt = Integer.parseInt(grade);
        String status = (gradeInt >= 18) ? "passed" : "not passed";

        sqlQuery = "UPDATE TakenExam SET grade = '" + grade + "', status = '" + status + "' WHERE student_id = '" + studentID + "' AND exam_name = '" + examName + "' AND exam_year = '" + examYear + "' AND exam_code = '" + examCode + "';";
        stmt.executeUpdate(sqlQuery);
    }

    //9 Method thet returns the students who passed a specific exam
    static void getPassedStudents () throws SQLException{ //list of students who passed an exam
        System.out.println("Which exam do you want to check?");
        System.out.println("Select a faculty from: \n" + getFaculty());
        String faculty = scan.nextLine();
        System.out.println("Select a study program from: \n" + getStudyProgram(faculty).getString("study_program_name"));
        String studyProgram = scan.nextLine();
        System.out.println("Select a course from: " + getCourses(studyProgram).getString("course_name"));
        String course = scan.nextLine();
        System.out.println("Select a year from: \n" + getYear(course).getString("year"));
        String year = scan.nextLine();

        String sqlQuery = "SELECT student_id FROM TakenExam WHERE status = 'passed' AND course_name = '" + course + "' AND course_year = '" + year + "' ;";
        ResultSet rs = executeQuery(sqlQuery);
        System.out.println("The students who passed the exam are: \n");
        
        while (rs.next()){
            System.out.println(rs.getString("student_id") + " " + rs.getString("name") + " " + rs.getString("lastname"));
        }
        rs.close();

    }

    //10 Method that returns the courses a student haven't attended yet or have not passed the exam yet
    static void getAvailableCourses () throws SQLException{
        String studentID = getUserID();
        //shows all available exams for the student
        String sqlQuery = "SELECT course_name FROM TakenExam WHERE status = 'notpassed' AND  student_id = '" + studentID + "');";
        ResultSet rs = executeQuery(sqlQuery);
        System.out.println("Available exams:\n" + rs.getString("course"));
        while (rs.next()){
            System.out.println(rs.getString("name"));
        }
    }

    //Support methods
    //1 prof, 2 student, 3 guest
    static String typeOfUser(){
        System.out.println("Are you a: " +
                                    "\n1 professor" +
                                    "\n2 student" +
                                    "\n3 guest");
        String input = scan.nextLine(); 

        return input; 
    }
    
    static ResultSet getFaculty (){
        String sqlQuery = "SELECT name FROM Faculty";
        ResultSet rs = executeQuery(sqlQuery);
        return rs;
    }
    
    static ResultSet getStudyProgram (String faculty){
        String sqlQuery = "SELECT study_program_name FROM IncludedStudyProgram WHERE faculty_name = " + faculty + ";" ;
        ResultSet rs = executeQuery(sqlQuery);
        return rs;
    }
    
    static ResultSet getCourses (String studyProgram){
        String sqlQuery = "SELECT course_name FROM OfferedCourses WHERE study_program_name = " + studyProgram + ";" ;
        ResultSet rs = executeQuery(sqlQuery);
        return rs;
    }
    
    static ResultSet getYear(String course) {
        String sqlQuery = "SELECT name, year course FROM Course WHERE name = " + course + ";" ;
        ResultSet rs = executeQuery(sqlQuery);
        return rs;
    }

    static ResultSet executeQuery(String query){
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    static String getUserID(){
        System.out.println("Insert ID:");
        String userID = scan.nextLine();
        return userID;
    }
}
