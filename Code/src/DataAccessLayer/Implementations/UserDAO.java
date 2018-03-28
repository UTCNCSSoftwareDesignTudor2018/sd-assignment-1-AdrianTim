package DataAccessLayer.Implementations;

import DataAccessLayer.Entities.User;
import DataAccessLayer.DbConnection;
import DataAccessLayer.IUserDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements IUserDAO{
    @Override
    public void update(User user) throws SQLException {

        Connection c = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE public.\"User\" SET \"username\"='");
        sql.append(user.getUsername());
        sql.append("', \"password\"='");
        sql.append(user.getPassword());
        sql.append("' WHERE \"username\"='");
        sql.append(user.getUsername());
        sql.append("';");

        statement.executeUpdate(sql.toString());

        statement.close();
        //c.close();

    }

    @Override
    public User get(String username) throws SQLException {

        Connection c = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM public.\"User\" WHERE \"username\"='");
        sql.append(username);
        sql.append("';");

        ResultSet rs = statement.executeQuery(sql.toString());

        rs.next();

        return new User(rs.getString("username"), rs.getString("password"));

    }
}
