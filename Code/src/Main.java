import BusinessLogicLayer.Implementations.TeacherLogic;
import BusinessLogicLayer.Implementations.UserLogic;
import DataAccessLayer.Entities.Teacher;
import DataAccessLayer.Entities.User;
import DataAccessLayer.Implementations.ReflectiveDAO;
import DataAccessLayer.Implementations.UserDAO;
import PresentationLayer.StartView;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class Main{

    public static void main(String[] args) {

        StartView startView = new StartView();
        JFrame frame = new JFrame();
        frame.setContentPane(startView.getPane());

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
