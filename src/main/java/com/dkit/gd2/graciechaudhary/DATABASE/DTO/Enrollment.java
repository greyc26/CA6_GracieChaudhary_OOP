package com.dkit.gd2.graciechaudhary.DATABASE.DTO;

public class Enrollment {

//AN ENROLLMENT WILL HAVE ID, COURSE_ID, CAO_NUMBER, DATE_ENROLLED

    private int ENROLLMENT_ID;
    private int COURSE_ID;
    private String DATE_ENROLLED;

    public Enrollment(int ENROLLMENT_ID, int COURSE_ID, String DATE_ENROLLED) {
        this.ENROLLMENT_ID = ENROLLMENT_ID;
        this.COURSE_ID = COURSE_ID;
        this.DATE_ENROLLED = DATE_ENROLLED;
    }

    public int getENROLLMENT_ID() {
        return ENROLLMENT_ID;
    }

    public void setENROLLMENT_ID(int ENROLLMENT_ID) {
        this.ENROLLMENT_ID = ENROLLMENT_ID;
    }

    public int getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(int COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public String getDATE_ENROLLED() {
        return DATE_ENROLLED;
    }

    public void setDATE_ENROLLED(String DATE_ENROLLED) {
        this.DATE_ENROLLED = DATE_ENROLLED;
    }

    @Override
    public String toString() {
        return "ENROLLMENT{" +
                "ENROLLMENT_ID=" + ENROLLMENT_ID +
                ", COURSE_ID=" + COURSE_ID +
                ", DATE_ENROLLED='" + DATE_ENROLLED + '\'' +
                '}';
    }
}