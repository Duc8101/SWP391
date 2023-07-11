package Entity;

public class Pay_Course {

    private int PayID;
    private String date;  
    private int CourseID;
    private int StudentID;

    public Pay_Course() {
    }

    public Pay_Course(int PayID, String date, int CourseID, int StudentID) {
        this.PayID = PayID;
        this.date = date;
        this.CourseID = CourseID;
        this.StudentID = StudentID;
    }

    public Pay_Course(String date, int CourseID, int StudentID) {
        this.date = date;
        this.CourseID = CourseID;
        this.StudentID = StudentID;
    }

    public Pay_Course(int CourseID, int StudentID) {
        this.CourseID = CourseID;
        this.StudentID = StudentID;
    }

    public int getPayID() {
        return PayID;
    }

    public String getDate() {
        return date;
    }

    public int getCourseID() {
        return CourseID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setPayID(int PayID) {
        this.PayID = PayID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

}
