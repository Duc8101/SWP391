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

public class ManagerCourseServlet extends HttpServlet {

    private final DAOTeacher daoTeacher = new DAOTeacher();
    private final DAOCourse daoCourse = new DAOCourse();
    private final DAOQuiz daoQuiz = new DAOQuiz();
    private final DAOLesson daoLesson = new DAOLesson();
    private final DAOLessonPDF daoPDF = new DAOLessonPDF();
    private final DAOLessonVideo daoVideo = new DAOLessonVideo();
    private final DAOPayCourse daoPay = new DAOPayCourse();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String service)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (service.equals("DisplayCourse")) {
            Teacher teacher = daoTeacher.getTeacher(account.getUsername());
            int numberPage = daoCourse.getNumberPage(0, teacher.getID());
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            String preURL = "ManagerCourse?page=" + (page - 1);
            String nextURL = "ManagerCourse?page=" + (page + 1);
            List<Course> list = daoCourse.getListCourse(page, teacher.getID());
            request.setAttribute("pageSelected", page);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("list", list);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            Dispatcher.forward(request, response, "/View/ManagerCourse/Index.jsp");
        }

        if (service.equals("CreateCourse")) {
            Dispatcher.forward(request, response, "/View/ManagerCourse/Create.jsp");
        }

        if (service.equals("EditCourse")) {
            String CourseID = request.getParameter("CourseID") == null ? "0" : request.getParameter("CourseID");
            int courseID = Integer.parseInt(CourseID);
            Teacher teacher = daoTeacher.getTeacher(account.getUsername());
            Course course = teacher == null ? null : this.daoCourse.getCourse(courseID, teacher.getID());
            // if not find course 
            if (course == null) {
                response.sendRedirect("ManagerCourse");
            } else {
                request.setAttribute("course", course);
                Dispatcher.forward(request, response, "/View/ManagerCourse/Edit.jsp");
            }
        }

        if (service.equals("DeleteCourse")) {
            String CourseID = request.getParameter("CourseID") == null ? "0" : request.getParameter("CourseID");
            int courseID = Integer.parseInt(CourseID);
            Teacher teacher = daoTeacher.getTeacher(account.getUsername());
            Course course = teacher == null ? null : this.daoCourse.getCourse(courseID, teacher.getID());
            // if not find course 
            if (course == null) {
                response.sendRedirect("ManagerCourse");
            } else if (this.daoPay.isExist(courseID)) {
                request.setAttribute("mess", "Course name : '" + course.getCourseName() + "' exists student learning");
                this.processRequest(request, response, "DisplayCourse");
            } else {
                List<Lesson> list = daoLesson.getListLesson(courseID);
                for (Lesson lesson : list) {
                    daoQuiz.DeleteQuiz("LessonID", lesson.getLessonID());
                    daoPDF.DeletePDF("LessonID", lesson.getLessonID());
                    daoVideo.DeleteVideo("LessonID", lesson.getLessonID());
                    daoLesson.DeleteLesson(lesson.getLessonID());
                }
                int number = daoCourse.DeleteCourse("CourseID", courseID);
                // if delete successful
                if (number > 0) {
                    request.setAttribute("mess", "Course name : '" + course.getCourseName() + "' deleted successful");
                    this.processRequest(request, response, "DisplayCourse");
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service") == null ? "DisplayCourse" : request.getParameter("service");
        processRequest(request, response, service);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String service = request.getParameter("service");
        Teacher teacher = daoTeacher.getTeacher(account.getUsername());
        String NewCourseName = request.getParameter("CourseName").trim();
        String Price = request.getParameter("price");
        double price = Double.parseDouble(Price);
        String CategoryID = request.getParameter("category");
        int catID = Integer.parseInt(CategoryID);
        String image = request.getParameter("image").trim();
        String description = request.getParameter("description").trim();
        if (service.equals("CreateCourse")) {
            if (NewCourseName.isEmpty()) {
                request.setAttribute("message", "You have to input course name");
            } else if (daoCourse.isExist(NewCourseName, teacher.getID())) {
                request.setAttribute("message", "Course name existed");
            } else if (price <= 0) {
                request.setAttribute("message", "Price must be greater than 0");
            } else {
                Course course = new Course(NewCourseName, image, price, catID, teacher.getID(), description.isEmpty() ? null : description);
                int number = daoCourse.AddCourse(course);
                // if add successful
                if (number > 0) {
                    request.setAttribute("mess", "Create successful");
                }
            }
            Dispatcher.forward(request, response, "/View/ManagerCourse/Create.jsp");
        } else {
            String CourseID = request.getParameter("CourseID");
            int courseID = Integer.parseInt(CourseID);
            String OldCourseName = request.getParameter("oldName");
            Course course = new Course(courseID, NewCourseName, image, price, catID, teacher.getID(), description.isEmpty() ? null : description);
            request.setAttribute("course", course);
            if (NewCourseName.isEmpty()) {
                request.setAttribute("message", "You have to input course name");
            } else if (daoCourse.isExist(NewCourseName, teacher.getID()) && !NewCourseName.equalsIgnoreCase(OldCourseName)) {
                request.setAttribute("message", "Course name existed");
            } else if (price <= 0) {
                request.setAttribute("message", "Price must be greater than 0");
            } else {
                int number = daoCourse.UpdateCourse(course);
                // if update successful
                if (number > 0) {
                    request.setAttribute("mess", "Update successful");
                }
            }
            Dispatcher.forward(request, response, "/View/ManagerCourse/Edit.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
