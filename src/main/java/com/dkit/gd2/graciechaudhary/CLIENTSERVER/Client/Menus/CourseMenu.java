package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Client.Menus;

import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Enum.Colours;

import java.util.Scanner;

public class CourseMenu implements IMenu{

    public CourseMenu(){

    }

    @Override
    public String getChoice(Scanner keyboard) {
        boolean loop = true;
        String output = "";

        while(loop){
            printMenu();
            System.out.println(Colours.BLUE_BOLD_BRIGHT+"Enter a number to select an option (enter 0 to exit): "+Colours.RESET);
            if(keyboard.hasNextLine()){
                output = keyboard.nextLine();
            }
            else{
                System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid number." + Colours.RESET);
                keyboard.nextLine();
            }
            loop = false;
            int choice = keyboard.nextInt();
            switch(choice){
                case 0:
                    loop = false;
                    output = MultithreadedServerDetails.RETURN;
                    break;
                case 1:
                    output = MultithreadedServerDetails.DISPLAY_ALLCOURSES;
                    loop = false;
                    break;
                case 2:
                    output = MultithreadedServerDetails.DISPLAY_COURSE_BYID;
                    loop = false;
                    break;
                case 3:
                    output = MultithreadedServerDetails.DISPLAY_DELETECOURSE;
                    loop = false;
                    break;
                case 4:
                    output = MultithreadedServerDetails.DISPLAY_ADDCOURSE;
                    loop = false;
                    break;

                default:
                    System.out.println("Invalid option entered, please try again");
            }
        }

        return output;

    }

    private void printMenu(){
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"\n*** COURSE MENU ***"+Colours.RESET);
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"0. Exit");
        System.out.println("1. Find all Courses");
        System.out.println("2. Find and Display a Course by ID");
        System.out.println("3. Delete Course by ID");
        System.out.println("4. Insert Course"+Colours.RESET);
    }
}
