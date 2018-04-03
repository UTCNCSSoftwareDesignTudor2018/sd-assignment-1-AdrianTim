package BusinessLogicLayer;

import BusinessLogicLayer.BusinessModels.Report;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportMaker {

    public static void createReport(Report report){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();

        try {

            PrintWriter file = new PrintWriter("Report-" + report.getStudent().getName() + "--" + d.getTime() + "-.txt", "UTF-8");

            file.println("-----------------------------------------------------------------------------------------------------------------------");
            file.println("                                                         Report                                                        ");
            file.println("-----------------------------------------------------------------------------------------------------------------------");
            file.println("        Student Surname : " + report.getStudent().getSurname());
            file.println("        Student Name : " + report.getStudent().getName());
            file.println("        Student Personal Number : " + report.getStudent().getPersonalNumber());
            file.println("-----------------------------------------------------------------------------------------------------------------------");
            file.println("        Courses enrolled : " + report.getCourses().size());
            file.println("        Average Grade : " + report.getGradeAverage());
            file.println("-----------------------------------------------------------------------------------------------------------------------");
            file.println("        Report generated at : " + dateFormat.format(d));
            file.println("-----------------------------------------------------------------------------------------------------------------------");

            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
