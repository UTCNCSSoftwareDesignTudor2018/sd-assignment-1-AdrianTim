package DataAccessLayer.Implementations;

import DataAccessLayer.Entities.Course;
import DataAccessLayer.DbConnection;
import DataAccessLayer.ICourseDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CourseDAO implements ICourseDAO {
    @Override
    public void insert(String studentId, String courseId) throws SQLException {

        Connection c = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO public.\"MM_Student_Course\" (\"id\", \"studentId\", \"courseId\") VALUES ('");
        sql.append(UUID.randomUUID().toString());
        sql.append("', '");
        sql.append(studentId);
        sql.append("', '");
        sql.append(courseId);
        sql.append("');");

        statement.executeUpdate(sql.toString());




    }

    @Override
    public void delete(String studentId, String courseId) throws SQLException {

        Connection c = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM public.\"MM_Student_Course\" WHERE \"studentId\"='");
        sql.append(studentId);
        sql.append("' AND \"courseId\"='");
        sql.append(courseId);
        sql.append("';");


        statement.executeUpdate(sql.toString());

    }

    @Override
    public List<Course> getCourses(String studentId) throws SQLException {

        List<Course> courses = new LinkedList<>();

        Connection c = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT public.\"Course\".\"id\", public.\"Course\".\"subject\", public.\"Course\".\"teacherId\" \n" +
                "\tFROM public.\"Course\", public.\"MM_Student_Course\", public.\"Student\"\n" +
                "\tWHERE public.\"Course\".\"id\" = public.\"MM_Student_Course\".\"courseId\" and public.\"MM_Student_Course\".\"studentId\" = '");
        sql.append(studentId);
        sql.append("';");

        ResultSet rs = statement.executeQuery(sql.toString());

        while(rs.next()){

            Course course = new Course();

            course.setId(rs.getString("id"));
            course.setSubject(rs.getString("subject"));
            course.setTeacherId(rs.getString("teacherId"));

            courses.add(course);

        }

        return courses;

    }
}
