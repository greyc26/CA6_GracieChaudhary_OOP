package com.dkit.gd2.graciechaudhary.DAO.Course;


import com.dkit.gd2.graciechaudhary.DAO.mySqlDAO;
import com.dkit.gd2.graciechaudhary.DTO.Course;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MySqlCourseDAO extends mySqlDAO implements CourseDaoInterface {

    private static HashSet<Integer> ids = new HashSet<>(); // for feature 6


    @Override
    public List<Course> findAllCourses() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> coursesAvailable = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM course";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                int instructor_id = rs.getInt("instructor_id");
                int max_num_students = rs.getInt("max_num_students");
                Course course = new Course(course_id, course_name, instructor_id, max_num_students);
                coursesAvailable.add(course);
            }
        } catch (Exception e) {
            throw new DAOException("findAllCourses() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOException("findAllCourses() " + e.getMessage());
            }
        }
        return coursesAvailable;
    }

    @Override
    public Course findCourseById(int courseId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course = null;

        try {
            con = this.getConnection();
            String query = "SELECT * FROM course WHERE course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            if(rs.next()){
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                int instructor_id = rs.getInt("instructor_id");
                int max_num_students = rs.getInt("max_num_students");
                course = new Course(course_id, course_name, instructor_id, max_num_students);
            }
        } catch (Exception e) {
            throw new DAOException("findCourseById() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOException("findCourseById() " + e.getMessage());
            }
        }
        return course;
    }

    @Override
    public boolean deleteCourseById(int courseId) throws DAOException {
//        Connection con = null;
//        PreparedStatement psFinal = null;
//        ResultSet rs = null;
//        boolean deleted = false;
//
//        try {
//            con = this.getConnection();
//            // Update related rows in the enrollment table to remove reference to course
//            String updateEnrollmentQuery = "UPDATE enrollment SET course_id = NULL WHERE course_id = ?";
//            PreparedStatement updateEnrollmentTable = con.prepareStatement(updateEnrollmentQuery);
//            updateEnrollmentTable.setInt(1, courseId);
//            updateEnrollmentTable.executeUpdate();
//            updateEnrollmentTable.close();
//        }
//                // Delete related rows in the submission table for course
//                String deleteSubmissionSql = "DELETE FROM submission WHERE assignment_id IN (SELECT id FROM assignment WHERE course_id = ?)";
//                PreparedStatement deleteSubmissionps = connection.prepareStatement(deleteSubmissionSql);
//                deleteSubmissionps.setInt(1, courseId);
//                deleteSubmissionps.executeUpdate();
//                deleteSubmissionps.close();
//
//                // Delete rows in the assignment table for course
//                String deleteAssignmentSql = "DELETE FROM assignment WHERE course_id = ?";
//                PreparedStatement deleteAssignmentps = connection.prepareStatement(deleteAssignmentSql);
//                deleteAssignmentps.setInt(1, courseId);
//                deleteAssignmentps.executeUpdate();
//                deleteAssignmentps.close();
//
//                // Delete the course with course_id = ?
//                String deleteCourseSql = "DELETE FROM course WHERE course_id = ?";
//                PreparedStatement deleteCourseps = connection.prepareStatement(deleteCourseSql);
//                deleteCourseps.setInt(1, courseId);
//                deleteCourseps.executeUpdate();
//                deleteCourseps.close();
//            }
//        }
//
//        try {
//            con = this.getConnection();
//            String query = "DELETE FROM course WHERE course_id = ?";
//            ps = con.prepareStatement(query);
//            ps.setInt(1, courseId);
//            int rowsAffected = ps.executeUpdate();
//
//            if(rowsAffected > 0){
//                deleted = true;
//            }
//        } catch (Exception e) {
//            throw new DAOException("deleteCourseById() " + e.getMessage());
//        } finally {
//            try{
//                if(rs != null){
//                    rs.close();
//                }
//                if(ps != null){
//                    ps.close();
//                }
//                this.freeConnection(con);
//            } catch (Exception e) {
//                throw new DAOException("deleteCourseById() " + e.getMessage());
//            }
//        }
//        return deleted;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasBeenDeleted = false;

        try {
            con = this.getConnection();

            // Delete from enrollment table
            String deleteEnrollment = "DELETE FROM enrollment WHERE course_id = ?";
            ps = con.prepareStatement(deleteEnrollment);
            ps.setInt(1, courseId);
            ps.executeUpdate();
            ps.close();

            // Delete from assignment table
            String deleteAssignment = "DELETE FROM assignment WHERE course_id = ?";
            ps = con.prepareStatement(deleteAssignment);
            ps.setInt(1, courseId);
            ps.executeUpdate();
            ps.close();

            // Delete from course table
            String deleteCourse = "DELETE FROM course WHERE course_id = ?";
            ps = con.prepareStatement(deleteCourse);
            ps.setInt(1, courseId);
            ps.executeUpdate();
            ps.close();

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                hasBeenDeleted = true;
            }

            System.out.println("Course deleted successfully");
        }

        catch (Exception e) {
            throw new DAOException("findAllCourses() " + e.getMessage());
        }

        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e.getMessage());
            }
        }

        return hasBeenDeleted;
    }

    @Override
    public boolean insertCourse(Course course) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasBeenInserted = false;

        try{
            con = this.getConnection();
            String query = "INSERT INTO course (course_name, instructor_id, max_num_students) VALUES (?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, course.getCOURSE_NAME());
            ps.setInt(2, course.getINSTRUCTOR_ID());
            ps.setInt(3, course.getMAX_ENROLLMENT_NUMBER());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                hasBeenInserted = true;
            }

            System.out.println("Course inserted successfully");

        } catch (Exception e) {
            throw new DAOException("insertCourse() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOException("insertCourse() " + e.getMessage());
            }
        }

        return hasBeenInserted;
    }

    @Override
    public List<Course> findCourseUsingFilter(int maxEnrollemtNumber) throws DAOException {
        // will find course where max enrollement is less than a given number
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<Course>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM course WHERE max_num_students < ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, maxEnrollemtNumber);
            rs = ps.executeQuery();

            while(rs.next()){
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                int instructor_id = rs.getInt("instructor_id");
                int max_num_students = rs.getInt("max_num_students");
                Course course = new Course(course_id, course_name, instructor_id, max_num_students);
                courses.add(course);
            }

        } catch (Exception e) {
            throw new DAOException("findCourseUsingFilter() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOException("findCourseUsingFilter() " + e.getMessage());
            }
        }

        return courses;
    }

    @Override
    public String findAllCoursesAsJSON() throws DAOException {
        List<Course> allCourses = findAllCourses();
        Gson gson = new Gson();
        return gson.toJson(allCourses);
    }

    @Override
    public String findCoursesByIdAsJSON(int id) throws DAOException {
        Course course = findCourseById(id);
        Gson gson = new Gson();
        return gson.toJson(course);
    }
}

//hello