package Controller;

import Entity.Account;
import Model.DAOAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dispatcher.forward(request, response, "ChangePassword.jsp");//
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
        DAOAccount dao = new DAOAccount();
        Account account = (Account) session.getAttribute("account");
        String OldPassword = request.getParameter("old");
        String NewPassword = request.getParameter("new");
        String ComfirmPassword = request.getParameter("confirm");
        if (!account.getPassword().equals(OldPassword)) {
            request.setAttribute("message", "Your old password not correct");
        } else if (!NewPassword.equals(ComfirmPassword)) {
            request.setAttribute("message", "Your confirm password not the same new password");
        } else if (NewPassword.length() > 50) {
            request.setAttribute("message", "Password max 50 character");
        } else {
            int number = dao.UpdatePassword(account.getUsername(), NewPassword);
            // if update successful
            if (number > 0) {
                request.setAttribute("mess", "Change successful!");
            }
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
