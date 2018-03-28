package BusinessLogicLayer.Implementations;

import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Exam;
import DataAccessLayer.Entities.Grade;
import DataAccessLayer.Entities.Student;
import BusinessLogicLayer.IStudentLogic;
import DataAccessLayer.ICourseDAO;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.IStudentDAO;
import DataAccessLayer.Implementations.CourseDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;
import DataAccessLayer.Implementations.StudentDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentLogic implements IStudentLogic{

    private IReflectiveDAO reflectiveDAO;
    private IStudentDAO studentDAO;
    private ICourseDAO courseDAO;

    public StudentLogic(){

        reflectiveDAO = new ReflectiveDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();

    }

    @Override
    public Student get(String username) throws SQLException {
        return studentDAO.get(username);
    }

    @Override
    public void insert(Student student) throws IllegalAccessException, SQLException, ClassNotFoundException {
        reflectiveDAO.insert(student);
    }

    @Override
    public void update(Student student) throws IllegalAccessException, SQLException, NoSuchFieldException {

        if(student.getPersonalNumber().length() != 13){
            return;
        }
        if(student.getName().length() > 40){
            return;
        }
        if(student.getSurname().length() > 40){
            return;
        }

        // Maybe do more checking

        reflectiveDAO.update(student);

    }

    @Override
    public Student viewProfile(Student student) throws NoSuchFieldException, IllegalAccessException, SQLException {

        Student result = (Student) reflectiveDAO.get(student);

        return result;


    }

    @Override
    public List<Grade> viewGrades(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        List<Grade> grades;
        List<Object> objects;

        objects = reflectiveDAO.getAll(new Grade());

        grades = objects.stream().filter(o -> o instanceof Grade).map(o -> (Grade) o).filter(g -> g.getStudentId().equals(student.getId())).collect(Collectors.toList());

        return grades;

    }

    @Override
    public List<Course> viewCourses(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        return courseDAO.getCourses(student.getId());

    }

    @Override
    public List<Exam> viewExams(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        List<Exam> exams = new LinkedList<>();
        List<Grade> grades = viewGrades(student);

        for(Grade grade : grades){

            Exam exam  = new Exam();
            exam.setId(grade.getExamId());

            exams.add((Exam)reflectiveDAO.get(exam));

        }

        return exams;

    }

    @Override
    public void enroll(Student student, String courseId) throws SQLException {

        // do some more checking, such as, the student should not be able to enrol the same time to a course he is already enrolled in

        courseDAO.insert(student.getId(), courseId);

    }
}
