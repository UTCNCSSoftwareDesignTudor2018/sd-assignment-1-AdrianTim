package BusinessLogicLayer.BusinessModels;

import java.sql.Date;

public class ExamModel {

    private String courseName;
    private Date date;
    private int mark;

    public ExamModel(String courseName, Date date, int mark) {
        this.courseName = courseName;
        this.date = date;
        this.mark = mark;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
