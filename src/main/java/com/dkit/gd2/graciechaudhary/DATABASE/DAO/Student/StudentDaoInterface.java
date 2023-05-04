package com.dkit.gd2.graciechaudhary.DATABASE.DAO.Student;

import com.dkit.gd2.graciechaudhary.DATABASE.DTO.Student;
import com.dkit.gd2.graciechaudhary.Exceptions.DAOException;

import java.util.Date;
import java.util.List;

public interface StudentDaoInterface {


    List<Student> findAllStudents() throws DAOException;
    Student findStudentById(int studentId) throws DAOException;
    boolean deleteStudentById(int studentId) throws DAOException;

    boolean insertStudent(Student student) throws DAOException;
    List<Student> findStudentUsingFilter(Date dob) throws DAOException;
    String findAllStudentsAsJSON() throws DAOException;
    String findStudentsByIdAsJSON(int studentId) throws DAOException;
}
