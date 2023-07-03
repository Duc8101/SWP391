package Model;

import Entity.Result;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOResult extends ConnectDatabase {

    public int DeleteResult(String username) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Result]\n"
                + "      WHERE [username] = '" + username + "'";
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
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

    public int DeleteResult(int LessonID) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Result]\n"
                + "      WHERE [LessonID] = " + LessonID;
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }
}
