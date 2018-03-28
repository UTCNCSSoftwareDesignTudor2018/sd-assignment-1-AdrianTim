package BusinessLogicLayer;

import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Exam;
import DataAccessLayer.Entities.Grade;
import DataAccessLayer.Entities.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IStudentLogic {

    Student get(String username) throws SQLException;
    void insert(Student student) throws IllegalAccessException, SQLException, ClassNotFoundException;
    void update(Student student) throws IllegalAccessException, SQLException, NoSuchFieldException;
    Student viewProfile(Student student) throws NoSuchFieldException, IllegalAccessException, SQLException;
    List<Grade> viewGrades(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    List<Course> viewCourses(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    List<Exam> viewExams(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException;
    void enroll(Student student, String courseId) throws SQLException;

}
