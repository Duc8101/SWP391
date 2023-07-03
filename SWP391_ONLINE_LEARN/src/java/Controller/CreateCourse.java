package Controller;

import Entity.Account;
import Entity.Course;
import Entity.Teacher;
import Model.DAOCourse;
import Model.DAOTeacher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateCourse extends HttpServlet {

    private final DAOCourse daoCourse = new DAOCourse();
    private final DAOTeacher daoTeacher = new DAOTeacher();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dispatcher.forward(request, response, "CreateCourse.jsp");
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
        Account account = (Account) session.getAttribute("account");
        Teacher teacher = daoTeacher.getTeacher(account.getUsername());
        String CourseName = request.getParameter("CourseName").trim();
        String Price = request.getParameter("price");
        double price = Double.parseDouble(Price);
        String CategoryID = request.getParameter("category");
        int catID = Integer.parseInt(CategoryID);
        String image = request.getParameter("image").trim();
        String description = request.getParameter("description").trim();
        if (CourseName.isEmpty()) {
            request.setAttribute("message", "You have to input course name");
        } else if (daoCourse.CheckCourseName(CourseName)) {
            request.setAttribute("message", "Course name existed");
        } else if (price <= 0) {
            request.setAttribute("message", "Price must be greater than 0");
        } else {
            Course course = new Course(CourseName, image, price, catID, teacher.getID(), description.isEmpty() ? null : description);
            int number = daoCourse.AddCourse(course);
            // if add successful
            if (number > 0) {
                request.setAttribute("mess", "Create successful");
            }
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
