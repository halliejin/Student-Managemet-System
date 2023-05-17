package test;

import static org.junit.jupiter.api.Assertions.*;

import course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import role.Admin;
import role.Professor;
import role.Student;
import utils.FileUtils;

public class FileReaderTest {
    private FileUtils db;

    @BeforeEach
    void setUp() throws Exception {
        // initialize the db
        this.db = new FileUtils();

        // setup the db
        this.db.setUp("studentInfo.txt", "profInfo.txt", "adminInfo.txt"
                ,"courseInfo.txt" );
    }

    @Test
    void Test_SetUp() {
        Student test1 = this.db.getStudentsInfo().get(0);
        Student test2 = this.db.getStudentsInfo().get(1);
        assertEquals(2, this.db.getStudentsInfo().size());
        assertEquals("student", test1.getUserType());
        assertEquals("001", test1.getID());
        assertEquals("StudentName1", test1.getName());
        assertEquals("testStudent01", test1.getUserName());
        assertEquals("password590", test1.getPassWord());
        assertEquals("A", test2.getGrades().get("CIT592"));
        assertEquals("A-", test2.getGrades().get("CIT593"));

        Professor test3 = db.getProfessorsInfo().get(0);
        assertEquals(32, this.db.getProfessorsInfo().size());
        assertEquals("professor", test3.getUserType());
        assertEquals("Clayton Greenberg", test3.getName());

        Admin test4 = db.getAdminsInfo().get(1);
        assertEquals(3, this.db.getAdminsInfo().size());
        assertEquals("admin", test4.getUserType());
        assertEquals("admin02", test4.getUserName());
    }

    @Test
    void Test_StudentLogin() {
        Student test1 = this.db.getStudentsInfo().get(0);
        Student test2 = this.db.studentLogin(test1.getUserName(), test1.getPassWord());
        assertEquals(test2.getUserName(), test1.getUserName());
        assertEquals(test2.getPassWord(), test1.getPassWord());
        assertEquals(test2.getName(), test1.getName());

        assertEquals(null, this.db.studentLogin(test1.getUserName(), "akakakak"));
    }

    @Test
    void Test_ProfessorLogin() {
        Professor test1 = this.db.getProfessorsInfo().get(0);
        Professor test2 = this.db.professorLogin(test1.getUserName(), test1.getPassWord());
        assertEquals(test2.getUserName(), test1.getUserName());
        assertEquals(test2.getPassWord(), test1.getPassWord());
        assertEquals(test2.getName(), test1.getName());

        assertEquals(null, this.db.professorLogin(test1.getUserName(), "akakakak"));
    }


    //test admin log in
    @Test
    void Test_AdminLogin() {
        Admin test1 = this.db.getAdminsInfo().get(0);
        Admin test2 = this.db.adminLogin(test1.getUserName(), test1.getPassWord());
        assertEquals(test2.getUserName(), test1.getUserName());
        assertEquals(test2.getPassWord(), test1.getPassWord());
        assertEquals(test2.getName(), test1.getName());

        assertEquals(null, this.db.adminLogin(test1.getUserName(), "akakakak"));
    }


    //test capacity
    @Test
    void Test_FindCapacity() {
        assertEquals(100, this.db.findCapacity("100"));
        assertEquals(0, this.db.findCapacity(""));
    }


    //test find course
    @Test
    void Test_FindCourse() {
        Course test1 = this.db.getCourseInfo().get(0);
        Course test2 = this.db.findCourse(test1.getCourseNumber());
        assertEquals(test2.getCourseNumber(), test1.getCourseNumber());
        assertEquals(test2.getCourseName(), test1.getCourseName());

        assertEquals(null, this.db.findCourse("938485"));
    }


    //test add New Student
    @Test
    void Test_AddNewStudent() {
        int i1 = this.db.getStudentsInfo().size();

        assertTrue(this.db.addNewStudent("100", "sdkdk", "akwkdk", "23kfk"));
        assertEquals(i1 + 1, this.db.getStudentsInfo().size());
    }


    //test add new professor
    @Test
    void Test_AddNewProf() {
        Professor test1 = this.db.getProfessorsInfo().get(0);
        int i1 = this.db.getProfessorsInfo().size();

        assertFalse(this.db.addNewProf(test1.getID(), test1.getName(), test1.getUserName(), test1.getPassWord()));
        assertEquals(i1, this.db.getProfessorsInfo().size());

        assertFalse(this.db.addNewProf("3213123", test1.getName(), test1.getUserName(), test1.getPassWord()));
        assertEquals(i1, this.db.getProfessorsInfo().size());

        assertTrue(this.db.addNewProf("900", "test1", "test1", "test1590"));
        assertEquals(i1 + 1, this.db.getProfessorsInfo().size());

    }

}
