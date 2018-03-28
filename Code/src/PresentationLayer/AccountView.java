package PresentationLayer;

import BusinessLogicLayer.IStudentLogic;
import BusinessLogicLayer.IUserLogic;
import BusinessLogicLayer.Implementations.StudentLogic;
import BusinessLogicLayer.Implementations.UserLogic;
import DataAccessLayer.Entities.Course;
import DataAccessLayer.Entities.Exam;
import DataAccessLayer.Entities.Student;
import DataAccessLayer.ICourseDAO;
import DataAccessLayer.IReflectiveDAO;
import DataAccessLayer.IStudentDAO;
import DataAccessLayer.Implementations.ReflectiveDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class AccountView {

    private JFrame frame;

    private JPanel p;

    private JTabbedPane roles = new JTabbedPane();

    private JTextField surname = new JTextField(40);
    private JTextField name = new JTextField(40);
    private JTextField personalNumber = new JTextField(40);
    private JTextField identityNumber = new JTextField(40);
    private JTextField address = new JTextField(40);

    private JButton saveChanges = new JButton("Save changes");

    private JTable courseTable;
    private JTable examTable;
    private JTable gradeTables;

    private IUserLogic userLogic;
    private IStudentLogic studentLogic;

    private String loggedUser;

    public AccountView(String loggedUser) {

        this.loggedUser = loggedUser;

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
        JPanel p3 = new JPanel();

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
        p2.add(saveChanges);



        try {

            Student student = studentLogic.get(loggedUser);

            surname.setText(student.getSurname());
            name.setText(student.getName());
            personalNumber.setText(student.getPersonalNumber());
            identityNumber.setText(student.getIdentityCardNumber());
            address.setText(student.getAddress());

            saveChanges.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Student s = new Student();

                    s.setStudentUsername(student.getStudentUsername());
                    s.setId(student.getId());
                    s.setSurname(surname.getText());
                    s.setName(name.getText());
                    s.setPersonalNumber(personalNumber.getText());
                    s.setIdentityCardNumber(identityNumber.getText());
                    s.setAddress(address.getText());

                    try {
                        studentLogic.update(s);
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchFieldException e1) {
                        e1.printStackTrace();
                    }

                }


            });

            String[] fieldsCourses = {"Id", "Subject"};
            Object[][] dataCourses = new Object[studentLogic.viewCourses(student).size()][fieldsCourses.length];

            String[] fieldsExams = {"Id", "Date", "Room"};
            Object[][] dataExams = new Object[studentLogic.viewExams(student).size()][fieldsExams.length];

            int i = 0;
            for(Course course : studentLogic.viewCourses(student)){

                dataCourses[i][0] = course.getId();
                dataCourses[i][1] = course.getSubject();

            }

            i = 0;
            for(Exam exam: studentLogic.viewExams(student)){

                dataExams[i][0] = exam.getId();
                dataExams[i][1] = exam.getDate();
                dataExams[i][2] = exam.getRoom();

            }

            courseTable = new JTable(dataCourses, fieldsCourses);

            JScrollPane scroll = new JScrollPane(courseTable);
            p3.add(scroll);

            p.add(p1);
            p.add(p2);
            p.add(p3);

            p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
            frame.setContentPane(p);
            frame.setVisible(true);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
