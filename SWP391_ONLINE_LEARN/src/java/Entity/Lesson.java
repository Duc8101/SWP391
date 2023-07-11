package Entity;

public class Lesson {

    private int LessonID;
    private String LessonName;
    private int CourseID;
    private int LessonNo;

    public Lesson() {
    }

    public Lesson(int LessonID, String LessonName, int CourseID, int LessonNo) {
        this.LessonID = LessonID;
        this.LessonName = LessonName;
        this.CourseID = CourseID;
        this.LessonNo = LessonNo;
    }

    public Lesson(String LessonName, int CourseID, int LessonNo) {
        this.LessonName = LessonName;
        this.CourseID = CourseID;
        this.LessonNo = LessonNo;
    }

    public Lesson(int LessonID, String LessonName, int LessonNo) {
        this.LessonID = LessonID;
        this.LessonName = LessonName;
        this.LessonNo = LessonNo;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }

    public String getLessonName() {
        return LessonName;
    }

    public void setLessonName(String LessonName) {
        this.LessonName = LessonName;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getLessonNo() {
        return LessonNo;
    }

    public void setLessonNo(int LessonNo) {
        this.LessonNo = LessonNo;
    }

}
