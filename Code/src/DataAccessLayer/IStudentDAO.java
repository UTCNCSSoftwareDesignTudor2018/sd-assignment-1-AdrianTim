package DataAccessLayer;

import DataAccessLayer.Entities.Student;

import java.sql.SQLException;

public interface IStudentDAO {

    void delete(Student student) throws SQLException;
    Student get(String username) throws SQLException;

}
