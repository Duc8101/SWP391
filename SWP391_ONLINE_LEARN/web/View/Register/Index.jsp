<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLearning</title>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="resources/lib/animate/animate.min.css" rel="stylesheet">
        <link href="resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->

        <!-- Template Stylesheet -->
        <link href="resources/css/style.css" rel="stylesheet">

        <script src="https://kit.fontawesome.com/42147adfb1.js" crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            h4{
                color:red;
            }
        </style>
    </head>
    <body>
        <% // get attribute
            String message = (String) request.getAttribute("message");
            String mess = (String) request.getAttribute("mess");
        %>
        <section class="h-100" style="background-color: #06BBCC">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card card-registration my-4">
                            <div class="row g-0">
                                <div class="col-xl-6 d-none d-xl-block">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
                                         alt="Sample photo" class="img-fluid"
                                         style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
                                </div>
                                <div class="col-xl-6">
                                    <form action="Register" method="POST">
                                        <div class="card-body p-md-5 text-black">
                                            <h3 class="mb-5 text-uppercase">CREATE ACCOUNT</h3>
                                            <p class="text-danger"><%=message == null ? "" : message%></p>
                                            <p style="color: green"><%=mess == null ? "" : mess%></p>
                                            <div class="row">
                                                <div class="col-md-12 mb-6">
                                                    <div class="form-outline">
                                                        <input type="text" name="FullName" value="" class="form-control form-control-lg" required/>
                                                        <label class="form-label" for="form3Example1m">Full name</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="text" name="phone" value="" class="form-control form-control-lg" />
                                                        <label class="form-label" for="form3Example1m1">Phone (Optional)</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="email" name="email" value="" class="form-control form-control-lg" required/>
                                                        <label class="form-label" for="form3Example1n1">Email</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="text" name="address" class="form-control form-control-lg" value=""/>
                                                <label class="form-label" for="form3Example8">Address (Optional)</label>
                                            </div>

                                            <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">

                                                <h6 class="mb-0 me-4">Gender: </h6>

                                                <div class="form-check form-check-inline mb-0 me-4">
                                                    <input class="form-check-input" type="radio" name="gender" id="femaleGender"
                                                           value="Female" />
                                                    <label class="form-check-label" for="femaleGender">Female</label>
                                                </div>

                                                <div class="form-check form-check-inline mb-0 me-4">
                                                    <input class="form-check-input" type="radio" name="gender" id="maleGender"
                                                           value="Male" checked/>
                                                    <label class="form-check-label" for="maleGender">Male</label>
                                                </div>

                                                <div class="form-check form-check-inline mb-0">
                                                    <input class="form-check-input" type="radio" name="gender" id="otherGender"
                                                           value="Other" />
                                                    <label class="form-check-label" for="otherGender">Other</label>
                                                </div>

                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="text" name="username" value="" class="form-control form-control-lg" required/>
                                                <label class="form-label" for="form3Example9">User Name</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="password" name="password" value="" class="form-control form-control-lg" required/>
                                                <label class="form-label" for="form3Example90">Password</label>
                                            </div>

                                            <div class="d-flex justify-content-end pt-3">
                                                <button type="reset" class="btn btn-light btn-lg">Reset all</button>
                                                <button type="submit" class="btn btn-warning btn-lg ms-2">Submit</button>
                                            </div>
                                            <div class="d-flex justify-content-start pt-3">
                                                <a href="Login" class="btn btn-outline-warning"><i class="bi bi-arrow-left-circle"></i> Back </a> 
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
