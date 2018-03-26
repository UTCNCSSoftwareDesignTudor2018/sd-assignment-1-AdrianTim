package DataAccessLayer.Implementations;

import DataAccessLayer.DbConnection;
import DataAccessLayer.IReflectiveDAO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ReflectiveDAO implements IReflectiveDAO {


    @Override
    public void insert(Object o) throws SQLException, ClassNotFoundException, IllegalAccessException {

        Connection c  = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO public.\"");
        sql.append(o.getClass().getSimpleName());
        sql.append("\" (");

        Class cls = o.getClass();

        Field[] fields = cls.getDeclaredFields();

        String comma = "";
        for(Field f : fields){

            f.setAccessible(true);
            sql.append(comma);
            comma = ", ";
            sql.append("\"" + f.getName() + "\"");

        }

        sql.append(") VALUES (");

        comma = "";
        for(Field f : fields){

            sql.append(comma);
            comma = ", ";
            if(f.getType().getSimpleName().equals("String")){
                sql.append("'");
            }
            Object value = f.get(cls.cast(o));
            sql.append(value.toString());
            if(f.getType().getSimpleName().equals("String")){
                sql.append("'");
            }

        }

        sql.append(");");

        statement.executeUpdate(sql.toString());

        statement.close();
        //c.close();

    }

    @Override
    public void update(Object o) throws SQLException, IllegalAccessException, NoSuchFieldException {

        Connection c  = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE public.\"");
        sql.append(o.getClass().getSimpleName());
        sql.append("\"");
        sql.append(" SET ");

        Class cls = o.getClass();

        Field[] fields = cls.getDeclaredFields();

        String comma = "";
        for(Field f : fields){

            f.setAccessible(true);
            sql.append(comma);
            comma = ", ";
            sql.append("\"" + f.getName() + "\"");
            sql.append("=");

            if(f.getType().getSimpleName().equals("String")){
                sql.append("'");
            }
            Object value = f.get(cls.cast(o));
            sql.append(value.toString());
            if(f.getType().getSimpleName().equals("String")){
                sql.append("'");
            }

        }

        sql.append(" WHERE id='");
        Field f = cls.getDeclaredField("id");
        f.setAccessible(true);
        sql.append(f.get(cls.cast(o)).toString());

        sql.append("';");

        System.out.println(sql.toString());

        statement.executeUpdate(sql.toString());

        statement.close();
        //c.close();

    }

    @Override
    public Object get(Object o) throws SQLException, NoSuchFieldException, IllegalAccessException {

        Class cls = o.getClass();
        Field idField = cls.getDeclaredField("id");
        idField.setAccessible(true);
        String id = (String)idField.get(cls.cast(o));

        Connection c  = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");

        Field[] fields = cls.getDeclaredFields();

        String comma = "";
        for(Field f : fields){

            sql.append(comma);
            comma = ",";
            sql.append("\"");
            sql.append(f.getName());
            sql.append("\"");

        }

        sql.append(" FROM public.\"");
        sql.append(cls.getSimpleName());
        sql.append("\" WHERE id='");
        sql.append(id);
        sql.append("';");

        System.out.println(sql);

        ResultSet rs = statement.executeQuery(sql.toString());

        while(rs.next()){

            for(Field f : fields){

                f.setAccessible(true);
                f.set(cls.cast(o), rs.getObject(f.getName()));

            }

        }

        rs.close();
        statement.close();
        //c.close();

        return o;
    }

    @Override
    public List<Object> getAll(Object o) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        List<Object> result = new LinkedList<>();
        Class cls = o.getClass();

        Connection c  = DbConnection.getInstance();
        Statement statement = c.createStatement();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");

        Field[] fields = cls.getDeclaredFields();

        String comma = "";
        for(Field f : fields){

            sql.append(comma);
            comma = ",";
            sql.append("\"");
            sql.append(f.getName());
            sql.append("\"");

        }

        sql.append(" FROM public.\"");
        sql.append(cls.getSimpleName());
        sql.append("\";");

        System.out.println(sql.toString());

        ResultSet rs = statement.executeQuery(sql.toString());

        Constructor cs = cls.getConstructor();

        while(rs.next()){

            Object obj = cs.newInstance();
            fields = obj.getClass().getDeclaredFields();

            for(Field f : fields){

                f.setAccessible(true);
                f.set(cls.cast(obj), rs.getObject(f.getName()));

            }

            result.add(obj);

        }

        return result;
    }
}
