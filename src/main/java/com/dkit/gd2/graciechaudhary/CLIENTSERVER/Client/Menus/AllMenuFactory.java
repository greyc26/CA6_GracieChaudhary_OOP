package com.dkit.gd2.graciechaudhary.CLIENTSERVER.Client.Menus;

import com.dkit.gd2.graciechaudhary.Core.MultithreadedServerDetails;

public class AllMenuFactory {

    public static IMenu getMenu(String menu){
        return switch (menu) {
            //case MultithreadedServerDetails.MAIN_MENU -> new MainMenu();
            case MultithreadedServerDetails.COURSE_MENU -> new CourseMenu();
            case MultithreadedServerDetails.INSTRUCTOR_MENU -> new InstructorMenu();
            default -> null;
        };
    }


}
