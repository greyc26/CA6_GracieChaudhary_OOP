package com.dkit.gd2.graciechaudhary.DTO;

public class Course {

    private int COURSE_ID;
    private String COURSE_NAME;
    //to set level of course with help of an enum
    private int INSTRUCTOR_ID;
    private int MAX_ENROLLMENT_NUMBER;


    public Course(int COURSE_ID, String COURSE_NAME, int INSTRUCTOR_ID, int MAX_ENROLLMENT_NUMBER) {
        this.COURSE_ID = COURSE_ID;
        this.COURSE_NAME = COURSE_NAME;
        this.INSTRUCTOR_ID = INSTRUCTOR_ID;
        this.MAX_ENROLLMENT_NUMBER = MAX_ENROLLMENT_NUMBER;
    }

    public int getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(int COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public String getCOURSE_NAME() {
        return COURSE_NAME;
    }

    public void setCOURSE_NAME(String COURSE_NAME) {
        this.COURSE_NAME = COURSE_NAME;
    }

    public int getMAX_ENROLLMENT_NUMBER() {
        return MAX_ENROLLMENT_NUMBER;
    }

    public void setMAX_ENROLLMENT_NUMBER(int MAX_ENROLLMENT_NUMBER) {
        this.MAX_ENROLLMENT_NUMBER = MAX_ENROLLMENT_NUMBER;
    }

    public int getINSTRUCTOR_ID() {
        return INSTRUCTOR_ID;
    }

    public void setINSTRUCTOR_ID(int INSTRUCTOR_ID) {
        this.INSTRUCTOR_ID = INSTRUCTOR_ID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "COURSE_ID=" + COURSE_ID +
                ", COURSE_NAME='" + COURSE_NAME + '\'' +
                ", INSTRUCTOR_ID=" + INSTRUCTOR_ID +
                ", MAX_ENROLLMENT_NUMBER=" + MAX_ENROLLMENT_NUMBER +
                '}';
    }
}

//completed
