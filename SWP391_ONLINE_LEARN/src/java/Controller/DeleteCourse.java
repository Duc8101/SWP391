package Controller;

import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCourse extends HttpServlet {

    private final DAOCourse daoCourse = new DAOCourse();
    private final DAOPayCourse daoPay = new DAOPayCourse();
    private final DAOQuiz daoQuiz = new DAOQuiz();
    private final DAOLesson daoLesson = new DAOLesson();
    private final DAOLessonPDF daoPDF = new DAOLessonPDF();
    private final DAOLessonVideo daoVideo = new DAOLessonVideo();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String CourseID = request.getParameter("CourseID");
        int courseID = Integer.parseInt(CourseID);
        Course course = this.daoCourse.getCourse(courseID);
        List<Lesson> list = daoLesson.GetListLesson(courseID);
        for (Lesson lesson : list) {
            daoQuiz.DeleteQuiz("LessonID", lesson.getLessonID());
            daoPDF.DeletePDF("LessonID", lesson.getLessonID());
            daoVideo.DeleteVideo("LessonID", lesson.getLessonID());
            daoLesson.DeleteLesson(lesson.getLessonID());
        }
        int number = daoCourse.DeleteCourse("CourseID", courseID);
        // if delete successful
        if (number > 0) {
            String mess = "Course name : '" + course.getCourseName() + "' delete successful";
            response.sendRedirect("MyTeachingCourse?mess=" + mess);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
