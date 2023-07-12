package Model;

import Entity.Teacher;
import java.sql.*;
import java.util.*;

public class DAOTeacher extends ConnectDatabase {

    private List<Teacher> getList(String sql) {
        List<Teacher> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ID = result.getInt(1);
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String image = result.getString(4);
                String email = result.getString(5);
                String gender = result.getString(6);
                String username = result.getString(7);
                Teacher teacher = new Teacher(ID, FullName, phone, image, email, gender, username);
                list.add(teacher);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Teacher getTeacher(String username) {
        String sql = "SELECT * FROM [dbo].[Teacher]\n"
                + "WHERE [username] = '" + username + "'";
        List<Teacher> list = this.getList(sql);
        return list.isEmpty() ? null : list.get(0);
    }

    public Teacher getTeacher(int ID) {
        String sql = "SELECT * FROM [dbo].[Teacher]\n"
                + "WHERE [ID] = " + ID;
        List<Teacher> list = this.getList(sql);
        return list.isEmpty() ? null : list.get(0);
    }
}
