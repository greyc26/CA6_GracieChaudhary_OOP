package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Client;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Client.Menus.IMenu;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Command;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.CommandFactory;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Client.Menus.AllMenuFactory;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Client
{
    public static void main(String[] args) throws InterruptedException {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Local IP Address: " + localHost.getHostAddress());
            System.out.println("Local Host Name: " + localHost.getHostName());
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host Exception caught: " + e.getMessage());
        }

        try (Socket dataSocket = new Socket("localhost", MultithreadedServerDetails.SERVER_PORT)) {
            System.out.println("Connected to server: " + dataSocket);

            PrintWriter out = new PrintWriter(dataSocket.getOutputStream(), true);
            Scanner input = new Scanner(dataSocket.getInputStream());

            Scanner keyboard = new Scanner(System.in);
            String command = "";
            String menu = "";
           // String response = "";
            CommandFactory commandFactory = new CommandFactory();

            while (!command.equals(MultithreadedServerDetails.CALL_QUIT)) {
                printMenu();
                System.out.println("Enter a number to choose an option (0 to quit): ");
                int choice = keyboard.nextInt();
                System.out.println("You chose: " + command);

                switch(choice){
                    case 0:
                        command = MultithreadedServerDetails.CALL_QUIT;
                        break;
                    case 1:
                        command = MultithreadedServerDetails.COURSE_MENU;
                        menu = MultithreadedServerDetails.COURSE_MENU;
                        break;
                    case 2:
                        command = MultithreadedServerDetails.INSTRUCTOR_MENU;
                        menu = MultithreadedServerDetails.INSTRUCTOR_MENU;
                        break;
                }

                if(!command.equals(MultithreadedServerDetails.CALL_QUIT)){
                    if(command.equals(MultithreadedServerDetails.COURSE_MENU)){

//                        boolean loop = true;
//                        while(loop){
//                            IMenu courseMenu = AllMenuFactory.getMenu(menu);
//                            String command2 = courseMenu.getChoice(keyboard);
//
//                            Command c = commandFactory.getCommand(command2);
//                            command2 = c.generateRequest(keyboard);
//
//                            out.println(command2);
//                            c.handleResponse(input.nextLine());
//
//                            if(command2.equals(MultithreadedServerDetails.CALL_QUIT)){
//                                command = MultithreadedServerDetails.CALL_QUIT;
//                                c = commandFactory.getCommand(command);
//                                command = c.generateRequest(keyboard);
//
//                                out.println(command);
//                                c.handleResponse(input.nextLine());
//                                loop = false;
//                            }
//
//                        }
                        IMenu courseMenu = AllMenuFactory.getMenu(menu);
                        command = courseMenu.getChoice(keyboard);

                        if(command.equals(MultithreadedServerDetails.CALL_QUIT)){
                        continue;
                    }
                }
                Command c = commandFactory.getCommand(command);
                command = c.generateRequest(keyboard);

                out.println(command);
                c.handleResponse(input.nextLine());
//                Command c = CommandFactory.getCommand(commandParts[0]);
//                response = c.generateResponse(commandParts);
//
//                out.println(response);
//                System.out.println("Response from server: " + response);
//
//                if (command.equals(MultithreadedServerDetails.CALL_QUIT)) {
//                    System.out.println("Goodbye");
//                }
//                else {
//                    IMenu menu = AllMenuFactory.getMenu(command);
//                    if (menu != null) {
//                        String choice = menu.getChoice(keyboard);
//                        System.out.println("You chose: " + choice);
//                        out.println(choice);
//                        response = in.nextLine();
//                        System.out.println("Response from server: " + response);
//                    }
//                }
            }
        }
        }
        catch (IOException | NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Client is logging out");
        System.out.println(Colours.YELLOW_BOLD_BRIGHT+"exiting...."+Colours.RESET);
        sleep(1000);
        System.out.println(Colours.CYAN_BOLD_BRIGHT+"Goodbye, Thank for visiting our Moodle Service"+Colours.RESET);

    }

        private static void printMenu() {
        System.out.println(Colours.BLUE_BOLD_BRIGHT + "\n*** Main Menu ***" + Colours.RESET);
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "0. Quit");
        System.out.println("1. Course Menu");
        System.out.println("2. Instructor Menu" + Colours.RESET);


    }


}

