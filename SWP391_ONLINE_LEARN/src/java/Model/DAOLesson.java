package Model;

import Entity.Lesson;
import java.sql.*;
import java.util.*;

public class DAOLesson extends ConnectDatabase {

    private List<Lesson> getList(String sql) {
        List<Lesson> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int LessonID = result.getInt(1);
                String LessonName = result.getString(2);
                int CourseID = result.getInt(3);
                int LessonNo = result.getInt(4);
                Lesson lesson = new Lesson(LessonID, LessonName, CourseID, LessonNo);
                list.add(lesson);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Lesson> getListLesson(int CourseID) {
        String sql = "select * from Lesson where CourseID = " + CourseID;
        return this.getList(sql);
    }

    public int DeleteLesson(int LessonID) {
        int number = 0;
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
}
