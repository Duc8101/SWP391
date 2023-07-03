package Model;

import Entity.Course;
import Entity.Pay_Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOPayCourse extends ConnectDatabase {

    public int DeletePayCourse(int StudentID) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Pay_Course]\n"
                + "      WHERE [StudentID] = " + StudentID;
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public boolean CheckStudentExist(List<Course> list) {
        for (Course course : list) {
            String sql = "SELECT * FROM [dbo].[Pay_Course] where [CourseID] = " + course.getCourseID();
            ResultSet result = getData(sql);
            try {
                // if get data successful
                if (result.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return false;
    }

    public int AddPayCourse(Pay_Course pay) {
        int number = 0; // number of row affected
        String sql = "INSERT INTO [dbo].[Pay_Course]\n"
                + "           ([Date]\n"
                + "           ,[type]\n"
                + "           ,[CourseID]\n"
                + "           ,[StudentID])\n"
                + "     VALUES\n"
                + "   (GETDATE(),?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, Pay_Course.TYPE);
            prepare.setInt(2, pay.getCourseID());
            prepare.setInt(3, pay.getStudentID());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public boolean CheckStudentExist(int CourseID) {
        String sql = "SELECT * FROM [dbo].[Pay_Course] where [CourseID] = " + CourseID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
}
