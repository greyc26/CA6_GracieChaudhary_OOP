package com.dkit.gd2.graciechaudhary.Core;

import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Instructor;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class Utility {
    public static final String COURSE_HEADER = format("%-20s%-40s%-20s%-10s\n", "COURSE_ID", "COURSE_NAME", "INSTRUCTOR_ID", "MAX_ENROLLEMENT_NUMBER");
    public static final String INSTRUCTOR_HEADER = format("%-6s%-35s%-45s\n", "INSTRUCTOR_ID", "INSTRUCTOR_NAME", "INSTRUCTOR_EMAIL");

    public static int getIntInput(Scanner keyboard) {
        int choice = -1;
        try{
            System.out.println(Colours.BLUE_BOLD_BRIGHT + "Enter your choice: " + Colours.RESET);
            if(keyboard.hasNextLine()){
                choice = keyboard.nextInt();
            }
            else {
                System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid number." + Colours.RESET);
                keyboard.nextLine();
            }
        }

        catch (Exception e){
            System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid number." + Colours.RESET);
            keyboard.nextLine();
        }

        return choice;
    }


    public static int getIdInput(Scanner keyboard) {
        int choice = -1;

        while (choice < 1){
            try{
                if(keyboard.hasNextLine()) {
                    choice = keyboard.nextInt();
                    if (choice < 1)
                        throw new Exception();
                }
                else{
                    System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid ID." + Colours.RESET);
                    System.out.print(Colours.BLUE_BOLD_BRIGHT + "Enter a valid ID: " + Colours.RESET);
                    choice = -1;
                    keyboard.nextLine();
                }
            }
            catch (Exception e){
                System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid ID." + Colours.RESET);
                System.out.print(Colours.BLUE_BOLD_BRIGHT + "Enter a valid ID: " + Colours.RESET);
                choice = -1;
                keyboard.nextLine();
            }
        }
        return choice;
    }


    public static String getNameInput(Scanner keyboard) {
        String name = "";
        keyboard.nextLine();
        while (name.length() < 3 || name.length() > 45){
            try{
                name = keyboard.nextLine();
                if (name.length() < 2)
                    throw new Exception();
            }
            catch (Exception e){
                System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid name." + Colours.RESET);
                System.out.print(Colours.BLUE_BOLD_BRIGHT + "Enter a valid name: " + Colours.RESET);
                name = "";
            }
        }
        return name;
    }


    public static int getInstructorIdInput(Scanner keyboard) {
        int instructorId = -1;
        while (instructorId < 0 || instructorId > 11){
            try{
                if(keyboard.hasNextLine()) {
                    instructorId = keyboard.nextInt();
                    if (instructorId < 0)
                        throw new Exception();
                }
                else{
                    System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid instructor ID." + Colours.RESET);
                    System.out.print(Colours.BLUE_BOLD_BRIGHT +"Enter a valid instructor ID: " + Colours.RESET);
                    keyboard.nextLine();
                    instructorId = -1;
                }
            }
            catch (Exception e){
                System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid price." + Colours.RESET);
                System.out.print(Colours.BLUE_BOLD_BRIGHT +"Enter a valid price: " + Colours.RESET);
                keyboard.nextLine();
                instructorId = -1;
            }
        }
        return instructorId;
    }



    public static Course getCourseFromJSON(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Course.class);
    }

    public static String getJSONFromCourse(Course course){
        Gson gson = new Gson();
        return gson.toJson(course);
    }

    public static List<Course> getCoursesFromJSON(String json){
        Gson gson = new Gson();
        Type listType = new com.google.gson.reflect.TypeToken<List<Course>>(){}.getType();
        return gson.fromJson(json, listType);
    }




    public static Instructor getInstructorFromJSON(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Instructor.class);
    }

    public static String getJSONFromInstructor(Instructor instructor){
        Gson gson = new Gson();
        return gson.toJson(instructor);
    }

    private static boolean checkId(int id, MySqlCourseDAO courseDao) throws DAOException {
        if(id < 0){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid course id"+Colours.RESET);
            return false;
        }

        List<Course> allCourses = courseDao.findAllCourses();
        for(Course c : allCourses){
            if(c.getCOURSE_ID() == id){
                System.out.println(Colours.RED_BOLD_BRIGHT+"\nCourse id already exists"+Colours.RESET);
                return false;
            }
        }
        return true;
    }

    private static boolean checkName(String name, MySqlCourseDAO courseDao) throws DAOException {
        if(name.length() < 3 || name.length() > 45){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid course name"+Colours.RESET);
            return false;
        }

        List<Course> allCourses = courseDao.findAllCourses();
        for(Course c : allCourses){
            if(c.getCOURSE_NAME().equals(name)){
                System.out.println(Colours.RED_BOLD_BRIGHT+"\nCourse name already exists"+Colours.RESET);
                return false;
            }
        }
        return true;
    }

    private static boolean checkInstructorID(int id, MySqlCourseDAO courseDao) throws DAOException {
        if(id > 10){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid instructor id"+Colours.RESET);
            return false;
        }
        return true;
    }

    private static boolean checkMaxEnrollmentNumber(int number, MySqlCourseDAO courseDao) throws DAOException {
        if(number < 10){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid max number of students"+Colours.RESET);
            return false;
        }
        return true;
    }
    public static Course addCourse(MySqlCourseDAO courseDao, Scanner keyboard) throws DAOException {
        try {
            System.out.println(Colours.YELLOW_BOLD_BRIGHT + "\nTo insert a course you need to enter password:" + Colours.RESET);
            if (keyboard.hasNextInt()) {
                int password = keyboard.nextInt();
                if (password == 2607) {
                    System.out.println(Colours.BLUE_BOLD_BRIGHT + "\nEnter course id: ");
                    if (keyboard.hasNextInt()) {
                        int course_Id = keyboard.nextInt();
                        boolean check = checkId(course_Id, courseDao);
                        keyboard.nextLine();

                        if (check) {
                            System.out.println("Enter course name: ");
                            String course_Name = keyboard.nextLine();

                            check = checkName(course_Name, courseDao);
                            if (check) {
                                System.out.println("Enter instructor id: ");
                                if (keyboard.hasNextInt()) {
                                    int instructor_ID = keyboard.nextInt();
                                    check = checkInstructorID(instructor_ID, courseDao);

                                    if (check) {
                                        System.out.println("Enter max enrollment number: " + Colours.RESET);
                                        if (keyboard.hasNextInt()) {
                                            int max_Enrollment_Number = keyboard.nextInt();
                                            Course course = new Course(course_Id, course_Name, instructor_ID, max_Enrollment_Number);
                                            check = checkMaxEnrollmentNumber(max_Enrollment_Number, courseDao);
                                            if (check) {
                                                Course newCourse = new Course(course_Id, course_Name, instructor_ID, max_Enrollment_Number);
                                                return newCourse;
                                            } else {
                                                System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid max enrollment number" + Colours.RESET);
                                            }
                                        } else {
                                            System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid input" + Colours.RESET);
                                        }
                                    } else {
                                        System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid instructor id" + Colours.RESET);
                                    }
                                } else {
                                    System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid input" + Colours.RESET);
                                }
                            } else {
                                System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid course name" + Colours.RESET);
                            }
                        } else {
                            System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid course id" + Colours.RESET);
                        }
                    } else {
                        System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid input" + Colours.RESET);
                    }
                } else {
                    System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid password" + Colours.RESET);
                }
            } else {
                System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid input" + Colours.RESET);
            }
        }

        catch (InputMismatchException e) {
            System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid choice, please try again" + Colours.RESET);
            keyboard.next(); // discard invalid input
        }
        catch (NumberFormatException e) {
            System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid choice, please try again" + Colours.RESET);

        }
        return null;
}



//    public static List<Item> getItemsFromJson(String json){
//        Gson gson = new Gson();
//        Type listType = new com.google.gson.reflect.TypeToken<List<Item>>(){}.getType();
//        return gson.fromJson(json, listType);
//    }
//
//    public static List<DetailedBuild> getDetailedBuildsFromJson(String json){
//        Gson gson = new Gson();
//        Type listType = new com.google.gson.reflect.TypeToken<List<DetailedBuild>>(){}.getType();
//        return gson.fromJson(json, listType);
//    }
//
//    public static String getJsonFromBuild(Build build){
//        Gson gson = new Gson();
//        return gson.toJson(build);
//    }
//
//    public static Build getBuildFromJson(String json){
//        Gson gson = new Gson();
//        return gson.fromJson(json, Build.class);
//    }

    public static String getJsonFromList(List<?> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }



}

