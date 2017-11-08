<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <title>TeamFire TimeSheet Web Application</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/main.css"/>" rel="stylesheet">
    <!-- Custom styles for this template -->
     <link href="<c:url value="/static/css/custom.css"/>"  rel="stylesheet">
    <link href="<c:url value="/static/css/jumbotron.css"/>"  rel="stylesheet">
    <link href="<c:url value="/static/css/panels.css"/>"  rel="stylesheet">
    <script src="<c:url value="/static/js/user.js"/>"></script>
</head>

<body style="background-image: url(/static/welcome.jpg);  height: 60vh; background-attachment: fixed; background-size: 100vw 100vh;background-repeat: no-repeat;margin-bottom:40px;">

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Fire-TimeSheet</a>
        </div>

        <ul class="nav navbar-nav">
            <sec:authorize access="hasRole('ROLE_STUDENT')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Student <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"></a></li>
                    <li><a href="/student/Courselist">My CourseList</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">My Profile</a></li>
                </ul>
            </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_FACULTY')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Faculty <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"></a></li>
                    <li><a href="/faculty/courseList">Course List</a></li>
                    <li role="separator" class="divider"></li>
                    
                    
                </ul>
            </li>

            </sec:authorize>
             <sec:authorize access="hasRole('ROLE_STAFF')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">STAFF <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"></a></li>
                    <li><a href="/student/find">Find Student</a></li>
                    <li role="separator" class="divider"></li>                  
                    <li><a href="/getallblocks">Manage Attendance</a></li>
                    <li role="separator" class="divider"></li>
                    
                </ul>
            </li>

            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"></a></li>
                    <li><a href="/student/find">Manage User</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="/getallblocks">Manage Attendance</a></li>
                </ul>
            </li>
            
            </sec:authorize>
        </ul>
        <sec:authentication var="principal" property="principal" />
        <div id="navbar" class="navbar-collapse collapse">
            <form action="/login" class="navbar-form navbar-right">
                <sec:authorize access="isAuthenticated()">
                   <a class="navbar-brand" href="../user/get/${username}"> <span class="glyphicon glyphicon-user"> ${lastName} </span></a>
                </sec:authorize>

                <button type="submit" class="btn btn-success">Sign Out</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>

<div style="height:50px;"></div>	