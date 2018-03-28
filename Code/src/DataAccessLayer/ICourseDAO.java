package DataAccessLayer;

import DataAccessLayer.Entities.Course;

import java.sql.SQLException;
import java.util.List;

public interface ICourseDAO {

    void insert(String studentId, String courseId) throws SQLException;
    void delete(String studentId, String courseId) throws SQLException;
    List<Course> getCourses(String studentId) throws SQLException;

}
