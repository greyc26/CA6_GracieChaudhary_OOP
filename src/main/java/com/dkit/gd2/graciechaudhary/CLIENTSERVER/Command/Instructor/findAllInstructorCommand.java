package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Instructor;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Command;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;
import com.dkit.gd2.graciechaudhary.Core.Utility;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Student.MySqlStudentDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Instructor;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.List;
import java.util.Scanner;

public class findAllInstructorCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlStudentDAO studentDao = new MySqlStudentDAO();

        try {
            String allStudents = studentDao.findAllStudentsAsJSON();
            if (allStudents != null)
                return allStudents;
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return Colours.RED_BOLD_BRIGHT + "ERROR" + Colours.RESET;

    }

    @Override
    public String generateRequest(Scanner keyboard) throws DAOException {
        return MultithreadedServerDetails.DISPLAY_ALLINSTRUCTORS;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Colours.MAGENTA_BOLD_BRIGHT + "\nServer response:" + Colours.RESET);
        try {
            List<Instructor> allInstructors = Utility.getInstructorsFromJSON(response);

            if (allInstructors.size() == 0)
                System.out.println(Colours.RED + "Error: Instructors not found" + Colours.RESET);
            else {
                System.out.println(Colours.GREEN_BOLD_BRIGHT + "Instructors found successfully" + Colours.RESET + Colours.YELLOW_BOLD_BRIGHT);
                System.out.printf(Utility.INSTRUCTOR_HEADER + Colours.RESET + Colours.WHITE_BOLD_BRIGHT);

                for (Instructor i : allInstructors) {
                    i.printIntructor();
                }
                System.out.println(Colours.RESET);
            }
        } catch (Exception e) {
            System.out.println(Colours.RED_BOLD_BRIGHT + "Error: Instructors not found" + Colours.RESET);
        }
    }
}
