package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course.findAllCoursesCommand;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course.findCourseByIDCommand;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course.removeCourseCommand;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Instructor.findAllInstructorCommand;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;

public class CommandFactory {

    public static Command getCommand(String command){
        return switch (command) {
            case MultithreadedServerDetails.DISPLAY_ADDCOURSE -> new findCourseByIDCommand();
            case MultithreadedServerDetails.DISPLAY_DELETECOURSE -> new removeCourseCommand();
            case MultithreadedServerDetails.DISPLAY_COURSE_BYID -> new findCourseByIDCommand();
            case MultithreadedServerDetails.DISPLAY_ALLCOURSES -> new findAllCoursesCommand();
            case MultithreadedServerDetails.DISPLAY_ALLINSTRUCTORS -> new findAllInstructorCommand();
            case MultithreadedServerDetails.DISPLAY_INSTRUCTOR_BYID -> new findAllInstructorCommand();
            case MultithreadedServerDetails.CALL_QUIT -> new QuitCommand();
            case MultithreadedServerDetails.RETURN -> new ReturnCommand();
            case MultithreadedServerDetails.UNRECOGNIZED_COMMAND -> new UnrecognizedCommand();

            default -> new UnrecognizedCommand();
        };
    }
}