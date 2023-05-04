package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Command;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Scanner;

public class removeCourseCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlCourseDAO courseDAO = new MySqlCourseDAO();

        int courseID = Integer.parseInt(commandParts[1]);

        try {
            if (courseDAO.deleteCourseById(courseID)){
                return Utility.getJSONFromCourse(courseDAO.findCourseById(courseID));
            }
            else{
                return Colours.RED_BOLD_BRIGHT+"Error: Course not deleted"+Colours.RESET;
            }
        }
        catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateRequest(Scanner keyboard) throws DAOException {
        System.out.println("Enter course ID to delete: ");
        int courseID = keyboard.nextInt();
        String response = MultithreadedServerDetails.DISPLAY_DELETECOURSE+ MultithreadedServerDetails.BREAKING_CHARACTER+courseID;
        return response;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);
        try {
            Course course = Utility.getCourseFromJSON(response);
            if(course!=null){
                System.out.println(Colours.GREEN_BOLD_BRIGHT+"Course deleted successfully"+Colours.RESET);
                System.out.println(Utility.COURSE_HEADER);
                course.printCourse();
            }
            else
            {
                System.out.println(Colours.RED_BOLD_BRIGHT+"Error: Course not deleted"+Colours.RESET);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
