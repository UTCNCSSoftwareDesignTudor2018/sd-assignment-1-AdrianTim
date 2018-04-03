package DataAccessLayer;

import DataAccessLayer.Entities.Course;

import java.sql.SQLException;
import java.util.List;

public interface ICourseDAO {

    void insert(String studentId, String courseId) throws SQLException;
    void delete(String studentId, String courseId) throws SQLException;
    List<Course> getStudentCourses(String studentId) throws SQLException;
    List<Course> getStudentAvailableCourses(String studentId) throws SQLException;
    List<Course> getTeacherCourses(String teacherId) throws SQLException;

}
