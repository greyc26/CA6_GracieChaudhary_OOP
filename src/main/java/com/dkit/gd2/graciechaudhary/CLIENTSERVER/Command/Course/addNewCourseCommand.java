package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Command;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.List;
import java.util.Scanner;

public class addNewCourseCommand implements Command {

    MySqlCourseDAO courseDAO = new MySqlCourseDAO();
    @Override
    public String generateResponse(String[] commandParts) {
        Course course = Utility.getCourseFromJSON(commandParts[1]);
        MySqlCourseDAO courseDao = new MySqlCourseDAO();

        try {
            if (courseDAO.insertCourse(course)){
                String response = Utility.getJSONFromCourse(course);
                return response;
            }
            else{
                return "Error: Course not added";
            }
        }
        catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public String generateRequest(Scanner keyboard) throws DAOException {

        Course course = Utility.addCourse(courseDAO, keyboard);
        String courseAsJSON = Utility.getJSONFromCourse(course);
        String response = MultithreadedServerDetails.DISPLAY_ADDCOURSE+MultithreadedServerDetails.BREAKING_CHARACTER+ courseAsJSON;

        return response;


    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);
        try {
            Course course = Utility.getCourseFromJSON(response);
            if(course!=null){
                System.out.println(Colours.GREEN_BOLD_BRIGHT+"Course added successfully"+Colours.RESET);
                System.out.println(Utility.COURSE_HEADER);
                course.printCourse();
            }
            else
                System.out.println(Colours.RED_BOLD_BRIGHT+"Error: Course not added"+Colours.RESET);
        }
        catch (Exception e) {
            System.out.println("Error: Course not added");
        }
    }
}