package com.dkit.gd2.graciechaudhary.DATABASE.DTO;

public class Instructor {

    //AN INSTRUCTOR  WILL HAVE ID, NAME, EMAIL

    private int INSTRUCTOR_ID;
    private String INSTRUCTOR_NAME;

    private String INSTRUCTOR_EMAIL;

    public Instructor(int INSTRUCTOR_ID, String INSTRUCTOR_NAME, String INSTRUCTOR_EMAIL) {
        this.INSTRUCTOR_ID = INSTRUCTOR_ID;
        this.INSTRUCTOR_NAME = INSTRUCTOR_NAME;
        this.INSTRUCTOR_EMAIL = INSTRUCTOR_EMAIL;
    }

    public int getINSTRUCTOR_ID() {
        return INSTRUCTOR_ID;
    }

    public void setINSTRUCTOR_ID(int INSTRUCTOR_ID) {
        this.INSTRUCTOR_ID = INSTRUCTOR_ID;
    }

    public String getINSTRUCTOR_NAME() {
        return INSTRUCTOR_NAME;
    }

    public void setINSTRUCTOR_NAME(String INSTRUCTOR_NAME) {
        this.INSTRUCTOR_NAME = INSTRUCTOR_NAME;
    }

    public String getINSTRUCTOR_EMAIL() {
        return INSTRUCTOR_EMAIL;
    }

    public void setINSTRUCTOR_EMAIL(String INSTRUCTOR_EMAIL) {
        this.INSTRUCTOR_EMAIL = INSTRUCTOR_EMAIL;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "INSTRUCTOR_ID=" + INSTRUCTOR_ID +
                ", INSTRUCTOR_NAME='" + INSTRUCTOR_NAME + '\'' +
                ", INSTRUCTOR_EMAIL='" + INSTRUCTOR_EMAIL + '\'' +
                '}';
    }

    public void printIntructor() {
        System.out.printf("%-15s%-25s%-45s\n", INSTRUCTOR_ID, INSTRUCTOR_NAME,INSTRUCTOR_EMAIL);
    }
}
