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

public class CoursesServlet extends HttpServlet {

    private final DAOCourse daoCourse = new DAOCourse();
    private final DAOLesson daoLesson = new DAOLesson();
    private final DAOStudent daoStudent = new DAOStudent();
    private final DAOPayCourse daoPay = new DAOPayCourse();
    private final DAOLessonPDF daoPDF = new DAOLessonPDF();
    private final DAOLessonVideo daoVideo = new DAOLessonVideo();
    private final DAOQuiz daoQuiz = new DAOQuiz();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String service = request.getParameter("service") == null ? "DisplayCourse" : request.getParameter("service");
        if (service.equals("DisplayCourse")) {
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            String properties = request.getParameter("properties");
            String flow = request.getParameter("flow");
            String CategoryID = request.getParameter("CategoryID") == null ? "0" : request.getParameter("CategoryID");
            int CatID = Integer.parseInt(CategoryID);
            List<Course> listCoursePage = daoCourse.getListCourse(CatID, page, properties, flow);
            int numberPage = daoCourse.getNumberPage(CatID, 0);
            // if login
            if (account != null) {
                List<Course> listCourseUser = daoCourse.getListCourse(account.getRoleName(), account.getUsername());
                request.setAttribute("listUser", listCourseUser);
            }
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL;
            String nextURL;
            // if not sort
            if (properties == null && flow == null) {
                // if not choose category
                if (CatID == 0) {
                    preURL = "Courses" + "?page=" + prePage;
                    nextURL = "Courses" + "?page=" + nextPage;
                } else {
                    preURL = "Courses" + "?CategoryID=" + CategoryID + "&page=" + prePage;
                    nextURL = "Courses" + "?CategoryID=" + CategoryID + "&page=" + nextPage;
                }
            } else {
                // if not choose category
                if (CatID == 0) {
                    preURL = "Courses" + "?properties=" + properties + "&flow=" + flow + "&page=" + prePage;
                    nextURL = "Courses" + "?properties=" + properties + "&flow=" + flow + "&page=" + nextPage;
                } else {
                    preURL = "Courses" + "?CategoryID=" + CategoryID + "&properties=" + properties + "&flow=" + flow + "&page=" + prePage;
                    nextURL = "Courses" + "?CategoryID=" + CategoryID + "&properties=" + properties + "&flow=" + flow + "&page=" + nextPage;
                }
            }
            request.setAttribute("pageSelected", page);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("listPage", listCoursePage);
            request.setAttribute("properties", properties);
            request.setAttribute("flow", flow);
            request.setAttribute("CategoryID", CatID);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            Dispatcher.forward(request, response, "/View/Courses/Index.jsp");
        }

        if (service.equals("Detail")) {
            // if login
            if (account != null) {
                List<Course> listCourseUser = daoCourse.getListCourse(account.getRoleName(), account.getUsername());
                request.setAttribute("listUser", listCourseUser);
            }
            String CourseID = request.getParameter("CourseID");
            int courseID = Integer.parseInt(CourseID);
            Course course = daoCourse.getCourse(courseID);
            // if not find course
            if (course == null) {
                response.sendRedirect("Courses");
            } else {
                List<Lesson> listLesson = daoLesson.getListLesson(courseID);
                request.setAttribute("course", course);
                request.setAttribute("listLesson", listLesson);
                Dispatcher.forward(request, response, "/View/Courses/Detail.jsp");
            }
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
                Pay_Course pay = this.daoPay.getPayCourse(courseID, account.getUsername());
                // if not find student or not find course or student already pay course
                if (student == null || course == null || pay != null) {
                    response.sendRedirect("Courses");
                } else {
                    request.setAttribute("course", course);
                    request.setAttribute("student", student);
                    Dispatcher.forward(request, response, "/View/Courses/RegisterCourse.jsp");
                }
            }
        }

        if (service.equals("Checkout")) {
            // if not login
            if (account == null) {
                response.sendRedirect("Login");
            } else {
                String CourseID = request.getParameter("CourseID");
                int courseID = Integer.parseInt(CourseID);
                Student student = daoStudent.getStudent(account.getUsername());
                Course course = daoCourse.getCourse(courseID);
                Pay_Course pay = this.daoPay.getPayCourse(courseID, account.getUsername());
                // if not find student or not find course or student already pay course
                if (student == null || course == null || pay != null) {
                    response.sendRedirect("Courses");
                } else {
                    pay = new Pay_Course(courseID, student.getID());
                    int number = daoPay.AddPayCourse(pay);
                    // if pay successful
                    if (number > 0) {
                        request.setAttribute("course", course);
                        request.setAttribute("student", student);
                        request.setAttribute("mess", "Checkout successful");
                        Dispatcher.forward(request, response, "/View/Courses/RegisterCourse.jsp");
                    }
                }
            }
        }

        if (service.equals("LearnCourse")) {
            // if not login
            if (account == null) {
                response.sendRedirect("Login");
            } else {
                String CourseID = request.getParameter("CourseID");
                Integer courseID;
                // if learning course
                if (CourseID == null) {
                    courseID = (Integer) session.getAttribute("CourseID");
                } else {
                    courseID = Integer.parseInt(CourseID);
                    Pay_Course pay = this.daoPay.getPayCourse(courseID, account.getUsername());
                    // if not pay course
                    if (pay == null) {
                        response.sendRedirect("Courses");
                    } else {
                        session.setAttribute("CourseID", courseID);
                    }
                }
                // if not set value for course id
                if (courseID == null) {
                    response.sendRedirect("Courses");
                } else {
                    //file video
                    String video = request.getParameter("video");
                    //video name or pdf name
                    String name = request.getParameter("name");
                    //file PDF
                    String PDF = request.getParameter("PDF");
                    String LessonID = request.getParameter("LessonID") == null ? "0" : request.getParameter("LessonID");
                    int lessonID = Integer.parseInt(LessonID);
                    String VideoID = request.getParameter("VideoID") == null ? "0" : request.getParameter("VideoID");
                    int videoID = Integer.parseInt(VideoID);
                    String PDFID = request.getParameter("PDFID") == null ? "0" : request.getParameter("PDFID");
                    int pdfID = Integer.parseInt(PDFID);
                    List<Lesson> listLesson = daoLesson.getListLesson(courseID);
                    List<LessonPDF> listPDF = daoPDF.getAllPDF();
                    // get all lesson exist quiz
                    List<Integer> listLessonQuiz = daoQuiz.getAllLesson();
                    List<LessonVideo> listVideo = daoVideo.getAllVideo();
                    // if start to learn course
                    if (video == null && PDF == null) {
                        // get first video of first lesson
                        LessonVideo lessonVideo = daoVideo.getFirstVideo(courseID);
                        // if exist video
                        if (lessonVideo != null) {
                            video = lessonVideo.getFileVideo();
                            name = lessonVideo.getVideoName();
                            videoID = lessonVideo.getVideoID();
                            lessonID = lessonVideo.getLessonID();
                        }
                    }
                    request.setAttribute("listLesson", listLesson);
                    request.setAttribute("listVideo", listVideo);
                    request.setAttribute("listPDF", listPDF);
                    request.setAttribute("listLessonQuiz", listLessonQuiz);
                    request.setAttribute("video", video);
                    request.setAttribute("PDF", PDF);
                    request.setAttribute("name", name);
                    request.setAttribute("vID", videoID);
                    request.setAttribute("lID", lessonID);
                    request.setAttribute("pID", pdfID);
                    Dispatcher.forward(request, response, "/View/Courses/LearnCourse.jsp");
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
