package Model;

import Entity.Account;
import Entity.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOCourse extends ConnectDatabase {

    private static final int MAX_COURSE_IN_PAGE = 6;

    public List<Course> getTop3CoursesByPrice() {
        String sql = "SELECT TOP 3 * FROM [dbo].[Course]\n"
                + "ORDER BY price DESC";
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

    public List<Course> getListCourse(String role, String username) {
        String sql;
        if (role.equals(Account.ROLE_STUDENT)) {
            sql = "select distinct c.* from (Course c join Pay_Course pc \n"
                    + "on c.CourseID = pc.CourseID) join Student s\n"
                    + "on pc.StudentID = s.ID\n"
                    + "where s.username = '" + username + "'";
        } else {
            sql = "select distinct c.* from Course c join Teacher t \n"
                    + "on c.TeacherID = t.ID \n"
                    + "where t.username = '" + username + "'";
        }
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

    public List<Course> GetListCourse(int CategoryID, int page, String properties, String flow) {
        List<Course> list = new ArrayList<>();
        String sql;
        // if not sort
        if (properties == null && flow == null) {
            // if not choose category
            if (CategoryID == 0) {
                sql = "SELECT * FROM [dbo].[Course]\n"
                        + "	order by [CourseID]\n"
                        + "	offset (" + MAX_COURSE_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_COURSE_IN_PAGE + " row only";
            } else {
                sql = "SELECT * FROM [dbo].[Course]\n"
                        + "               where [CategoryID] = " + CategoryID + "\n"
                        + "	order by [CourseID]\n"
                        + "	offset (" + MAX_COURSE_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_COURSE_IN_PAGE + " row only";
            }
        } else {
            // if not choose category
            if (CategoryID == 0) {
                sql = "SELECT * FROM [dbo].[Course]\n"
                        + "	order by [" + properties + "] " + flow + "\n"
                        + "	offset (" + MAX_COURSE_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_COURSE_IN_PAGE + " row only";
            } else {
                sql = "SELECT * FROM [dbo].[Course]\n"
                        + "               where [CategoryID] = " + CategoryID + "\n"
                        + "	order by [" + properties + "] " + flow + "\n"
                        + "	offset (" + MAX_COURSE_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_COURSE_IN_PAGE + " row only";
            }
        }

        // get data
        ResultSet result = getData(sql);
        try {
            // loop for traverse all row
            while (result.next()) {
                int CourseID = result.getInt(1);
                String CourseName = result.getString(2);
                String image = result.getString(3);
                double price = result.getDouble(4);
                int CatID = result.getInt(5);
                int TeacherID = result.getInt(6);
                String description = result.getString(7);
                Course course = new Course(CourseID, CourseName, image, price, CatID, TeacherID, description);
                list.add(course);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int GetNumberOfPage(String CategoryID, Integer TeacherID) {
        String sql;
        // if not teacher course
        if (TeacherID == null) {
            // if all course
            if (CategoryID == null) {
                sql = "select count(c.CourseID) from Course c";
            } else {
                sql = "select count(c.CourseID) from Course c where c.CategoryID = " + CategoryID;
            }
        } else {
            sql = "select count(c.CourseID) from Course c where c.TeacherID = " + TeacherID;
        }
        // get data
        ResultSet result = getData(sql);
        double number = 0;
        try {
            // if get data successful
            if (result.next()) {
                number = result.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (number <= MAX_COURSE_IN_PAGE) {
            return 1;
        } else if ((number / MAX_COURSE_IN_PAGE) > (Math.round(number / MAX_COURSE_IN_PAGE))) {
            number = Math.floor(number / MAX_COURSE_IN_PAGE) + 1;
        } else {
            number = Math.round(number / MAX_COURSE_IN_PAGE);
        }
        return (int) number;
    }

    public Course getCourse(int CourseID) {
        String sql = "select c.*  from Course c, Teacher t\n"
                + "where c.TeacherID = t.ID and c.CourseID = " + CourseID;
        // get data
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String CourseName = result.getString(2);
                String image = result.getString(3);
                double price = result.getDouble(4);
                int CategoryID = result.getInt(5);
                int TeacherID = result.getInt(6);
                String description = result.getString(7);
                Course course = new Course(CourseID, CourseName, image, price, CategoryID, TeacherID, description);
                return course;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int DeleteCourse(String column, int ID) {
        int number = 0; // number of row affected
        String sql = "DELETE FROM [dbo].[Course]\n"
                + "      WHERE [" + column + "] = " + ID;
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public List<Course> GetListCourse(int page, int TeacherID) {
        List<Course> list = new ArrayList<>();
        String sql = "select * from Course\n"
                + "where TeacherID = " + TeacherID + "\n"
                + "order by CourseID\n"
                + "offset " + MAX_COURSE_IN_PAGE + "*" + (page - 1) + " row fetch next " + MAX_COURSE_IN_PAGE + " row only";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int CourseID = result.getInt(1);
                String CourseName = result.getString(2);
                String image = result.getString(3);
                double price = result.getDouble(4);
                int CategoryID = result.getInt(5);
                String description = result.getString(7);
                Course course = new Course(CourseID, CourseName, image, price, CategoryID, TeacherID, description);
                list.add(course);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public boolean CheckCourseName(String name) {
        String sql = "SELECT * FROM [dbo].[Course]";
        // get data
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                // if find course name
                if (name.equalsIgnoreCase(result.getString(2))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public int AddCourse(Course course) {
        String sql = "INSERT INTO [dbo].[Course]\n"
                + "           ([CourseName]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[CategoryID]\n"
                + "           ,[TeacherID]\n"
                + "           ,[description])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, course.getCourseName());
            prepare.setString(2, course.getImage());
            prepare.setDouble(3, course.getPrice());
            prepare.setInt(4, course.getCategoryID());
            prepare.setInt(5, course.getTeacherID());
            prepare.setString(6, course.getDescription());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
    
     public int UpdateCourse(Course course) {
        String sql = "UPDATE [dbo].[Course]\n"
                + "   SET [CourseName] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[TeacherID] = ?\n"
                + "      ,[description] = ?\n"
                + " WHERE [CourseID] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, course.getCourseName());
            prepare.setString(2, course.getImage());
            prepare.setDouble(3, course.getPrice());
            prepare.setInt(4, course.getCategoryID());
            prepare.setInt(5, course.getTeacherID());
            prepare.setString(6, course.getDescription());
            prepare.setInt(7, course.getCourseID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

}
