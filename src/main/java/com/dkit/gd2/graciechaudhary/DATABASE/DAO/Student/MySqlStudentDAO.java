package com.dkit.gd2.graciechaudhary.DATABASE.DAO.Student;

import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Student;
import com.dkit.gd2.graciechaudhary.Enum.Colours;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;
import com.google.gson.Gson;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.mySqlDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MySqlStudentDAO extends mySqlDAO implements StudentDaoInterface {
    @Override
    public List<Student> findAllStudents() throws DAOException {
        // will find course where max enrollement is less than a given number
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<Student>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM student";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("student_email");
                Date studentDOB = rs.getDate("student_dob");
                Student student = new Student(studentId, studentName, studentEmail, studentDOB);
                students.add(student);

            }
        } catch (Exception e) {
            throw new DAOException(Colours.RED_BOLD_BRIGHT + "findAllStudents() " + e.getMessage() + Colours.RESET);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOException(Colours.RED_BOLD_BRIGHT + "findAllStudents() " + e.getMessage() + Colours.RESET);
            }
        }

        return students;

    }

    @Override
    public Student findStudentById(int studentid) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course = null;

        Student student = null;
        try {
            con = this.getConnection();
            String query = "SELECT * FROM student WHERE student_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, studentid);
            rs = ps.executeQuery();

            if (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("student_email");
                Date studentDOB = rs.getDate("student_dob");
                student = new Student(studentId, studentName, studentEmail, studentDOB);
            }
        } catch (Exception e) {
            throw new DAOException(Colours.RED_BOLD_BRIGHT + "findStudentById() " + e.getMessage() + Colours.RESET);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOException(Colours.RED_BOLD_BRIGHT + "findStudentById() " + e.getMessage() + Colours.RESET);
            }
        }
        return student;
    }


    @Override
    public boolean deleteStudentById(int studentId) throws DAOException {
        Connection con = null;
        boolean hasBeenDeleted = false;
        PreparedStatement deleteStudentStatement =  null;


                try{
                    con = this.getConnection();

                    // Delete student's enrolments
                    String deleteEnrolmentSql = "DELETE FROM enrollment WHERE student_id = ?";
                    PreparedStatement deleteEnrolmentStatement = con.prepareStatement(deleteEnrolmentSql);
                    deleteEnrolmentStatement.setInt(1, studentId);
                    int enrolmentDeleted = deleteEnrolmentStatement.executeUpdate();

                    // Delete student's submissions
                    String deleteSubmissionSql = "DELETE FROM submission WHERE student_id = ?";
                    PreparedStatement deleteSubmissionStatement = con.prepareStatement(deleteSubmissionSql);
                    deleteSubmissionStatement.setInt(1, studentId);
                    int submissionDeleted = deleteSubmissionStatement.executeUpdate();

                    String deleteStudent = "DELETE FROM student WHERE student_id = ?";
                    deleteStudentStatement = con.prepareStatement(deleteStudent);
                    deleteStudentStatement.setInt(1,studentId);
                    int studentDeleted = deleteStudentStatement.executeUpdate();

                    if(studentDeleted==0){
                        System.out.println("No student deleted as student not found.");
                        return hasBeenDeleted;
                    }



                    hasBeenDeleted = true;

                }
                catch(Exception e){
                    throw new DAOException(Colours.RED_BOLD_BRIGHT+"deleteStudentById " + e.getMessage()+Colours.RESET);

                }

                finally {
                    try {
//                    if (rs != null) {
//                        rs.close();
//                    }
                        if (deleteStudentStatement != null) {
                            deleteStudentStatement.close();
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





    @Override
    public boolean insertStudent(Student student) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasBeenInserted = false;

        try {
            con = this.getConnection();
            String query = "INSERT INTO student (student_id, student_name, student_email, student_dob) VALUES ( ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, student.getSTUDENT_ID());
            ps.setString(2, student.getSTUDENT_NAME());
            ps.setString(3, student.getSTUDENT_EMAIL());
            ps.setDate(4, (java.sql.Date) student.getSTUDENT_DOB());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                hasBeenInserted = true;
            }
        }
        catch (Exception e) {
            throw new DAOException(Colours.RED_BOLD_BRIGHT+"insertStudent() " + e.getMessage()+Colours.RESET);
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
                throw new DAOException(Colours.RED_BOLD_BRIGHT+"insertStudent() " + e.getMessage()+Colours.RESET);
            }
        }

        return hasBeenInserted;
    }

    @Override
    public List<Student> findStudentUsingFilter(Date dob) throws DAOException {
        // will find course where max enrollement is less than a given number
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<Student>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM student WHERE dob <?";
            ps = con.prepareStatement(query);
            ps.setDate(1, (java.sql.Date) dob);
            rs = ps.executeQuery();

            while (rs.next()){
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("student_email");
                Date studentDOB = rs.getDate("dob");
                Student student = new Student(studentId, studentName, studentEmail, studentDOB );
                students.add(student);

            }
        }
        catch (Exception e){
            throw new DAOException(Colours.RED_BOLD_BRIGHT+"findCourseUsingFilter() " + e.getMessage()+Colours.RESET);

        }
        finally {
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

        return students;

    }

    @Override
    public String findAllStudentsAsJSON() throws DAOException {
        List<Student> allStudents = findAllStudents();
        Gson gson = new Gson();
        String json = gson.toJson(allStudents);
        return json;
    }

    @Override
    public String findStudentsByIdAsJSON(int studentId) throws DAOException {
        Student student = findStudentById(studentId);
        Gson gson = new Gson();
        String json = gson.toJson(student);
        return json;
    }
}
