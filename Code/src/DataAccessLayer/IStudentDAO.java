package DataAccessLayer;

import DataAccessLayer.Entities.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {

    void delete(Student student) throws SQLException;
    Student get(String username) throws SQLException;
    List<Student> getStudentsAttendingCourse(String courseId) throws SQLException;

}
