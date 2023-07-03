package Controller;

import Entity.Account;
import Entity.Course;
import Entity.Teacher;
import Model.DAOCourse;
import Model.DAOTeacher;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyTeachingCourse extends HttpServlet {

    private final DAOTeacher daoTeacher = new DAOTeacher();
    private final DAOCourse daoCourse = new DAOCourse();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Teacher teacher = daoTeacher.getTeacher(account.getUsername());
        int numberPage = daoCourse.GetNumberOfPage(null, teacher.getID());
        String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
        int page = Integer.parseInt(pageSelected);
        List<Course> list = daoCourse.GetListCourse(page, teacher.getID());
        String mess = request.getParameter("mess");
        request.setAttribute("pageSelected", page);
        request.setAttribute("numberOfPage", numberPage);
        request.setAttribute("list", list);
        request.setAttribute("mess", mess);
        Dispatcher.forward(request, response, "MyTeachingCourse.jsp");
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
