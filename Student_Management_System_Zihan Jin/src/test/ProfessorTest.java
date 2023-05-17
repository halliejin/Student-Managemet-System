package test;

import course.Course;
import role.Professor;
import utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfessorTest {
    private Professor prof;
    private FileUtils db;

    @BeforeEach
    void setUp() {
        this.db = new FileUtils();
        this.prof = new Professor("001", "Clayton Greenberg", "Greenberg", "password590");
        this.db.setUp("studentInfo.txt", "profInfo.txt", "adminInfo.txt"
                ,"courseInfo.txt" );
    }

    @Test
    void Test_GetUserType() {
        assertEquals("professor",prof.getUserType());
    }

    @Test
    void Test_AddCourse() throws ParseException {
        Course c1 = this.db.getCourseInfo().get(0);
        assertEquals(1,this.prof.addCourse(c1));
        assertEquals(0,this.prof.addCourse(c1));
    }

}

