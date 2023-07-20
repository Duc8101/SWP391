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
        return this.getList(sql);
    }

    public List<Course> getListCourse(String role, String username) {
        if (role == null || (!role.equals(ConstValue.ROLE_STUDENT) && !role.equals(ConstValue.ROLE_TEACHER))) {
            return new ArrayList<>();
        }
        String sql;
        if (role.equals(ConstValue.ROLE_STUDENT)) {
            sql = "SELECT c.* FROM ( [dbo].[Course] c join [dbo].[Pay_Course] p\n"
                    + "on c.CourseID = p.CourseID ) join [dbo].[Student] s on p.StudentID = s.ID\n"
                    + "where s.username = '" + username + "'";
        } else {
            sql = "SELECT c.* FROM [dbo].[Course] c join [dbo].[Teacher] t\n"
                    + "on c.TeacherID = t.ID\n"
                    + "where t.username = '" + username + "'";
        }
        List<Course> listCourse = this.getList(sql);
        return listCourse;
    }

    public Course getCourse(int CourseID) {
        String sql = "SELECT * FROM [dbo].[Course]\n"
                + "WHERE [CourseID] = " + CourseID;
        return this.getList(sql).isEmpty() ? null : this.getList(sql).get(0);
    }

    public int getNumberPage(int CategoryID, int TeacherID) {
        String sql = "select * from [dbo].[Course]\n";
        // if not teacher course
        if (TeacherID == 0) {
            // if choose category
            if (CategoryID != 0) {
                sql = sql + "where [CategoryID] = " + CategoryID;
            }
        } else {
            sql = sql + "where [TeacherID] = " + TeacherID;
        }
        double number = this.getList(sql).size();
        if (number <= ConstValue.MAX_COURSE_IN_PAGE) {
            number = 1;
        } else if ((number / ConstValue.MAX_COURSE_IN_PAGE) > (Math.round(number / ConstValue.MAX_COURSE_IN_PAGE))) {
            number = Math.floor(number / ConstValue.MAX_COURSE_IN_PAGE) + 1;
        } else {
            number = Math.round(number / ConstValue.MAX_COURSE_IN_PAGE);
        }
        return (int) number;
    }

    public List<Course> getListCourse(int CategoryID, int page, String properties, String flow) {
        String sql = "SELECT * FROM [dbo].[Course]\n";
        if (CategoryID != 0) {
            sql = sql + "where [CategoryID] = " + CategoryID + "\n";
        }
        // if not sort
        if (properties == null && flow == null) {
            sql = sql + "order by [CourseID]\n";
        } else {
            sql = sql + "order by [" + properties + "] " + flow + "\n";
        }
        sql = sql + "	offset (" + ConstValue.MAX_COURSE_IN_PAGE + "*" + (page - 1) + ") row fetch next " + ConstValue.MAX_COURSE_IN_PAGE + " row only";
        return this.getList(sql);
    }
}
