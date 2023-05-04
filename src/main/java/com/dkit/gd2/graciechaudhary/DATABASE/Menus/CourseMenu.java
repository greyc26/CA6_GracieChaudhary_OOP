package com.dkit.gd2.graciechaudhary.DATABASE.Menus;

import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class CourseMenu {

    private  Scanner keyboard = new Scanner(System.in);
    MySqlCourseDAO courseDao = new MySqlCourseDAO();

//    public static void main(String[] args) {
//        MySqlCourseDAO courseDao = new MySqlCourseDAO();
//        callMenu(courseDao);
//    }

    private  void printMenu()
    {
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"\n*** COURSE MENU ***"+Colours.RESET);
        System.out.println(Colours.MAGENTA+"0. Exit");
        System.out.println("1. Find all Courses");
        System.out.println("2. Find and Display a Course by ID");
        System.out.println("3. Find course by maximum number of students");
        System.out.println("4. Delete Course by ID");
        System.out.println("5. Insert Course");
        System.out.println("6. Find all courses as JSON");
        System.out.println("7. Find courses by id as JSON"+Colours.RESET);

    }

    private int getChoiceForMenu(){
        int choice = 0;
        boolean valid = false;
        while(!valid){
            try{
                System.out.println(Colours.BLUE_BOLD_BRIGHT+ "Enter choice: "+Colours.RESET);
                if(keyboard.hasNextInt()){
                    choice = keyboard.nextInt();
                    if(choice > -1 && choice <8){
                        valid = true;
                    }
                    else{
                        System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid choice, please try again"+Colours.RESET);
                    }
                }
                else{
                    System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid choice, please try again"+Colours.RESET);
                    keyboard.next(); // discard invalid input
                }
            }
            catch(Exception e){
                System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid choice, please try again"+Colours.RESET);
                keyboard.next();
            }

        }
        return choice;
    }

    private  void feature1(MySqlCourseDAO courseDao) {
        try{
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nAll courses: "+Colours.RESET+Colours.YELLOW_BOLD_BRIGHT);
            List<Course> allCourses = courseDao.findAllCourses();
            System.out.println(Utility.COURSE_HEADER+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
            for(Course course : allCourses){
                course.printCourse();
            }
            System.out.println(Colours.RESET);
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private  void feature2(MySqlCourseDAO courseDao) {
        try{
            System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nEnter course id: "+Colours.RESET);
            if(keyboard.hasNextLine()) {
                int courseId = keyboard.nextInt();
                Course course = courseDao.findCourseById(courseId);
                if (course != null) {
                    System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nCourse found: " + Colours.RESET + Colours.YELLOW_BOLD_BRIGHT);
                    System.out.println(Utility.COURSE_HEADER + Colours.RESET + Colours.WHITE_BOLD_BRIGHT);
                    course.printCourse();
                    System.out.println(Colours.RESET);
                } else {
                    System.out.println(Colours.RED_BOLD_BRIGHT + "Course not found" + Colours.RESET);
                }
            }
            else{
                System.out.println(Colours.RED_BOLD_BRIGHT+"Invalid input"+Colours.RESET);
                keyboard.next();
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
        catch(InputMismatchException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Invalid input"+Colours.RESET);
            keyboard.next();
        }
    }

    private  void feature3(MySqlCourseDAO courseDao) {
        try{
            System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nEnter number to show courses with enrollment less than that: "+ Colours.RESET);
            int maxEnrollemtNumber = keyboard.nextInt();
            List<Course> allCourses = courseDao.findCourseUsingFilter(maxEnrollemtNumber);
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nCourses with enrollment less than "+maxEnrollemtNumber+Colours.RESET);
            System.out.println(Colours.YELLOW_BOLD_BRIGHT);
            System.out.println(Utility.COURSE_HEADER+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
            for(Course course : allCourses){
                System.out.println(course);
            }
            System.out.println(Colours.RESET);
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private  void feature4(MySqlCourseDAO courseDao) {
        try {
            System.out.println(Colours.YELLOW_BOLD_BRIGHT + "\nTo delete a course you need to enter password:" + Colours.RESET);
            if (keyboard.hasNextLine()) {
                int password = keyboard.nextInt();
                if (password == 2607) {
                    System.out.println(Colours.BLUE_BOLD_BRIGHT + "\nEnter course id: " + Colours.RESET);
                    if (keyboard.hasNextLine()) {
                        int courseId = keyboard.nextInt();
                        boolean check = courseDao.deleteCourseById(courseId);
                        if (check == true) {
                            System.out.println(Colours.GREEN_BOLD_BRIGHT + "Course deleted" + Colours.RESET);
                        } else {
                            System.out.println(Colours.RED_BOLD_BRIGHT + "Course not found" + Colours.RESET);
                        }
                    } else {
                        System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input" + Colours.RESET);
                        keyboard.next();
                    }

                }
                else{
                    System.out.println(Colours.RED_BOLD_BRIGHT+"Not authorised to delete course."+Colours.RESET);
                }
            }
        }
            catch(DAOException e){
                System.out.println(Colours.RED_BOLD_BRIGHT+"Error "+e.getMessage()+Colours.RESET);
            }
            catch(Exception e){
                System.out.println(Colours.RED_BOLD_BRIGHT+"Error "+e.getMessage()+Colours.RESET);
                keyboard.next();
            }
        }



    private  boolean checkCourse(Course course, MySqlCourseDAO courseDao) throws DAOException {

        if(course.getCOURSE_NAME().length() < 3 || course.getCOURSE_NAME().length() > 45){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Invalid course name"+Colours.RESET);
            return false;
        }

        if(course.getMAX_ENROLLMENT_NUMBER() < 10){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Invalid max number of students"+Colours.RESET);
            return false;
        }

        if(course.getINSTRUCTOR_ID() > 10){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Invalid instructor id"+Colours.RESET);
            return false;
        }

        return true;
    }

    private  boolean checkId(int id, MySqlCourseDAO courseDao) throws DAOException {
        if(id < 0){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid course id"+Colours.RESET);
            return false;
        }

        try{
            List<Course> allCourses = courseDao.findAllCourses();
            for(Course c : allCourses){
                if(c.getCOURSE_ID() == id){
                    System.out.println(Colours.RED_BOLD_BRIGHT+"\nCourse id already exists"+Colours.RESET);
                    return false;
                }
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error "+e.getMessage()+Colours.RESET);
        }
        return true;
    }

    private  boolean checkName(String name, MySqlCourseDAO courseDao) throws DAOException {
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

    private  boolean checkInstructorID(int id, MySqlCourseDAO courseDao) throws DAOException {
        if(id > 10){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid instructor id"+Colours.RESET);
            return false;
        }
        return true;
    }

    private  boolean checkMaxEnrollmentNumber(int number, MySqlCourseDAO courseDao) throws DAOException {
        if(number < 10){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid max number of students"+Colours.RESET);
            return false;
        }
        return true;
    }
    private  void feature5(MySqlCourseDAO courseDao) {
        System.out.println(Colours.YELLOW_BOLD_BRIGHT+"\nTo insert a course you need to enter password:"+Colours.RESET);
        if(keyboard.hasNextInt()) {
            int password = keyboard.nextInt();
            if (password == 2607) {
                try {
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
                                if(keyboard.hasNextInt()){
                                    int instructor_ID = keyboard.nextInt();
                                    check = checkInstructorID(instructor_ID, courseDao);

                                    if (check) {
                                        System.out.println("Enter max enrollment number: " + Colours.RESET);
                                        if(keyboard.hasNextInt()){
                                            int max_Enrollment_Number = keyboard.nextInt();
                                            Course course = new Course(course_Id, course_Name, instructor_ID, max_Enrollment_Number);
                                            check = checkMaxEnrollmentNumber(max_Enrollment_Number, courseDao);
                                            if (check) {
                                                System.out.println(Colours.GREEN_BOLD_BRIGHT + "All details entered correctly. Proceeding to insert course." + Colours.RESET);
                                                boolean check2 = courseDao.insertCourse(course);
                                                if (check2) {
                                                    System.out.println(Colours.GREEN_BOLD_BRIGHT + "Course inserted" + Colours.RESET);
                                                } else {
                                                    System.out.println(Colours.RED_BOLD_BRIGHT + "Course not inserted" + Colours.RESET);
                                                }
                                            } else {
                                                System.out.println(Colours.RED_BOLD_BRIGHT + "Error in enrollment number, course not inserted" + Colours.RESET);
                                            }
                                        }
                                        else{
                                            System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid choice, please try again" + Colours.RESET);
                                            keyboard.next();
                                        }
                                    }
                                    else {
                                        System.out.println(Colours.RED_BOLD_BRIGHT + "Error in instructor id, course not inserted" + Colours.RESET);
                                    }
                                }
                                else {
                                    System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid choice, please try again" + Colours.RESET);
                                    keyboard.next();
                                }

                            }
                            else {
                                System.out.println(Colours.RED_BOLD_BRIGHT + "Error in course name, course not inserted" + Colours.RESET);
                            }
                        }
                        else {
                            System.out.println(Colours.RED_BOLD_BRIGHT + "Error in course id,course not inserted" + Colours.RESET);
                        }
                    }
                    else {
                        System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid choice, please try again" + Colours.RESET);
                        keyboard.next();
                    }
                }
                catch (DAOException e) {
                    System.out.println(Colours.RED_BOLD_BRIGHT + "Error" + e.getMessage() + Colours.RESET);
                }
                catch (InputMismatchException e) {
                    System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid choice, please try again" + Colours.RESET);
                    keyboard.next(); // discard invalid input
                }
                catch (NumberFormatException e) {
                    System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid choice, please try again" + Colours.RESET);

                }
            } else {
                System.out.println(Colours.RED_BOLD_BRIGHT + "Not authorised to insert course" + Colours.RESET);
            }
        }
        else{
            System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid choice, please try again" + Colours.RESET);
            keyboard.next();
        }
    }

    private  void feature6(MySqlCourseDAO courseDao) {
        try{
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nAll courses as JSON: "+Colours.RESET);
            System.out.println(Colours.WHITE_BOLD_BRIGHT);
            String json = courseDao.findAllCoursesAsJSON();

            String[] lines = json.split("\n");
            for(String line : lines){
                System.out.println(line);
            }
            System.out.println(Colours.RESET);
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private void feature7(MySqlCourseDAO courseDao) {
        try{
            System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nEnter course id: "+Colours.RESET);
            if(keyboard.hasNextLine()) {
                int courseId = keyboard.nextInt();
                System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "Course by id as JSON: " + Colours.RESET);
                System.out.println(Colours.WHITE_BOLD_BRIGHT + courseDao.findCoursesByIdAsJSON(courseId) + Colours.RESET);
            }
            else{
                System.out.println(Colours.RED_BOLD_BRIGHT+"Invalid choice, please try again"+Colours.RESET);
                keyboard.next();
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
        catch (InputMismatchException e) {
            System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid choice, please try again" + Colours.RESET);
            keyboard.next(); // discard invalid input
        }
        catch (NumberFormatException e) {
            System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid choice, please try again" + Colours.RESET);

        }
    }
    public  void callMenu() throws InterruptedException {
       System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nentering course menu.........."+Colours.RESET);
       sleep(500);
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
              case 0:
                  System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nexiting course menu.........."+Colours.RESET);
                  sleep(500);
                  loop = false;
                  break;
          }
       }
    }
}
