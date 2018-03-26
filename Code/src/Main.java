import BusinessLogicLayer.IUserLogic;
import BusinessLogicLayer.Implementations.UserLogic;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        IUserLogic bs = new UserLogic();

        try {
            //bs.register("Adrianoo", "1234");
            System.out.println(bs.login("Adrianoo", "1234"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
