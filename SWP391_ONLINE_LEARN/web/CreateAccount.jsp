<%@page import="Entity.Teacher"%>
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
        <style>
            img{
                border-radius: 10px;
            }
        </style>
    </head>
    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->
        <nav class="navbar navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand"><i class="bi bi-person-circle"></i> WELCOME TO ADMIN</a>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="name" required>
                    <button class="btn btn-outline-success" type="submit" name="submit" value="Search">Search</button>
                </form>
            </div>
        </nav>
        <% // get attribute
            String message = (String) request.getAttribute("message");
            String mess = (String) request.getAttribute("mess");
        %>
        <section style="margin-top: 50px">
            <div class="row vh-100" >
                <div class="col-lg-3">
                    <ul class="list-group">
                        <li class="list-group-item list-group-item-info"><a href="ManagerAccount" >Account Management</a></li>
                        <li class="list-group-item list-group-item-info"><a href="ManagerAccount?service=CreateAccount">Create Account</a></li>
                        <p class="text-danger" style="text-align: center"><%=message == null ? "" : message%></p>
                        <p style="color: green;text-align: center"><%=mess == null ? "" : mess%></p>
                    </ul>
                    <a href="Logout" class="btn btn-lg btn-primary position-fixed bottom-0 start-0 ">Logout <i class="bi bi-box-arrow-right"></i></a>
                </div>
                <div class="col-lg-9 row">
                    <form action="ManagerAccount" method="POST">
                        <input type="hidden" name="service" value="CreateAccount">
                        <div class="container rounded bg-white mt-5 mb-5">
                            <div class="row">
                                <div class="col-md-12 border-right">
                                    <div class="p-3 py-8">
                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                            <h4 class="text-right">CREATE TEACHER ACCOUNT</h4>       
                                        </div>
                                        <div class="row mt-2">
                                            <div class="col-md-12"><label class="labels">FullName</label><input type="text" class="form-control" placeholder="Full Name" name="FullName" required></div>                                          
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-12">
                                                <label class="labels">Email</label>
                                                <input type="email" class="form-control" placeholder="Email" name="email" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Phone(Optional)</label>
                                                <input type="text" class="form-control" placeholder="Phone" name="phone">
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Gender</label>
                                                <select name="gender" class="form-control">
                                                    <option value="<%=Teacher.GENDER_FEMALE%>"><%=Teacher.GENDER_FEMALE%></option>
                                                    <option value="<%=Teacher.GENDER_MALE%>"><%=Teacher.GENDER_MALE%></option>
                                                    <option value="<%=Teacher.GENDER_OTHER%>"><%=Teacher.GENDER_OTHER%></option>
                                                </select>
                                            </div>                                         
                                            <div class="col-md-12">
                                                <label class="labels">Username</label>
                                                <input type="text" class="form-control" placeholder="Your username" name="username" required>
                                            </div>
                                            <div class="col-md-12">
                                                <label class="labels">Password</label>
                                                <input type="password" class="form-control" placeholder="Your password" name="password" required>
                                            </div>
                                        </div>
                                        <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Submit</button></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </section>

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
