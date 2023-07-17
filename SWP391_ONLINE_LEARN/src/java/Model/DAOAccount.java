package Model;

import Entity.Account;
import java.sql.*;
import java.util.*;

public class DAOAccount extends ConnectDatabase {

    public List<Account> getList(String sql) {
        List<Account> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ID = result.getInt(1);
                String username = result.getString(2);
                String password = result.getString(3);
                String role = result.getString(4);
                Account account = new Account(ID, username, password, role);
                list.add(account);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Account getAccount(String username) {
        String sql = "select * from [dbo].[Account] where [username] = '" + username + "'";
        return this.getList(sql).isEmpty() ? null : this.getList(sql).get(0);
    }

    public int AddAccount(Account account) {
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
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
