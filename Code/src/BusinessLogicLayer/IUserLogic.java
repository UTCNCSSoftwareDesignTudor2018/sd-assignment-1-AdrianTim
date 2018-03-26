package BusinessLogicLayer;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IUserLogic {

    void register(String username, String password) throws NoSuchAlgorithmException, IllegalAccessException, SQLException, ClassNotFoundException;
    boolean login(String username, String password) throws SQLException, NoSuchAlgorithmException;

}
