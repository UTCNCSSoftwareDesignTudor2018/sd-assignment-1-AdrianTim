package BusinessLogicLayer.Implementations;

import BusinessLogicLayer.IExamsLogic;
import DataAccessLayer.Entities.Exam;
import DataAccessLayer.Entities.Grade;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExamsLogic implements IExamsLogic {

    private IReflectiveDAO reflectiveDAO;

    public ExamsLogic(){

        reflectiveDAO = new ReflectiveDAO();

    }

    @Override
    public List<Exam> getCourseExams(String courseId) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        return reflectiveDAO.getAll(new Exam()).stream()
                .filter(o -> o instanceof Exam)
                .map(o -> (Exam) o)
                .filter(e -> e.getCourseId().equals(courseId))
                .collect(Collectors.toList());

    }

    @Override
    public void graderStudent(String studentId, String examId, int mark) throws IllegalAccessException, SQLException, ClassNotFoundException {

        Grade grade = new Grade();
        grade.setId(UUID.randomUUID().toString());
        grade.setMark(mark);
        grade.setStudentId(studentId);
        grade.setExamId(examId);

        reflectiveDAO.insert(grade);

    }
}
