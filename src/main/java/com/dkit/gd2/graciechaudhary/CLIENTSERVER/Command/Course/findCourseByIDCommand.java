package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Command;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Scanner;

public class findCourseByIDCommand implements Command {


    @Override
    public String generateResponse(String[] commandParts) {
        MySqlCourseDAO courseDao = new MySqlCourseDAO();
        int courseID = Integer.parseInt(commandParts[1]);

        try{
            return courseDao.findCoursesByIdAsJSON(courseID);
        }
        catch (DAOException e) {
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error: Course not found"+ Colours.RESET);
        }

        return Colours.RED_BOLD_BRIGHT+"Error: Course not found"+ Colours.RESET;
    }

    @Override
    public String generateRequest(Scanner keyboard) throws DAOException {
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"Enter course ID to find: "+Colours.RESET);
        int courseID = keyboard.nextInt();

        String response = MultithreadedServerDetails.DISPLAY_COURSE_BYID + MultithreadedServerDetails.BREAKING_CHARACTER + courseID;
        return response;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);

        try{
            Course course = Utility.getCourseFromJSON(response);

            if(course!=null){
                System.out.println(Colours.GREEN_BOLD_BRIGHT+"Course found successfully"+Colours.RESET);
                System.out.println(Utility.COURSE_HEADER);
                course.printCourse();
            }
            else{
                System.out.println(Colours.RED_BOLD_BRIGHT+"Error: Course not found"+Colours.RESET);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
