package BusinessLogicLayer.BusinessModels;

import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Grade;

import java.util.List;

public class Report {

    private List<Grade> grades;
    private double gradeAverage;
    private List<Course> courses;

    public Report(List<Grade> grades, double gradeAverage, List<Course> courses) {
        this.grades = grades;
        this.gradeAverage = gradeAverage;
        this.courses = courses;
    }

    public Report(){

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
