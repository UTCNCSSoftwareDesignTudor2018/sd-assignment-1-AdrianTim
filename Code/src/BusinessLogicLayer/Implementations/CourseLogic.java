package BusinessLogicLayer.Implementations;

import BusinessLogicLayer.ICourseLogic;
import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Exam;
import DataAccessLayer.Entities.Student;
import DataAccessLayer.ICourseDAO;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.Implementations.CourseDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

public class CourseLogic implements ICourseLogic {

    private ICourseDAO courseDAO;
    private IReflectiveDAO reflectiveDAO;

    public CourseLogic(){

        this.courseDAO = new CourseDAO();
        this.reflectiveDAO = new ReflectiveDAO();

    }

    @Override
    public void unEnrolStudent(String studentId, String courseId) throws SQLException {
        courseDAO.delete(studentId, courseId);
    }

    @Override
    public void addCourse(String subjectName, String teacherId) throws IllegalAccessException, SQLException, ClassNotFoundException {

        Course course = new Course(UUID.randomUUID().toString(), subjectName, teacherId);
        reflectiveDAO.insert(course);

    }

    @Override
    public void addExam(Date date, String room, String courseId) throws IllegalAccessException, SQLException, ClassNotFoundException {

        Exam exam = new Exam(UUID.randomUUID().toString(), date, room, courseId);

        reflectiveDAO.insert(exam);
    }
}
