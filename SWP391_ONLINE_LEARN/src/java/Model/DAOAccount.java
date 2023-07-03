package Model;

import Entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOAccount extends ConnectDatabase {

    public Account getAccount(String username) {
        String sql = "select * from [dbo].[Account] where [username] = '" + username + "'";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int ID = result.getInt(1);
                String password = result.getString(3);
                String role = result.getString(4);
                return new Account(ID, username, password, role);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int AddAccount(Account account) {
        int number = 0; // number of row affected
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[RoleName])\n"
                + "     VALUES\n"
                + "   (?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, account.getUsername());
            prepare.setString(2, account.getPassword());
            prepare.setString(3, account.getRoleName());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public List<Account> GetListAccount(String name) {
        List<Account> list = new ArrayList<>();
        String sql;
        // if get all account
        if (name == null || name.trim().isEmpty()) {
            sql = "SELECT * FROM [dbo].[Account]";
        } else {
            sql = "SELECT * FROM [dbo].[Account]\n"
                    + "  where [username] LIKE '%" + name + "%'";
        }
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ID = result.getInt(1);
                String username = result.getString(2);
                String password = result.getString(3);
                String RoleName = result.getString(4);
                Account account = new Account(ID, username, password, RoleName);
                list.add(account);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int DeleteAccount(String username) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Account]\n"
                + "      WHERE [username]='" + username + "'";
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public int UpdateRole(String role, String username) {
        int number = 0; // number of row affected
        String sql = "UPDATE [dbo].[Account]\n"
                + "SET [RoleName] = ?\n"
                + "WHERE [username] = ?\n";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, role);
            prepare.setString(2, username);
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public int UpdatePassword(String username, String password) {
        int number = 0; // number of row affected
        String sql = "UPDATE [dbo].[Account]\n"
                + "SET [password] = ?\n"
                + "WHERE [username] = ?\n";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, password);
            prepare.setString(2, username);
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }
}
