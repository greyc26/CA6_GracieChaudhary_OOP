package com.dkit.gd2.graciechaudhary.DAO.Course;

import com.dkit.gd2.graciechaudhary.DTO.Course;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Set;

public interface CourseDaoInterface {

   // Gson gson = new Gson();
    Set<Course> findAllCourses() throws DAOException;
    Course findCourse(String courseId) throws DAOException;
    boolean registerCourse(Course c) throws DAOException;
    boolean updateCourse(Course c) throws DAOException;
    boolean deleteCourse(Course c) throws DAOException; // for this you would need to delete all the enrollments first and inform the students
    Set<Course> findCoursesByCredits(int COURSE_CREDITS) throws DAOException;
    Set<Course> sortCoursesByCredits() throws DAOException;
    Set<Course> sortCourseByMaximumEnrollment() throws DAOException;
    Course displayCourseDetails(String COURSE_ID) throws DAOException;
    Course displayCourseDetailsByInstrutor(String INSTRUCTOR_NAME) throws DAOException;
}
