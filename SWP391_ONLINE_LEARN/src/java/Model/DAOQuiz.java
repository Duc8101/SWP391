package Model;

import Entity.Quiz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOQuiz extends ConnectDatabase {

    public int DeleteQuiz(String column, int ID) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Quiz]\n"
                + "      WHERE [" + column + "] = " + ID;
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public List<Integer> GetAllLessonOfQuiz() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT distinct LessonID FROM [dbo].[Quiz]";
        ResultSet result = getData(sql);
        try {
            // loop for traverse through all lesson
            while (result.next()) {
                int LessonID = result.getInt(1);
                list.add(LessonID);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Quiz> GetListQuiz(int LessonID) {
        List<Quiz> list = new ArrayList<>();
        String sql = "select * from Quiz\n"
                + "where LessonID = " + LessonID;
        // get data
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int QuestionID = result.getInt(1);
                String question = result.getString(2);
                String Answer1 = result.getString(4);
                String Answer2 = result.getString(5);
                String Answer3 = result.getString(6);
                String Answer4 = result.getString(7);
                int CorrectAnswer = result.getInt(8);
                Quiz quiz = new Quiz(QuestionID, question, LessonID, Answer1, Answer2, Answer3, Answer4, CorrectAnswer);
                list.add(quiz);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Quiz getQuiz(String QuestionID) {
        String sql = "select * from Quiz\n"
                + "where QuestionID = " + QuestionID;
        // get data
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String question = result.getString(2);
                int LessonID = result.getInt(3);
                String Answer1 = result.getString(4);
                String Answer2 = result.getString(5);
                String Answer3 = result.getString(6);
                String Answer4 = result.getString(7);
                int CorrectAnswer = result.getInt(8);
                Quiz quiz = new Quiz(Integer.parseInt(QuestionID), question, LessonID, Answer1, Answer2, Answer3, Answer4, CorrectAnswer);
                return quiz;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int AddQuestion(Quiz quiz) {
        String sql = "INSERT INTO [dbo].[Quiz]\n"
                + "           ([Question]\n"
                + "           ,[LessonID]\n"
                + "           ,[Answer1]\n"
                + "           ,[Answer2]\n"
                + "           ,[Answer3]\n"
                + "           ,[Answer4]\n"
                + "           ,[AnswerCorrect])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, quiz.getQuestion());
            prepare.setInt(2, quiz.getLessonID());
            prepare.setString(3, quiz.getAnswer1());
            prepare.setString(4, quiz.getAnswer2());
            prepare.setString(5, quiz.getAnswer3());
            prepare.setString(6, quiz.getAnswer4());
            prepare.setInt(7, quiz.getCorrectAnswer());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdateQuestion(Quiz quiz) {
        String sql = "UPDATE [dbo].[Quiz]\n"
                + "   SET [Question] = ?\n"
                + "      ,[LessonID] = ?\n"
                + "      ,[Answer1] = ?\n"
                + "      ,[Answer2] = ?\n"
                + "      ,[Answer3] = ?\n"
                + "      ,[Answer4] = ?\n"
                + "      ,[AnswerCorrect] = ?\n"
                + " WHERE QuestionID = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, quiz.getQuestion());
            prepare.setInt(2, quiz.getLessonID());
            prepare.setString(3, quiz.getAnswer1());
            prepare.setString(4, quiz.getAnswer2());
            prepare.setString(5, quiz.getAnswer3());
            prepare.setString(6, quiz.getAnswer4());
            prepare.setInt(7, quiz.getCorrectAnswer());
            prepare.setInt(8, quiz.getQuestionID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
