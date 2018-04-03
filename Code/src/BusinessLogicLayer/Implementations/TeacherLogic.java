package BusinessLogicLayer.Implementations;

import BusinessLogicLayer.BusinessModels.CourseModel;
import BusinessLogicLayer.BusinessModels.Report;
import BusinessLogicLayer.IStudentLogic;
import BusinessLogicLayer.ReportMaker;
import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Grade;
import DataAccessLayer.Entities.Student;
import BusinessLogicLayer.ITeacherLogic;
import DataAccessLayer.Entities.Teacher;
import DataAccessLayer.ICourseDAO;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.ITeacherDAO;
import DataAccessLayer.Implementations.CourseDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;
import DataAccessLayer.Implementations.TeacherDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TeacherLogic implements ITeacherLogic {

    private IReflectiveDAO reflectiveDAO;
    private ITeacherDAO teacherDAO;
    private ICourseDAO courseDAO;

    private IStudentLogic studentLogic;

    public TeacherLogic(){
        reflectiveDAO = new ReflectiveDAO();
        teacherDAO = new TeacherDAO();
        courseDAO = new CourseDAO();
        studentLogic = new StudentLogic();
    }

    @Override
    public void generateReport(String studentId, Date startDate, Date endData) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        Report report = new Report();
        Student student = new Student();
        student.setId(studentId);
        student = (Student)reflectiveDAO.get(student);

        report.setGrades(studentLogic.viewGrades(student));
        report.setStudent(student);
        int gradeSum = 0;
        for(Grade grade : report.getGrades()){
            gradeSum += grade.getMark();
        }
        report.setGradeAverage((1.0 * gradeSum) / report.getGrades().size());
        report.setCourses(studentLogic.viewCourses(student));

        ReportMaker.createReport(report);

    }

    @Override
    public void insert(String surname, String name, String teacherUsername) throws IllegalAccessException, SQLException, ClassNotFoundException {
        Teacher t = new Teacher();

        t.setId(UUID.randomUUID().toString());
        t.setName(name);
        t.setSurname(surname);
        t.setTeacherUsername(teacherUsername);

        reflectiveDAO.insert(t);
    }

    @Override
    public void update(Teacher o) throws IllegalAccessException, SQLException, NoSuchFieldException {
        reflectiveDAO.update(o);
    }

    @Override
    public Teacher get(String username) throws SQLException {

        return teacherDAO.get(username);

    }

    @Override
    public List<CourseModel> viewCourses(Teacher teacher) throws SQLException {

        List<Course> courses =  courseDAO.getTeacherCourses(teacher.getId());
        List<CourseModel> result = new LinkedList();

        for(Course course : courses){

            result.add(new CourseModel(course.getId(), course.getSubject(), teacher.getName()));

        }

        return result;

    }

    @Override
    public void promoteToTeacher(String studentId) throws NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {

        Student student = new Student();
        student.setId(studentId);

        student = (Student)reflectiveDAO.get(student);

        insert(student.getSurname(), student.getName(), student.getStudentUsername());

    }
}
