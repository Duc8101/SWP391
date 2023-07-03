package Model;

import Entity.Teacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOTeacher extends ConnectDatabase {

    public Teacher getTeacher(String username) {
        String sql = "SELECT * FROM [dbo].[Teacher]\n"
                + "WHERE [username] ='" + username + "'";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int ID = result.getInt(1);
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String image = result.getString(4);
                String email = result.getString(5);
                String gender = result.getString(6);
                Teacher teacher = new Teacher(ID, FullName, phone, image, email, gender, username);
                return teacher;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public Teacher getTeacher(int ID) {
        String sql = "SELECT * FROM [dbo].[Teacher]\n"
                + "WHERE [ID] = " + ID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String image = result.getString(4);
                String email = result.getString(5);
                String gender = result.getString(6);
                String username = result.getString(7);
                Teacher teacher = new Teacher(ID, FullName, phone, image, email, gender, username);
                return teacher;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public List<Teacher> GetTop4Teacher() {
        List<Teacher> list = new ArrayList<>();
        String sql = "SELECT TOP 4* FROM [dbo].[Teacher]";
        // get data
        ResultSet result = getData(sql);
        try {
            // loop for traverse all row
            while (result.next()) {
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String image = result.getString(4);
                String email = result.getString(5);
                String gender = result.getString(6);
                String username = result.getString(7);
                Teacher teacher = new Teacher(FullName, phone, image, email, gender, username);
                list.add(teacher);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int DeleteTeacher(String username) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Teacher]\n"
                + "      WHERE [username]='" + username + "'";
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public int AddTeacher(Teacher teacher) {
        int number = 0; // number of row affected
        String sql = "INSERT INTO [dbo].[Teacher]\n"
                + "           ([FullName]\n"
                + "           ,[phone]\n"
                + "           ,[image]\n"
                + "           ,[email]\n"
                + "           ,[gender]\n"
                + "           ,[username])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, teacher.getFullName());
            prepare.setString(2, teacher.getPhone());
            prepare.setString(3, teacher.getImage());
            prepare.setString(4, teacher.getEmail());
            prepare.setString(5, teacher.getGender());
            prepare.setString(6, teacher.getUsername());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public int UpdateTeacher(Teacher teacher) {
        int number = 0;// number of row affected
        String sql = "UPDATE [dbo].[Teacher]\n"
                + "   SET [FullName] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, teacher.getFullName());
            prepare.setString(2, teacher.getPhone());
            prepare.setString(3, teacher.getImage());
            prepare.setString(4, teacher.getEmail());
            prepare.setString(5, teacher.getGender());
            prepare.setString(6, teacher.getUsername());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }
}
