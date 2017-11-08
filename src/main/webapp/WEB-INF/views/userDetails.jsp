<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">


	<fieldset class="col-md-4">
		<legend>
			<span class="text-info">${user.roles.role}</span> <span
				class="text-primary">User </span>
		</legend>
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table table-striped table table-hover">
					
					<tr>
						<td>UserId: ${user.id}</td>
					</tr>
					<tr>
						<td>User Name: ${user.name}</td>
					</tr>

					<tr>
						<td>Role: ${user.roles.role}</td>
					</tr>
					<c:if test="${user.roles.role == 'STUDENT'}">					
					
						<tr>
							<td>Student ID: ${student.studentId}</td></tr>
						<tr><td>First Name: ${student.firstName}</td></tr>
						<tr><td>Last Name: ${student.lastName}</td></tr>
						<tr><td>Student Email: ${student.emailaddress}</td></tr>
						<tr><td>Entry Date: ${student.entryDate}<td></tr>				

					</c:if>
					
				  <c:if test="${user.roles.role == 'FACULTY'}">				
						<tr>
							<td>Faculty ID: ${faculty.id}</td>
						<tr><td>First Name: ${faculty.firstName}</td></tr>
						<tr><td>Last Name: ${faculty.lastName}</td>							
						</tr>				
                   </c:if>
					<tr>												
						<td>
							<form action="../../get" method="GET">
							<input type="hidden" class="form-control" id="id" name="userName" value="${user.name}" >
								<button type="submit" class="btn btn-primary">less Details</button>								
							</form>
						</td>
					</tr>
				</table>				
			</div>
		</div>
	</fieldset>
	<div class="clearfix"></div>	
	<a href="/student/find"> Home</a>
	</div>


</body>

<%@include file="/WEB-INF/views/template/footer.jsp"%>