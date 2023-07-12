package Model;

import Entity.*;
import java.sql.*;
import java.util.*;

public class DAOPayCourse extends ConnectDatabase {

    public List<Pay_Course> getListPay_Course(int StudentID) {
        String sql = "SELECT * FROM [dbo].[Pay_Course]\n"
                + "where [StudentID] = " + StudentID;
        List<Pay_Course> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int PayID = result.getInt(1);
                String date = result.getString(2);
                int CourseID = result.getInt(3);
                Pay_Course pay = new Pay_Course(PayID, date, CourseID, StudentID);
                list.add(pay);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
