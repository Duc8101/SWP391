package Model;

import Entity.LessonVideo;
import java.sql.*;
import java.util.*;

public class DAOLessonVideo extends ConnectDatabase {

    private List<LessonVideo> getList(String sql) {
        List<LessonVideo> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int VideoID = result.getInt(1);
                String VideoName = result.getString(2);
                String VideoFile = result.getString(3);
                int LessonID = result.getInt(4);
                LessonVideo video = new LessonVideo(VideoID, VideoName, VideoFile, LessonID);
                list.add(video);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<LessonVideo> getAllVideo() {
        String sql = "select * from LessonVideo";
        return this.getList(sql);
    }

    // get first video of first lesson
    public LessonVideo getFirstVideo(int CourseID) {
        String sql = "select top 1 * from LessonVideo where LessonID = (\n"
                + "select LessonID from Lesson where CourseID = " + CourseID + " and LessonNo = 1)";
        return this.getList(sql).isEmpty() ? null : this.getList(sql).get(0);
    }
    
    public int DeleteVideo(String column, int ID) {
        int number = 0;
        String sql = "DELETE FROM [dbo].[LessonVideo]\n"
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
