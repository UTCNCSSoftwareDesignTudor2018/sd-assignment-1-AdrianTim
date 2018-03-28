package BusinessLogicLayer;

import BusinessLogicLayer.BusinessModels.Report;
import DataAccessLayer.Entities.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;

public interface ITeacherLogic {

    Report generateReport(Student student, Date startDate, Date endData) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    void insert(Object o) throws IllegalAccessException, SQLException, ClassNotFoundException;
    void update(Object o) throws IllegalAccessException, SQLException, NoSuchFieldException;

}
