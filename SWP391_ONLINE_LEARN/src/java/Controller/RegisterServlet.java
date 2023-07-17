package Controller;

import Const.ConstValue;
import Entity.Account;
import Entity.Student;
import Model.DAOAccount;
import Model.DAOStudent;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            Dispatcher.forward(request, response, "/View/Register/Index.jsp");
        } else {
            response.sendRedirect("Home");
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
        DAOAccount daoAccount = new DAOAccount();
        DAOStudent daoStudent = new DAOStudent();
        String FullName = request.getParameter("FullName").trim();
        String phone = request.getParameter("phone");
        String email = request.getParameter("email").trim();
        String address = request.getParameter("address").trim();
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = daoAccount.getAccount(username);
        if (FullName.isEmpty()) {
            request.setAttribute("message", "You have to input your name");
            // if phone invalid
        } else if (!phone.isEmpty() && !phone.matches(ConstValue.FORMAT_PHONE)) {
            request.setAttribute("message", "Phone only number and length is " + ConstValue.LENGTH_PHONE);     
        } else if (!email.isEmpty() && !email.matches(ConstValue.FORMAT_EMAIL)) {
            request.setAttribute("message", "Invalid email");
            // if username invalid
        } else if (!username.matches(ConstValue.FORMAT_USERNAME) || username.length() > ConstValue.MAX_LENGTH_USERNAME) {
             request.setAttribute("message", "Username max " + ConstValue.MAX_LENGTH_USERNAME + " characters , starts with letters and contain only letter and digit");
            // if password invalid
        } else if (password.length() > ConstValue.MAX_LENGTH_PASSWORD) {
            request.setAttribute("message", "Password most 50 character");
            // if username exist 
        } else if (account != null) {
            request.setAttribute("message", "Your account has existed");
        } else {
            account = new Account(username, password, ConstValue.ROLE_STUDENT);
            daoAccount.AddAccount(account);
            Student student = new Student(FullName, phone.isEmpty() ? null : phone, address.isEmpty() ? null : address, ConstValue.AVATAR, email, gender, username, null);
            int number = daoStudent.AddStudent(student);
            // if add successful
            if (number > 0) {
                request.setAttribute("mess", "Congratulation! Register Account successful");
            }
        }
        Dispatcher.forward(request, response, "/View/Register/Index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
