package Entity;

public class LessonVideo {

    private int VideoID;
    private String VideoName;
    private String FileVideo;
    private int LessonID;

    public LessonVideo() {
    }

    public LessonVideo(int VideoID, String VideoName, String FileVideo, int LessonID) {
        this.VideoID = VideoID;
        this.VideoName = VideoName;
        this.FileVideo = FileVideo;
        this.LessonID = LessonID;
    }

    public LessonVideo(String VideoName, String FileVideo, int LessonID) {
        this.VideoName = VideoName;
        this.FileVideo = FileVideo;
        this.LessonID = LessonID;
    }

    public LessonVideo(int VideoID, String VideoName, String FileVideo) {
        this.VideoID = VideoID;
        this.VideoName = VideoName;
        this.FileVideo = FileVideo;
    }

    public int getVideoID() {
        return VideoID;
    }

    public void setVideoID(int VideoID) {
        this.VideoID = VideoID;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String VideoName) {
        this.VideoName = VideoName;
    }

    public String getFileVideo() {
        return FileVideo;
    }

    public void setFileVideo(String FileVideo) {
        this.FileVideo = FileVideo;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }

}
