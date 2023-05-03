package com.dkit.gd2.graciechaudhary.DATABASE.DTO;

public class Submission {

    //submission will have assignment id, student id, submission date, submission mark, submission feedback

    private int ASSIGNMENT_ID;
    private int STUDENT_ID;
    private String SUBMISSION_DATE;
    private int SUBMISSION_MARK;
    private String SUBMISSION_FEEDBACK;

    public Submission(int ASSIGNMENT_ID, int STUDENT_ID, String SUBMISSION_DATE, int SUBMISSION_MARK, String SUBMISSION_FEEDBACK) {
        this.ASSIGNMENT_ID = ASSIGNMENT_ID;
        this.STUDENT_ID = STUDENT_ID;
        this.SUBMISSION_DATE = SUBMISSION_DATE;
        this.SUBMISSION_MARK = SUBMISSION_MARK;
        this.SUBMISSION_FEEDBACK = SUBMISSION_FEEDBACK;
    }

    public int getASSIGNMENT_ID() {
        return ASSIGNMENT_ID;
    }

    public void setASSIGNMENT_ID(int ASSIGNMENT_ID) {
        this.ASSIGNMENT_ID = ASSIGNMENT_ID;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getSUBMISSION_DATE() {
        return SUBMISSION_DATE;
    }

    public void setSUBMISSION_DATE(String SUBMISSION_DATE) {
        this.SUBMISSION_DATE = SUBMISSION_DATE;
    }

    public int getSUBMISSION_MARK() {
        return SUBMISSION_MARK;
    }

    public void setSUBMISSION_MARK(int SUBMISSION_MARK) {
        this.SUBMISSION_MARK = SUBMISSION_MARK;
    }

    public String getSUBMISSION_FEEDBACK() {
        return SUBMISSION_FEEDBACK;
    }

    public void setSUBMISSION_FEEDBACK(String SUBMISSION_FEEDBACK) {
        this.SUBMISSION_FEEDBACK = SUBMISSION_FEEDBACK;
    }

    @Override
    public String toString() {
        return "Submission{" + "ASSIGNMENT_ID=" + ASSIGNMENT_ID + ", STUDENT_ID=" + STUDENT_ID + ", SUBMISSION_DATE=" + SUBMISSION_DATE + ", SUBMISSION_MARK=" + SUBMISSION_MARK + ", SUBMISSION_FEEDBACK=" + SUBMISSION_FEEDBACK + '}';
    }

}
