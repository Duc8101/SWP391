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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String service = request.getParameter("service") == null ? "DisplayCourse" : request.getParameter("service");
        if (service.equals("DisplayCourse")) {
            Teacher teacher = daoTeacher.getTeacher(account.getUsername());
            int numberPage = daoCourse.getNumberPage(0, teacher.getID());
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            String preURL = "ManagerCourse?page=" + (page - 1);
            String nextURL = "ManagerCourse?page=" + (page + 1);
            List<Course> list = daoCourse.getListCourse(page, teacher.getID());
            String mess = request.getParameter("mess");
            request.setAttribute("pageSelected", page);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("list", list);
            request.setAttribute("mess", mess);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            Dispatcher.forward(request, response, "/View/ManagerCourse/Index.jsp");
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
