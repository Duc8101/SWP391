<%@page import="Entity.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLearning</title>
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
        <!-- Navbar Start -->
        <%@ include file="component/navbar.jsp" %>
        <!-- Navbar End -->
        <%
            Student student = (Student) request.getAttribute("student");
            String message = (String) request.getAttribute("message");
            String mess = (String) request.getAttribute("mess");
        %>
        <div class="row">
            <div class="col-lg-3" style="margin-top: 50px">
                <ul class="list-group">
                    <li class="list-group-item list-group-item-info list-group-item-action text-center"><a href="Profile">Edit profile</a></li>
                    <li class="list-group-item list-group-item-info list-group-item-action text-center"><a href="ChangePassword">Edit password</a></li>
                    <p style="color:red; text-align: center"><%=message == null ? "" : message%></p>
                    <p style="color:green; text-align: center"><%=mess == null ? "" : mess%></p>
                </ul>
            </div>
            <div class="col-lg-9 row">
                <form action="Profile" method="POST">
                    <div class="container rounded bg-white mt-5 mb-5">
                        <div class="row">
                            <div class="col-md-4 border-right">
                                <input  id="file" type="file" name="image" accept=".jpg, .png" multiple value="<%=student.getImage()%>">
                                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                                    <img class="rounded-circle mt-5" width="250px" src="<%=student.getImage()%>">
                                    <span class="font-weight-bold"><%=student.getFullName()%></span>
                                    <span class="text-black-50"><%=student.getEmail()%></span>
                                </div>
                                <input type="hidden" name="valueImg" value="<%=student.getImage()%>" >  
                            </div>
                            <div class="col-md-8 border-right">
                                <div class="p-3 py-5">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h4 class="text-right">Profile Settings</h4>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col-md-6"><label class="labels">FullName</label><input type="text" class="form-control" placeholder="Full Name" value="<%=student.getFullName()%>" name="FullName" required></div>
                                        <div class="col-md-6"><label class="labels">Date Of Birth</label><input type="date" class="form-control" value="<%=student.getDOB() == null ? "" : student.getDOB()%>" placeholder="Date Of Birth" name="DOB"></div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-md-12"><label class="labels">Address</label><input type="text" class="form-control" placeholder="Address" value="<%=student.getAddress() == null ? "" : student.getAddress()%>" name="address"></div>
                                        <div class="col-md-12">
                                            <label class="labels">Email</label>
                                            <input type="email" class="form-control" placeholder="Email" value="<%=student.getEmail()%>" name="email" required>
                                        </div>
                                        <div class="col-md-12"><label class="labels">Phone</label><input type="text" class="form-control" placeholder="Phone" value="<%=student.getPhone() == null ? "" : student.getPhone()%>" name="phone"></div>
                                        <div class="col-md-12">
                                            <label class="labels">Gender</label>
                                            <select name="gender" class="form-control">
                                                <option value="<%=Student.GENDER_FEMALE%>" <%=student.getGender().equalsIgnoreCase(Student.GENDER_FEMALE) ? "selected" : ""%>>Female</option>
                                                <option value="<%=Student.GENDER_MALE%>" <%=student.getGender().equalsIgnoreCase(Student.GENDER_MALE) ? "selected" : ""%>>Male</option>
                                                <option value="<%=Student.GENDER_OTHER%>" <%=student.getGender().equalsIgnoreCase(Student.GENDER_OTHER) ? "selected" : ""%>>Other</option>
                                            </select>
                                        </div>                                    
                                    </div>
                                    <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>

            </div>
        </div>
        <!-- Footer Start -->
        <%@include file="component/footer.jsp" %>
        <!-- Footer End -->   

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
