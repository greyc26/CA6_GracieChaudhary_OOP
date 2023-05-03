package com.dkit.gd2.graciechaudhary.DATABASE.DAO.Instructor;

import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Instructor;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.List;

public interface InstructorDaoInterface {

    List<Instructor> findAllInstructors() throws DAOException;
    Instructor findInstructorById(int courseId) throws DAOException;

    String findAllCoursesAsJSON() throws DAOException;
    String findCoursesByIdAsJSON(int id) throws DAOException;
}
