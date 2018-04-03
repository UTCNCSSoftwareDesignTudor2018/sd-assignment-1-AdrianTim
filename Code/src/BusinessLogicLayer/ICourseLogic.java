package BusinessLogicLayer;

import DataAccessLayer.Entities.Exam;
import DataAccessLayer.Entities.Student;

import java.sql.Date;
import java.sql.SQLException;

public interface ICourseLogic {

    void unEnrolStudent(String studentId, String courseId) throws SQLException;
    void addCourse(String subjectName, String teacherId) throws IllegalAccessException, SQLException, ClassNotFoundException;
    void addExam(Date date, String room, String courseId) throws IllegalAccessException, SQLException, ClassNotFoundException;

}
