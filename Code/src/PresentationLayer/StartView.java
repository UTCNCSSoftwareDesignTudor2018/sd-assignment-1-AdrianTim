package PresentationLayer;

import BusinessLogicLayer.IStudentLogic;
import BusinessLogicLayer.ITeacherLogic;
import BusinessLogicLayer.IUserLogic;
import BusinessLogicLayer.Implementations.StudentLogic;
import BusinessLogicLayer.Implementations.TeacherLogic;
import BusinessLogicLayer.Implementations.UserLogic;
import DataAccessLayer.Entities.Student;
import DataAccessLayer.Entities.Teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class StartView {
    private JTextField loginUsername;
    private JPasswordField loginPassword;
    private JButton loginButton;
    private JTextField registerUsername;
    private JPasswordField registerPassword;
    private JTextField registerSurname;
    private JTextField registerName;
    private JTextField registerPersonalNumber;
    private JTextField registerIdentityCard;
    private JTextField registerAddress;
    private JButton registerButton;
    private JPanel mainPane;
    private JButton registerAsTeacherButton;
    private JTextField teacherSurname;
    private JTextField teacherName;
    private JTextField teacherUsername;
    private JPasswordField teacherPassword;

    private IUserLogic userLogic;
    private IStudentLogic studentLogic;
    private ITeacherLogic teacherLogic;

    public StartView() {

        userLogic = new UserLogic();
        studentLogic = new StudentLogic();
        teacherLogic = new TeacherLogic();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = loginUsername.getText();
                char[] password = loginPassword.getPassword();

                if(username.length() > 0 && password.length > 0) {

                    try {
                        String usernameLogged = userLogic.login(username, String.valueOf(password));
                        if (usernameLogged.length() > 0) {

                            System.out.println("Logged in");

                            ProfileView view = new ProfileView(usernameLogged);

                            JFrame frame = new JFrame();
                            frame.setContentPane(view.getPane());

                            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);

//                            frame.setVisible(false);
//                            frame.dispose();

                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();

                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String username = registerUsername.getText();
                char[] password = registerPassword.getPassword();

                String surname = registerSurname.getText();
                String name = registerName.getText();
                String personalNumber = registerPersonalNumber.getText();
                String identityCardNumber = registerIdentityCard.getText();
                String address = registerAddress.getText();

                if(username.length() > 0 && password.length > 0 && personalNumber.length() >0){

                    try {

                        userLogic.register(username, String.valueOf(password));
                        studentLogic.insert(new Student(UUID.randomUUID().toString(), surname, name, personalNumber, username, identityCardNumber, address));

                        registerUsername.setText("");
                        registerPassword.setText("");
                        registerSurname.setText("");
                        registerName.setText("");
                        registerPersonalNumber.setText("");
                        registerIdentityCard.setText("");
                        registerAddress.setText("");

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            }
        });
        registerAsTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = teacherUsername.getText();
                char[] password = teacherPassword.getPassword();
                String surname = teacherSurname.getText();
                String name = teacherName.getText();

                if(username.length() > 0 && password.length > 0){

                    try {

                        userLogic.register(username, String.valueOf(password));
                        teacherLogic.insert(surname, name, username);

                        teacherUsername.setText("");
                        teacherPassword.setText("");
                        teacherName.setText("");
                        teacherSurname.setText("");

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            }
        });
    }

    public JPanel getPane(){
        return mainPane;
    }
}
