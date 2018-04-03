package DataAccessLayer.Implementations;

import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Student;
import DataAccessLayer.DbConnection;
import DataAccessLayer.IStudentDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    @Override
    public void delete(Student student) throws SQLException {

        Connection c = DbConnection.getInstance();
        Statement statement;
        StringBuilder sql;

        // Delete the student's grades

        statement = c.createStatement();
        sql = new StringBuilder();

        sql.append("DELETE FROM public.\"Grade\" WHERE \"studentId\"='");
        sql.append(student.getId());
        sql.append("';");

        statement.executeUpdate(sql.toString());
        statement.close();

        // Delete the student's relation to the courses

        statement = c.createStatement();
        sql = new StringBuilder();

        sql.append("DELETE FROM public.\"MM_Student_Course\" WHERE \"studentId\"='");
        sql.append(student.getId());
        sql.append("';");

        statement.executeUpdate(sql.toString());
        statement.close();

        // Finally delete the student

        statement = c.createStatement();
        sql = new StringBuilder();

        sql.append("DELETE FROM public.\"Student\" WHERE \"id\"='");
        sql.append(student.getId());
        sql.append("';");

        statement.executeUpdate(sql.toString());
        statement.close();

    }

    @Override
    public Student get(String username) throws SQLException {

        Student student = new Student();

        Connection c = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM public.\"Student\" WHERE \"studentUsername\"='");
        sql.append(username);
        sql.append("';");

        System.out.println(sql.toString());
        ResultSet rs = statement.executeQuery(sql.toString());

        if (rs.next()) {

            student.setId(rs.getString("id"));
            student.setAddress(rs.getString("address"));
            student.setIdentityCardNumber(rs.getString("identityCardNumber"));
            student.setStudentUsername(rs.getString("studentUsername"));
            student.setPersonalNumber(rs.getString("personalNumber"));
            student.setName(rs.getString("name"));
            student.setSurname(rs.getString("surname"));

            rs.close();
            statement.close();

        }
        return student;
    }

    @Override
    public List<Student> getStudentsAttendingCourse(String courseId) throws SQLException {

        List<Student> students = new LinkedList<>();

        Connection c = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT DISTINCT public.\"Student\".\"id\", public.\"Student\".\"surname\", public.\"Student\".\"name\", public.\"Student\".\"personalNumber\", public.\"Student\".\"identityCardNumber\", public.\"Student\".\"address\" \n" +
                "\tFROM public.\"Course\", public.\"MM_Student_Course\", public.\"Student\"\n" +
                "\tWHERE public.\"MM_Student_Course\".\"courseId\" = '");
        sql.append(courseId);
        sql.append("'and public.\"Student\".\"id\" = public.\"MM_Student_Course\".\"studentId\";");

        ResultSet rs = statement.executeQuery(sql.toString());

        while(rs.next()){

            Student s = new Student();

            s.setId(rs.getString("id"));
            s.setSurname(rs.getString("surname"));
            s.setName(rs.getString("name"));
            s.setPersonalNumber(rs.getString("personalNumber"));
            s.setIdentityCardNumber(rs.getString("identityCardNumber"));
            s.setAddress(rs.getString("address"));

            students.add(s);

        }

        return students;

    }
}
