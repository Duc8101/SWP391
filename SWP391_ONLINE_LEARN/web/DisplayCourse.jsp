<%@page import="Entity.Lesson"%>
<%@page import="Model.DAOLesson"%>
<%@page import="Entity.Category"%>
<%@page import="Entity.Course"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAOCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="utf-8">
        <title>eLEARNING</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar Start -->
        <%@ include file="component/navbar.jsp" %>
        <!-- Navbar End -->

        <%
            DAOCategory daoCategory = new DAOCategory();
            DAOLesson daoLesson = new DAOLesson();
            List<Course> listCoursePage = (List<Course>) request.getAttribute("listPage");
            int numberPage = (Integer) request.getAttribute("numberPage");
            int pageSelected = (Integer) request.getAttribute("pageSelected");
            int CategoryID = (Integer) request.getAttribute("CategoryID");
            String flow = (String) request.getAttribute("flow");
            String properties = (String) request.getAttribute("properties");
            List<Course> listCourseUser = (List<Course>) request.getAttribute("listUser");
            List<Category> listCategory = daoCategory.GetAllCategory();
        %>
        <!-- Courses Start -->
        <div class="container-xxl py-4">
            <div class="container">
                <div class="text-center wow fadeInDown" data-wow-delay="0.1s">
                    <h4 class="section-title bg-white text-center text-primary px-3">Courses</h4>
                </div>
                <div class="row">
                    <div class="col-2 border">
                        <div class="list-group mt-2" >
                            <%
                                for (Category category : listCategory) {
                            %>
                            <a  class="py-3 list-group-item list-group-item-action <%=category.getID() == CategoryID ? "active" : ""%>" href="Courses?CategoryID=<%=category.getID()%>"><%=category.getName()%></a>
                            <%}%>
                        </div>
                    </div>
                    <div class="border col-10">
                        <form action="Courses" class=" my-3 wow fadeInDown row fs-5 fw-bolder"  data-wow-delay="0.2s">
                            <% // if choose category
                                if (CategoryID != 0) {
                            %>
                            <input type="hidden" name="CategoryID" value="<%=CategoryID%>">
                            <%}%>
                            <div class="border col-2 text-center py-2 ms-2 fs-5 fw-bolder">Sort By : </div>
                            <select name="properties" class="form-select form-select-md col-3 mx-1 py-2 fs-5 fw-bolder" aria-label=".form-select-lg example" style="width: 15%">
                                <option value="CourseName" <%="CourseName".equals(properties) ? "selected" : ""%>>Title</option>
                                <option value="price" <%="price".equals(properties) ? "selected" : ""%>>Price</option>
                            </select>
                            <select name="flow" class="form-select form-select-md col-3 mx-1 py-2 fs-5 fw-bolder" aria-label=".form-select-lg example" style="width: 15%">
                                <option value="ASC" <%="ASC".equals(flow) ? "selected" : ""%>>Ascending</option>
                                <option value="DESC" <%="DESC".equals(flow) ? "selected" : ""%>>Descending</option>
                            </select>
                            <input class="mx-2 btn btn-primary border border-primary col-1 py-2 fs-5 fw-bolder" type="submit" value="Sort">
                        </form>

                        <div class="row g-4 justify-content-center">
                            <%                               
                                for (Course course : listCoursePage) {
                                    boolean IsExist = false;
                                    Teacher teacher = daoTeacher.getTeacher(course.getTeacherID());
                                    List<Lesson> listLesson = daoLesson.GetListLesson(course.getCourseID());
                            %>
                            <div class="col-lg-4 col-md-6 wow fadeInDown" data-wow-delay="0.1s">
                                <div class="course-item bg-light">
                                    <div class="position-relative overflow-hidden">
                                        <div style="height: 240px">
                                            <img class="img-fluid w-100" src="<%=course.getImage()%>" alt="">
                                        </div> 
                                        <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                            <a href="Courses?service=Detail&CourseID=<%=course.getCourseID()%>" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end"
                                               style="border-radius: 30px <%=listLesson.isEmpty() ? "" : "0 0"%> 30px;">Read More
                                            </a>
                                            <% // if login
                                                if (listCourseUser != null) {
                                                    for (Course courseUser : listCourseUser) {
                                                        // if exist student learn course
                                                        if (courseUser.getCourseID() == course.getCourseID()) {
                                                            IsExist = true;
                                                            break;
                                                        }
                                                    }
                                                }
                                                // if exist student learn course and exist lesson in course
                                                if (IsExist && !listLesson.isEmpty()) {
                                            %>
                                            <form action="Courses">
                                                <input type="hidden" name="service" value="LearnCourse">
                                                <input type="hidden" name="CourseID" value="<%=course.getCourseID()%>">
                                                <input type="submit" class="flex-shrink-0 btn btn-sm btn-primary px-3" style="border-radius: 0 30px 30px 0;" value="Learn Course">
                                            </form>
                                            <%  // if not exist student learn course and exist lesson in course
                                            } else if (!listLesson.isEmpty()) {
                                            %>
                                            <a href="Courses?service=RegisterCourse&CourseID=<%=course.getCourseID()%>" class="flex-shrink-0 btn btn-sm btn-primary px-3" 
                                               style="border-radius: 0 30px 30px 0;">Register Course</a>
                                            <%}%>
                                        </div>
                                    </div>

                                    <div class="text-center p-4 pb-0">
                                        <div class="mb-1">

                                        </div>
                                        <h5 class="mb-2"><%= course.getCourseName().length() <= 24 ? course.getCourseName() : course.getCourseName().substring(0, 24) + "..."%></h5>
                                        <h3 class="mb-2">$<%=course.getPrice()%></h3>

                                    </div>
                                    <div class="d-flex border-top">
                                        <small class="flex-fill text-center border-end py-2"><i class="fa fa-user-tie text-primary me-2"></i><%=teacher.getFullName()%></small>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                            <%
                                int prePageSelected = pageSelected - 1;
                                int nextPageSelected = pageSelected + 1;
                                String preURL;
                                String nextURL;
                                // if not sort
                                if (properties == null && flow == null) {
                                    // if not choose category
                                    if (CategoryID == 0) {
                                        preURL = "Courses" + "?page=" + prePageSelected;
                                        nextURL = "Courses" + "?page=" + nextPageSelected;
                                    } else {
                                        preURL = "Courses" + "?CategoryID=" + CategoryID + "&page=" + prePageSelected;
                                        nextURL = "Courses" + "?CategoryID=" + CategoryID + "&page=" + nextPageSelected;
                                    }
                                } else {
                                    // if not choose category
                                    if (CategoryID == 0) {
                                        preURL = "Courses" + "?properties=" + properties + "&flow=" + flow + "&page=" + prePageSelected;
                                        nextURL = "Courses" + "?properties=" + properties + "&flow=" + flow + "&page=" + nextPageSelected;
                                    } else {
                                        preURL = "Courses" + "?CategoryID=" + CategoryID + "&properties=" + properties + "&flow=" + flow + "&page=" + prePageSelected;
                                        nextURL = "Courses" + "?CategoryID=" + CategoryID + "&properties=" + properties + "&flow=" + flow + "&page=" + nextPageSelected;
                                    }
                                }
                            %>

                            <% // if exist course
                                if (listCoursePage.size() != 0) {
                            %>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item <%=pageSelected == 1 ? "disabled" : ""%>"><a class="page-link" href="<%=preURL%>">Previous</a></li>
                                        <% // loop for traverse all page
                                            for (int i = 1; i <= numberPage; i++) {
                                                String pageURL;
                                                // if not sort
                                                if (properties == null && flow == null) {
                                                    // if not choose category
                                                    if (CategoryID == 0) {
                                                        pageURL = "Courses" + "?page=" + i;
                                                    } else {
                                                        pageURL = "Courses" + "?CategoryID=" + CategoryID + "&page=" + i;
                                                    }
                                                } else {
                                                    // if not choose category
                                                    if (CategoryID == 0) {
                                                        pageURL = "Courses" + "?properties=" + properties + "&flow=" + flow + "&page=" + i;
                                                    } else {
                                                        pageURL = "Courses" + "?CategoryID=" + CategoryID + "&properties=" + properties + "&flow=" + flow + "&page=" + i;
                                                    }
                                                }
                                        %>
                                    <li class="page-item"><a class="page-link" href="<%=pageURL%>"><%=i%></a></li>                             
                                        <%}%>
                                    <li class="page-item <%=pageSelected == numberPage ? "disabled" : ""%>"><a class="page-link" href="<%=nextURL%>">Next</a></li>
                                </ul>
                            </nav>
                            <%}%>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Courses End -->

        <!-- Footer Start -->
        <%@include file="component/footer.jsp" %>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>
