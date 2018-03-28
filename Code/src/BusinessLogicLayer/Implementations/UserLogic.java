package BusinessLogicLayer.Implementations;

import DataAccessLayer.Entities.User;
import BusinessLogicLayer.IUserLogic;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.IUserDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;
import DataAccessLayer.Implementations.UserDAO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

public class UserLogic implements IUserLogic {

    private IReflectiveDAO reflectiveDAO;
    private IUserDAO userDAO;

    public UserLogic(){
        reflectiveDAO = new ReflectiveDAO();
        userDAO = new UserDAO();
    }

    @Override
    public boolean register(String username, String password) throws NoSuchAlgorithmException, IllegalAccessException, SQLException, ClassNotFoundException {

        // Compute the hash for the password

        if(username.length() >= 5 && password.length() >= 5) {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            String passwordHash = new String(Base64.getEncoder().encode(hash));

            reflectiveDAO.insert(new User(username, passwordHash));

            return true;

        }

        return false;
    }

    @Override
    public String login(String username, String password) throws SQLException, NoSuchAlgorithmException {

        User user = userDAO.get(username);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String paswd = new String(Base64.getEncoder().encode(hash));

       if(paswd.equals(user.getPassword())){

           return user.getUsername();

       };

        return "";
    }

}
