package DataAccessLayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection instance;

    public static Connection getInstance() {

        if(instance == null) {

            try{

                Class.forName("org.postgresql.Driver");
                instance = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NewSinu", "postgres", "root");
                System.out.println("Successfully connected to database");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return instance;

    }

    private DbConnection() {
    }


}
