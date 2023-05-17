package test;

import course.Course;
import role.Admin;
import role.Professor;
import role.Student;
import utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class AdminTest {

    private FileUtils db;

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin("001", "tom", "tom001", "123");

        // initialize the db
        this.db = new FileUtils();

        // setup the db
        this.db.setUp("studentInfo.txt", "profInfo.txt", "adminInfo.txt"
                ,"courseInfo.txt" );
    }

    @Test
    void Test_GetUserType() {
        assertEquals("admin", admin.getUserType());

    }

    @Test
    void Test_DeleteStudent() {
        Student test1 = this.db.getStudentsInfo().get(0);
        int originalSize = this.db.getStudentsInfo().size();

        // Delete first student manually
        admin.deleteStudent(this.db,"1");

        assertEquals(originalSize - 1, this.db.getStudentsInfo().size());
        assertEquals(0, test1.getCourseList().size());
    }

    @Test
    void Test_AddNewStudent() {
        Student test1 = this.db.getStudentsInfo().get(0);
        int originalSize = this.db.getStudentsInfo().size();

        // Add new student manually
        admin.addNewStudent(this.db,"1");
        assertEquals(originalSize + 1, this.db.getStudentsInfo().size());
    }

    @Test
    void Test_DeleteProf() {
        Professor test1 = this.db.getProfessorsInfo().get(0);
        int originalSize = this.db.getProfessorsInfo().size();

        // Delete first professor manually
        admin.deleteProf(this.db, "1");

        assertEquals(originalSize - 1, this.db.getProfessorsInfo().size());
        assertEquals(0, test1.getCourseList().size());
    }

    @Test
    void Test_AddNewProf() {
        Professor test1 = this.db.getProfessorsInfo().get(0);
        int originalSize = this.db.getProfessorsInfo().size();

        // Add new professor manually
        admin.addNewProf(this.db,"1");
        assertEquals(originalSize + 1, this.db.getProfessorsInfo().size());
    }

    @Test
    void Test_DeleteCourse() {
        Professor prof1 = this.db.getProfessorsInfo().get(0);
        Course course1 = prof1.getCourseList().get(0);
        int i1 = prof1.getCourseList().size();
        int i2 = this.db.getCourseInfo().size();

        admin.deleteCourse(this.db, this.db.getStudentsInfo(), prof1.getName(), course1);

        for (Student std : this.db.getStudentsInfo())
        {
            assertFalse(std.getCourseList().contains(course1));
        }

        assertEquals(i1 - 1, prof1.getCourseList().size());
        assertEquals(i2 - 1, this.db.getCourseInfo().size());
    }

}
