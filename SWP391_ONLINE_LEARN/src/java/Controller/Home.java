package Controller;

import Entity.Account;
import Entity.Course;
import Model.DAOCourse;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Home extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        DAOCourse dao = new DAOCourse();
        Account account = (Account) session.getAttribute("account");
        // if not login or login as student or teacher
        if (account == null || !account.getRoleName().equals(Account.ROLE_ADMIN)) {
            List<Course> listPrice = dao.getTop3CoursesByPrice();
            request.setAttribute("listPrice", listPrice);
            // if login
            if (account != null) {
                List<Course> listUser = dao.getListCourse(account.getRoleName(), account.getUsername());
                request.setAttribute("listUser", listUser);
            }
            Dispatcher.forward(request, response, "index.jsp");
        } else {
            response.sendRedirect("ManagerAccount");
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
