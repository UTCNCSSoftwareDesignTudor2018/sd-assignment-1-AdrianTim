package DataAccessLayer.Entities;

public class Student extends User {

    private String id;
    private String surname;
    private String name;
    private String personalNumber;
    private String studentUsername;
    private String identityCardNumber;
    private String address;

    public Student(String id, String surname, String name, String personalNumber, String studentUsername, String identityCardNumber, String address) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.personalNumber = personalNumber;
        this.studentUsername = studentUsername;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
    }

    public Student(){

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

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
