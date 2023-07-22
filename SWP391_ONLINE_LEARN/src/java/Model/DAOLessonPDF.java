package Model;

import Entity.LessonPDF;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
