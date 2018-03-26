package BusinessLogicLayer.BusinessEntities;

import java.util.Date;

public class Exam {

    private String id;
    private Date date;
    private String room;
    private String courseId;

    public Exam(String id, Date date, String room, String courseId) {
        this.id = id;
        this.date = date;
        this.room = room;
        this.courseId = courseId;
    }

    public Exam(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
