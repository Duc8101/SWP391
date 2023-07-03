package Controller;

import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerAccount extends HttpServlet {

    private final DAOAccount daoAccount = new DAOAccount();
    private final DAOResult daoResult = new DAOResult();
    private final DAOStudent daoStudent = new DAOStudent();
    private final DAOPayCourse daoPay = new DAOPayCourse();
    private final DAOTeacher daoTeacher = new DAOTeacher();
    private final DAOCourse daoCourse = new DAOCourse();
    private final DAOQuiz daoQuiz = new DAOQuiz();
    private final DAOLesson daoLesson = new DAOLesson();
    private final DAOLessonPDF daoPDF = new DAOLessonPDF();
    private final DAOLessonVideo daoVideo = new DAOLessonVideo();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service") == null ? "DisplayAccount" : request.getParameter("service");
        if (service.equals("DisplayAccount")) {
            List<Account> list = daoAccount.GetListAccount("");
            request.setAttribute("list", list);
            Dispatcher.forward(request, response, "DisplayAccount.jsp");
        }

        if (service.equals("RemoveAccount")) {
            String username = request.getParameter("username");
            Account account = daoAccount.getAccount(username);
            if (account.getRoleName().equals(Account.ROLE_STUDENT)) {
                daoResult.DeleteResult(username);
                Student student = daoStudent.getStudent(username);
                daoPay.DeletePayCourse(student.getID());
                daoStudent.DeleteStudent(username);
                int number = daoAccount.DeleteAccount(username);
                // if delete successful
                if (number > 0) {
                    List<Account> list = daoAccount.GetListAccount("");
                    request.setAttribute("list", list);
                    request.setAttribute("mess", "Remove successful");
                    Dispatcher.forward(request, response, "DisplayAccount.jsp");
                }
            } else {
                List<Course> listCourse = daoCourse.getListCourse(account.getRoleName(), username);
                List<Lesson> listLesson = daoLesson.GetListLesson(listCourse);
                for (Lesson lesson : listLesson) {
                    daoQuiz.DeleteQuiz("LessonID", lesson.getLessonID());
                    daoPDF.DeletePDF("LessonID", lesson.getLessonID());
                    daoVideo.DeleteVideo("LessonID", lesson.getLessonID());
                    daoLesson.DeleteLesson(lesson.getLessonID());
                }
                Teacher teacher = daoTeacher.getTeacher(username);
                daoCourse.DeleteCourse("TeacherID", teacher.getID());
                daoTeacher.DeleteTeacher(username);
                int number = daoAccount.DeleteAccount(username);
                // if delete successful
                if (number > 0) {
                    request.setAttribute("mess", "Remove successful");
                    List<Account> listAccount = daoAccount.GetListAccount("");
                    request.setAttribute("list", listAccount);
                    Dispatcher.forward(request, response, "DisplayAccount.jsp");
                }
            }
        }

        if (service.equals("EditAccount")) {
            String username = request.getParameter("username");
            Account account = daoAccount.getAccount(username);
            String STT = request.getParameter("STT");
            request.setAttribute("STT", STT);
            request.setAttribute("account", account);
            Dispatcher.forward(request, response, "EditAccount.jsp");
        }

        if (service.equals("ViewAccount")) {
            String username = request.getParameter("username");
            String STT = request.getParameter("STT");
            Account account = daoAccount.getAccount(username);
            request.setAttribute("username", username);
            request.setAttribute("STT", STT);
            if (account.getRoleName().equals(Account.ROLE_STUDENT)) {
                Student student = daoStudent.getStudent(username);
                request.setAttribute("student", student);
                Dispatcher.forward(request, response, "ViewAccountStudent.jsp");
            } else {
                Teacher teacher = daoTeacher.getTeacher(username);
                request.setAttribute("teacher", teacher);
                Dispatcher.forward(request, response, "ViewAccountTeacher.jsp");
            }
        }

        if (service.equals("SearchAccount")) {
            String name = request.getParameter("name").trim();
            List<Account> list = daoAccount.GetListAccount(name);
            request.setAttribute("name", name);
            // if not found
            if (list.isEmpty()) {
                request.setAttribute("message", "not found");
            } else {
                request.setAttribute("list", list);
            }
            Dispatcher.forward(request, response, "DisplayAccount.jsp");
        }

        if (service.equals("CreateAccount")) {
            Dispatcher.forward(request, response, "CreateAccount.jsp");
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
        String service = request.getParameter("service");
        if (service.equals("EditAccount")) {
            String username = request.getParameter("username").trim();
            String role = request.getParameter("role");
            String roleEdit = request.getParameter("roleEdit");
            // if change student to teacher
            if (roleEdit.equals(Account.ROLE_TEACHER) && role.equals(Account.ROLE_STUDENT)) {
                daoResult.DeleteResult(username);
                Student student = daoStudent.getStudent(username);
                daoPay.DeletePayCourse(student.getID());
                daoStudent.DeleteStudent(username);
                daoAccount.UpdateRole(roleEdit, username);
                Teacher teacher = new Teacher(student.getFullName(), student.getPhone(), student.getImage(), student.getEmail(), student.getGender(), username);
                int number = daoTeacher.AddTeacher(teacher);
                // if add successful
                if (number > 0) {
                    List<Account> list = daoAccount.GetListAccount("");
                    request.setAttribute("list", list);
                    request.setAttribute("mess", "Update successful");
                    Dispatcher.forward(request, response, "DisplayAccount.jsp");
                }
            } else {
                List<Account> list = daoAccount.GetListAccount("");
                request.setAttribute("list", list);
                request.setAttribute("mess", "Update successful");
                Dispatcher.forward(request, response, "DisplayAccount.jsp");
            }
        } else {
            String FullName = request.getParameter("FullName").trim();
            String phone = request.getParameter("phone");
            String email = request.getParameter("email").trim();
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
                account = new Account(username, password, Account.ROLE_TEACHER);
                daoAccount.AddAccount(account);
                Teacher teacher = new Teacher(FullName, phone.isEmpty() ? null : phone, Teacher.AVATAR, email, gender, username);
                int number = daoTeacher.AddTeacher(teacher);
                // if create successful
                if (number > 0) {
                    request.setAttribute("mess", "Create Successful");
                }
            }
            Dispatcher.forward(request, response, "CreateAccount.jsp");
        }
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
