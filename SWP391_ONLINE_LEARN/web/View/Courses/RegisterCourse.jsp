<%@page import="Entity.Student"%>
<%@page import="Entity.Course"%>
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
        <link href="form-validation.css" rel="stylesheet">
        <style>
            .nmv{
                margin-top: 20px;
            }
            .er{
                color: green;
            }
            .bv{
                font-size: 25px;
                margin-auto: 5px;
            }
            .abc{
                background-color: #06BBCC;
                color: white;
                margin-left:180px;
                size: 250px;
                padding: 24px 48px;
            }
        </style>
    </head>
    <body class="bg-light">
        <!-- Navbar Start -->
        <jsp:include page="/View/Shared/navbar.jsp"></jsp:include>
            <!-- Navbar End -->

        <% // get attribute
            Course course = (Course) request.getAttribute("course");
            Student student = (Student) request.getAttribute("student");
            String mess = (String) request.getAttribute("mess");
        %>

        <div class="container">
            <div class="py-4 text-center">
                <!--<img class="d-block mx-auto mb-4" src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">-->
            </div>

            <div class="row">
                <div class="col-md-6 order-md-2 mb-5">
                    <h4 class="d-flex justify-content-between align-items-center mb-4">
                        <span class="text-muted">Your cart</span>
                        <span class="badge badge-secondary badge-pill">3</span>
                    </h4>
                    <ul class="list-group mb-6">
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div style="height: 270px">
                                <img class="img-fluid" src="<%=course.getImage()%>" alt="" style="width:350px; height: 270px">
                            </div>
                            <div class="mx-3">
                                <h6 class="my-0">Product name</h6>
                                <small class="text-muted"><%=course.getCourseName()%></small>
                            </div>
                            <div style="margin-bottom: 150px">
                                <% // if not check out
                                    if (mess == null) {
                                %>
                                <a href="Courses"
                                   class="nav-item nav-link active" onclick="return confirm('Are you sure you want to delete?')" 
                                   style="" xpath="1"><button class="nav-item nav-link active">Cancel</button></a>
                                <%}%>
                            </div>
                        </li>
                        <div class="nmv">
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <span style="color: black ">Price: (USD)</span>
                                <strong>$<%=course.getPrice()%></strong>
                            </li>
                        </div>
                    </ul>
                </div>


                <div class="col-md-6 order-md-1">
                    <h4 class="mb-2">Billing address</h4>
                    <form class="needs-validation" novalidate action="" method="post">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">FullName</label>
                                <input type="text" class="form-control" id="firstName" readonly placeholder="<%=student.getFullName()%>" value="<%=student.getFullName()%>" required name="firstname">
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Gender</label>
                                <input type="text" class="form-control" id="lastName" readonly placeholder="" value="<%=student.getGender()%>" required name="lastname">
                                <div class="invalid-feedback">
                                    Valid last name is required.
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="username">Username</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                </div>
                                <input type="text" class="form-control" id="username" readonly placeholder="Username" value="<%=student.getUsername()%>" required name="username">
                                <div class="invalid-feedback" style="width: 100%;">
                                    Your username is required.
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" readonly id="email" value="<%=student.getEmail()%>" placeholder="you@example.com" name="email">
                            <div class="invalid-feedback">
                                Please enter a valid email address for shipping updates.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" id="address" readonly value="<%=student.getAddress()%>" placeholder="1234 Main St" required name="address">
                            <div class="invalid-feedback">
                                Please enter your shipping address.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="phone">Phone</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                </div>
                                <input type="text" class="form-control" id="phone" readonly value="<%=student.getPhone()%>" placeholder="Phone" required name="phone">
                                <div class="invalid-feedback" style="width: 100%;">
                                    Your username is required.
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="username">Image</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                </div>
                                <input type="text" class="form-control" id="username" readonly value="<%=student.getImage()%>" placeholder="link" required name="phone">
                                <div class="invalid-feedback" style="width: 100%;">
                                    Your username is required.
                                </div>
                            </div>
                        </div>
                    </form>
                    <hr class="mb-4">   
                    <% // if not check out
                        if (mess == null) {
                    %>
                    <hr class="mb-4">
                    <a href="Courses?service=Checkout&CourseID=<%=course.getCourseID()%>" class="nav-item nav-link active btn btn-primary" onclick="return confirm('Are you sure you want to continue!!!')" style="" xpath="1">Continue Checkout</a>    
                    <%} else {%>
                    <div>
                        <button><a href="MyCourse" class="bv">Enroll Course</a> </button>
                    </div>
                    <hr class="mb-4">
                    <div class="er">
                        <p><%=mess%></p>
                    </div>
                    <%}%>
                </div>            
            </div>
        </div>

        <!-- Footer Start -->
        <jsp:include page="/View/Shared/footer.jsp"></jsp:include>
        <!-- Footer End -->
    </body>
</html>
