package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command;

//import com.dkit.gd2.graciechaudhary.Server.CourseMenuCommands.*;

import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course.findAllCoursesCommand;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course.findCourseByIDCommand;
import com.dkit.gd2.graciechaudhary.CLIENTSERVER.Command.Course.removeCourseCommand;
import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;

public class CommandFactory {

    public static Command getCommand(String command){
        return switch (command) {
            case MultithreadedServerDetails.DISPLAY_ADDCOURSE -> new findCourseByIDCommand();
            case MultithreadedServerDetails.DISPLAY_DELETECOURSE -> new removeCourseCommand();
            case MultithreadedServerDetails.DISPLAY_COURSE_BYID -> new findCourseByIDCommand();
            case MultithreadedServerDetails.DISPLAY_ALLCOURSES -> new findAllCoursesCommand();
            default -> new UnrecognizedCommand();
        };
    }
}