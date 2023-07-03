package Model;

import Entity.LessonVideo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOLessonVideo extends ConnectDatabase {

    public int DeleteVideo(String column, int ID) {
        int number = 0; // number of row affected
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

    public List<LessonVideo> GetAllLessonVideo() {
        List<LessonVideo> list = new ArrayList<>();
        String sql = "select * from LessonVideo";
        // get data
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

    public LessonVideo getFirstVideo(int CourseID) {
        String sql = "select top 1 FileVideo, VideoName, VideoID, LessonID from LessonVideo where LessonID = (\n"
                + "select top 1 LessonID from Lesson where CourseID = " + CourseID + " and LessonNo = 1)";
        // get data
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String FileVideo = result.getString(1);
                String VideoName = result.getString(2);
                int VideoID = result.getInt(3);
                int LessonID = result.getInt(4);
                LessonVideo video = new LessonVideo(VideoID, VideoName, FileVideo, LessonID);
                return video;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public LessonVideo getLessonVideo(String VideoID) {
        String sql = "select * from LessonVideo where VideoID = " + VideoID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String VideoName = result.getString(2);
                String VideoFile = result.getString(3);
                int LessonID = result.getInt(4);
                LessonVideo video = new LessonVideo(Integer.parseInt(VideoID), VideoName, VideoFile, LessonID);
                return video;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public boolean CheckVideoNameExist(String VideoName, int LessonID) {
        String sql = "select * from LessonVideo where VideoName = '" + VideoName + "' and LessonID = " + LessonID;
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

    public boolean CheckFileVideoExist(String VideoFile, int LessonID) {
        String sql = "select * from LessonVideo where FileVideo = '" + VideoFile + "' and LessonID = " + LessonID;
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

    public int AddVideo(LessonVideo video) {
        String sql = "INSERT INTO [dbo].[LessonVideo]\n"
                + "           ([VideoName]\n"
                + "           ,[FileVideo]\n"
                + "           ,[LessonID])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, video.getVideoName());
            prepare.setString(2, video.getFileVideo());
            prepare.setInt(3, video.getLessonID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdateVideo(LessonVideo video) {
        String sql = "UPDATE [dbo].[LessonVideo]\n"
                + "   SET [VideoName] = ?\n"
                + "      ,[FileVideo] = ?\n"
                + " WHERE [VideoID] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, video.getVideoName());
            prepare.setString(2, video.getFileVideo());
            prepare.setInt(3, video.getVideoID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

}
