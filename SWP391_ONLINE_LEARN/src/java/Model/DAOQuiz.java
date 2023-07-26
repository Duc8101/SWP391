package Model;

import Entity.Quiz;
import java.sql.*;
import java.util.*;

public class DAOQuiz extends ConnectDatabase {

    // get all lesson exist quiz
    public List<Integer> getAllLesson() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT distinct LessonID FROM [dbo].[Quiz]";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int LessonID = result.getInt(1);
                list.add(LessonID);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    private List<Quiz> getList(String sql) {
        List<Quiz> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int QuestionID = result.getInt(1);
                String question = result.getString(2);
                int LessonID = result.getInt(3);
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

    public List<Quiz> getListQuiz(int LessonID) {
        String sql = "select * from Quiz\n"
                + "where LessonID = " + LessonID;
        return this.getList(sql);
    }

    public int DeleteQuiz(String column, int ID) {
        int number = 0;
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
}
