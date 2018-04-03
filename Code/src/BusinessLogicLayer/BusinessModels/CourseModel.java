package BusinessLogicLayer.BusinessModels;

public class CourseModel {

    private String id;
    private String subject;
    private String teacherName;

    public CourseModel(String id, String subject, String teacherName) {
        this.id = id;
        this.subject = subject;
        this.teacherName = teacherName;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
