<%@page import="Entity.Result"%>
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
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/style.css" rel="stylesheet">
        <link href="css/table.css" rel="stylesheet">
        <style>
            .content {
                padding: var(--height-header);
                padding-top: calc(var(--height-header) + 50px);
            }

            .result {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                margin: auto;
                margin-top: 50px;
                background-color: white;
                border-radius: 10px;
                width: 50%;
                text-transform: uppercase;
            }

            .result_info {
                margin-top: 50px;
                display: flex;
            }

            .result_info-time,
            .result_info-point {
                display: flex;
                flex-direction: column;
                margin: 0 50px;
                text-align: center;
            }

            .result_info-time span:first-child,
            .result_info-point span:first-child {
                font-size: 32px;
                color: #a7adc2;
                font-weight: 500;
            }

            .result_info-time span:last-child,
            .result_info-point span:last-child {
                font-size: 24px;
                color: #333;
                font-weight: 500;
                margin-top: 12px;
            }

            .result_info-point {}

            .result_info-time {}

            .result_status {
                display: flex;
                flex-direction: column;
                font-size: 32px;
                color: #333;
                font-weight: 500;
                text-align: center;
                margin: 50px 0;
            }

            body{
                background-color: #f6f7fb;
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


        <!-- Navbar Start -->
        <%@ include file="component/navbar.jsp" %>

        <!-- Navbar End -->

        <% // get attribute
            Result result = (Result) request.getAttribute("result");
        %>
        <!-- Result Start -->

        <!--Header-->
        <div class="content" style="background-color: #f6f7fb;">
            <div class="result" style=" height: 400px;">
                <div class="result_info">
                    <div class="result_info-point">
                        <span class="">Your result</span>
                        <span class=""><%=result.getScore()%>/10</span>
                    </div>
                    <div class="result_info-time">
                        <span>Name</span>
                        <span><%=result.getUsername()%></span>
                    </div>
                </div>
                <div class="result_status">
                    <span>You <span style="color: <%=result.getStatus().equals("PASS") ? "green" : "red"%>"><%=result.getStatus()%></span> this exam</span>
                </div>
                <div class="btn btn-info">
                    <a href="Courses?service=LearnCourse" style="text-decoration: none; color: black;">Cancel</a>
                </div>
            </div>
        </div>
        <!-- Result End -->

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
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
