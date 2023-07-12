package Model;

import Entity.Student;
import java.sql.*;

public class DAOStudent extends ConnectDatabase {

    public Student getStudent(String username) {
        String sql = "SELECT * FROM [dbo].[Student]\n"
                + "WHERE [username] = '" + username + "'";
        ResultSet result = getData(sql);
        try {
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
}
