package BusinessLogicLayer;

import DataAccessLayer.Entities.Exam;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IExamsLogic {

    List<Exam> getCourseExams(String courseId) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    void graderStudent(String studentId, String examId, int mark) throws IllegalAccessException, SQLException, ClassNotFoundException;

}
