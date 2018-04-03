package BusinessLogicLayer.BusinessModels;

import DataAccessLayer.Entities.Grade;
import DataAccessLayer.Entities.Student;

import java.util.List;

public class Report {

    private Student student;
    private List<Grade> grades;
    private double gradeAverage;
    private List<CourseModel> courses;

    public Report(Student student, List<Grade> grades, double gradeAverage, List<CourseModel> courses) {
        this.student = student;
        this.grades = grades;
        this.gradeAverage = gradeAverage;
        this.courses = courses;
    }

    public Report(){

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public double getGradeAverage() {
        return gradeAverage;
    }

    public void setGradeAverage(double gradeAverage) {
        this.gradeAverage = gradeAverage;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
}
