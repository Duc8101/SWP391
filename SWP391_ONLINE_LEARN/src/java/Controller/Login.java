package Controller;

import Entity.Account;
import Model.DAOAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    private final DAOAccount dao = new DAOAccount();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = getUsername(request);
        if (username == null) {
            Dispatcher.forward(request, response, "login.jsp");
        } else {
            Account account = dao.getAccount(username);
            // if account not exist
            if (account == null) {
                Dispatcher.forward(request, response, "login.jsp");
            } else {
                session.setAttribute("account", account);
                if (account.getRoleName().equals(Account.ROLE_ADMIN)) {
                    response.sendRedirect("ManagerAccount");
                } else {
                    response.sendRedirect("Home");
                }
            }

        }
    }

    private String getUsername(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        // if not exist username in cookie
        if (cookie == null) {
            return null;
        }
        for (Cookie cook : cookie) {
            // if exist username
            if (cook.getName().equals("username")) {
                return cook.getValue();
            }
        }
        return null;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        Account account = dao.getAccount(username);
        // if login failed
        if (account == null || !account.getPassword().equals(password)) {
            request.setAttribute("message", "Username or password incorrect!");
            Dispatcher.forward(request, response, "login.jsp");
        } else {
            // if choose remember
            if (remember != null) {
                Cookie userCookie = new Cookie("username", username);
                // set time 
                userCookie.setMaxAge(24 * 3600);
                // store username on browser
                response.addCookie(userCookie);
            }
            session.setAttribute("account", account);
            if (account.getRoleName().equals(Account.ROLE_ADMIN)) {
                response.sendRedirect("ManagerAccount");
            } else {
                response.sendRedirect("Home");
            }
        }
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
