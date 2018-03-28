package BusinessLogicLayer;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IUserLogic {

    boolean register(String username, String password) throws NoSuchAlgorithmException, IllegalAccessException, SQLException, ClassNotFoundException;
    String login(String username, String password) throws SQLException, NoSuchAlgorithmException;

}
