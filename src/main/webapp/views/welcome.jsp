<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attendance System</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="../CSS/admin.css" rel="stylesheet">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<style>
body {
	/* background-image: url("/images/img5.jpe"); */
	background-color: #cccccc;
}
</style>
</head>
<body>
	<div class="page-header">
		<h1 style="color: blue;">
			WelCome <small style="color: blue;">|Student Attendance
				System !!</small>
		</h1>
	</div>

	<div class='container'>
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand active">OnlineStudentAttendance</a>
			</div>
			<security:authorize access="hasRole('STAFF')">
				<ul class="nav navbar-nav">
					<li><a href="/students">Student List</a></li>
				</ul>
			</security:authorize>

			<security:authorize access="hasRole('Student')">
				<ul class="nav navbar-nav">
					<li><form action="/course" method="post">
							<input type="hidden" name="lastName"
								value="${pageContext.request.userPrincipal.name}" /> <input
								type="submit" value="Course List">
						</form></li>
				</ul>
			</security:authorize>

			<security:authorize access="hasRole('Student')">
				<ul class="nav navbar-nav">
					<li><form action="/User" method="post">
							<input type="text" name="userId" /> <input type="submit"
								value="Search">
						</form></li>
				</ul>
			</security:authorize>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
		 ${name}</c:if></a></li>
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>

		</div>
		</nav>


		<div class="row">
			<c:forEach var="offered" items="${courseOfferings}">
				<ul>
					<li><a href="../attendance/${offered.id}">
							${offered.course.name}(${offered.course.abbr}
							${offered.startDate})</a></li>
				</ul>
			</c:forEach>
		</div>

	</div>
</body>
</html>