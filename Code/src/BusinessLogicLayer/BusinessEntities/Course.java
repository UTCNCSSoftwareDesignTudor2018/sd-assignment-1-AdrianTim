package BusinessLogicLayer.BusinessEntities;

public class Course {

    private String id;
    private String subject;
    private String teacherId;

    public Course(String id, String subject, String teacherId) {
        this.id = id;
        this.subject = subject;
        this.teacherId = teacherId;
    }

    public Course(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
