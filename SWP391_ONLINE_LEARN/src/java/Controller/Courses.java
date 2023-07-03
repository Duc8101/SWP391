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

public class Courses extends HttpServlet {

    private final DAOCourse daoCourse = new DAOCourse();
    private final DAOLesson daoLesson = new DAOLesson();
    private final DAOStudent daoStudent = new DAOStudent();
    private final DAOPayCourse daoPayCourse = new DAOPayCourse();
    private final DAOLessonPDF daoPDF = new DAOLessonPDF();
    private final DAOLessonVideo daoVideo = new DAOLessonVideo();
    private final DAOQuiz daoQuiz = new DAOQuiz();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String service = request.getParameter("service") == null ? "DisplayCourse" : request.getParameter("service");
        String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
        int page = Integer.parseInt(pageSelected);
        String properties = request.getParameter("properties");
        String flow = request.getParameter("flow");
        String CategoryID = request.getParameter("CategoryID") == null ? "0" : request.getParameter("CategoryID");
        int CatID = Integer.parseInt(CategoryID);
        List<Course> listCoursePage = daoCourse.GetListCourse(CatID, page, properties, flow);
        int numberPage = daoCourse.GetNumberOfPage(CategoryID, null);
        if (service.equals("DisplayCourse")) {
            // if login
            if (account != null) {
                List<Course> listCourseUser = daoCourse.getListCourse(account.getRoleName(), account.getUsername());
                request.setAttribute("listUser", listCourseUser);
            }
            request.setAttribute("pageSelected", page);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("listPage", listCoursePage);
            request.setAttribute("properties", properties);
            request.setAttribute("flow", flow);
            request.setAttribute("CategoryID", CatID);
            Dispatcher.forward(request, response, "DisplayCourse.jsp");
        }

        if (service.equals("Detail")) {
            // if login
            if (account != null) {
                List<Course> listCourseUser = daoCourse.getListCourse(account.getRoleName(), account.getUsername());
                request.setAttribute("listUser", listCourseUser);
            }
            String CourseID = request.getParameter("CourseID");
            int courseID = Integer.parseInt(CourseID);
            List<Lesson> listLesson = daoLesson.GetListLesson(courseID);
            Course course = daoCourse.getCourse(courseID);
            request.setAttribute("course", course);
            request.setAttribute("listLesson", listLesson);
            Dispatcher.forward(request, response, "DetailCourse.jsp");
        }

        if (service.equals("RegisterCourse")) {
            // if not login
            if (account == null) {
                response.sendRedirect("Login");
            } else {
                String CourseID = request.getParameter("CourseID");
                int courseID = Integer.parseInt(CourseID);
                Student student = daoStudent.getStudent(account.getUsername());
                Course course = daoCourse.getCourse(courseID);
                request.setAttribute("course", course);
                request.setAttribute("student", student);
                Dispatcher.forward(request, response, "RegisterCourse.jsp");
            }
        }

        if (service.equals("CheckOut")) {
            // if not login
            if (account == null) {
                response.sendRedirect("Login");
            } else {
                String CourseID = request.getParameter("CourseID");
                int courseID = Integer.parseInt(CourseID);
                Student student = daoStudent.getStudent(account.getUsername());
                Pay_Course pay = new Pay_Course(courseID, student.getID());
                int number = daoPayCourse.AddPayCourse(pay);
                // if check out successful
                if (number > 0) {
                    Course course = daoCourse.getCourse(courseID);
                    request.setAttribute("course", course);
                    request.setAttribute("student", student);
                    request.setAttribute("mess", "Checkout successful");
                    Dispatcher.forward(request, response, "RegisterCourse.jsp");
                }
            }
        }

        if (service.equals("LearnCourse")) {
            // if not login
            if (account == null) {
                response.sendRedirect("Login");
            } else {
                String CourseID = request.getParameter("CourseID");
                int courseID;
                // if learning course
                if (CourseID == null) {
                    courseID = (int) session.getAttribute("CourseID");
                } else {
                    courseID = Integer.parseInt(CourseID);
                    session.setAttribute("CourseID", courseID);
                }
                String video = request.getParameter("video");
                String name = request.getParameter("name");
                String PDF = request.getParameter("PDF");
                String LessonID = request.getParameter("LessonID");
                String VideoID = request.getParameter("VideoID") == null ? "0" : request.getParameter("VideoID");
                String PDFID = request.getParameter("PDFID") == null ? "0" : request.getParameter("PDFID");
                List<Lesson> listLesson = daoLesson.GetListLesson(courseID);
                List<LessonPDF> listPDF = daoPDF.GetAllLessonPDF();
                List<Integer> listLessonQuiz = daoQuiz.GetAllLessonOfQuiz();
                List<LessonVideo> listVideo = daoVideo.GetAllLessonVideo();
                // if start to learn course
                if (video == null && PDF == null) {
                    LessonVideo lessonVideo = daoVideo.getFirstVideo(courseID);
                    video = lessonVideo.getFileVideo();
                    name = lessonVideo.getVideoName();
                    VideoID = String.valueOf(lessonVideo.getVideoID());
                    LessonID = String.valueOf(lessonVideo.getLessonID());
                }
                int videoID = Integer.parseInt(VideoID);
                int lessonID = Integer.parseInt(LessonID);
                int PDFId = Integer.parseInt(PDFID);
                request.setAttribute("listLesson", listLesson);
                request.setAttribute("listVideo", listVideo);
                request.setAttribute("listPDF", listPDF);
                request.setAttribute("listLessonQuiz", listLessonQuiz);
                request.setAttribute("video", video);
                request.setAttribute("PDF", PDF);
                request.setAttribute("name", name);
                request.setAttribute("vID", videoID);
                request.setAttribute("lID", lessonID);
                request.setAttribute("pID", PDFId);
                Dispatcher.forward(request, response, "LearnCourse.jsp");
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
