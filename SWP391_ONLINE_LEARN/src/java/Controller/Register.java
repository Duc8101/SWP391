package Controller;

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

public class Register extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            Dispatcher.forward(request, response, "RegisterAccount.jsp");            
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
        // if phone invalid
        if (!phone.isEmpty() && !phone.matches("^[0-9]{10}+$")) {
            request.setAttribute("message", "Phone only number and length is 10");
            // if username invalid
        } else if (username.contains(" ") || username.length() > 50) {
            request.setAttribute("message", "Username most 50 character and no space");
            // if password invalid
        } else if (password.length() > 50) {
            request.setAttribute("message", "Password most 50 character");
            // if username exist 
        } else if (account != null) {
            request.setAttribute("message", "Your account has existed");
        } else {
            account = new Account(username, password, Account.ROLE_STUDENT);
            daoAccount.AddAccount(account);
            Student student = new Student(FullName, phone.isEmpty() ? null : phone, address.isEmpty() ? null : address, Student.AVATAR, email, gender, username, null);
            int number = daoStudent.AddStudent(student);
            // if add successful
            if (number > 0) {
                request.setAttribute("mess", "Congratulation! Register Account successful");
            }
        }
        Dispatcher.forward(request, response, "RegisterAccount.jsp");
        //processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
