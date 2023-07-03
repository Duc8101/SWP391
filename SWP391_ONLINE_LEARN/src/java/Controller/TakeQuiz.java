package Controller;

import Entity.Account;
import Entity.Quiz;
import Entity.Result;
import Model.DAOQuiz;
import Model.DAOResult;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TakeQuiz extends HttpServlet {

    private final DAOQuiz daoQuiz = new DAOQuiz();
    private final DAOResult daoResult = new DAOResult();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int time = 5; // 5 minutes
        String LessonID = request.getParameter("LessonID");
        int lessonID = Integer.parseInt(LessonID);
        List<Quiz> list = daoQuiz.GetListQuiz(lessonID);
        request.setAttribute("quiz", list.get(0));
        request.setAttribute("LessonID", lessonID);
        request.setAttribute("minutes", time - 1);
        request.setAttribute("seconds", 59);
        request.setAttribute("question", 1);
        request.setAttribute("button", list.size() == 1 ? "Finish" : "Next");
        session.setAttribute("1", 0);
        Dispatcher.forward(request, response, "TakeQuiz.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String LessonID = request.getParameter("LessonID");
        int lessonID = Integer.parseInt(LessonID);
        List<Quiz> list = daoQuiz.GetListQuiz(lessonID);
        String QuestionNo = request.getParameter("question");
        int questionNo = Integer.parseInt(QuestionNo);
        String button = request.getParameter("button");
        String Answer = request.getParameter("answer") == null ? "0" : request.getParameter("answer");
        int answer = Integer.parseInt(Answer);
        String TimeOut = request.getParameter("timeOut");
        String minutes = request.getParameter("minutes");
        String seconds = request.getParameter("seconds");
        session.setAttribute(QuestionNo, answer);
        // if at final question and finish or time out
        if ((questionNo == list.size() && button != null && button.equals("Finish")) || TimeOut.equals("1")) {
            int score = 0;
            for (int i = 0; i < list.size(); i++) {
                String question = (i + 1) + "";
                Integer ans = (Integer) session.getAttribute(question);
                int ansInt = ans == null ? 0 : ans;
                // if choose correct answer
                if (ansInt == list.get(i).getCorrectAnswer()) {
                    score++;
                }
            }
            float FinalScore = ((float) score / list.size()) * 10;
            String status = FinalScore >= 5 ? "PASS" : "NOT PASS";
            Result result = new Result(lessonID, account.getUsername(), FinalScore, status);
            int number = daoResult.AddResult(result);
            // if add successful
            if (number > 0) {
                request.setAttribute("result", result);
                Dispatcher.forward(request, response, "Result.jsp");
            }
        } else {
            if (button != null && button.equals("Back")) {
                request.setAttribute("quiz", list.get(questionNo - 2));
                request.setAttribute("question", questionNo - 1);
                request.setAttribute("button", "Next");
            } else if (button != null && button.equals("Next")) {
                request.setAttribute("quiz", list.get(questionNo));
                request.setAttribute("question", questionNo + 1);
                request.setAttribute("button", questionNo + 1 == list.size() ? "Finish" : "Next");
                Integer ans = (Integer) session.getAttribute((questionNo + 1) + "");
                session.setAttribute((questionNo + 1) + "", ans == null ? 0 : ans);
            }
            request.setAttribute("LessonID", lessonID);
            request.setAttribute("minutes", Integer.parseInt(minutes));
            request.setAttribute("seconds", Integer.parseInt(seconds));
            Dispatcher.forward(request, response, "TakeQuiz.jsp");
        }
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
