package BusinessLogicLayer;

import BusinessLogicLayer.BusinessEntities.Grade;
import BusinessLogicLayer.BusinessEntities.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IStudentLogic {

    void update(Student student) throws IllegalAccessException, SQLException, NoSuchFieldException;
    Student viewProfile(Student student) throws NoSuchFieldException, IllegalAccessException, SQLException;
    List<Grade> viewGrades(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    void viewCourses(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    void viewExams(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    void enroll(Student student, String courseId);

}
