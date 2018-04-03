package BusinessLogicLayer;

import BusinessLogicLayer.BusinessModels.CourseModel;
import BusinessLogicLayer.BusinessModels.ExamModel;
import DataAccessLayer.Entities.Grade;
import DataAccessLayer.Entities.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IStudentLogic {

    Student get(String username) throws SQLException;
    Student getById(String id) throws SQLException, NoSuchFieldException, IllegalAccessException;
    void insert(Student student) throws IllegalAccessException, SQLException, ClassNotFoundException;
    void update(Student student) throws IllegalAccessException, SQLException, NoSuchFieldException;
    Student viewProfile(Student student) throws NoSuchFieldException, IllegalAccessException, SQLException;
    List<Grade> viewGrades(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    List<CourseModel> viewCourses(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException;
    List<ExamModel> viewExams(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException;
    void enroll(Student student, String courseId) throws SQLException;
    List<CourseModel> viewAvailableCourses(Student student) throws SQLException, NoSuchFieldException, IllegalAccessException;
    List<Student> getStudentsEnrolled(String courseId) throws SQLException;

}
