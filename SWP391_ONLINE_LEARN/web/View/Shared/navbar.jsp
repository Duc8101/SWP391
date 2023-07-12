<%@page import="Const.ConstValue"%>
<%@page import="Entity.Teacher"%>
<%@page import="Entity.Student"%>
<%@page import="Model.DAOTeacher"%>
<%@page import="Model.DAOStudent"%>
<%@page import="Entity.Account"%>
<%
    DAOStudent daoStudent = new DAOStudent();
    DAOTeacher daoTeacher = new DAOTeacher();
    Account account = (Account) session.getAttribute("account");
    String role = account == null ? null : account.getRoleName();
    String d_none1 = role == null || role.equals(ConstValue.ROLE_STUDENT) ? "d-none" : "";
    String d_none2 = role == null || role.equals(ConstValue.ROLE_STUDENT) ? "" : "d-none";
    String d_none3 = role != null && role.equals(ConstValue.ROLE_TEACHER) ? "" : "d-none";
%>
<nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
    <a href="Home" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <h2 class="m-0 text-info"><i class="fa fa-book me-3"></i>eLEARNING</h2>
    </a>
    <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
            <a href="Home" class="nav-item nav-link">Home</a>
            <a href="About" class="nav-item nav-link">About</a>
            <a href="ManagerCourse?service=CreateCourse" class="nav-item nav-link <%=d_none1%>">Create Course</a>
            <%  // if not login or login as student
                if (account == null || account.getRoleName().equals(ConstValue.ROLE_STUDENT)) {
            %>
            <a href="Courses" class="nav-item nav-link">Courses</a>
            <%}%>
        </div>   
    </div>

    <%
        String image = ConstValue.AVATAR;
        // if not login
        if (account == null) {
    %>
    <a href="Login" class="btn btn-info py-4 px-lg-5 d-none d-lg-block text-white">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
        <%} else {
            if (role.equals(ConstValue.ROLE_STUDENT)) {
                Student student = daoStudent.getStudent(account.getUsername());
                image = student.getImage();
            } else {
                Teacher teacher = daoTeacher.getTeacher(account.getUsername());
                image = teacher.getImage();
            }
        %>
    <div class="nav-item dropdown">
        <a href="#" class="btn btn-info py-4 px-lg-5 text-white"><img src="<%=image%>" alt="Avatar" class="avatar">  <%=account.getUsername()%></a>
        <div class="dropdown-menu fade-down m-0">
            <a href="Profile" class="dropdown-item">Profile</a>
            <a href="MyCourse" class="dropdown-item <%=d_none2%>">My Course</a>
            <a href="ManagerCourse" class="dropdown-item <%=d_none3%>">My Teaching Course</a>
            <a href="Logout" class="dropdown-item">Log Out <i class="bi bi-box-arrow-right"></i></a>
        </div>
    </div>
    <%}%>

</nav>