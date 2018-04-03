package BusinessLogicLayer.Implementations;

import BusinessLogicLayer.BusinessModels.CourseModel;
import BusinessLogicLayer.BusinessModels.ExamModel;
import DataAccessLayer.Entities.*;
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
    public Student getById(String id) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Student s = new Student();
        s.setId(id);
        return (Student)reflectiveDAO.get(s);
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
    public List<CourseModel> viewCourses(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        List<Course> courses =  courseDAO.getStudentCourses(student.getId());
        return createListOfCourseModels(courses);

    }

    @Override
    public List<CourseModel> viewAvailableCourses(Student student) throws SQLException, NoSuchFieldException, IllegalAccessException {

        List<Course> courses =  courseDAO.getStudentAvailableCourses(student.getId());
        return createListOfCourseModels(courses);

    }

    @Override
    public List<Student> getStudentsEnrolled(String courseId) throws SQLException {

        return studentDAO.getStudentsAttendingCourse(courseId);

    }

    private List<CourseModel> createListOfCourseModels(List<Course> courses) throws NoSuchFieldException, IllegalAccessException, SQLException {

        List<CourseModel> result = new LinkedList<>();

        for(Course course : courses){

            Teacher t = new Teacher();
            t.setId(course.getTeacherId());
            t = (Teacher)reflectiveDAO.get(t);

            result.add(new CourseModel(course.getId(), course.getSubject(), t.getName()));

        }

        return result;

    }

    @Override
    public List<ExamModel> viewExams(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        List<ExamModel> result = new LinkedList<>();
        List<Grade> grades = viewGrades(student);

        for(Grade grade : grades){

            Exam exam  = new Exam();
            exam.setId(grade.getExamId());
            exam = (Exam)reflectiveDAO.get(exam);

            Course course = new Course();
            course.setId(exam.getCourseId());
            course = (Course)reflectiveDAO.get(course);

            result.add(new ExamModel(course.getSubject(), exam.getDate(), grade.getMark()));

        }

        return result;

    }

    @Override
    public void enroll(Student student, String courseId) throws SQLException {

        // do some more checking, such as, the student should not be able to enrol the same time to a course he is already enrolled in

        courseDAO.insert(student.getId(), courseId);

    }

}
