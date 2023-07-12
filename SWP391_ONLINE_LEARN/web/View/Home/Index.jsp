<%@page import="Model.DAOTeacher"%>
<%@page import="Entity.Teacher"%>
<%@page import="Model.DAOLesson"%>
<%@page import="Entity.Lesson"%>
<%@page import="Const.ConstValue"%>
<%@page import="Entity.Account"%>
<%@page import="Entity.Course"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLEARNING</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="resources/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="resources/lib/animate/animate.min.css" rel="stylesheet">
        <link href="resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="resources/css/style.css" rel="stylesheet">
    </head>
    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar Start -->
        <jsp:include page="/View/Shared/navbar.jsp"></jsp:include>
            <!-- Navbar End -->

        <%
            List<Course> listPrice = (List<Course>) request.getAttribute("listPrice");
            List<Course> listUser = (List<Course>) request.getAttribute("listUser");
            Account account = (Account) session.getAttribute("account");
            DAOTeacher daoTeacher = new DAOTeacher();
            DAOLesson daoLesson = new DAOLesson();
        %>
        <!-- Carousel Start -->
        <% // if not login or login as student
            if (account == null || account.getRoleName().equals(ConstValue.ROLE_STUDENT)) {
        %>
        <div class="container-fluid p-0 mb-5">
            <div class="owl-carousel header-carousel position-relative">
                <%
                    for (Course course : listPrice) {
                %>
                <div class="owl-carousel-item position-relative">
                    <img class="img " src="<%=course.getImage()%>" alt="" style="height: 720px">
                    <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                        <div class="container">
                            <div class="row justify-content-start">
                                <div class="col-sm-10 col-lg-8">
                                    <h5 class="text-primary text-uppercase mb-3 animated slideInDown">Best Online Courses</h5>
                                    <h1 class="display-3 text-white animated slideInDown"><%=course.getCourseName()%></h1>
                                    <div  class="d-flex flex-row">
                                        <a href="Courses?service=Detail&CourseID=<%=course.getCourseID()%>" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read More</a>
                                        <%      boolean isExist = false;
                                            // if login
                                            if (listUser != null) {
                                                for (Course cour : listUser) {
                                                    // if exist student learn course 
                                                    if (cour.getCourseID() == course.getCourseID()) {
                                                        isExist = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            List<Lesson> listLesson = daoLesson.getListLesson(course.getCourseID());
                                            // if student learn course and exist lesson in course
                                            if (isExist && !listLesson.isEmpty()) {
                                        %>
                                        <form action="Courses">
                                            <input type="hidden" name="service" value="LearnCourse">
                                            <input type="hidden" name="CourseID" value="<%=course.getCourseID()%>">
                                            <input type="submit" class="btn btn-light py-md-3 px-md-5 animated slideInRight" value="Learn Course">
                                        </form>
                                        <% // if student not learn course and exist lesson in course
                                        } else if (!listLesson.isEmpty()) {
                                        %>
                                        <a href="Courses?service=RegisterCourse&CourseID=<%=course.getCourseID()%>" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Register Course</a>
                                        <%}%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
        <%}%>
        <!-- Carousel End -->

        <!-- About Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="row g-5">
                    <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.1s" style="min-height: 400px;">
                        <div class="position-relative h-100">
                            <img class="img-fluid position-absolute w-100 h-100" src="resources/img/about.jpg" alt="" style="object-fit: cover;">
                        </div>
                    </div>
                    <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.3s">
                        <h6 class="section-title bg-white text-start text-primary pe-3">About Us</h6>
                        <h1 class="mb-4">Welcome to eLEARNING</h1>
                        <p class="mb-4">
                            Changing learning for the better<br>
                            Whether you want to learn or to share what you know, you are come to the right place. As a global destination for online learning, we connect people through knowledge.
                        </p>
                        <p class="mb-4">At E-Learning, we are all learners and instructors. We live out our values every day to create a culture that is diverse, inclusive, and committed to helping employees thrive.</p>
                        <div class="row gy-2 gx-4 mb-4">
                            <div class="col-sm-6">
                                <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Skilled Instructors</p>
                            </div>
                            <div class="col-sm-6">
                                <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Online Classes</p>
                            </div>
                            <div class="col-sm-6">
                                <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>International Certificate</p>
                            </div>
                            <div class="col-sm-6">
                                <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Skilled Instructors</p>
                            </div>
                            <div class="col-sm-6">
                                <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Online Classes</p>
                            </div>
                            <div class="col-sm-6">
                                <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>International Certificate</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- About End -->

        <!-- Courses Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h6 class="section-title bg-white text-center text-primary px-3">Courses</h6>
                    <h1 class="mb-5">Most Expensive Courses</h1>
                </div>
                <div class="row g-4 justify-content-center">
                    <%
                        for (Course course : listPrice) {
                            Teacher teacher = daoTeacher.getTeacher(course.getTeacherID());
                    %>
                    <div class="col-lg-4 col-md-6 wow fadeInDown" data-wow-delay="0.3s">
                        <div class="course-item bg-light">
                            <div class="position-relative overflow-hidden">
                                <div style="height: 240px">
                                    <img class="img-fluid w-100" src="<%=course.getImage()%>" alt="">
                                </div>
                                <%      boolean isExist = false;
                                    // if login
                                    if (listUser != null) {
                                        // loop for traverse all course
                                        for (Course cour : listUser) {
                                            // if student learn course 
                                            if (cour.getCourseID() == course.getCourseID()) {
                                                isExist = true;
                                                break;
                                            }
                                        }
                                    }
                                    List<Lesson> listLesson = daoLesson.getListLesson(course.getCourseID());
                                %>
                                <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                    <%  // if not login or login as student
                                        if (account == null || account.getRoleName().equals(ConstValue.ROLE_STUDENT)) {
                                    %>
                                    <a href="Courses?service=Detail&CourseID=<%=course.getCourseID()%>" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read More</a>
                                    <%}%>
                                    <% // if student learn course and course exist lesson
                                        if (isExist && !listLesson.isEmpty()) {
                                    %>
                                    <form action="Courses">
                                        <input type="hidden" name="service" value="LearnCourse">
                                        <input type="hidden" name="CourseID" value="<%=course.getCourseID()%>">
                                        <input type="submit" class="btn btn-light py-md-3 px-md-5 animated slideInRight" value="Learn Course">
                                    </form>
                                    <%  // if student not learn course and course exist lesson
                                    } else if (!listLesson.isEmpty()) {
                                    %>
                                    <a href="Courses?service=RegisterCourse&CourseID=<%=course.getCourseID()%>" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Register Course</a>
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
                                <small class="flex-fill text-center border-end py-2"><i class="fa fa-user-tie text-primary me-2"></i><%=teacher == null ? "" : teacher.getFullName()%></small>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
        <!-- Courses End -->

        <!-- Testimonial Start -->
        <jsp:include page="/View/Shared/testimonial.jsp"></jsp:include>
            <!-- Testimonial End -->

            <!-- Footer Start -->
        <jsp:include page="/View/Shared/footer.jsp"></jsp:include>
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="resources/lib/wow/wow.min.js"></script>
        <script src="resources/lib/easing/easing.min.js"></script>
        <script src="resources/lib/waypoints/waypoints.min.js"></script>
        <script src="resources/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="resources/js/main.js"></script>
    </body>
</html>
