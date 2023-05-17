package role;

import course.Course;

import java.text.ParseException;

public abstract class User {
    /**
     * Fields: name, ID, courseList and grades
     */

    // name of the user
    private String name;

    // ID of the user
    private String ID;

    /**
     * Credential information: userName and passWord
     */
    private String userName;

    private String passWord;


    // constructor
    public User(String id, String name, String userName, String passWord) {
        this.name = name;
        this.ID  = id;
        this.userName = userName;
        this.passWord = passWord;
    }


    public abstract String getUserType ();


    // get userName
    public String getUserName() {
        return userName;
    }


    // get password
    public String getPassWord() {
        return passWord;
    }


    // get ID
    public String getID() {
        return ID;
    }

    // get name
    public String getName() {
        return name;
    }


    public abstract int addCourse(Course course) throws ParseException;
}
