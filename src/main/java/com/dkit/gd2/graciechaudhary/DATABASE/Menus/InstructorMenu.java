package com.dkit.gd2.graciechaudhary.DATABASE.Menus;

import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Instructor.MySqlInstructorDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Instructor;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class InstructorMenu {

    private Scanner keyboard = new Scanner(System.in);
    MySqlInstructorDAO instructorDAO = new MySqlInstructorDAO();
    private void printMenu(){
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"\n*** INSTRUCTOR MENU ***"+Colours.RESET);
        System.out.println(Colours.MAGENTA+"0. Exit");
        System.out.println("1. Find all Instructors");
        System.out.println("2. Find and Display an Instructor by ID");
        System.out.println("3. Find all instructors as JSON");
        System.out.println("4. Find instructors by id as JSON"+Colours.RESET);
    }

    private int getChoiceForMenu(){
        int choice = 0;
        boolean valid = false;
        while(!valid){
            try{
                System.out.println(Colours.BLUE_BOLD_BRIGHT+ "Enter choice: "+Colours.RESET);
                if(keyboard.hasNextInt()){
                    choice = keyboard.nextInt();
                    if(choice > -1 && choice <5){
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

    private void feature1(){
        try{
            System.out.println("\nAll instructors: ");
            List<Instructor> allInstructors = instructorDAO.findAllInstructors();
            System.out.println(Colours.YELLOW_BOLD_BRIGHT);
            System.out.println(Utility.INSTRUCTOR_HEADER+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
            for(Instructor instructor: allInstructors){
                instructor.printIntructor();
            }
            System.out.println(Colours.RESET);
        }
        catch(Exception e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private void feature2(){
        try{
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nEnter instructor id: "+Colours.RESET);
            int instructorId = keyboard.nextInt();
            Instructor instructor = instructorDAO.findInstructorById(instructorId);
            if(instructor!=null){
                System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nInstructor found: "+Colours.RESET+Colours.YELLOW_BOLD_BRIGHT);
                System.out.println(Utility.INSTRUCTOR_HEADER+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
                instructor.printIntructor();
                System.out.println(Colours.RESET);
            }
            else{
                System.out.println(Colours.RED_BOLD_BRIGHT+"\nInstructor not found"+Colours.RESET);
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private void feature3(){
        try{
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nAll instructors as JSON: "+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
            String allInstructors = instructorDAO.findAllCoursesAsJSON();
            System.out.println(allInstructors);
            System.out.println(Colours.RESET);
        }
        catch(Exception e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private void feature4(){
        try{
            System.out.println("\nEnter instructor id: ");
            int instructorId = keyboard.nextInt();
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"Instructor by id as JSON: "+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
            System.out.println(instructorDAO.findInstructorById(instructorId));
            System.out.println(Colours.RESET);
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }



    public void callMenu() throws InterruptedException {
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nentering instructor menu.........."+Colours.RESET);
        sleep(500);
        boolean loop = true;

        while (loop){
            printMenu();
            int choice = getChoiceForMenu();
            switch(choice){
                case 0:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nexiting instructor menu.........."+Colours.RESET);
                    sleep(500);
                    loop = false;
                    break;
                case 1:
                    feature1();
                    break;
                case 2:
                    feature2();
                    break;
                case 3:
                    feature3();
                    break;
                case 4:
                    feature4();
                    break;
            }
        }
   }
}
