package Controller;

import Entity.Quiz;
import Model.DAOQuiz;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerQuiz extends HttpServlet {

    private final DAOQuiz dao = new DAOQuiz();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String service = request.getParameter("service") == null ? "DisplayQuiz" : request.getParameter("service");
        String mess = request.getParameter("mess");
        String LessonID = request.getParameter("LessonID");
        if (service.equals("DisplayQuiz")) {
            int lessonID = Integer.parseInt(LessonID);
            List<Quiz> list = dao.GetListQuiz(lessonID);
            session.setAttribute("LessonID", lessonID);
            request.setAttribute("list", list);
            request.setAttribute("mess", mess);
            Dispatcher.forward(request, response, "DisplayQuiz.jsp");
        }

        if (service.equals("ViewQuestion")) {
            String QuestionID = request.getParameter("QuestionID");
            Quiz quiz = dao.getQuiz(QuestionID);
            request.setAttribute("quiz", quiz);
            Dispatcher.forward(request, response, "ViewQuiz.jsp");
        }

        if (service.equals("CreateQuestion")) {
            Dispatcher.forward(request, response, "CreateQuestion.jsp");
        }

        if (service.equals("UpdateQuestion")) {
            String QuestionID = request.getParameter("QuestionID");
            Quiz quiz = dao.getQuiz(QuestionID);
            request.setAttribute("quiz", quiz);
            Dispatcher.forward(request, response, "UpdateQuestion.jsp");
        }

        if (service.equals("DeleteQuestion")) {
            int LessonId = (int) session.getAttribute("LessonID");
            String QuestionID = request.getParameter("QuestionID");
            int questionID = Integer.parseInt(QuestionID);
            int number = dao.DeleteQuiz("QuestionID", questionID);
            // if delete successful
            if (number > 0) {
                response.sendRedirect("ManagerQuiz?LessonID=" + LessonId + "&mess=Remove successful");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        HttpSession session = request.getSession();
        int LessonID = (int) session.getAttribute("LessonID");
        String question = request.getParameter("question").trim();
        String answer1 = request.getParameter("answer1").trim();
        String answer2 = request.getParameter("answer2").trim();
        String answer3 = request.getParameter("answer3").trim();
        String answer4 = request.getParameter("answer4").trim();
        String CorrectAnswer = request.getParameter("CorrectAnswer");
        int correct = Integer.parseInt(CorrectAnswer);
        if (service.equals("CreateQuestion")) {
            if (question.isEmpty()) {
                request.setAttribute("message", "You have to input question");
                Dispatcher.forward(request, response, "CreateQuestion.jsp");
            } else if (answer1.isEmpty() || answer2.isEmpty()) {
                request.setAttribute("message", "A question contains at least 2 answers. Answer 1 and answer 2 not empty");
                Dispatcher.forward(request, response, "CreateQuestion.jsp");
            } else {
                Quiz quiz = new Quiz(question, LessonID, answer1, answer2, answer3.isEmpty() ? null : answer3, answer4.isEmpty() ? null : answer4, correct);
                int number = dao.AddQuestion(quiz);
                // if add successful
                if (number > 0) {
                    String mess = "Create Successful";
                    response.sendRedirect("ManagerQuiz?LessonID=" + LessonID + "&mess=" + mess);
                }

            }
        } else {
            String QuestionID = request.getParameter("QuestionID");
            int questionID = Integer.parseInt(QuestionID);
            if (question.isEmpty()) {
                request.setAttribute("message", "You have to input question");
                Dispatcher.forward(request, response, "UpdateQuestion.jsp");
            } else if (answer1.isEmpty() || answer2.isEmpty()) {
                request.setAttribute("message", "A question contains at least 2 answers. Answer 1 and answer 2 not empty");
                Dispatcher.forward(request, response, "UpdateQuestion.jsp");
            } else {
                Quiz quiz = new Quiz(questionID, question, LessonID, answer1, answer2, answer3.isEmpty() ? null : answer3, answer4.isEmpty() ? null : answer4, correct);
                int number = dao.UpdateQuestion(quiz);
                // if update successful
                if (number > 0) {
                    String mess = "Update Successful";
                    response.sendRedirect("ManagerQuiz?LessonID=" + LessonID + "&mess=" + mess);
                }
            }

        }

        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
