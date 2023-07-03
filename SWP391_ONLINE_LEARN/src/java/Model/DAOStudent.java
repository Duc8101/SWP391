package Model;

import Entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOStudent extends ConnectDatabase {

    public Student getStudent(String username) {
        String sql = "SELECT * FROM [dbo].[Student]\n"
                + "WHERE [username] ='" + username + "'";
        // get data
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int ID = result.getInt(1);
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String address = result.getString(4);
                String image = result.getString(5);
                String email = result.getString(6);
                String gender = result.getString(7);
                String DOB = result.getString(9);
                Student student = new Student(ID, FullName, phone, address, image, email, gender, username, DOB);
                return student;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int AddStudent(Student student) {
        int number = 0;// number of row affected
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([FullName]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[image]\n"
                + "           ,[email]\n"
                + "           ,[gender]\n"
                + "           ,[username]\n"
                + "           ,[DateOfBirth])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, student.getFullName());
            prepare.setString(2, student.getPhone());
            prepare.setString(3, student.getAddress());
            prepare.setString(4, student.getImage());
            prepare.setString(5, student.getEmail());
            prepare.setString(6, student.getGender());
            prepare.setString(7, student.getUsername());
            prepare.setString(8, student.getDOB());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public int DeleteStudent(String username) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Student]\n"
                + "      WHERE [username]='" + username + "'";
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public int UpdateStudent(Student student) {
        int number = 0; // number of row affected
        String sql = "UPDATE [dbo].[Student]\n"
                + "SET [FullName] = ?,\n"
                + "[phone] = ?,\n"
                + "[address] =?,\n"
                + "[image] = ?,\n"
                + "[email] = ?,\n"
                + "[gender] = ?,\n"
                + "[DateOfBirth] = ?\n"
                + "WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, student.getFullName());
            prepare.setString(2, student.getPhone());
            prepare.setString(3, student.getAddress());
            prepare.setString(4, student.getImage());
            prepare.setString(5, student.getEmail());
            prepare.setString(6, student.getGender());
            prepare.setString(7, student.getDOB());
            prepare.setString(8, student.getUsername());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }
}
