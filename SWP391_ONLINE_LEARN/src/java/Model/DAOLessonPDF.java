package Model;

import Entity.LessonPDF;
import java.sql.*;
import java.util.*;

public class DAOLessonPDF extends ConnectDatabase {

    private List<LessonPDF> getList(String sql) {
        List<LessonPDF> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int PDFID = result.getInt(1);
                String PDFName = result.getString(2);
                String FilePDF = result.getString(3);
                int LessonID = result.getInt(4);
                LessonPDF PDF = new LessonPDF(PDFID, PDFName, FilePDF, LessonID);
                list.add(PDF);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
     public List<LessonPDF> getAllPDF() {
        String sql = "select * from LessonPDF";
        return this.getList(sql);
    }
     
     public int DeletePDF(String column, int ID) {
        int number = 0;
        String sql = "DELETE FROM [dbo].[LessonPDF]\n"
                + "      WHERE [" + column + "] = " + ID;
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }
}
