<%@page import="java.util.List"%>
<%@page import="Entity.Course"%>
<%@page import="Model.DAOCategory"%>
<%@page import="Entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
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
        <%@ include file="component/navbar.jsp" %>
        <!-- Navbar End -->

        <%
            DAOCategory daoCategory = new DAOCategory();
            List<Category> list = daoCategory.GetAllCategory();
            // get attribute
            Course course = (Course) request.getAttribute("course");
            String message = (String) request.getAttribute("message");
            String mess = (String) request.getAttribute("mess");
        %>
        
        <form action="UpdateCourse" method="POST">
            <input type="hidden" name="CourseID" value="<%=course.getCourseID()%>"> 
            <input type="hidden" name="oldName" value="<%=course.getCourseName()%>"> 
            <div class="container pt-4 px-4">
                <div class="row">
                    <h3 class="mb-4 col-12 p-auto text-center ">Update Course</h3>
                    <p style="color:red;margin-left: 10px"><%=message == null ? "" : message%></p>
                    <p style="color:green;margin-left: 10px"><%=mess == null ? "" : mess%></p>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="bg-light rounded h-100 p-4">
                            <div class="form-floating mb-3">
                                <input  name="CourseName" type="text" class="form-control" id="floatingCourseName"
                                        placeholder="name@example.com" value="<%=course.getCourseName()%>" required>
                                <label for="floatingCourseName">Course Name</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input name="price" type="number" class="form-control" id="floatingPrice" step="any"
                                       placeholder="Price" value="<%=course.getPrice()%>" required>
                                <label for="floatingPrice">Price</label>
                            </div>  

                            <div class="form-floating mb-3">
                                <select name="category" class="form-select" id="floatingSelect"
                                        aria-label="Floating label select example">
                                    <%
                                        for (Category category : list) {
                                    %>
                                    <option value="<%=category.getID()%>" <%=category.getID() == course.getCategoryID() ? "selected" : ""%>><%=category.getName()%></option>
                                    <%}%>
                                </select>
                                <label for="floatingSelect">Course Category</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input name="image" type="text" class="form-control" id="floatingImage"
                                       placeholder="Image" value="<%=course.getImage()%>" required>
                                <label for="floatingImage">Image Link</label>
                            </div>

                            <div class="form-floating mb-3">
                                <textarea name="description" class="form-control" placeholder="Description"
                                          id="floatingDescription" style="height: 150px;"><%=course.getDescription() == null ? "" : course.getDescription()%></textarea>
                                <label for="floatingDescription">Description</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="">
                        <button type="submit" class="btn btn-primary mt-5 col-12 py-2 text-center fs-5">Update Course</button>
                    </div>
                </div>
            </div>
        </form>

        <!-- Footer Start -->
        <%@include file="component/footer.jsp" %>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


        <!-- JavaScript Libraries -->
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
