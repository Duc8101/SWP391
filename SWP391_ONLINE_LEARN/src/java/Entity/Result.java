package Entity;

public class Result {

    private int ResultID;
    private int LessonID;
    private String username;
    private String DateRecord;
    private float score;
    private String status;

    public Result() {
    }

    public Result(int ResultID, int LessonID, String username, String DateRecord, float score, String status) {
        this.ResultID = ResultID;
        this.LessonID = LessonID;
        this.username = username;
        this.DateRecord = DateRecord;
        this.score = score;
        this.status = status;
    }

    public Result(int ResultID, int LessonID, String username, float score, String status) {
        this.ResultID = ResultID;
        this.LessonID = LessonID;
        this.username = username;
        this.score = score;
        this.status = status;
    }

    public Result(int LessonID, String username, float score, String status) {
        this.LessonID = LessonID;
        this.username = username;
        this.score = score;
        this.status = status;
    }

    public int getResultID() {
        return ResultID;
    }

    public void setResultID(int ResultID) {
        this.ResultID = ResultID;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateRecord() {
        return DateRecord;
    }

    public void setDateRecord(String DateRecord) {
        this.DateRecord = DateRecord;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
