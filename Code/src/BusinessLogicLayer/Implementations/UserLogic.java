package BusinessLogicLayer.Implementations;

import BusinessLogicLayer.BusinessEntities.User;
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
    public void register(String username, String password) throws NoSuchAlgorithmException, IllegalAccessException, SQLException, ClassNotFoundException {

        // Compute the hash for the password

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        String passwordHash = new String(Base64.getEncoder().encode(hash));

        reflectiveDAO.insert(new User(username, passwordHash));

    }

    @Override
    public boolean login(String username, String password) throws SQLException, NoSuchAlgorithmException {

        User user = userDAO.get(username);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String paswd = new String(Base64.getEncoder().encode(hash));

        // more stuff to be done

       return paswd.equals(user.getPassword());

    }

}
