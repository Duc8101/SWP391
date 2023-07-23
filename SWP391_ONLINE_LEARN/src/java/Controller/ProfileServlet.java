package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet {

    private final DAOStudent daoStudent = new DAOStudent();
    private final DAOTeacher daoTeacher = new DAOTeacher();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account.getRoleName().equals(ConstValue.ROLE_STUDENT)) {
            Student student = daoStudent.getStudent(account.getUsername());
            if (student == null) {
                response.sendRedirect("Home");
            } else {
                request.setAttribute("student", student);
                Dispatcher.forward(request, response, "/View/Profile/Student.jsp");
            }
        } else {
            Teacher teacher = this.daoTeacher.getTeacher(account.getUsername());
            if (teacher == null) {
                response.sendRedirect("Home");
            } else {
                request.setAttribute("teacher", teacher);
                Dispatcher.forward(request, response, "/View/Profile/Teacher.jsp");
            }
        }
    }

    private boolean isValidDOB(String DOB) {
        //use class SimpleDateFormat to initialize format date is yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date DateCurrent = new Date();
        try {
            //convert string to date
            Date date = sdf.parse(DOB);
            // if date in the future
            if (date.after(DateCurrent)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String FullName = request.getParameter("FullName").trim();
        String phone = request.getParameter("phone").isEmpty() ? null : request.getParameter("phone");
        String image = request.getParameter("image");
        String email = request.getParameter("email").trim();
        String gender = request.getParameter("gender");
        // if not choose file
        if (image == null || image.isEmpty()) {
            image = request.getParameter("valueImg");
        } else {
            if (image.endsWith(".jpg") || image.endsWith(".png")) {
                if (!image.startsWith("./img/")) {
                    image = "resources/img/" + image;
                }
            }
        }
        if (account.getRoleName().equals(ConstValue.ROLE_STUDENT)) {
            String address = request.getParameter("address").isEmpty() ? null : request.getParameter("address").trim();
            String DOB = request.getParameter("DOB").isEmpty() ? null : request.getParameter("DOB");
            Student student = new Student(FullName, phone, address, image, email, gender, account.getUsername(), DOB);
            request.setAttribute("student", student);
            if (FullName.isEmpty()) {
                request.setAttribute("message", "You have to input your name");
            } else if (phone != null && !phone.matches(ConstValue.FORMAT_PHONE)) {
                request.setAttribute("message", "Phone only number and length is " + ConstValue.LENGTH_PHONE);
            } else if (DOB != null && !isValidDOB(DOB)) {
                request.setAttribute("message", "Date of birth not in the future");
            } else {
                int number = daoStudent.UpdateStudent(student);
                // if update successful
                if (number > 0) {
                    request.setAttribute("mess", "Update successful");
                }
            }
            Dispatcher.forward(request, response, "/View/Profile/Student.jsp");
        } else {
            Teacher teacher = new Teacher(FullName, phone, image, email, gender, account.getUsername());
            request.setAttribute("teacher", teacher);
            if (FullName.isEmpty()) {
                request.setAttribute("message", "You have to input your name");
            } else if (phone != null && !phone.matches(ConstValue.FORMAT_PHONE)) {
                request.setAttribute("message", "Phone only number and length is " + ConstValue.LENGTH_PHONE);
            } else {
                int number = daoTeacher.UpdateTeacher(teacher);
                // if update successful
                if (number > 0) {
                    request.setAttribute("mess", "Update successful");
                }
            }
            Dispatcher.forward(request, response, "/View/Profile/Teacher.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
