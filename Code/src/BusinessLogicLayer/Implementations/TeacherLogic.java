package BusinessLogicLayer.Implementations;

import BusinessLogicLayer.BusinessModels.Report;
import BusinessLogicLayer.IStudentLogic;
import DataAccessLayer.Entities.Grade;
import DataAccessLayer.Entities.Student;
import BusinessLogicLayer.ITeacherLogic;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;

public class TeacherLogic implements ITeacherLogic {

    private IReflectiveDAO reflectiveDAO;

    public TeacherLogic(){
        reflectiveDAO = new ReflectiveDAO();
    }

    @Override
    public Report generateReport(Student student, Date startDate, Date endData) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        IStudentLogic studentLogic = new StudentLogic();

        Report report = new Report();

        report.setGrades(studentLogic.viewGrades(student));
        int gradeSum = 0;
        for(Grade grade : report.getGrades()){
            gradeSum += grade.getMark();
        }
        report.setGradeAverage((1.0 * gradeSum) / report.getGrades().size());
        report.setCourses(studentLogic.viewCourses(student));

        return report;

    }

    @Override
    public void insert(Object o) throws IllegalAccessException, SQLException, ClassNotFoundException {
        reflectiveDAO.insert(o);
    }

    @Override
    public void update(Object o) throws IllegalAccessException, SQLException, NoSuchFieldException {
        reflectiveDAO.update(o);
    }
}
