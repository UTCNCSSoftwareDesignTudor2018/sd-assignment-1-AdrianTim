package DataAccessLayer.Entities;

public class Teacher extends User {

    private String id;
    private String surname;
    private String name;
    private String teacherUsername;

    public Teacher(String id, String surname, String name, String teacherUsername) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.teacherUsername = teacherUsername;
    }

    public Teacher(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }
}
