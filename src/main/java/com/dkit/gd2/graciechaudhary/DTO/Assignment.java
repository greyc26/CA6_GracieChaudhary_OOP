package com.dkit.gd2.graciechaudhary.DTO;

public class Assignment {

    //assignment will have id, course id, name, deadline and weight

    private int ASSIGNMENT_ID;
    private int COURSE_ID;
    private String ASSIGNMENT_NAME;
    private String ASSIGNMENT_DEADLINE;
    private int ASSIGNMENT_WEIGHTAGE;

    public Assignment(int ASSIGNMENT_ID, int COURSE_ID, String ASSIGNMENT_NAME, String ASSIGNMENT_DEADLINE, int ASSIGNMENT_WEIGHTAGE) {
        this.ASSIGNMENT_ID = ASSIGNMENT_ID;
        this.COURSE_ID = COURSE_ID;
        this.ASSIGNMENT_NAME = ASSIGNMENT_NAME;
        this.ASSIGNMENT_DEADLINE = ASSIGNMENT_DEADLINE;
        this.ASSIGNMENT_WEIGHTAGE = ASSIGNMENT_WEIGHTAGE;
    }

    public int getASSIGNMENT_ID() {
        return ASSIGNMENT_ID;
    }

    public void setASSIGNMENT_ID(int ASSIGNMENT_ID) {
        this.ASSIGNMENT_ID = ASSIGNMENT_ID;
    }

    public int getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(int COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public String getASSIGNMENT_NAME() {
        return ASSIGNMENT_NAME;
    }

    public void setASSIGNMENT_NAME(String ASSIGNMENT_NAME) {
        this.ASSIGNMENT_NAME = ASSIGNMENT_NAME;
    }

    public String getASSIGNMENT_DEADLINE() {
        return ASSIGNMENT_DEADLINE;
    }

    public void setASSIGNMENT_DEADLINE(String ASSIGNMENT_DEADLINE) {
        this.ASSIGNMENT_DEADLINE = ASSIGNMENT_DEADLINE;
    }

    public int getASSIGNMENT_WEIGHTAGE() {
        return ASSIGNMENT_WEIGHTAGE;
    }

    public void setASSIGNMENT_WEIGHTAGE(int ASSIGNMENT_WEIGHTAGE) {
        this.ASSIGNMENT_WEIGHTAGE = ASSIGNMENT_WEIGHTAGE;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "ASSIGNMENT_ID=" + ASSIGNMENT_ID +
                ", COURSE_ID=" + COURSE_ID +
                ", ASSIGNMENT_NAME='" + ASSIGNMENT_NAME + '\'' +
                ", ASSIGNMENT_DEADLINE='" + ASSIGNMENT_DEADLINE + '\'' +
                ", ASSIGNMENT_WEIGHTAGE=" + ASSIGNMENT_WEIGHTAGE +
                '}';
    }
}
