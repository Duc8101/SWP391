package Model;

import Entity.Course;
import Entity.Lesson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOLesson extends ConnectDatabase {

    public List<Lesson> GetListLesson(int CourseID) {
        List<Lesson> list = new ArrayList<>();
        String sql = "select * from Lesson where CourseID = " + CourseID + "";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int LessonID = result.getInt(1);
                String LessonName = result.getString(2);
                int LessonNo = result.getInt(4);
                Lesson lesson = new Lesson(LessonID, LessonName, CourseID, LessonNo);
                list.add(lesson);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Lesson> GetListLesson(List<Course> listCourse) {
        List<Lesson> listLesson = new ArrayList<>();
        for (Course course : listCourse) {
            String sql = "SELECT * FROM [dbo].[Lesson] \n"
                    + "where [CourseID] = " + course.getCourseID();
            ResultSet result = getData(sql);
            try {
                while (result.next()) {
                    int LessonID = result.getInt(1);
                    String LessonName = result.getString(2);
                    int LessonNo = result.getInt(4);
                    Lesson lesson = new Lesson(LessonID, LessonName, course.getCourseID(), LessonNo);
                    listLesson.add(lesson);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return listLesson;
    }

    public int DeleteLesson(int LessonID) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Lesson]\n"
                + "      WHERE [LessonID] = " + LessonID;
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public Lesson getLesson(int LessonID) {
        String sql = "select * from Lesson where LessonID = " + LessonID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String LessonName = result.getString(2);
                int CourseID = result.getInt(3);
                int LessonNo = result.getInt(4);
                Lesson lesson = new Lesson(LessonID, LessonName, CourseID, LessonNo);
                return lesson;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public boolean CheckLessonExist(String name, int CourseID) {
        String sql = "select * from Lesson where LessonName = '" + name + "' and CourseID =" + CourseID;
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

    public int AddLesson(Lesson lesson) {
        String sql = "INSERT INTO [dbo].[Lesson]\n"
                + "           ([LessonName]\n"
                + "           ,[CourseID]\n"
                + "           ,[LessonNo])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, lesson.getLessonName());
            prepare.setInt(2, lesson.getCourseID());
            prepare.setInt(3, lesson.getLessonNo());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdateLessonName(String name, int LessonID) {
        String sql = "UPDATE [dbo].[Lesson]\n"
                + "   SET [LessonName] = ?\n"
                + " WHERE [LessonID] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, name);
            prepare.setInt(2, LessonID);
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdateLessonNo(int no, int LessonID) {
        String sql = "UPDATE [dbo].[Lesson]\n"
                + "   SET [LessonNo] = ?\n"
                + " WHERE [LessonID] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, no);
            prepare.setInt(2, LessonID);
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
