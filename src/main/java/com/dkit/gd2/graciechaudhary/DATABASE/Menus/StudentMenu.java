package com.dkit.gd2.graciechaudhary.DATABASE.Menus;

import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Student.MySqlStudentDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Student;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;
import jdk.jshell.execution.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class StudentMenu {

    private Scanner keyboard = new Scanner(System.in);
    MySqlStudentDAO studentDao = new MySqlStudentDAO();

    private void printMenu(){
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"\n*** STUDENT MENU ***"+Colours.RESET);
        System.out.println(Colours.MAGENTA+"0. Exit");
        System.out.println("1. Find all students");
        System.out.println("2. Find student by id");
        System.out.println("3. Delete student by id");
        System.out.println("4. Insert student");
        System.out.println("5. Find student using filter");
        System.out.println("6. Find all students as JSON");
        System.out.println("7. Find student by id as JSON"+Colours.RESET);

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

    public void callMenu() throws InterruptedException, DAOException {
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nentering course menu.........."+Colours.RESET);
        sleep(500);
        boolean loop = true;

        while(loop){
            printMenu();
            int choice = getChoiceForMenu();
            switch(choice){
                case 0:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nExiting student menu.........."+Colours.RESET);
                    sleep(500);
                    loop = false;
                    break;
                case 1:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nFinding all students.........."+Colours.RESET);
                    sleep(500);
                    feature1(studentDao);
                    break;
                case 2:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nFinding student by id.........."+Colours.RESET);
                    sleep(500);
                    feature2(studentDao);
                    break;
                case 3:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nDeleting student by id.........."+Colours.RESET);
                    sleep(500);
                    feature3(studentDao);
                    break;
                case 4:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nInserting student.........."+Colours.RESET);
                    sleep(500);
                    feature4(studentDao);
                    break;
                case 5:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nFinding student using filter.........."+Colours.RESET);
                    sleep(500);
                    feature5(studentDao);
                    break;
                case 6:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nFinding all students as JSON.........."+Colours.RESET);
                    sleep(500);
                    feature6(studentDao);
                    break;
                case 7:
                    System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nFinding student by id as JSON.........."+Colours.RESET);
                    sleep(500);
                    feature7(studentDao);
                    break;
                default:
                    System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid choice, please try again"+Colours.RESET);
                    break;
            }
        }
    }

    private void feature1(MySqlStudentDAO studentDao) throws DAOException {
        try{
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nAll courses: "+Colours.RESET+Colours.YELLOW_BOLD_BRIGHT);
            System.out.println(Utility.STUDENT_HEADER+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
            List<Student> allStudents = studentDao.findAllStudents();
            for(Student s : allStudents){
                s.printStudent();
            }
            System.out.println(Colours.RESET);
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private void feature2(MySqlStudentDAO studentDao) throws DAOException {
        try{
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nEnter student id: "+Colours.RESET);
            int id = keyboard.nextInt();
            Student s = studentDao.findStudentById(id);
            if(s != null){
                System.out.println(Colours.YELLOW_BOLD_BRIGHT+Utility.STUDENT_HEADER+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);
                s.printStudent();
                System.out.println(Colours.RESET);
            }
            else{
                System.out.println(Colours.RED_BOLD_BRIGHT+"\nNo student found with id "+id+Colours.RESET);
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
    }

    private void feature3(MySqlStudentDAO studentDao) throws DAOException {
        System.out.println(Colours.YELLOW_BOLD_BRIGHT + "\nTo delete a student you need to enter password:" + Colours.RESET);

        Scanner keyboard = new Scanner(System.in);
        if(keyboard.hasNextLine()) {
            int password = keyboard.nextInt();
            if (password == 2607) {
                try {
                    System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nEnter student id: " + Colours.RESET);
                    if(keyboard.hasNextLine()) {
                        int id = keyboard.nextInt();
                        System.out.println(Colours.YELLOW_BOLD_BRIGHT + "Are you sure? If yes enter password to confirm, if no then enter 0" + Colours.RESET);
                        if (keyboard.hasNextLine()) {
                            password = keyboard.nextInt();
                            if (password == 2607) {
                                boolean success = studentDao.deleteStudentById(id);
                                if (success) {
                                    System.out.println(Colours.GREEN_BOLD_BRIGHT + "\nStudent deleted successfully" + Colours.RESET);
                                } else {
                                    System.out.println(Colours.RED_BOLD_BRIGHT + "\nNo student found with id " + id + Colours.RESET);
                                }
                            } else {
                                System.out.println(Colours.RED_BOLD_BRIGHT + "\nIncorrect password" + Colours.RESET);
                            }
                        } else {
                            System.out.println(Colours.RED_BOLD_BRIGHT + "\nInvalid input" + Colours.RESET);
                        }
                    }
                    else{
                        System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid input"+Colours.RESET);
                    }
                } catch (DAOException e) {
                    System.out.println(Colours.RED_BOLD_BRIGHT + "Error" + e.getMessage() + Colours.RESET);
                }
            } else {
                System.out.println(Colours.RED_BOLD_BRIGHT + "\nIncorrect password" + Colours.RESET);
            }
        }
        else{
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid input"+Colours.RESET);
        }
    }

    private boolean checkId(int id,MySqlStudentDAO studentDao){

        if(id<0){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid student id"+Colours.RESET);
            return false;
        }

        try{
            Student s = studentDao.findStudentById(id);
            if(s != null){
                System.out.println(Colours.RED_BOLD_BRIGHT+"\nStudent with id "+id+" already exists"+Colours.RESET);
                return false;
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
        return true;
    }

    private boolean checkName(String name,MySqlStudentDAO studentDao){
        if(name.length() < 2 || name.length() > 30){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid name"+Colours.RESET);
            return false;
        }

        try{
            List<Student> allStudents = studentDao.findAllStudents();
            for(Student s : allStudents){
                if(s.getSTUDENT_NAME().equals(name)){
                    System.out.println(Colours.RED_BOLD_BRIGHT+"\nStudent with name "+name+" already exists"+Colours.RESET);
                    return false;
                }
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }
        return true;
    }

    private boolean checkEmail(String email,MySqlStudentDAO studentDao){

        if(email.length() < 2 || email.length() > 45){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid email"+Colours.RESET);
            return false;
        }

        try{
            List<Student> allStudents = studentDao.findAllStudents();
            for(Student s : allStudents){
                if(s.getSTUDENT_EMAIL().equals(email)){
                    System.out.println(Colours.RED_BOLD_BRIGHT+"\nStudent with email "+email+" already exists"+Colours.RESET);
                    return false;
                }
            }
        }
        catch(DAOException e){
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error"+e.getMessage()+Colours.RESET);
        }

        return true;
    }

    private boolean checkDOB(Date date,MySqlStudentDAO studentDao){
        if(date == null){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid date of birth"+Colours.RESET);
            return false;
        }

        if(date.after(new Date())){
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid date of birth"+Colours.RESET);
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        String dateString = sdf.format(date);
        try {
            sdf.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void feature4(MySqlStudentDAO studentDao){

        System.out.println(Colours.YELLOW_BOLD_BRIGHT + "\nTo insert a student you need to enter password:" + Colours.RESET);

        Scanner keyboard = new Scanner(System.in);
        if(keyboard.hasNextLine()) {
            int password = keyboard.nextInt();
            if (password == 2607) {

                try{
                    System.out.println(Colours.BLUE_BOLD_BRIGHT + "\nEnter student id: ");
                    if(keyboard.hasNextLine()){
                        int id = keyboard.nextInt();
                        boolean check = checkId(id,studentDao);
                        keyboard.nextLine();
                        if(check){
                            System.out.println("Enter student name: ");
                            String name = keyboard.nextLine();
                            check = checkName(name,studentDao);
                            if(check){
                                System.out.println("Enter student email: ");
                                String email = keyboard.nextLine();
                                check = checkEmail(email,studentDao);
                                if(check){
                                    System.out.println("Enter student date of birth (yyyy-mm-dd): ");
                                    String dob = keyboard.nextLine();
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = null;
                                    date = format.parse(dob);
                                    check = checkDOB(date,studentDao);
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                    if(check){
                                        Student s = new Student(id, name, email, sqlDate);
                                        boolean success = studentDao.insertStudent(s);
                                        if(success){
                                            System.out.println(Colours.GREEN_BOLD_BRIGHT+"\nStudent inserted successfully"+Colours.RESET);
                                        }
                                        else{
                                            System.out.println(Colours.RED_BOLD_BRIGHT+"\nStudent already exsists"+Colours.RESET);
                                        }
                                    }
                                    else{
                                        System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid input for date of birth. (Empty)"+Colours.RESET);
                                    }
                                }
                            }
                            else{
                                System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid input for name. (Already exsists/ Empty)"+Colours.RESET);
                            }
                        }
                        else{
                            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid input for id. (Already exsists/ Negative value)"+Colours.RESET);
                        }
                    }
                    else{
                        System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid input"+Colours.RESET);
                    }

                } catch (DAOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(Colours.RED_BOLD_BRIGHT + "\nIncorrect password" + Colours.RESET);
            }
        }
        else{
            System.out.println(Colours.RED_BOLD_BRIGHT+"\nInvalid input"+Colours.RESET);
        }
    }

    private void feature5(MySqlStudentDAO studentDao){

        try{
            System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nEnter student DOB to display students younger than given: "+Colours.RESET);
            String dob = keyboard.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = format.parse(dob);
            List<Student> students = studentDao.findStudentUsingFilter(date);
            if(students.size() == 0){
                System.out.println(Colours.RED_BOLD_BRIGHT+"\nNo students found"+Colours.RESET);
            }
            else{
                System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nStudents found: "+Colours.RESET+Colours.YELLOW_BOLD_BRIGHT);
                System.out.println(Utility.STUDENT_HEADER+Colours.RESET+Colours.WHITE_BOLD_BRIGHT);

                for(Student s : students){
                    s.printStudent();
                }
                System.out.println(Colours.RESET);
            }
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
        catch(ParseException e){
            throw new RuntimeException(e);
        }

    }

    private void feature6(MySqlStudentDAO studentDao){
        try{
            System.out.println(Colours.MAGENTA_BOLD_BRIGHT+"\nAll students as JSON: "+Colours.RESET);
            System.out.println(Colours.WHITE_BOLD_BRIGHT);
            String json = studentDao.findAllStudentsAsJSON();

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

    private void feature7(MySqlStudentDAO studentDao){
        try{
            System.out.println(Colours.BLUE_BOLD_BRIGHT+"\nEnter student id: "+Colours.RESET);
            if(keyboard.hasNextLine()) {
                int studentId = keyboard.nextInt();
                System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "Student by id as JSON: " + Colours.RESET);
                System.out.println(Colours.WHITE_BOLD_BRIGHT + studentDao.findStudentsByIdAsJSON(studentId)+ Colours.RESET);
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
}
