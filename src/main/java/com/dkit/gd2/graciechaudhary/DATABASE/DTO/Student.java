package com.dkit.gd2.graciechaudhary.DATABASE.DTO;

import java.util.Date;

public class Student {

    //for this dto, student has an id, a name, email and dob

    private int STUDENT_ID;
    private String STUDENT_NAME;
    private String STUDENT_EMAIL;
    private Date STUDENT_DOB;

    public Student(int STUDENT_ID, String STUDENT_NAME, String STUDENT_EMAIL, Date STUDENT_DOB) {
        this.STUDENT_ID = STUDENT_ID;
        this.STUDENT_NAME = STUDENT_NAME;
        this.STUDENT_EMAIL = STUDENT_EMAIL;
        this.STUDENT_DOB = STUDENT_DOB;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getSTUDENT_NAME() {
        return STUDENT_NAME;
    }

    public void setSTUDENT_NAME(String STUDENT_NAME) {
        this.STUDENT_NAME = STUDENT_NAME;
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
        return "Student{" +
                "STUDENT_ID=" + STUDENT_ID +
                ", STUDENT_NAME='" + STUDENT_NAME + '\'' +
                ", STUDENT_EMAIL='" + STUDENT_EMAIL + '\'' +
                ", STUDENT_DOB=" + STUDENT_DOB +
                '}';
    }

    public void printStudent(){
        System.out.printf("%-6s%-40s%-45s%-15s\n", STUDENT_ID, STUDENT_NAME, STUDENT_EMAIL, STUDENT_DOB);
    }
}
