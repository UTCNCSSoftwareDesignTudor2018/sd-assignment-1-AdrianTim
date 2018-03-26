package DataAccessLayer;

import BusinessLogicLayer.BusinessEntities.User;

import java.sql.SQLException;

public interface IUserDAO {

    void update(User user) throws SQLException;
    User get(String username) throws SQLException;

}
