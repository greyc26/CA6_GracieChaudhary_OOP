package com.dkit.gd2.graciechaudhary;

import com.dkit.gd2.graciechaudhary.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DTO.Course;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.List;
import java.util.Scanner;


public class MainMenu {

    private static Scanner keyboard = new Scanner(System.in);

    //Feature 1 – Find all Entities e.g. findAllPlayers() to return a List of all the entities and display that list.
    // Feature 2 – Find and Display (a single) Entity by Key e.g. findPlayerById( id ) – return a single entity and display its contents.
    // Feature 3 – Delete an Entity by key e.g. deletePlayerById( id ) – remove entity from database
    // Feature 4 – Insert an Entity in the database using DAO (gather data from user, store in DTO object, pass into method insertPlayer ( Player ), return new entity including assigned auto-id.
    // Feature 5 – List entities using a filter e.g. findPlayerUsingFilter( playerAgeComparator )
    // Feature 6 – Create a Cache using a HashSet that will maintain the ids of all players and refactor the findPlayerById() method so that
    // it checks for the existence of a player id before it makes a query to the SQL database.

    public static void main(String[] args) {
        MySqlCourseDAO courseDao = new MySqlCourseDAO();
        callMenu(courseDao);
    }

    private static void printMenu()
    {
        System.out.println("\n**********MENU**********");
        System.out.println("1. Find all Courses");
        System.out.println("2. Find and Display a Course by ID");
        System.out.println("3. Find course by maximum number of students");
        System.out.println("4. Delete Course by ID");
        System.out.println("5. Insert Course");
        System.out.println("6. Find all courses as JSON");
        System.out.println("7. Find courses by id as JSON");
        System.out.println("8. Exit");
    }

