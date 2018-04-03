package PresentationLayer;

import BusinessLogicLayer.*;
import BusinessLogicLayer.BusinessModels.CourseModel;
import BusinessLogicLayer.BusinessModels.ExamModel;
import BusinessLogicLayer.Implementations.*;
import DataAccessLayer.Entities.Exam;
import DataAccessLayer.Entities.Student;
import DataAccessLayer.Entities.Teacher;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProfileView {
    private JTabbedPane tabbedPane;
    private JPanel mainPane;
    private JButton updateButton;
    private JTextField surname;
    private JTextField name;
    private JTextField personalNumber;
    private JTextField identitiyCardNumber;
    private JTextField address;
    private JTable courseTable;
    private JTable gradesTable;
    private JTextField teacherSurname;
    private JTextField teacherName;
    private JButton teacherUpdate;
    private JTable coursesTable;
    private JTable studentTable;
    private JButton updateStudent;
    private JTextField studentSurname;
    private JTextField studentName;
    private JTextField studentPersonalNumber;
    private JTextField studentIdentityCardNumber;
    private JTextField studentAddress;
    private JButton generateReportForStudentButton;
    private JTextField reportStartDate;
    private JTextField reportEndDate;
    private JTable availableCoursesTable;
    private JButton enrolButton;
    private JPanel studentTab;
    private JPanel teacherTab;
    private JPanel studentsPanel;
    private JPanel editStudentPanel;
    private JButton unenrolStudentButton;
    private JScrollPane courseScroll;
    private JScrollPane examsScroll;
    private JScrollPane availableCoursesScroll;
    private JScrollPane teacherCoursesScroll;
    private JScrollPane teacherStudentsScroll;
    private JTextField newCourse;
    private JButton createCourseButton;
    private JButton addExamButton;
    private JTextField examDate;
    private JTextField examRoom;
    private JButton promoteToTeacherButton;
    private JTable studentExams;
    private JTextField grade;
    private JButton gradeButton;
    private JScrollPane studentsExamsScroll;

    private String loggedUser;
    private String courseToEnrol;
    private String courseToView;
    private String studentToEdit;

    private IUserLogic userLogic;
    private IStudentLogic studentLogic;
    private ITeacherLogic teacherLogic;
    private ICourseLogic courseLogic;
    private IExamsLogic examsLogic;

    public ProfileView(String loggedUser) throws SQLException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {

        this.loggedUser = loggedUser;

        this.studentLogic = new StudentLogic();
        this.userLogic = new UserLogic();
        this.teacherLogic = new TeacherLogic();
        this.courseLogic = new CourseLogic();
        this.examsLogic = new ExamsLogic();

        Student student = studentLogic.get(loggedUser);
        Teacher teacher = teacherLogic.get(loggedUser);

        System.out.println(teacher.getId());

        if(student.getId() == null){
            studentTab.setVisible(false);
            tabbedPane.setEnabledAt(0, false);
        }else{
            tabbedPane.setEnabledAt(0, true);
            studentTab.setVisible(true);

            // Set the info

            surname.setText(student.getSurname());
            name.setText(student.getName());
            personalNumber.setText(student.getPersonalNumber());
            identitiyCardNumber.setText(student.getIdentityCardNumber());
            address.setText(student.getAddress());

            // Set the tables

            updateStudentTables(student);

            availableCoursesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    studentCoursesTableListener();

                }
            });



        }

        if(teacher.getId() == null){
            teacherTab.setVisible(false);
            tabbedPane.setEnabledAt(1, false);
        }else{
            tabbedPane.setEnabledAt(1, true);
            teacherTab.setVisible(true);
            studentsPanel.setVisible(false);
            editStudentPanel.setVisible(false);

            // Set the info

            teacherSurname.setText(teacher.getSurname());
            teacherName.setText(teacher.getName());

            // Set the tables

            updateTeacherCoursesTable(teacher);

            coursesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    teacherCoursesTableListener();

                }
            });

            studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    teacherStudentsTableListener();
                    try {
                        updateStudentsExams();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            });

        }

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Student s = new Student();

                s.setStudentUsername(student.getStudentUsername());
                s.setId(student.getId());
                s.setSurname(surname.getText());
                s.setName(name.getText());
                s.setPersonalNumber(personalNumber.getText());
                s.setIdentityCardNumber(identitiyCardNumber.getText());
                s.setAddress(address.getText());

                try {
                    studentLogic.update(s);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        teacherUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Teacher t = new Teacher();

                t.setTeacherUsername(teacher.getTeacherUsername());
                t.setId(teacher.getId());
                t.setSurname(teacherSurname.getText());
                t.setName(teacherName.getText());

                try {
                    teacherLogic.update(t);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        updateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Find a way to get the id of the student, there might be a problem with the update as well
                Student student = new Student();

                student.setId(studentToEdit);
                student.setSurname(studentSurname.getText());
                student.setName(studentName.getText());
                student.setPersonalNumber(studentPersonalNumber.getText());
                student.setIdentityCardNumber(studentIdentityCardNumber.getText());
                student.setAddress(studentAddress.getText());

                try {
                    studentLogic.update(student);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        enrolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    studentLogic.enroll(student, courseToEnrol);
                    updateStudentTables(student);

                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        unenrolStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    courseLogic.unEnrolStudent(studentToEdit, courseToView);
                    updateStudentTables(student);

                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        createCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    courseLogic.addCourse(newCourse.getText(), teacher.getId());
                    newCourse.setText("");
                    updateStudentsTable();
                    updateTeacherCoursesTable(teacher);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        addExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    courseLogic.addExam(Date.valueOf(examDate.getText()), examRoom.getText(), courseToView);
                    examDate.setText("");
                    examRoom.setText("");
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        promoteToTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    teacherLogic.promoteToTeacher(studentToEdit);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        generateReportForStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date startDate = Date.valueOf(reportStartDate.getText());
                Date endDate = Date.valueOf(reportEndDate.getText());

                try {
                    teacherLogic.generateReport(studentToEdit, startDate, endDate);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });
        gradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int mark = Integer.parseInt(grade.getText());
                String examId = studentExams.getValueAt(studentExams.getSelectedRow(), 0).toString();

                try {
                    examsLogic.graderStudent(studentToEdit, examId, mark);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
    }

    public JPanel getPane(){
        return mainPane;
    }

    private  JTable createStudentCoursesTable(Student student) throws IllegalAccessException, InstantiationException, SQLException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        List<CourseModel> courses = studentLogic.viewCourses(student);
        String[] studentCoursesColumns = {"Id", "Subject Name", "Teacher Name"};

        return new JTable(getCoursesTableData(courses, studentCoursesColumns), studentCoursesColumns);

    }

    private JTable createAvailableCoursesTable(Student student) throws NoSuchFieldException, IllegalAccessException, SQLException {

        List<CourseModel> availableCourses = studentLogic.viewAvailableCourses(student);
        String[] columns = {"Id", "Subject", "Teacher Name"};

        return new JTable(getCoursesTableData(availableCourses, columns), columns);

    }

    private Object[][] getCoursesTableData(List<CourseModel> courses, String[] columns){

        Object[][] coursesData = new Object[courses.size()][columns.length];

        int i = 0;
        for(CourseModel course : courses){

            coursesData[i][0] = course.getId();
            coursesData[i][1] = course.getSubject();
            coursesData[i][2] = course.getTeacherName();

            i++;

        }

        return coursesData;

    }

    private JTable createExamTable(Student student) throws IllegalAccessException, InstantiationException, SQLException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        List<ExamModel> exams = studentLogic.viewExams(student);

        System.out.println("There are  " + exams.size() + " number of exams");

        String[] studentExamsColumns = {"Course", "Date", "Mark"};
        Object[][] studentExamsData = new Object[exams.size()][studentExamsColumns.length];

        int i = 0;
        for(ExamModel exam : exams){

            studentExamsData[i][0] = exam.getCourseName();
            studentExamsData[i][1] = exam.getDate().toString();
            studentExamsData[i][2] = exam.getMark();

            i++;

        }

        return new JTable(studentExamsData, studentExamsColumns);

    }

    private void updateTeacherCoursesTable(JTable newTable){

        teacherCoursesScroll.getViewport().remove(coursesTable);
        coursesTable = newTable;
        coursesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                teacherCoursesTableListener();
            }
        });
        teacherCoursesScroll.getViewport().add(coursesTable);

    }

    private void updateTeachersStudentsTable(JTable newTable){

        teacherStudentsScroll.getViewport().remove(studentTable);
        studentTable = newTable;
        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                teacherStudentsTableListener();
                try {
                    updateStudentsExams();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occured", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        teacherStudentsScroll.getViewport().add(studentTable);

    }

    private JTable createTeacherCoursesTable(Teacher teacher) throws SQLException {

        List<CourseModel> courses = teacherLogic.viewCourses(teacher);
        String[] columns = {"Id", "Subject", "Teacher"};

        return new JTable(getCoursesTableData(courses, columns), columns);

    }

    private JTable createTeacherStudentsTable(String courseId) throws SQLException {

        List<Student> students = studentLogic.getStudentsEnrolled(courseId);
        String[] columns = {"Id", "Surname", "Name", "Personal Number", "Identity Card Number", "Address"};
        Object[][] studentsData = new Object[students.size()][columns.length];

        int i = 0;
        for(Student student : students){

            studentsData[i][0] = student.getId();
            studentsData[i][1] = student.getSurname();
            studentsData[i][2] = student.getName();
            studentsData[i][3] = student.getPersonalNumber();
            studentsData[i][4] = student.getIdentityCardNumber();
            studentsData[i][5] = student.getAddress();

            i++;

        }

        return new JTable(studentsData, columns);

    }

    private JTable createStudentsExamsTable(String courseId) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        List<Exam> exams = examsLogic.getCourseExams(courseId);
        String[] columns = {"Id", "Date", "Room"};
        Object[][] data = new Object[exams.size()][columns.length];

        System.out.println("This is the total number of exams " + exams.size());

        int i = 0;
        for(Exam exam : exams){

            data[i][0] = exam.getId();
            data[i][1] = exam.getDate().toString();
            data[i][2] = exam.getRoom();

            i++;

        }

        return new JTable(data, columns);

    }

    private void updateTeacherCoursesTable(Teacher teacher) throws SQLException {

        updateTeacherCoursesTable(createTeacherCoursesTable(teacher));

    }

    private void updateStudentsTable() throws SQLException {

        updateTeachersStudentsTable(createTeacherStudentsTable(courseToView));

    }

    private void updateStudentsExams() throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        studentsExamsScroll.getViewport().remove(studentExams);
        studentExams = createStudentsExamsTable(courseToView);
        studentsExamsScroll.getViewport().add(studentExams);

    }

    private void updateStudentCourses(JTable newTable){

        courseScroll.getViewport().remove(courseTable);
        courseTable = newTable;
        courseScroll.getViewport().add(courseTable);

    }

    private void updateStudentGrades(JTable newTable){

        examsScroll.getViewport().remove(gradesTable);
        gradesTable = newTable;
        examsScroll.getViewport().add(gradesTable);

    }

    private void updateAvailableCoursesTable(JTable newTable){

        availableCoursesScroll.getViewport().remove(availableCoursesTable);
        availableCoursesTable = newTable;
        availableCoursesScroll.getViewport().add(availableCoursesTable);

    }

    private void updateStudentTables(Student student) throws IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, NoSuchMethodException, NoSuchFieldException {

        updateStudentCourses(createStudentCoursesTable(student));
        updateStudentGrades(createExamTable(student));
        updateAvailableCoursesTable(createAvailableCoursesTable(student));

    }

    private void teacherCoursesTableListener(){

        courseToView = coursesTable.getValueAt(coursesTable.getSelectedRow(), 0).toString();
        // Update the students table
        try {
            studentsPanel.setVisible(true);
            updateStudentsTable();
        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occurred", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void teacherStudentsTableListener(){

        try {

            editStudentPanel.setVisible(true);
            // Update the students info
            Student student = studentLogic.getById((String)studentTable.getValueAt(studentTable.getSelectedRow(), 0));

            studentSurname.setText(student.getSurname());
            studentName.setText(student.getName());
            studentPersonalNumber.setText(student.getPersonalNumber());
            studentIdentityCardNumber.setText(student.getIdentityCardNumber());
            studentAddress.setText(student.getAddress());

            studentToEdit = student.getId();

        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "An error has occurred", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void studentCoursesTableListener(){

        // System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
        // This is the course to enrol

        courseToEnrol = availableCoursesTable.getValueAt(availableCoursesTable.getSelectedRow(), 0).toString();

    }

}
