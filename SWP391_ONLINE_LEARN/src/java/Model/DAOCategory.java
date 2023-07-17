package Model;

import Entity.Category;
import java.sql.*;
import java.util.*;

public class DAOCategory extends ConnectDatabase {

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ID = result.getInt(1);
                String name = result.getString(2);
                Category category = new Category(ID, name);
                list.add(category);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
