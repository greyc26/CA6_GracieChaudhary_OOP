package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Instructor;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Command;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Student.MySqlStudentDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Instructor;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Map;
import java.util.Scanner;

public class findInstuctorByIDCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlStudentDAO studentDao = new MySqlStudentDAO();
        int studentId = Integer.parseInt(commandParts[1]);

        try {
            String student = studentDao.findStudentsByIdAsJSON(studentId);
            if (student != null)
                return student;
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return Colours.RED_BOLD_BRIGHT+"Error: Course not found"+ Colours.RESET;

    }

    @Override
    public String generateRequest(Scanner keyboard) throws DAOException {
        System.out.println(Colours.BLUE_BOLD_BRIGHT+"Enter instructor ID to find: "+Colours.RESET);
        int instructorId = keyboard.nextInt();

        String response = MultithreadedServerDetails.DISPLAY_INSTRUCTOR_BYID + MultithreadedServerDetails.BREAKING_CHARACTER + instructorId;
        return response;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);

        try{
            Instructor instructor = Utility.getInstructorFromJSON(response);

            if(instructor!=null){
                System.out.println(Colours.GREEN_BOLD_BRIGHT+"Instructor found successfully"+Colours.RESET);
                System.out.println(Utility.INSTRUCTOR_HEADER);
                instructor.printIntructor();
            }
            else{
                System.out.println(Colours.RED_BOLD_BRIGHT+"Error: Instructor not found"+Colours.RESET);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
