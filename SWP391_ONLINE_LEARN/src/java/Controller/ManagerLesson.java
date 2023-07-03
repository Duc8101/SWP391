package Controller;

import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerLesson extends HttpServlet {

    private final DAOLesson daoLesson = new DAOLesson();
    private final DAOLessonPDF daoPDF = new DAOLessonPDF();
    private final DAOLessonVideo daoVideo = new DAOLessonVideo();
    private final DAOQuiz daoQuiz = new DAOQuiz();
    private final DAOResult daoResult = new DAOResult();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String service = request.getParameter("service") == null ? "ViewLesson" : request.getParameter("service");
        String LessonID = request.getParameter("LessonID") == null ? "0" : request.getParameter("LessonID");
        if (service.equals("ViewLesson")) {
            String CourseID = request.getParameter("CourseID");
            int courseID;
            // if viewing lesson
            if (CourseID == null) {
                courseID = (int) session.getAttribute("CourseID");
            } else {
                courseID = Integer.parseInt(CourseID);
                session.setAttribute("CourseID", courseID);
            }
            String video = request.getParameter("video");
            String name = request.getParameter("name");
            String PDF = request.getParameter("PDF");
            String message = request.getParameter("message");
            String mess = request.getParameter("mess");
            List<Lesson> listLesson = daoLesson.GetListLesson(courseID);
            List<LessonPDF> listPDF = daoPDF.GetAllLessonPDF();
            List<LessonVideo> listVideo = daoVideo.GetAllLessonVideo();
            // if start to view lesson
            if (video == null && PDF == null) {
                // get first video of first lesson in course
                LessonVideo lessonVideo = this.daoVideo.getFirstVideo(courseID);
                // if get data successful
                if (lessonVideo != null) {
                    video = lessonVideo.getFileVideo();
                    name = lessonVideo.getVideoName();
                    LessonID = lessonVideo.getLessonID() + "";
                }
            }
            int lessonID = Integer.parseInt(LessonID);
            request.setAttribute("listLesson", listLesson);
            request.setAttribute("listVideo", listVideo);
            request.setAttribute("listPDF", listPDF);
            request.setAttribute("video", video);
            request.setAttribute("PDF", PDF);
            request.setAttribute("name", name);
            request.setAttribute("lID", lessonID);
            request.setAttribute("message", message);
            request.setAttribute("mess", mess);
            Dispatcher.forward(request, response, "ViewLesson.jsp");
        }

        if (service.equals("DeleteLesson")) {
            int lessonID = Integer.parseInt(LessonID);
            Lesson lesson = daoLesson.getLesson(lessonID);
            this.daoResult.DeleteResult(lessonID);
            daoQuiz.DeleteQuiz("LessonID", lessonID);
            daoVideo.DeleteVideo("LessonID", lessonID);
            daoPDF.DeletePDF("LessonID", lessonID);
            int number = daoLesson.DeleteLesson(lessonID);
            int CourseID = (int) session.getAttribute("CourseID");
            List<Lesson> list = daoLesson.GetListLesson(CourseID);
            for (int i = 0; i < list.size(); i++) {
                // if find lesson having lesson no bigger than lesson no deleted
                if (list.get(i).getLessonNo() > lesson.getLessonNo()) {
                    daoLesson.UpdateLessonNo(list.get(i).getLessonNo() - 1, list.get(i).getLessonID());
                }
            }
            // if delete successful
            if (number > 0) {
                String mess = "Remove lesson successfully";
                response.sendRedirect("ManagerLesson?mess=" + mess);
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
        int CourseID = (int) session.getAttribute("CourseID");
        String NewLessonName = request.getParameter("LessonName").trim();
        if (service.equals("AddLesson")) {
            if (NewLessonName.isEmpty()) {
                String message = "You have to input lesson name";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (daoLesson.CheckLessonExist(NewLessonName, CourseID)) {
                String message = "Lesson '" + NewLessonName + "' has existed in course";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else {
                List<Lesson> listLesson = daoLesson.GetListLesson(CourseID);
                int LessonNo = listLesson.get(listLesson.size() - 1).getLessonNo() + 1;
                Lesson lesson = new Lesson(NewLessonName, CourseID, LessonNo);
                int number = daoLesson.AddLesson(lesson);
                // if add successful
                if (number > 0) {
                    String mess = "Added Lesson " + NewLessonName + " Successfully!";
                    response.sendRedirect("ManagerLesson?mess=" + mess);
                }
            }
        } else {
            String LessonID = request.getParameter("LessonID");
            String OldLessonName = request.getParameter("OldName");
            int lessonID = Integer.parseInt(LessonID);
            if (NewLessonName.isEmpty()) {
                String message = "You have to input lesson name";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (!NewLessonName.equalsIgnoreCase(OldLessonName) && daoLesson.CheckLessonExist(NewLessonName, CourseID)) {
                String message = "Lesson '" + NewLessonName + "' has existed in course";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else {
                int number = this.daoLesson.UpdateLessonName(NewLessonName, lessonID);
                // if update successful
                if (number > 0) {
                    String mess = "Update Lesson Successfully!";
                    response.sendRedirect("ManagerLesson?mess=" + mess);
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
