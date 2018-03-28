package PresentationLayer;

import BusinessLogicLayer.IStudentLogic;
import BusinessLogicLayer.IUserLogic;
import BusinessLogicLayer.Implementations.StudentLogic;
import BusinessLogicLayer.Implementations.UserLogic;
import DataAccessLayer.Entities.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class MainView {

    private JFrame frame;

    private JTextField loginUsername = new JTextField(40);
    private JTextField registerUsername = new JTextField(40);

    private JPasswordField loginPassword = new JPasswordField(40);
    private JPasswordField registerPassword = new JPasswordField(40);

    private JTextField surname = new JTextField(40);
    private JTextField name = new JTextField(40);
    private JTextField personalNumber = new JTextField(40);
    private JTextField identityNumber = new JTextField(40);
    private JTextField address = new JTextField(40);

    private JButton login = new JButton("Login");
    private JButton register = new JButton("Register");

    private JPanel p;

    private IUserLogic userLogic;
    private IStudentLogic studentLogic;

    public MainView(){

        userLogic = new UserLogic();
        studentLogic = new StudentLogic();

        frame = new JFrame("Student/Teacher manager");
        frame.setSize(500, 900);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                super.windowClosing(e);
            }
        });

        p = new JPanel();

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.add(new JLabel("Username"));
        p1.add(loginUsername);
        p1.add(new JLabel("Password"));
        p1.add(loginPassword);
        p1.add(login);

        p2.add(new JLabel("Username"));
        p2.add(registerUsername);
        p2.add(new JLabel("Password"));
        p2.add(registerPassword);
        p2.add(new JLabel("Surname"));
        p2.add(surname);
        p2.add(new JLabel("Name"));
        p2.add(name);
        p2.add(new JLabel("Personal Number"));
        p2.add(personalNumber);
        p2.add(new JLabel("Identity Number"));
        p2.add(identityNumber);
        p2.add(new JLabel("Address"));
        p2.add(address);
        p2.add(register);

        //p2.setLayout(new GridBagLayout());

        p.add(p1);
        p.add(p2);

        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        frame.setContentPane(p);
        frame.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = loginUsername.getText();
                char[] password = loginPassword.getPassword();

                if(username.length() > 0 && password.length > 0) {

                    try {
                        String usernameLogged = userLogic.login(username, String.valueOf(password));
                        if (usernameLogged.length() > 0) {

                            System.out.println("Logged in");

                            AccountView accountView = new AccountView(usernameLogged);

                            frame.setVisible(false);
                            frame.dispose();

                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = registerUsername.getText();
                char[] password = registerPassword.getPassword();

                String registerSurname = surname.getText();
                String registerName = name.getText();
                String registerPersonalNumber = personalNumber.getText();
                String registerIDentityCardNumber = identityNumber.getText();
                String registerAddress = address.getText();

                if(username.length() > 0 && password.length > 0 && registerPersonalNumber.length() >0){

                    try {

                        userLogic.register(username, String.valueOf(password));

                        studentLogic.insert(new Student(UUID.randomUUID().toString(), registerSurname, registerName, registerPersonalNumber, username, registerIDentityCardNumber, registerAddress));

                        registerUsername.setText("");
                        registerPassword.setText("");
                        surname.setText("");
                        name.setText("");
                        personalNumber.setText("");
                        identityNumber.setText("");
                        address.setText("");

                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

    }

}
