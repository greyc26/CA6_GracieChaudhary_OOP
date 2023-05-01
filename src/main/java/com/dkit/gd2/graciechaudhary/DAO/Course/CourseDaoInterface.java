package com.dkit.gd2.graciechaudhary.DAO.Course;

import com.dkit.gd2.graciechaudhary.DTO.Course;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.List;
import java.util.Set;

public interface CourseDaoInterface {

   // Gson gson = new Gson();

 // Feature 1 – Find all Entities e.g. findAllPlayers() to return a List of all the entities and display that list.
 // Feature 2 – Find and Display (a single) Entity by Key e.g. findPlayerById( id ) – return a single entity and display its contents.
 // Feature 3 – Delete an Entity by key e.g. deletePlayerById( id ) – remove entity from database
 // Feature 4 – Insert an Entity in the database using DAO (gather data from user, store in DTO object, pass into method insertPlayer ( Player ), return new entity including assigned auto-id.
 // Feature 5 – List entities using a filter e.g. findPlayerUsingFilter( playerAgeComparator )
 // Feature 6 – Create a Cache using a HashSet that will maintain the ids of all players and refactor the findPlayerById() method so that it checks for the existence of a player id before it makes a query to the SQL database.
     List<Course> findAllCourses() throws DAOException;
     Course findCourseById(int courseId) throws DAOException;
     boolean deleteCourseById(int courseId) throws DAOException;
     boolean insertCourse(Course course) throws DAOException;
     List<Course> findCourseUsingFilter(int maxEnrollemtNumber) throws DAOException;

     String findAllCoursesAsJSON() throws DAOException;
     String findCoursesByIdAsJSON(int id) throws DAOException;
}

