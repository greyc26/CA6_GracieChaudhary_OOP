package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Command;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.MySqlCourseDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.List;
import java.util.Scanner;

import com.dkit.gd2.graciechaudhary.Core.Utility;

public class findAllCoursesCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlCourseDAO courseDao = new MySqlCourseDAO();

        try{
            String allCourses = courseDao.findAllCoursesAsJSON();
            if(allCourses != null)
            return allCourses;
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return Colours.RED_BOLD_BRIGHT+ "ERROR" + Colours.RESET;
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        return MultithreadedServerDetails.DISPLAY_ALLCOURSES;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);
        try {
            List<Course> allCourses = Utility.getCoursesFromJSON(response);

            if (allCourses.size() == 0)
                System.out.println(Colours.RED + "Error: Courses not found" + Colours.RESET);
            else {
                System.out.println(Colours.GREEN_BOLD_BRIGHT+"Courses found successfully"+Colours.RESET);
                System.out.printf(Utility.COURSE_HEADER+Colours.WHITE_BOLD_BRIGHT);

                for (Course c : allCourses) {
                    c.printCourse();
                }
                System.out.println(Colours.RESET);
            }
        }
        catch (Exception e) {
            System.out.println(Colours.RED_BOLD_BRIGHT+"Error: Courses not found"+Colours.RESET);
        }
    }
}
