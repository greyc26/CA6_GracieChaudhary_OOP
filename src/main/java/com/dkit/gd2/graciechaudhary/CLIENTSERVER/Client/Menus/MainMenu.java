package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Client.Menus;

import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Enum.Colours;

import java.util.Scanner;

public class MainMenu implements IMenu {


    @Override
    public String getChoice(Scanner keyboard) {
        boolean loop = true;
        String output = "";

        while (loop) {
            printMenu();
            System.out.println(Colours.BLUE_BOLD_BRIGHT + "Enter a number to select an option (enter 0 to exit): " + Colours.RESET);
            if (keyboard.hasNextLine()) {
                output = keyboard.nextLine();
            } else {
                System.out.println(Colours.RED_BOLD_BRIGHT + "Invalid input, enter a valid number." + Colours.RESET);
                keyboard.nextLine();
            }
            loop = false;
            String choice = keyboard.nextLine();
            switch (choice) {
                case "0":
                    loop = false;
                    output = MultithreadedServerDetails.EXIT;
                    break;
                case "1":
                    output = MultithreadedServerDetails.COURSE_MENU;
                    loop = false;
                    break;
                case "2":
                    output = MultithreadedServerDetails.INSTRUCTOR_MENU;
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid option entered, please try again");
            }
        }
        return output;

    }

    private static void printMenu() {
        System.out.println(Colours.BLUE_BOLD_BRIGHT + "\n*** Main Menu ***" + Colours.RESET);
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "0. Quit");
        System.out.println("1. Course Menu");
        System.out.println("2. Instructor Menu" + Colours.RESET);


    }

}
