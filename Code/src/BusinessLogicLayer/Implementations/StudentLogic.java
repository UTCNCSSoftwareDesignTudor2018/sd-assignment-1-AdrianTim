package BusinessLogicLayer.Implementations;

import BusinessLogicLayer.BusinessEntities.Course;
import BusinessLogicLayer.BusinessEntities.Exam;
import BusinessLogicLayer.BusinessEntities.Grade;
import BusinessLogicLayer.BusinessEntities.Student;
import BusinessLogicLayer.IStudentLogic;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentLogic implements IStudentLogic{

    private IReflectiveDAO reflectiveDAO;

    public StudentLogic(){

        reflectiveDAO = new ReflectiveDAO();

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
    public void viewCourses(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        List<Course> courses;
        List<Object>  objects;

        objects = reflectiveDAO.getAll(new Course());

        //courses = objects.stream().filter(o -> o instanceof Course).map(o -> (Course) o).filter(c -> c.)

    }

    @Override
    public void viewExams(Student student) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        List<Exam> exams;
        List<Object> objects;

        objects = reflectiveDAO.getAll(new Exam());

        //exams = objects.stream().filter(o -> o instanceof Exam).map(o -> (Exam) o).filter(e -> e.)

    }

    @Override
    public void enroll(Student student, String courseId) {

    }
}
