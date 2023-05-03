package com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course;


import com.dkit.gd2.graciechaudhary.DATABASE.DAO.mySqlDAO;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

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
        }
        catch (Exception e) {
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
            }
            catch (Exception e) {
                throw new DAOException(Colours.RED_BOLD_BRIGHT+"findAllCourses() " + e.getMessage()+Colours.RESET);
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
            throw new DAOException(Colours.RED_BOLD_BRIGHT+"findCourseById() " + e.getMessage()+Colours.RESET);
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
                throw new DAOException(Colours.RED_BOLD_BRIGHT+"findCourseById() " + e.getMessage()+Colours.RESET);
            }
        }
        return course;
    }

    @Override
    public boolean deleteCourseById(int courseId) throws DAOException {


        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
       // ResultSet rs = null;
        boolean hasBeenDeleted = false;

        System.out.println(Colours.YELLOW_BOLD_BRIGHT+"Are you sure? If yes enter password to confirm, if no then enter 0"+Colours.RESET);
        Scanner keyboard = new Scanner(System.in);
        int password = keyboard.nextInt();
        if(password == 2607){
            try {
                con = this.getConnection();

                // Delete from enrollment table
                String deleteEnrollment = "DELETE FROM enrollment WHERE course_id = ?";
                ps1 = con.prepareStatement(deleteEnrollment);
                ps1.setInt(1, courseId);
                ps1.executeUpdate();
                //ps1.close();

                //Delete from submission table where assignment_id in (select assignment_id from assignment where course_id = ?)
                String deleteSubmission = "DELETE FROM submission WHERE assignment_id IN (SELECT assignment_id FROM assignment WHERE course_id = ?)";
                ps2 = con.prepareStatement(deleteSubmission);
                ps2.setInt(1, courseId);
                ps2.executeUpdate();
                //ps2.close();

                // Delete from assignment table
                String deleteAssignment = "DELETE FROM assignment WHERE course_id = ?";
                ps3 = con.prepareStatement(deleteAssignment);
                ps3.setInt(1, courseId);
                ps3.executeUpdate();
               // ps3.close();

                // Delete from course table
                String deleteCourse = "DELETE FROM course WHERE course_id = ?";
                ps4 = con.prepareStatement(deleteCourse);
                ps4.setInt(1, courseId);
                ps4.executeUpdate();
//                ps1.close();
//                ps2.close();
//                ps3.close();
//              ps4.close();
                hasBeenDeleted = true;
               // System.out.println(Colours.GREEN_BOLD_BRIGHT+"Course deleted successfully"+Colours.RESET);
            }

            catch (Exception e) {
                throw new DAOException(Colours.RED_BOLD_BRIGHT+"findAllCourses() " + e.getMessage()+Colours.RESET);
            }

            finally {
                try {
//                    if (rs != null) {
//                        rs.close();
//                    }
                    if (ps4 != null) {
                        ps4.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println(Colours.RED_BOLD_BRIGHT+"Error closing database resources: " + e.getMessage()+Colours.RESET);
                }
            }
            return hasBeenDeleted;
        }
        else{
            System.out.println(Colours.RED_BOLD_BRIGHT+"Course not deleted as confirmation password was incorrect"+Colours.RESET);

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
            String query = "INSERT INTO course (course_id, course_name, instructor_id, max_num_students) VALUES ( ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, course.getCOURSE_ID());
            ps.setString(2, course.getCOURSE_NAME());
            ps.setInt(3, course.getINSTRUCTOR_ID());
            ps.setInt(4, course.getMAX_ENROLLMENT_NUMBER());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                hasBeenInserted = true;
            }

           // System.out.println("Course inserted successfully");

        } catch (Exception e) {
            throw new DAOException(Colours.RED_BOLD_BRIGHT+"insertCourse() " + e.getMessage()+Colours.RESET);
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
                throw new DAOException(Colours.RED_BOLD_BRIGHT+"insertCourse() " + e.getMessage()+Colours.RESET);
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
            throw new DAOException(Colours.RED_BOLD_BRIGHT+"findCourseUsingFilter() " + e.getMessage()+Colours.RESET);
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
                throw new DAOException(Colours.RED_BOLD_BRIGHT+"findCourseUsingFilter() " + e.getMessage()+Colours.RESET);
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