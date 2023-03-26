package com.dkit.gd2.graciechaudhary.DTO;

public class Instructor {

    //AN INSTRUCTOR  WILL HAVE ID, NAME, EMAIL

    private int INSTRUCTOR_ID;
    private String INSTRUCTOR_FIRSTNAME;
    private String INSTRUCTOR_LASTNAME;
    private String INSTRUCTOR_EMAIL;

    public Instructor(int INSTRUCTOR_ID, String INSTRUCTOR_NAME, String INSTRUCTOR_EMAIL) {
        this.INSTRUCTOR_ID = INSTRUCTOR_ID;
        this.INSTRUCTOR_FIRSTNAME = INSTRUCTOR_FIRSTNAME;
        this.INSTRUCTOR_LASTNAME = INSTRUCTOR_LASTNAME;
        this.INSTRUCTOR_EMAIL = INSTRUCTOR_EMAIL;
    }

    public int getINSTRUCTOR_ID() {
        return INSTRUCTOR_ID;
    }

    public void setINSTRUCTOR_ID(int INSTRUCTOR_ID) {
        this.INSTRUCTOR_ID = INSTRUCTOR_ID;
    }

    public String getINSTRUCTOR_FIRSTNAME() {
        return INSTRUCTOR_FIRSTNAME;
    }

    public void setINSTRUCTOR_FIRSTNAME(String INSTRUCTOR_FIRSTNAME) {
        this.INSTRUCTOR_FIRSTNAME = INSTRUCTOR_FIRSTNAME;
    }

    public String getINSTRUCTOR_LASTNAME() {
        return INSTRUCTOR_LASTNAME;
    }

    public void setINSTRUCTOR_LASTNAME(String INSTRUCTOR_LASTNAME) {
        this.INSTRUCTOR_LASTNAME = INSTRUCTOR_LASTNAME;
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
                ", INSTRUCTOR_FIRSTNAME='" + INSTRUCTOR_FIRSTNAME + '\'' +
                ", INSTRUCTOR_LASTNAME='" + INSTRUCTOR_LASTNAME + '\'' +
                ", INSTRUCTOR_EMAIL='" + INSTRUCTOR_EMAIL + '\'' +
                '}';
    }
}
