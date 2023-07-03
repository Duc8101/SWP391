<%@page import="java.util.List"%>
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
        <link href="css/table.css" rel="stylesheet">
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
            String mess = (String) request.getAttribute("mess");
            List<Quiz> list = (List<Quiz>) request.getAttribute("list");
        %>

        <!-- LIST Start -->
        <a href="ManagerQuiz?service=CreateQuestion" class="btn btn-info btn-md ms-2" style="margin:30px auto">
            <i class="fa fa-user-plus"></i> Create new Question
        </a>
        <p class="text-success"><%=mess == null ? "" : mess%></p>
        <div class="card mb-6 pb-5">
            <div class="card-header">
                <i class="fas fa-table me-1"></i>
                LIST QUIZ
            </div>
            <div class="card-body pb-5">
                <table id="datatablesSimple">
                    <thead>
                        <tr>
                            <th>Question</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Quiz quiz : list) {
                        %>
                        <tr>
                            <td><%=quiz.getQuestion()%></td>
                            <td>
                                <a href="ManagerQuiz?service=ViewQuestion&QuestionID=<%=quiz.getQuestionID()%>">
                                    <i class="fa fa-eye fs-5 me-2"></i>
                                </a>
                                <a href="ManagerQuiz?service=UpdateQuestion&QuestionID=<%=quiz.getQuestionID()%>">
                                    <i class="fa fa-edit fs-5 me-2"></i>
                                </a> 
                                <a href="ManagerQuiz?service=DeleteQuestion&QuestionID=<%=quiz.getQuestionID()%>" onclick="return confirm('Are you sure you want to delete this item?')">
                                    <i class="fa fa-trash fs-5"></i>
                                </a> 
                            </td>
                        </tr>                    
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- LIST End -->
        <a href="ManagerLesson" class="btn btn-info btn-md ms-2" style="margin:30px auto">Back to Lesson</a>    

        <!-- Footer Start -->
        <section style="margin-top: 5%">
            <%@include file="component/footer.jsp" %>
        </section>
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
