<%-- 
    Document   : ViewLesson
    Created on : Aug 18, 2022, 2:31:35 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="Entity.LessonPDF"%>
<%@page import="Entity.LessonVideo"%>
<%@page import="Entity.Lesson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            #lessonContent{
                background-color: #EEEEEE; 
                overflow-y: scroll; 
                height: 820px
            }
            #myVideo {
                width: 80%;
                height: 700px;
                margin-left: 10%;

            }
        </style>
    </head>
    <body>

        <!-- Navbar Start -->
        <%@ include file="component/navbar.jsp" %>
        <!-- Navbar End -->
        <%
            String message = (String) request.getAttribute("message");
            String mess = (String) request.getAttribute("mess");
            List<Lesson> listLesson = (List<Lesson>) request.getAttribute("listLesson");
            List<LessonVideo> listVideo = (List<LessonVideo>) request.getAttribute("listVideo");
            List<LessonPDF> listPDF = (List<LessonPDF>) request.getAttribute("listPDF");
            int LessonID = (Integer) request.getAttribute("lID");
            String name = (String) request.getAttribute("name");
            String Video = (String) request.getAttribute("video");
            String pdf = (String) request.getAttribute("PDF");
        %>

        <div class="container-fluid">
            <div class="row" style="border-right: 1px solid black">
                <div id="lessonContent" class="col-md-3" style="background-color: #EEEEEE; border-right: 1px solid black">
                    <h3 style="margin-top: 1%; margin-bottom: 1%"><a href="MyTeachingCourse" style="color: #06BBCC; text-decoration: none"> <i class="bi bi-arrow-left"></i> Back</a></h3>
                    <h3>COURSE CONTENT</h3>
                    <div class="teacher row">
                        <h5 style="color: red"><%=message == null ? "" : message%></h5>
                        <h5 style="color: green"><%=mess == null ? "" : mess%></h5>
                        <div class="col-md-4">
                            <button class="btn btn-info" style="color: black" data-bs-toggle="collapse" data-bs-target="#addLesson" aria-expanded="false" aria-controls="addLesson"> <i class="bi bi-folder-plus"></i> lesson</button>
                        </div>
                        <form action="ManagerLesson" method="POST">
                            <input type="hidden" name="service" value="AddLesson" />
                            <div class="card card-body collapse" id="addLesson" style="margin-top: 3%">
                                <input type="text" name="LessonName"  placeholder="Enter Lesson Name" required/>
                                <input type="submit" value="Submit" />
                            </div>
                        </form>
                    </div>

                    <div>
                        <%
                            for (Lesson lesson : listLesson) {
                        %>
                        <div class="list-group" style="margin: 15px 0">
                            <div class="list-group-item list-group-item-info" style="background-color: #06BBCC">
                                <div class="row">
                                    <div class="col-md-10">
                                        <!--Control card have same target and false is close -->
                                        <a style="background-color: #06BBCC; color: white; text-decoration: none; cursor: pointer" data-bs-toggle="collapse" data-bs-target="#collapseExample<%=lesson.getLessonNo()%>" aria-expanded="false" aria-controls="CollapseExample<%=lesson.getLessonNo()%>">
                                            <i class="bi bi-plus-circle-fill" style="margin-right: 3%"></i><%=lesson.getLessonNo()%>. <%=lesson.getLessonName()%> 
                                        </a>
                                    </div>
                                    <div class="col-md-2 teacher">
                                        <a style="float: right;" href="ManagerLesson?service=DeleteLesson&LessonID=<%=lesson.getLessonID()%>">
                                            <i onclick="return confirm('Are you sure you want to delete lesson: <%=lesson.getLessonName()%>')" style="color:red" class="bi bi-x">
                                            </i>
                                        </a>
                                        <a style="float: right; margin-right: 2%" type="button" data-bs-toggle="collapse" data-bs-target="#updateLesson<%=lesson.getLessonID()%>" aria-expanded="false" aria-controls="updateLesson<%=lesson.getLessonID()%>"><i class="bi bi-pencil" style="color: green;"></i></a>
                                    </div>
                                </div>

                                <!--Form update Lesson -->
                                <form action="ManagerLesson" method="POST">
                                    <input type="hidden" name="service" value="UpdateLesson" />
                                    <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                    <input type="hidden" name="OldName" value="<%=lesson.getLessonName()%>" />
                                    <div class="card card-body collapse" id="updateLesson<%=lesson.getLessonID()%>" style="margin-top: 1%">
                                        Lesson Name:<input type="text" name="LessonName"  required value="<%=lesson.getLessonName()%>"/>   
                                        <input type="submit" value="Submit" />
                                    </div>
                                </form>
                            </div>
                        </div>

                        <%
                            if (lesson.getLessonID() == LessonID) {
                        %>        
                        <div class="list-group collapse multi-collapse show" id="collapseExample<%=lesson.getLessonNo()%>">
                            <div class="teacher">
                                <div class="row" style="margin-bottom: 2%">
                                    <div class="col-md-4"><button style="background-color: #f0fbfc; border-radius: 10%" data-bs-toggle="collapse" data-bs-target="#addVideo<%=lesson.getLessonNo()%>" aria-expanded="false" aria-controls="addVideo<%=lesson.getLessonNo()%>">Add Video</button></div>
                                    <div class="col-md-4"><button style="background-color: #f0fbfc; border-radius: 10%" data-bs-toggle="collapse" data-bs-target="#addPDF<%=lesson.getLessonNo()%>" aria-expanded="false" aria-controls="addPDF<%=lesson.getLessonNo()%>">Add PDF</button></div>
                                    <div class="col-md-3">
                                        <button class="btn btn-info"><a style="text-decoration: none; color: black" href="ManagerQuiz?LessonID=<%=lesson.getLessonID()%>"><i class="bi bi-eye"></i> Quiz</a></button>
                                    </div>
                                </div>

                                <!--form add video-->
                                <form  action="ManagerVideo" method="POST">
                                    <input type="hidden" name="service" value="AddVideo" />
                                    <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                    <div class="card card-body collapse" id="addVideo<%=lesson.getLessonNo()%>" style="margin-top: 1%">
                                        <input type="text" name="VideoName" required value="" placeholder="Enter Video Name"/>
                                        <input type="file" name="FileVideo" required multiple accept=".mp4">
                                        <input type="submit" value="Submit" />
                                    </div>
                                </form>

                                <!--form add pdf-->
                                <form action="ManagerPDF" method="POST">
                                    <input type="hidden" name="service" value="AddPDF" />
                                    <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                    <div class="card card-body collapse" id="addPDF<%=lesson.getLessonNo()%>" style="margin-top: 1%">
                                        <input type="text" name="PDFName"  required value="" placeholder="Enter PDF Name"/>
                                        <input type="file" id="file-uploader" required name="FilePDF" accept=".pdf" multiple>
                                        <input type="submit" value="Submit" />
                                    </div>
                                </form>
                            </div>

                            <% 
                                for (LessonVideo video : listVideo) {
                                    if (lesson.getLessonID() == video.getLessonID()) {
                            %>            
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <div class="col-md-10">
                                        <a href="ManagerVideo?VideoID=<%=video.getVideoID()%>" style="text-decoration: none; color: black">
                                            <i class="bi bi-play-circle" style="color: red; margin-right: 2%"></i><%=video.getVideoName()%>
                                        </a>
                                    </div>
                                    <div class="col-md-2 teacher">
                                        <a style="float: right;" href="ManagerVideo?service=DeleteVideo&VideoID=<%=video.getVideoID()%>"><i onclick="return confirm('Are you sure you want to delete Video: <%=video.getVideoName()%>')" class="bi bi-x" style="color: red"></i></a>
                                        <a style="float: right; margin-right: 2%" type="button" data-bs-toggle="collapse" data-bs-target="#updateVideo<%=video.getVideoID()%>" aria-expanded="false" aria-controls="updateVideo<%=video.getVideoID()%>"><i class="bi bi-pencil" style="color: green;"></i></a>
                                    </div>
                                </div>
                            </div>

                            <form action="ManagerVideo" method="POST">
                                <input type="hidden" name="service" value="UpdateVideo" />
                                <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                <input type="hidden" name="VideoID" value="<%=video.getVideoID()%>" />
                                <input type="hidden" name="oldVideoName" value="<%=video.getVideoName()%>" />
                                <input type="hidden" name="oldFileVideo" value="<%=video.getFileVideo()%>" />
                                <div class="card card-body collapse" id="updateVideo<%=video.getVideoID()%>" style="margin-top: 1%">
                                    Name:<input type="text" name="VideoName" value="<%=video.getVideoName()%>" required/>
                                    New File Video:<input type="file" name="FileVideo" accept=".mp4" multiple/>
                                    <input type="submit" value="Submit" />
                                </div>
                            </form>
                            <%}%>
                            <%}%>

                            <%
                                for (LessonPDF PDF : listPDF) {
                                    if (lesson.getLessonID() == PDF.getLessonID()) {
                            %>
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <div class="col-md-10" >
                                        <a href="ManagerPDF?PDFID=<%=PDF.getPDFID()%>" style="text-decoration: none; color: black"> 
                                            <i class="bi bi-book" style="color: green; margin-right: 2%"></i><%=PDF.getPDFName()%>
                                        </a>
                                    </div>
                                    <div class="col-md-2 teacher" >
                                        <a style="float: right;" href="ManagerPDF?service=DeletePDF&PDFID=<%=PDF.getPDFID()%>"><i onclick="return confirm('Are you sure you want to delete pdf <%=PDF.getPDFName()%>')" class="bi bi-x" style="color: red"></i></a>
                                        <a style="float: right; margin-right: 2%" href="ManagerPDF?service=UpdatePDF" data-bs-toggle="collapse" data-bs-target="#updateVideo<%=PDF.getPDFID()%>" aria-expanded="false" aria-controls="updateVideo<%=PDF.getPDFID()%>"><i class="bi bi-pencil" style="color: green;"></i></a>
                                    </div>
                                </div>
                            </div>

                            <form action="ManagerPDF" method="POST">
                                <input type="hidden" name="service" value="UpdatePDF" />
                                <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                <input type="hidden" name="PDFID" value="<%=PDF.getPDFID()%>" />
                                <input type="hidden" name="oldFilePDF" value="<%=PDF.getFilePDF()%>" />
                                <input type="hidden" name="oldName" value="<%=PDF.getPDFName()%>" />
                                <div class="card card-body collapse" id="updateVideo<%=PDF.getPDFID()%>" style="margin-top: 1%">
                                    Name:<input type="text" name="PDFName" value="<%=PDF.getPDFName()%>" required/>
                                    New File PDF:<input type="file" name="FilePDF" accept=".pdf" multiple/>
                                    <input type="submit" value="Submit" />
                                </div>
                            </form>         
                            <%}%>
                            <%}%>

                        </div>
                        <%} else {%>
                        <!-- -->
                        <div class="list-group collapse multi-collapse" id="collapseExample<%=lesson.getLessonNo()%>">
                            <div class="teacher">
                                <div class="row" style="margin-bottom: 2%">
                                    <div class="col-md-4"><button style="background-color: #f0fbfc; border-radius: 10%" data-bs-toggle="collapse" data-bs-target="#addVideo<%=lesson.getLessonNo()%>" aria-expanded="false" aria-controls="addVideo<%=lesson.getLessonNo()%>">Add Video</button></div>
                                    <div class="col-md-4"><button style="background-color: #f0fbfc; border-radius: 10%" data-bs-toggle="collapse" data-bs-target="#addPDF<%=lesson.getLessonNo()%>" aria-expanded="false" aria-controls="addPDF<%=lesson.getLessonNo()%>">Add PDF</button></div>
                                    <div class="col-md-3">
                                        <button class="btn btn-info"><a style="text-decoration: none; color: black" href="ManagerQuiz?LessonID=<%=lesson.getLessonID()%>"><i class="bi bi-eye"></i> Quiz</a></button>
                                    </div>
                                </div>

                                <!--form add video-->
                                <form action="ManagerVideo" method="POST">
                                    <input type="hidden" name="service" value="AddVideo" />
                                    <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                    <div class="card card-body collapse" id="addVideo<%=lesson.getLessonNo()%>" style="margin-top: 1%">
                                        <input type="text" name="VideoName" required value="" placeholder="Enter Video Name"/>
                                        <input type="file" name="FileVideo" required  multiple accept=".mp4">
                                        <input type="submit" value="Submit" />
                                    </div>
                                </form>

                                <!--form add pdf-->
                                <form action="ManagerPDF" method="POST">
                                    <input type="hidden" name="service" value="AddPDF" />
                                    <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                    <div class="card card-body collapse" id="addPDF<%=lesson.getLessonNo()%>" style="margin-top: 1%">
                                        <input type="text" name="PDFName"  required value="" placeholder="Enter PDF Name"/>
                                        <input type="file" id="file-uploader" required name="FilePDF" accept=".pdf" multiple>
                                        <input type="submit" value="Submit" />
                                    </div>
                                </form>
                            </div>

                            <% 
                                for (LessonVideo video : listVideo) {
                                    if (lesson.getLessonID() == video.getLessonID()) {
                            %>
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <div class="col-md-10">
                                        <a href="ManagerVideo?VideoID=<%=video.getVideoID()%>" style="text-decoration: none; color: black">
                                            <i class="bi bi-play-circle" style="color: red; margin-right: 2%"></i><%=video.getVideoName()%>
                                        </a>
                                    </div>
                                    <div class="col-md-2 teacher">
                                        <a style="float: right;" href="ManagerVideo?service=DeleteVideo&VideoID=<%=video.getVideoID()%>"><i onclick="return confirm('Are you sure you want to delete Video: <%=video.getVideoName()%>')" class="bi bi-x" style="color: red"></i></a>
                                        <a style="float: right; margin-right: 2%" type="button" data-bs-toggle="collapse" data-bs-target="#updateVideo<%=video.getVideoID()%>" aria-expanded="false" aria-controls="updateVideo<%=video.getVideoID()%>"><i class="bi bi-pencil" style="color: green;"></i></a>
                                    </div>
                                </div>
                            </div>

                            <form action="ManagerVideo" method="POST">
                                <input type="hidden" name="service" value="UpdateVideo" />
                                <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                <input type="hidden" name="oldVideoName" value="<%=video.getVideoName()%>" />
                                <input type="hidden" name="VideoID" value="<%=video.getVideoID()%>" />
                                <input type="hidden" name="oldFileVideo" value="<%=video.getFileVideo()%>" />
                                <div class="card card-body collapse" id="updateVideo<%=video.getVideoID()%>" style="margin-top: 1%">
                                    Name:<input type="text" name="VideoName" value="<%=video.getVideoName()%>" required/>
                                    New File Video:<input type="file" name="FileVideo" accept=".mp4" multiple/>
                                    <input type="submit" value="Submit" />
                                </div>
                            </form>
                            <%}%>                
                            <%}%>

                            <%  
                                for (LessonPDF PDF : listPDF) {
                                    if (lesson.getLessonID() == PDF.getLessonID()) {
                            %>
                            <div class="list-group-item list-group-item-action">
                                <div class="row">
                                    <div class="col-md-10" >
                                        <a href="ManagerPDF?PDFID=<%=PDF.getPDFID()%>" style="text-decoration: none; color: black"> 
                                            <i class="bi bi-book" style="color: green; margin-right: 2%"></i><%=PDF.getPDFName()%>
                                        </a>
                                    </div>
                                    <div class="col-md-2 teacher" >
                                        <a style="float: right;" href="ManagerPDF?service=DeletePDF&PDFID=<%=PDF.getPDFID()%>"><i onclick="return confirm('Are you sure you want to delete pdf <%=PDF.getPDFName()%>')" class="bi bi-x" style="color: red"></i></a>
                                        <a style="float: right; margin-right: 2%" href="ManagerPDF?service=UpdatePDF" data-bs-toggle="collapse" data-bs-target="#updateVideo<%=PDF.getPDFID()%>" aria-expanded="false" aria-controls="updateVideo<%=PDF.getPDFID()%>"><i class="bi bi-pencil" style="color: green;"></i></a>
                                    </div>
                                </div>
                            </div>

                            <form action="ManagerPDF" method="POST">
                                <input type="hidden" name="service" value="UpdatePDF" />
                                <input type="hidden" name="LessonID" value="<%=lesson.getLessonID()%>" />
                                <input type="hidden" name="PDFID" value="<%=PDF.getPDFID()%>" />
                                <input type="hidden" name="oldName" value="<%=PDF.getPDFName()%>" />
                                <input type="hidden" name="oldFilePDF" value="<%=PDF.getFilePDF()%>" />
                                <div class="card card-body collapse" id="updateVideo<%=PDF.getPDFID()%>" style="margin-top: 1%">
                                    Name:<input type="text" name="PDFName" value="<%=PDF.getPDFName()%>" required/>
                                    New File PDF:<input type="file" name="FilePDF" accept=".pdf" multiple />
                                    <input type="submit" value="Submit" />
                                </div>
                            </form>
                            <%}%>  
                            <%}%>                
                        </div>
                        <%}%>
                        <%}%>
                    </div>
                </div>

                <div class="col-md-9" style="background-color: #f0fbfc" >
                    <h3 style="text-align: center; margin-top: 2%">Learning: <%=name == null ? "" : name%></h3>
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
                        <source src="./Video/<%=Video%>" type="video/mp4" >
                    </video>
                    <%}%>
                </div>
            </div>
        </div>     

        <!-- Footer Start -->
        <section>
            <%@include file="component/footer.jsp" %>
        </section>
        <!-- Footer End --> 

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    </body>
</html>
