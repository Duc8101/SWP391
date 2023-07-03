package Model;

import Entity.LessonPDF;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOLessonPDF extends ConnectDatabase {

    public int DeletePDF(String column, int ID) {
        int number = 0; // number of row affected
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

    public List<LessonPDF> GetAllLessonPDF() {
        List<LessonPDF> list = new ArrayList<>();
        String sql = "select * from LessonPDF";
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

    public LessonPDF getLessonPDF(String PDFID) {
        String sql = "select * from LessonPDF where PDFID = " + PDFID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String PDFName = result.getString(2);
                String FilePDF = result.getString(3);
                int LessonID = result.getInt(4);
                LessonPDF PDF = new LessonPDF(Integer.parseInt(PDFID), PDFName, FilePDF, LessonID);
                return PDF;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public boolean CheckPDFNameExist(String PDFName, int LessonID) {
        String sql = "select * from LessonPDF where PDFName = '" + PDFName + "' and LessonID = " + LessonID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public boolean CheckFilePDFExist(String FilePDF, int LessonID) {
        String sql = "select * from LessonPDF where FilePDF = '" + FilePDF + "' and LessonID = " + LessonID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public int AddPDF(LessonPDF PDF) {
        String sql = "INSERT INTO [dbo].[LessonPDF]\n"
                + "           ([PDFName]\n"
                + "           ,[FilePDF]\n"
                + "           ,[LessonID])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, PDF.getPDFName());
            prepare.setString(2, PDF.getFilePDF());
            prepare.setInt(3, PDF.getLessonID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdatePDF(LessonPDF PDF) {
        String sql = "UPDATE [dbo].[LessonPDF]\n"
                + "   SET [PDFName] = ?\n"
                + "      ,[FilePDF] = ?\n"
                + " WHERE [PDFID] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, PDF.getPDFName());
            prepare.setString(2, PDF.getFilePDF());
            prepare.setInt(3, PDF.getPDFID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

}
