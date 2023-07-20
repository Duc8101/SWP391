<%@page import="Model.DAOTeacher"%>
<%@page import="Entity.Teacher"%>
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
            List<Course> list = (List<Course>) request.getAttribute("list");
            DAOTeacher daoTeacher = new DAOTeacher();
        %>

        <!-- Courses Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInDown" data-wow-delay="0.1s">
                    <h6 class="section-title bg-white text-center text-primary px-3">My Courses</h6>
                </div>

                <div class="row g-4 justify-content-center">  
                    <%
                        for (Course course : list) {
                            Teacher teacher = daoTeacher.getTeacher(course.getTeacherID());
                    %>
                    <div class="col-lg-4 col-md-6 wow fadeInDown" data-wow-delay="0.3s">
                        <div class="course-item bg-light">
                            <div class="position-relative overflow-hidden">
                                <div style="height: 240px">
                                    <img class="img-fluid w-100" src="<%=course.getImage()%>" alt="">
                                </div>
                                <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                    <a href="Courses?service=Detail&CourseID=<%=course.getCourseID()%>" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px 0 0 30px;">Read More</a>
                                    <form action="Courses">
                                        <input type="hidden" name="service" value="LearnCourse">
                                        <input type="hidden" name="CourseID" value="<%=course.getCourseID()%>">
                                        <input type="submit" class="flex-shrink-0 btn btn-sm btn-primary px-3" style="border-radius: 0 30px 30px 0;" value="Learn Course">
                                    </form>
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
