package Model;

import Const.ConstValue;
import Entity.*;
import java.sql.*;

public class DAOPayCourse extends ConnectDatabase {

    public Pay_Course getPayCourse(int CourseID, String username) {
        String sql = "select p.* from Pay_Course p join Student s\n"
                + "on p.StudentID = s.ID\n"
                + "where p.CourseID = " + CourseID + " and s.username = '" + username + "'";
        ResultSet result = getData(sql);
        try {
            if (result.next()) {
                int PayID = result.getInt(1);
                String date = result.getString(2);
                int StudentID = result.getInt(5);
                Pay_Course pay = new Pay_Course(PayID, date, CourseID, StudentID);
                return pay;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int AddPayCourse(Pay_Course pay) {
        String sql = "INSERT INTO [dbo].[Pay_Course]\n"
                + "           ([Date]\n"
                + "           ,[type]\n"
                + "           ,[CourseID]\n"
                + "           ,[StudentID])\n"
                + "     VALUES\n"
                + "   (GETDATE(),?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, ConstValue.PAY_TYPE);
            prepare.setInt(2, pay.getCourseID());
            prepare.setInt(3, pay.getStudentID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
