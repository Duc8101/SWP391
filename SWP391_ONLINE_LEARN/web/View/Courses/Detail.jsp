<%@page import="Entity.Teacher"%>
<%@page import="Model.DAOTeacher"%>
<%@page import="Entity.Lesson"%>
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
        <%
            List<Lesson> listLesson = (List<Lesson>) request.getAttribute("listLesson");
            Course course = (Course) request.getAttribute("course");
            List<Course> listCourseUser = (List<Course>) request.getAttribute("listUser");
            DAOTeacher daoTeacher = new DAOTeacher();
        %>
        <!-- Spinner Start -->
        <div id="spinner"
             class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar Start -->
        <jsp:include page="/View/Shared/navbar.jsp"></jsp:include>
            <!-- Navbar End -->

            <div class="container-fluid py-5 wow fadeInDown row" data-wow-delay="0.1s">
                <div class="border col-4">
                    <div class="row g-4 justify-content-center">
                    <%
                        Teacher teacher = daoTeacher.getTeacher(course.getTeacherID());
                        boolean isExist = false;
                    %>
                    <div class="col-12 wow fadeInDown" data-wow-delay="0.1s">
                        <div class="course-item bg-light">
                            <div class="position-relative overflow-hidden">
                                <div style="height: 240px">
                                    <img style="width: 100%" class="img-fluid" src="<%=course.getImage()%>" alt="">
                                </div>
                                <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                    <% // if login
                                        if (listCourseUser != null) {
                                            for (Course courseUser : listCourseUser) {
                                                // if exist student learn course
                                                if (courseUser.getCourseID() == course.getCourseID()) {
                                                    isExist = true;
                                                    break;
                                                }
                                            }
                                        }
                                        // if exist student learn course and exist lesson in course
                                        if (isExist && !listLesson.isEmpty()) {
                                    %>
                                    <form action="Courses">
                                        <input type="hidden" name="service" value="LearnCourse">
                                        <input type="hidden" name="CourseID" value="<%=course.getCourseID()%>">
                                        <input type="submit" class="flex-shrink-0 btn btn-sm btn-primary px-3" style="border-radius: 0 30px 30px 0;" value="Learn Course">
                                    </form>
                                    <% // if not exist student learn course and exist lesson in course
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
                                <h5 class="mb-2"><%=course.getCourseName()%></h5>
                                <h3 class="mb-2">$<%=course.getPrice()%></h3>

                            </div>
                            <div class="d-flex border-top">
                                <small class="flex-fill text-center border py-2"><i class="fa fa-user-tie text-primary me-2"></i><%=teacher.getFullName()%></small>
                            </div>

                        </div>
                    </div>
                </div> 
                <div class="money-back text-center my-2">30-Day Money-Back Guarantee</div>     
            </div>


            <div class="col-8 row">
                <div class="border row align-items-center " >
                    <b style="font-size: 20px">Course Content Description:</b>
                    <p><%=course.getDescription() == null ? "" : course.getDescription()%></p>              
                </div>
                <div class="border row">
                    <div class="my-2">
                        <ol class="list-group list-group-numbered">
                            <%
                                for (Lesson lesson : listLesson) {
                            %>
                            <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-start">
                                <div class="ms-2 me-auto">
                                    <div class="fw-bold"><%=lesson.getLessonName()%></div>
                                </div>
                            </li>
                            <%}%>
                            <%  // if not exist lesson
                                if (listLesson.isEmpty()) {
                            %>
                            <h1 class="mb-4 justify-content-center text-primary"><i class="bi bi-exclamation-triangle display-1 text-primary"></i>This Course Is Under Development Process And Don't Have Any Courses Yet</h1>
                            <%}%>
                        </ol>
                    </div>
                </div>
            </div>
        </div>

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
