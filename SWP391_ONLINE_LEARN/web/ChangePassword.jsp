<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLearning</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
        <% // get attribute
            String message = (String) request.getAttribute("message");
            String mess = (String) request.getAttribute("mess");
        %>
        <div class="row">
            <div class="col-lg-3" style="margin-top: 50px">
                <ul class="list-group">
                    <li class="list-group-item list-group-item-info list-group-item-action text-center"><a href="Profile">Edit profile</a></li>
                    <li class="list-group-item list-group-item-info list-group-item-action text-center"><a href="ChangePassword">Edit password</a></li>
                    <p style="color:red;text-align: center"><%=message == null ? "" : message%></p>  
                    <p style="color:green;text-align: center"><%=mess == null ? "" : mess%></p>
                </ul>
            </div>
            <div class="col-lg-9 row">
                <form action="ChangePassword" method="POST">
                    <div class="container rounded bg-white mt-5 mb-5">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="p-3 py-5">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h4 class="text-right">Change password</h4>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col-md-12">
                                            <label class="labels">Current Password</label>
                                            <input type="password" name="old" class="form-control" placeholder="Current Password" required> 
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-md-12">
                                            <label class="labels">New Password</label>
                                            <input type="password" name="new" class="form-control" placeholder="New Password" required>  
                                        </div>
                                        <div class="col-md-12">
                                            <label class="labels">Confirm Password</label>   
                                            <input type="password" name="confirm" class="form-control" placeholder="Confirm Password" required> 
                                        </div>

                                    </div>
                                    <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save</button></div>
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
    </body>
</html>
