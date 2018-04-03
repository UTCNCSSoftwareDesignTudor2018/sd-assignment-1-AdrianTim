package BusinessLogicLayer;

import BusinessLogicLayer.BusinessModels.CourseModel;
import BusinessLogicLayer.BusinessModels.Report;
import DataAccessLayer.Entities.Student;
import DataAccessLayer.Entities.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ITeacherLogic {

    void generateReport(String studentId, Date startDate, Date endData) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException;
    void insert(String surname, String name, String teacherUsername) throws IllegalAccessException, SQLException, ClassNotFoundException;
    void update(Teacher o) throws IllegalAccessException, SQLException, NoSuchFieldException;
    Teacher get(String username) throws SQLException;
    List<CourseModel> viewCourses(Teacher teacher) throws SQLException;
    void promoteToTeacher(String studentId) throws NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException;

}
