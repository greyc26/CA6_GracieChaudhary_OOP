package com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course;

import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.List;

public interface CourseDaoInterface {


     List<Course> findAllCourses() throws DAOException;
     Course findCourseById(int courseId) throws DAOException;
     boolean deleteCourseById(int courseId) throws DAOException;
     boolean insertCourse(Course course) throws DAOException;
     List<Course> findCourseUsingFilter(int maxEnrollemtNumber) throws DAOException;

     String findAllCoursesAsJSON() throws DAOException;
     String findCoursesByIdAsJSON(int id) throws DAOException;
}

