<%-- 
    Document   : ViewQuiz
    Created on : Aug 22, 2022, 10:05:42 PM
    Author     : ADMIN
--%>

<%@page import="Entity.Quiz"%>
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
            Quiz quiz = (Quiz) request.getAttribute("quiz");
        %>

        <!-- VIEW Start -->
        <br>
        <div class="container">

            <h1>View Question</h1>
            <hr>
            <div class="row">
                <!-- left column -->
                <div class="col-md-3">
                </div>
                <!-- edit form column -->
                <div class="col-md-7 personal-info">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><b>Question:</b></label>
                            <div class="col-lg-8">
                                <textarea readonly class="form-control" name="ques" type="text"><%=quiz.getQuestion()%></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><b>Answer 1:</b></label>
                            <div class="col-lg-8">
                                <textarea readonly class="form-control" name="ans1" type="text"><%=quiz.getAnswer1()%></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><b>Answer 2:</b></label>
                            <div class="col-lg-8">
                                <textarea readonly class="form-control" name="ans2" type="text"><%=quiz.getAnswer2()%></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><b>Answer 3:</b></label>
                            <div class="col-lg-8">
                                <textarea readonly class="form-control" name="ans3" type="text"><%=quiz.getAnswer3() == null ? "" : quiz.getAnswer3()%></textarea>
                            </div>
                        </div>    
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><b>Answer 4:</b></label>
                            <div class="col-lg-8">
                                <textarea readonly class="form-control" name="ans4" type="text"><%=quiz.getAnswer4() == null ? "" : quiz.getAnswer4()%></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><b>Answer Correct:</b></label>
                            <div class="col-lg-8">
                                <input readonly class="form-control" name="correctAns" type="text" value="<%=quiz.getCorrectAnswer()%>">
                            </div>
                        </div>  
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <a href="ManagerQuiz?LessonID=<%=quiz.getLessonID()%>" class="btn btn-info">Cancel</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- VIEW End -->


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
