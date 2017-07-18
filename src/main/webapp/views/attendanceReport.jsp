
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Attendance System</title>
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

</head>
<body>
	<div class='container'>
		<h2>Attendance List</h2>
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
		<ul class="nav navbar-nav">
				<li><a href="/students">Student List</a></li>
					<li><form action="/course" method="post">
							<input type="hidden" name="lastName"
								value="${pageContext.request.userPrincipal.name}" /> 
								<input type="submit" value="Course List">
						</form></li>					
				</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/" />"><span
						class="glyphicon glyphicon glyphicon-home"></span> Home</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
		                ${pageContext.request.userPrincipal.name}</c:if></a></li>
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
		</nav>
		<table class="table table-striped, table table-hover table table-condensed table-bordered">
			<tr>

				<th>First Name</th>
				<th>Last Name</th>
				<th>Student Id</th>
				<th>Meditaion ExtraCredit</th>
				<th>Meditaion Percentage</th>
				<th>Course Offering</th>

			</tr>
			<c:forEach var="studentAttendance" items="${studentsAttendance}">

				<tr>
					<td>${studentAttendance.student.firstName}</td>
					<td>${studentAttendance.student.lastName}</td>
					<td>${studentAttendance.student.studentId}</td>
					<td>${studentAttendance.meditationExtraGrade}</td>
					<td>${studentAttendance.meditaionPercentage}</td>
					<td>${studentAttendance.courseOffering.course.abbr}</td>


				</tr>

			</c:forEach>
			<tr>
				<th colspan="1">Faculty Name</th>
				<td colspan="1">${faculty}</td>
				<td colspan="2">Average group meditation</td>
				<td colspan="1">${average}%</td>
			</tr>
		</table>
	</div>

</body>
</html>
