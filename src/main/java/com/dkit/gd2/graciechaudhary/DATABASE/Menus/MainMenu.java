package com.dkit.gd2.graciechaudhary.DATABASE.Menus;

import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Scanner;

public class MainMenu {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, DAOException {

        CourseMenu courseMenu = new CourseMenu();
        InstructorMenu instructorMenu = new InstructorMenu();
        StudentMenu studentMenu = new StudentMenu();
        callMenu(courseMenu, instructorMenu, studentMenu);

    }

    private static int getChoiceForMenu()
    {
        int choice = 0;
        boolean valid = false;
        while(!valid){
            try{
                System.out.println(Colours.BLUE_BOLD_BRIGHT+ "Enter choice: "+Colours.RESET);
                if(keyboard.hasNextInt()){
                    choice = keyboard.nextInt();
                    if(choice > -1 && choice <4){
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
                keyboard.nextLine();
            }
        }
        return choice;
    }
    private static void printMenu()
    {
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"\n*** MAIN MENU ***"+Colours.RESET);
        System.out.println(Colours.MAGENTA+"0. Exit");
        System.out.println("1. Course Menu");
        System.out.println("2. Student Menu");
        System.out.println("3. Instructor Menu"+Colours.RESET);


    }
    private static void callMenu(CourseMenu courseMenu, InstructorMenu instructorMenu,StudentMenu studentMenu) throws InterruptedException, DAOException {
        boolean loop = true;
        while(loop){
            printMenu();
            int choice = getChoiceForMenu();
            switch(choice){
                case 1:
                    courseMenu.callMenu();
                    break;
                case 2:
                    studentMenu.callMenu();
                    break;
                case 3:
                    instructorMenu.callMenu();
                    break;
                case 0:
                    System.out.println(Colours.RED_BOLD_BRIGHT+"Exiting..."+Colours.RESET);
                    System.out.println(Colours.GREEN_BOLD_BRIGHT+"Goodbye! Thank you for using my application."+Colours.RESET);
                    loop = false;
                    break;
            }
        }
    }


}
