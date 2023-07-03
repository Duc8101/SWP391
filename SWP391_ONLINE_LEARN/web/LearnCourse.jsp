<%-- 
    Document   : LearnCourse
    Created on : Aug 15, 2022, 11:48:25 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="Entity.Quiz"%>
<%@page import="Entity.LessonPDF"%>
<%@page import="Entity.LessonVideo"%>
<%@page import="Entity.Lesson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLearning</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->

        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/42147adfb1.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            #myVideo {
                width: 80%;
                height: 710px;
                margin-left: 10%;

            }
            .col-md-3{
                background-color: #EEEEEE; 

                overflow-y: scroll; 
                height: 820px
            }
            .col-md-9{
                background-color: #f0fbfc; 

            }
            .col-md-9 h3 {
                text-align: center;
                margin-top: 2%
            }
            #lesson {
                background-color: #06BBCC;
                color: white; text-decoration: none; 
                cursor: pointer
            }
            .footer{
                position: fixed;
                bottom: 0;
                left: 0
            }
        </style>
    </head>
    <body>

        <!-- Navbar Start -->
        <%@ include file="component/navbar.jsp" %>
        <!-- Navbar End -->

        <%
            List<Lesson> listLesson = (List<Lesson>) request.getAttribute("listLesson");
            int LessonID = (Integer) request.getAttribute("lID");
            List<LessonVideo> listVideo = (List<LessonVideo>) request.getAttribute("listVideo");
            int VideoID = (Integer) request.getAttribute("vID");
            List<LessonPDF> listPDF = (List<LessonPDF>) request.getAttribute("listPDF");
            int PDFID = (Integer) request.getAttribute("pID");
            List<Integer> listLessonQuiz = (List<Integer>) request.getAttribute("listLessonQuiz");
            String name = (String) request.getAttribute("name");
            String Video = (String) request.getAttribute("video");
            String pdf = (String) request.getAttribute("PDF");
        %> 
        <div class="container-fluid" style="height: 715px">
            <div class="row">
                <div class="col-md-3">
                    <h3 style="margin-top: 1%; margin-bottom: 1%"> <a class="text-info" style="text-decoration: none" href="MyCourse"> <i class="bi bi-arrow-left"></i> Back </a></h3>
                    <h3>COURSE CONTENT</h3>
                    <div>
                        <%
                            int VideoNumber = 0;
                            int PDFNumber = 0;
                            for (Lesson lesson : listLesson) {
                        %>
                        <div class="list-group" style="margin: 15px 0">
                            <div class="list-group-item list-group-item-info" style="background-color: #06BBCC">
                                <div class="row">
                                    <!--Control card have same target and false is close -->
                                    <a id="lesson" data-bs-toggle="collapse" data-bs-target="#collapseExample<%=lesson.getLessonNo()%>" aria-controls="CollapseExample<%=lesson.getLessonNo()%>">
                                        <i class="bi bi-plus-circle-fill" style="margin-right: 3%"></i><%=lesson.getLessonNo()%>. <%=lesson.getLessonName()%>   
                                    </a>
                                </div>
                            </div>
                        </div>
                        <% // if find lesson
                            if (lesson.getLessonID() == LessonID) {
                        %>
                        <div class="list-group collapse multi-collapse show" id="collapseExample<%=lesson.getLessonNo()%>">
                            <%
                                for (LessonVideo video : listVideo) {
                                    // if find lesson
                                    if (lesson.getLessonID() == video.getLessonID()) {
                                        VideoNumber++;
                                        // if find video
                                        if (video.getVideoID() == VideoID) {
                            %>       
                            <div class="list-group-item list-group-item-primary">
                                <div class="row">
                                    <a href="Courses?service=LearnCourse&LessonID=<%=lesson.getLessonID()%>&VideoID=<%=video.getVideoID()%>&name=<%=video.getVideoName()%>&video=<%=video.getFileVideo()%>"  style="text-decoration: none; color: black">
                                        <i class="bi bi-play-circle" style="color: red; margin-right: 2%"></i>Video <%=VideoNumber%>. <%=video.getVideoName()%>
                                    </a>
                                </div>
                            </div>
                            <%} else {%>
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <a href="Courses?service=LearnCourse&LessonID=<%=lesson.getLessonID()%>&VideoID=<%=video.getVideoID()%>&name=<%=video.getVideoName()%>&video=<%=video.getFileVideo()%>"  style="text-decoration: none; color: black">
                                        <i class="bi bi-play-circle" style="color: red; margin-right: 2%"></i>Video <%=VideoNumber%>. <%=video.getVideoName()%>
                                    </a>
                                </div>
                            </div>
                            <%}%>
                            <%}%>
                            <%}%>

                            <%
                                for (LessonPDF PDF : listPDF) {
                                    // if find lesson
                                    if (lesson.getLessonID() == PDF.getLessonID()) {
                                        PDFNumber++;
                                        // if find PDF
                                        if (PDF.getPDFID() == PDFID) {
                            %>
                            <div class="list-group-item list-group-item-primary">
                                <div class="row">
                                    <a href="Courses?service=LearnCourse&LessonID=<%=lesson.getLessonID()%>&PDFID=<%=PDF.getPDFID()%>&name=<%=PDF.getPDFName()%>&PDF=<%=PDF.getFilePDF()%>"  style="text-decoration: none; color: black">
                                        <i class="bi bi-book" style="color: green; margin-right: 2%"></i>PDF <%=PDFNumber%>. <%=PDF.getPDFName()%>
                                    </a>
                                </div>
                            </div>
                            <%} else {%>
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <a href="Courses?service=LearnCourse&LessonID=<%=lesson.getLessonID()%>&PDFID=<%=PDF.getPDFID()%>&name=<%=PDF.getPDFName()%>&PDF=<%=PDF.getFilePDF()%>"  style="text-decoration: none; color: black">
                                        <i class="bi bi-book" style="color: green; margin-right: 2%"></i>PDF <%=PDFNumber%>. <%=PDF.getPDFName()%>
                                    </a>
                                </div>
                            </div>
                            <%}%>
                            <%}%>
                            <%}%>

                            <%
                                for (Integer lessonID : listLessonQuiz) {
                                    // if find lesson
                                    if (lesson.getLessonID() == lessonID) {
                            %>
                            <ul class="list-group">
                                <a href="TakeQuiz?LessonID=<%=lesson.getLessonID()%>" class="list-group-item bg-light"><i class="bi bi-patch-question" style="margin-right: 2%"></i>Take Quiz</a>
                            </ul>
                            <%}%>
                            <%}%>
                        </div>
                        <%} else {%>
                        <div class="list-group collapse multi-collapse" id="collapseExample<%=lesson.getLessonNo()%>">
                            <%
                                for (LessonVideo video : listVideo) {
                                    // if find lesson
                                    if (lesson.getLessonID() == video.getLessonID()) {
                                        VideoNumber++;
                            %>
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <a href="Courses?service=LearnCourse&LessonID=<%=lesson.getLessonID()%>&VideoID=<%=video.getVideoID()%>&name=<%=video.getVideoName()%>&video=<%=video.getFileVideo()%>"  style="text-decoration: none; color: black">
                                        <i class="bi bi-play-circle" style="color: red; margin-right: 2%"></i>Video <%=VideoNumber%>. <%=video.getVideoName()%>
                                    </a>
                                </div>
                            </div>
                            <%}%>
                            <%}%>

                            <%
                                for (LessonPDF PDF : listPDF) {
                                    // if find lesson
                                    if (lesson.getLessonID() == PDF.getLessonID()) {
                                        PDFNumber++;
                            %>
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <a href="Courses?service=LearnCourse&LessonID=<%=lesson.getLessonID()%>&PDFID=<%=PDF.getPDFID()%>&name=<%=PDF.getPDFName()%>&PDF=<%=PDF.getFilePDF()%>"  style="text-decoration: none; color: black">
                                        <i class="bi bi-book" style="color: green; margin-right: 2%"></i>PDF <%=PDFNumber%>. <%=PDF.getPDFName()%>
                                    </a>
                                </div>
                            </div>
                            <%}%>
                            <%}%>  

                            <%
                                for (Integer lessonID : listLessonQuiz) {
                                    // if find lesson
                                    if (lesson.getLessonID() == lessonID) {
                            %>
                            <ul class="list-group">
                                <a href="TakeQuiz?LessonID=<%=lesson.getLessonID()%>" class="list-group-item bg-light"><i class="bi bi-patch-question" style="margin-right: 2%"></i>Take Quiz</a>
                            </ul>
                            <%}%>
                            <%}%>
                        </div>
                        <%}%>
                        <%}%>
                    </div>

                </div>

                <div class="col-md-9">
                    <h3>Learning: <%=name%></h3>
                    <% // if choose link PDF
                        if (pdf != null) {
                    %>
                    <div class="mx-5">
                        <embed id="myPDF" src="./PDF/<%=pdf%>" width="98%" height="750px" type="application/pdf" alt="sorry">
                    </div>
                    <%}%>

                    <% // if choose link video
                        if (Video != null) {
                    %>
                    <video id="myVideo" controls autoplay>
                        <source src="/Video/<%=Video%>" type="video/mp4" >
                    </video>
                    <%}%>
                </div>
            </div>
        </div>

        <div class="container-fluid bg-info text-center py-2 footer"> 

        </div>

        <script>
            var vid = document.getElementById("myVideo");
            window.onload = function () {
                vid.autoplay = true;
                vid.load();
            }
        </script>

        <script>
            function Back() {
                history.back();
            }
        </script>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>


    </body>
</html>
