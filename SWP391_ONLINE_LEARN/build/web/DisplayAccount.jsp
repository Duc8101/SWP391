<%@page import="Model.DAOPayCourse"%>
<%@page import="Model.DAOCourse"%>
<%@page import="Entity.Course"%>
<%@page import="Entity.Account"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en">
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
    </head>
    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->
        <%  // get attribute
            String name = (String) request.getAttribute("name");
            String message = (String) request.getAttribute("message");
            String mess = (String) request.getAttribute("mess");
            List<Account> list = (List<Account>) request.getAttribute("list");
        %>
        <nav class="navbar navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand"><i class="bi bi-person-circle"></i> WELCOME TO ADMIN</a>
                <form class="d-flex" action="ManagerAccount">
                    <input type="hidden" name="service" value="SearchAccount">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="name" value ="<%=name == null ? "" : name%>" required>                   
                    <button class="btn btn-outline-success" type="submit" >Search</button>
                </form>
            </div>
        </nav>

        <section style="margin-top: 50px">
            <div class="row vh-100" >
                <div class="col-lg-3">
                    <ul class="list-group">
                        <li class="list-group-item list-group-item-info"><a href="ManagerAccount">Account Management</a></li>
                        <li class="list-group-item list-group-item-info"><a href="ManagerAccount?service=CreateAccount">Create Account</a></li>
                    </ul>
                    <p style="color:red;text-align: center"><%=message == null ? "" : message%></p>
                    <p style="color:green;text-align: center"><%=mess == null ? "" : mess%></p>
                    <br>
                    <a href="Logout" class="btn btn-lg btn-primary position-fixed bottom-0 start-0 ">Logout <i class="bi bi-box-arrow-right"></i></a>
                </div>

                <div class="col-lg-9">
                    <table class="table table-info">
                        <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Username</th>
                                <th scope="col">RoleName</th>
                                <th scope="col">Edit| Remove</th>
                                <th scope="col">View Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int STT = 0;
                                DAOCourse daoCourse = new DAOCourse();
                                DAOPayCourse daoPay = new DAOPayCourse();
                                boolean check = true;
                                if (list != null) {
                                    for (Account account : list) {
                                        if (!account.getRoleName().equals(Account.ROLE_ADMIN)) {
                                            STT++;
                                            boolean isStudent = true;
                                            if (account.getRoleName().equals(Account.ROLE_TEACHER)) {
                                                List<Course> listCourse = daoCourse.getListCourse(account.getRoleName(), account.getUsername());
                                                check = daoPay.CheckStudentExist(listCourse);
                                                isStudent = false;
                                            }
                            %>
                            <tr>
                                <th scope="row"><%=STT%></th>
                                <td><%=account.getUsername()%></td>
                                <td><%=account.getRoleName()%></td>
                                <td>
                                    <%
                                        if (account.getRoleName().equals(Account.ROLE_STUDENT)) {
                                    %>
                                    <a href="ManagerAccount?service=EditAccount&STT=<%=STT%>&username=<%=account.getUsername()%>">Edit</a>| 
                                    <%}%>
                                    <%
                                        if (isStudent || !check) {
                                    %>
                                    <a href="ManagerAccount?service=RemoveAccount&username=<%=account.getUsername()%>" onclick="return confirm('Are you sure you want to delete?')">Remove</a>
                                    <%}%>
                                </td>
                                <td><a href="ManagerAccount?service=ViewAccount&username=<%=account.getUsername()%>&STT=<%=STT%>">View</a></td>
                            </tr>
                            <%}%> 
                            <%}%>
                            <%}%>
                        </tbody>
                    </table>

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
