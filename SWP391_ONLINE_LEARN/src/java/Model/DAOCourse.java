package Model;

import Const.ConstValue;
import Entity.*;
import java.sql.*;
import java.util.*;

public class DAOCourse extends ConnectDatabase {

    private List<Course> getList(String sql) {
        List<Course> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int CourseID = result.getInt(1);
                String CourseName = result.getString(2);
                String image = result.getString(3);
                double price = result.getDouble(4);
                int CategoryID = result.getInt(5);
                int TeacherID = result.getInt(6);
                String description = result.getString(7);
                Course course = new Course(CourseID, CourseName, image, price, CategoryID, TeacherID, description);
                list.add(course);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Course> getTop3CoursesByPrice() {
        String sql = "SELECT TOP 3 * FROM [dbo].[Course]\n"
                + "ORDER BY price DESC";
        List<Course> list = this.getList(sql);
        return list;
    }

    public List<Course> getListCourse(String role, String username) {
        DAOStudent daoStudent = new DAOStudent();
        DAOTeacher daoTeacher = new DAOTeacher();
        DAOPayCourse daoPay = new DAOPayCourse();
        List<Course> listCourse = new ArrayList<>();
        if (role.equals(ConstValue.ROLE_STUDENT)) {
            Student student = daoStudent.getStudent(username);
            // if find student successful
            if (student != null) {
                List<Pay_Course> listPay = daoPay.getListPay_Course(student.getID());
                for (Pay_Course pay : listPay) {
                    Course course = this.getCourse(pay.getCourseID());
                    // if find course successful
                    if (course != null) {
                        listCourse.add(course);
                    }
                }
            }
        } else {
            Teacher teacher = daoTeacher.getTeacher(username);
            // if find teacher successful
            if (teacher != null) {
                String sql = "SELECT * FROM [dbo].[Course]\n"
                        + "WHERE [TeacherID] = " + teacher.getID();
                listCourse = this.getList(sql);
            }
        }
        return listCourse;
    }

    public Course getCourse(int CourseID) {
        String sql = "SELECT * FROM [dbo].[Course]\n"
                + "WHERE [CourseID] = " + CourseID;
        List<Course> list = this.getList(sql);
        return list.isEmpty() ? null : list.get(0);
    }
}
