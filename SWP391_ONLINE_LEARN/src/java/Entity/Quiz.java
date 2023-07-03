package Entity;

public class Quiz {

    private int QuestionID;
    private String question;
    private int LessonID;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;
    private int CorrectAnswer;

    public Quiz() {
    }

    public Quiz(int QuestionID, String question, int LessonID, String Answer1, String Answer2, String Answer3, String Answer4, int CorrectAnswer) {
        this.QuestionID = QuestionID;
        this.question = question;
        this.LessonID = LessonID;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.Answer4 = Answer4;
        this.CorrectAnswer = CorrectAnswer;
    }

    public Quiz(String question, int LessonID, String Answer1, String Answer2, String Answer3, String Answer4, int CorrectAnswer) {
        this.question = question;
        this.LessonID = LessonID;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.Answer4 = Answer4;
        this.CorrectAnswer = CorrectAnswer;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonId(int LessonId) {
        this.LessonID = LessonId;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String Answer1) {
        this.Answer1 = Answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String Answer2) {
        this.Answer2 = Answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String Answer3) {
        this.Answer3 = Answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String Answer4) {
        this.Answer4 = Answer4;
    }

    public int getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(int CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

}
