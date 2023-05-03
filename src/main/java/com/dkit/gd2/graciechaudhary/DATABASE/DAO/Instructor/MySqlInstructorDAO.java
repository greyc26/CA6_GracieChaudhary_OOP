package com.dkit.gd2.graciechaudhary.DATABASE.DAO.Instructor;

import com.dkit.gd2.graciechaudhary.DATABASE.DAO.Course.CourseDaoInterface;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Course;
import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Instructor;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;
import com.dkit.gd2.graciechaudhary.DATABASE.DAO.mySqlDAO;
import com.google.gson.Gson;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
public class MySqlInstructorDAO extends mySqlDAO implements InstructorDaoInterface{

    @Override
    public List<Instructor> findAllInstructors() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Instructor> instructors = new ArrayList<>();

        try {
            con = this.getConnection();

            String query = "SELECT * FROM instructor";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int instructorId = rs.getInt("instructor_id");
                String instructorName = rs.getString("instructor_name");
                String instructorEmail = rs.getString("instructor_email");
                Instructor i = new Instructor(instructorId, instructorName, instructorEmail);
                instructors.add(i);
            }
        }
        catch (Exception e) {
            throw new DAOException("findAllCourses() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }
            catch (SQLException e) {
                throw new DAOException("findAllInstructors() " + e.getMessage());
            }
            finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null){
                        ps.close();
                    }
                    if (con != null){
                        freeConnection(con);
                    }
                }
                catch (Exception e) {
                    throw new DAOException("findAllCourses() " + e.getMessage());
                }
            }
        }
        return instructors;
    }

    @Override
    public Instructor findInstructorById(int instructorId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Instructor instructor = null;

        try {
            con = this.getConnection();

            String query = "SELECT * FROM instructor WHERE instructor_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, instructorId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String instructorName = rs.getString("instructor_name");
                String instructorEmail = rs.getString("instructor_email");
                instructor = new Instructor(instructorId, instructorName, instructorEmail);
            }
        } catch (SQLException e) {
            throw new DAOException("findInstructorById() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DAOException("findInstructorById() " + e.getMessage());
            }
        }
        return instructor;
    }

    @Override
    public String findAllCoursesAsJSON() throws DAOException {
        List<Instructor> allInstructors = findAllInstructors();
        Gson gson = new Gson();
        return gson.toJson(allInstructors);
    }

    @Override
    public String findCoursesByIdAsJSON(int id) throws DAOException {
        Instructor instructor = findInstructorById(id);
        Gson gson = new Gson();
        return gson.toJson(instructor);
    }
}
