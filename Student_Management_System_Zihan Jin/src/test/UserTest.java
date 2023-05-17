package test;

import role.Admin;
import role.Professor;
import role.Student;
import utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserTest {

    private Student stu;
    private Professor prof;
    private Admin ad;
    private FileUtils file;

    @BeforeEach
    void setUp() {
        stu = new Student("001", "Tom", "Tom", "123");
        prof = new Professor("002", "Jack", "Jack002", "123");
        ad = new Admin("003","Lucy", "Lucy003", "123");
        file = new FileUtils();
    }

    @Test
    void Test_GetUserName() {
        assertEquals("Tom", stu.getUserName());
        assertNotEquals("Jim",prof.getUserName());

    }

    @Test
    void Test_GetPassWord() {
        assertNotEquals("111", stu.getPassWord());
        assertEquals("123", ad.getPassWord());
    }

    @Test
    void Test_GetID() {
        assertEquals("003",ad.getID());
        assertNotEquals("222",stu.getID());
    }

    @Test
    void Test_GetName() {
        assertNotEquals("Jack",stu.getName());
        assertEquals("Jack",prof.getName());
    }

}