package Model;

import Entity.Result;
import java.sql.*;

public class DAOResult extends ConnectDatabase {

    public Result getResult(int LessonID, String username) {
        String sql = "SELECT TOP 1 * FROM [dbo].[Result]\n"
                + "WHERE [LessonID] = " + LessonID + " and [username] = '" + username + "'\n"
                + "order by [ResultID] desc";
        ResultSet result = getData(sql);
        try {
            if (result.next()) {
                int ResultID = result.getInt(1);
                String DateRecord = result.getString(4);
                float score = result.getFloat(5);
                String status = result.getString(6);
                return new Result(ResultID, LessonID, username, DateRecord, score, status);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public int AddResult(Result result) {
        int number = 0; // number of row affected
        String sql = "INSERT INTO [dbo].[Result]\n"
                + "           ([LessonID]\n"
                + "           ,[Username]\n"
                + "           ,[DateRecord]\n"
                + "           ,[score]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,GETDATE(),?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, result.getLessonID());
            prepare.setString(2, result.getUsername());
            prepare.setFloat(3, result.getScore());
            prepare.setString(4, result.getStatus());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }
}
