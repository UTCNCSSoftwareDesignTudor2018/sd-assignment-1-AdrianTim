package DataAccessLayer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IReflectiveDAO {

    void insert(Object o) throws SQLException, ClassNotFoundException, IllegalAccessException;
    void update(Object o) throws SQLException, IllegalAccessException, NoSuchFieldException;
    Object get(Object o) throws SQLException, NoSuchFieldException, IllegalAccessException;
    List<Object> getAll(Object o) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

}
