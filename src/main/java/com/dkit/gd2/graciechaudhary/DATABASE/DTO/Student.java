package com.dkit.gd2.graciechaudhary.DATABASE.DTO;

import java.util.Date;

public class Student {

    //for this dto, student has an id, a name, email and dob

    private int STUDENT_ID;
    private String STUDENT_FIRSTNAME;
    public String STUDENT_LASTNAME;
    private String STUDENT_EMAIL;
    private Date STUDENT_DOB;

    public Student(int STUDENT_ID, String STUDENT_FIRSTNAME,String STUDENT_LASTNAME, String STUDENT_EMAIL, Date STUDENT_DOB) {
        this.STUDENT_ID = STUDENT_ID;
        this.STUDENT_FIRSTNAME = STUDENT_FIRSTNAME;
        this.STUDENT_LASTNAME = STUDENT_LASTNAME;
        this.STUDENT_EMAIL = STUDENT_EMAIL;
        this.STUDENT_DOB = STUDENT_DOB;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getSTUDENT_FIRSTNAME() {
        return STUDENT_FIRSTNAME;
    }

    public void setSTUDENT_FIRSTNAME(String STUDENT_FIRSTNAME) {
        this.STUDENT_FIRSTNAME = STUDENT_FIRSTNAME;
    }

    public String getSTUDENT_LASTNAME() {
        return STUDENT_LASTNAME;
    }

    public void setSTUDENT_LASTNAME(String STUDENT_LASTNAME) {
        this.STUDENT_LASTNAME = STUDENT_LASTNAME;
    }

    public String getSTUDENT_EMAIL() {
        return STUDENT_EMAIL;
    }

    public void setSTUDENT_EMAIL(String STUDENT_EMAIL) {
        this.STUDENT_EMAIL = STUDENT_EMAIL;
    }

    public Date getSTUDENT_DOB() {
        return STUDENT_DOB;
    }

    public void setSTUDENT_DOB(Date STUDENT_DOB) {
        this.STUDENT_DOB = STUDENT_DOB;
    }

    @Override
    public String toString() {
        return "STUDENT{" +
                "STUDENT_ID=" + STUDENT_ID +
                ", STUDENT_FIRSTNAME='" + STUDENT_FIRSTNAME + '\'' +
                ", STUDENT_LASTNAME='" + STUDENT_LASTNAME + '\'' +
                ", STUDENT_EMAIL='" + STUDENT_EMAIL + '\'' +
                ", STUDENT_DOB=" + STUDENT_DOB +
                '}';
    }
}
