package DataAccessLayer.Implementations;

import DataAccessLayer.DbConnection;
import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Teacher;
import DataAccessLayer.ITeacherDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherDAO implements ITeacherDAO{
    @Override
    public Teacher get(String username) throws SQLException {

        Teacher teacher = new Teacher();

        Connection c = DbConnection.getInstance();

        PreparedStatement statement = c.prepareStatement("SELECT \"id\", \"surname\", \"name\", \"teacherUsername\" FROM public.\"Teacher\" WHERE \"teacherUsername\"= ? ;");
        statement.setString(1, username);

        System.out.println(statement.toString());

        ResultSet rs = statement.executeQuery();

        if(rs.next()){

            teacher.setId(rs.getString("id"));
            teacher.setName(rs.getString("name"));
            teacher.setSurname(rs.getString("surname"));
            teacher.setTeacherUsername(rs.getString("teacherUsername"));

        }

        return teacher;

    }

}
