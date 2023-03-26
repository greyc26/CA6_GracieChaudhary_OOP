package com.dkit.gd2.graciechaudhary.DTO;

public class Course {

    private int COURSE_ID;
    private String COURSE_NAME;
    //to set level of course with help of an enum
    private int COURSE_CREDITS;
    private int MAX_ENROLLMENT_NUMBER;
    private int CURRENT_ENROLLMENT_NUMBER;
    private int instructorId;

    public Course(int COURSE_ID, String COURSE_NAME, int MAX_ENROLLMENT_NUMBER, int CURRENT_ENROLLMENT_NUMBER, int instructorId) {
        this.COURSE_ID = COURSE_ID;
        this.COURSE_NAME = COURSE_NAME;
        this.MAX_ENROLLMENT_NUMBER = MAX_ENROLLMENT_NUMBER;
        this.CURRENT_ENROLLMENT_NUMBER = CURRENT_ENROLLMENT_NUMBER;
        this.instructorId = instructorId;
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

    public int getCURRENT_ENROLLMENT_NUMBER() {
        return CURRENT_ENROLLMENT_NUMBER;
    }

    public void setCURRENT_ENROLLMENT_NUMBER(int CURRENT_ENROLLMENT_NUMBER) {
        this.CURRENT_ENROLLMENT_NUMBER = CURRENT_ENROLLMENT_NUMBER;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "Course{" + "COURSE_ID=" + COURSE_ID + ", COURSE_NAME=" + COURSE_NAME + ", MAX_ENROLLMENT_NUMBER=" + MAX_ENROLLMENT_NUMBER + ", CURRENT_ENROLLMENT_NUMBER=" + CURRENT_ENROLLMENT_NUMBER + ", instructorId=" + instructorId + '}';
    }
}
