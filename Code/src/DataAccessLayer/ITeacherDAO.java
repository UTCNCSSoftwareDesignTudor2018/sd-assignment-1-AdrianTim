package DataAccessLayer;

import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface ITeacherDAO {

    Teacher get(String username) throws SQLException;

}
