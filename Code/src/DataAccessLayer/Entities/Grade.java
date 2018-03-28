package DataAccessLayer.Entities;

public class Grade {

    private String id;
    private int mark;
    private String studentId;
    private String examId;

    public Grade(String id, int mark, String studentId, String examId) {
        this.id = id;
        this.mark = mark;
        this.studentId = studentId;
        this.examId = examId;
    }

    public Grade(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