    private static int getChoiceForMenu(){
        int choice = 0;
        boolean valid = false;
        while(!valid){
            try{
                System.out.println("Enter choice: ");
                choice = keyboard.nextInt();
                if(choice > 0 && choice < 9){
                    valid = true;
                }
                else{
                    System.out.println("Invalid choice, please try again");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid choice, please try again");
            }
        }
        return choice;
    }

    private static void feature1(MySqlCourseDAO courseDao) {
        try{
            System.out.println("\nAll courses: ");
            List<Course> allCourses = courseDao.findAllCourses();
            for(Course course : allCourses){
                System.out.println(course);
            }
        }
        catch(DAOException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    private static void feature2(MySqlCourseDAO courseDao) {
        try{
            System.out.println("\nEnter course id: ");
            int courseId = keyboard.nextInt();
            Course course = courseDao.findCourseById(courseId);
            if(course!=null){
                System.out.println(course);
            }
            else{
                System.out.println("Course not found");
            }
        }
        catch(DAOException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    private static void feature3(MySqlCourseDAO courseDao) {
        try{
            System.out.println("\nEnter maximum number of student for filters: ");
            int maxEnrollemtNumber = keyboard.nextInt();
            List<Course> allCourses = courseDao.findCourseUsingFilter(maxEnrollemtNumber);
            for(Course course : allCourses){
                System.out.println(course);
            }
        }
        catch(DAOException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    private static void feature4(MySqlCourseDAO courseDao) {
        try{
            System.out.println("\nEnter course id: ");
            int courseId = keyboard.nextInt();
            boolean check = courseDao.deleteCourseById(courseId);
            if(check){
                System.out.println("Course deleted");
            }
            else{
                System.out.println("Course not found");
            }
        }
        catch(DAOException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    private static boolean checkCourse(Course course, MySqlCourseDAO courseDao) throws DAOException {



        if(course.getCOURSE_NAME().length() < 3 || course.getCOURSE_NAME().length() > 45){
            System.out.println("Invalid course name");
            return false;
        }

        if(course.getMAX_ENROLLMENT_NUMBER() < 10){
            System.out.println("Invalid max number of students");
            return false;
        }

        if(course.getINSTRUCTOR_ID() > 10){
            System.out.println("Invalid instructor id");
            return false;
        }

        return true;
    }

    private static boolean checkId(int id, MySqlCourseDAO courseDao) throws DAOException {
        if(id < 10){
            System.out.println("\nInvalid course id");
            return false;
        }

        List<Course> allCourses = courseDao.findAllCourses();
        for(Course c : allCourses){
            if(c.getCOURSE_ID() == id){
                System.out.println("\nCourse id already exists");
                return false;
            }
        }
        return true;
    }

    private static boolean checkName(String name, MySqlCourseDAO courseDao) throws DAOException {
        if(name.length() < 3 || name.length() > 45){
            System.out.println("\nInvalid course name");
            return false;
        }

        List<Course> allCourses = courseDao.findAllCourses();
        for(Course c : allCourses){
            if(c.getCOURSE_NAME().equals(name)){
                System.out.println("\nCourse name already exists");
                return false;
            }
        }
        return true;
    }

    private static boolean checkInstructorID(int id, MySqlCourseDAO courseDao) throws DAOException {
        if(id > 10){
            System.out.println("\nInvalid instructor id");
            return false;
        }
        return true;
    }

    private static boolean checkMaxEnrollmentNumber(int number, MySqlCourseDAO courseDao) throws DAOException {
        if(number < 10){
            System.out.println("\nInvalid max number of students");
            return false;
        }
        return true;
    }
    private static void feature5(MySqlCourseDAO courseDao) {
        try {
            System.out.println("\nEnter course id: ");
            int course_Id = keyboard.nextInt();
            boolean check = checkId(course_Id, courseDao);
            if (check) {
                System.out.println("\nEnter course name: ");
                String course_Name = keyboard.next();
                keyboard.nextLine();
                check = checkName(course_Name, courseDao);
                if (check) {
                    System.out.println("Enter instructor id: ");
                    int instructor_ID = keyboard.nextInt();
                    check = checkInstructorID(instructor_ID, courseDao);
                    if (check) {
                        System.out.println("Enter max enrollment number: ");
                        int max_Enrollment_Number = keyboard.nextInt();
                        Course course = new Course(course_Id, course_Name, instructor_ID, max_Enrollment_Number);
                        check = checkCourse(course, courseDao);
                        if (check) {
                            boolean check2 = courseDao.insertCourse(course);
                            if (check2) {
                                System.out.println("Course inserted");
                            } else {
                                System.out.println("Course not inserted");
                            }
                        } else {
                            System.out.println("Error in enrollment number, course not inserted");
                        }
                    } else {
                        System.out.println("Error in instructor id, course not inserted");
                    }
                } else {
                    System.out.println("Error in course name, course not inserted");
                }
            } else {
                System.out.println("Error in course id,course not inserted");
            }
        }

        catch(DAOException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    private static void feature6(MySqlCourseDAO courseDao) {
        try{
            System.out.println("\nAll courses as JSON: ");
            System.out.println(courseDao.findAllCoursesAsJSON());
        }
        catch(DAOException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    private static void feature7(MySqlCourseDAO courseDao) {
        try{
            System.out.println("\nEnter course id: ");
            int courseId = keyboard.nextInt();
            System.out.println("Course by id as JSON: ");
            System.out.println(courseDao.findCoursesByIdAsJSON(courseId));
        }
        catch(DAOException e){
            System.out.println("Error"+e.getMessage());
        }
    }
    public static void callMenu(MySqlCourseDAO courseDao) {
       boolean loop = true;

       while (loop){

           printMenu();
          int choice = getChoiceForMenu();

          switch (choice){
              case 1:
                  feature1(courseDao);
                  break;
              case 2:
                  feature2(courseDao);
                  break;
              case 3:
                  feature3(courseDao);
                  break;
              case 4:
                  feature4(courseDao);
                  break;
              case 5:
                  feature5(courseDao);
                  break;
              case 6:
                  feature6(courseDao);
                  break;
              case 7:
                  feature7(courseDao);
                  break;
              case 8:
                  loop = false;
                  break;


          }
       }
    }
}
