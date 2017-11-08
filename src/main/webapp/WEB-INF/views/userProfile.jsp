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

<div class="col-md-offset-1 col-lg-6"
			style="background: rgba(0, 0, 0, 0.3); color: white; border-radius: 5px;">
	<fieldset class="col-md-6" style="color: white;" >
		<legend>
			<span class="text-info" style="color: white;">${user.roles.role}</span> <span
				class="text-primary" style="color: white;">User </span>
		</legend>
		<div class="panel panel-default" style="color: white;">
			<div class="panel-body">
				<table class="table table-striped table table-condensed">
					
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
						<td><a href="../../user/editStudent/${user.studentId}/${user.name}">Details:</a>
						</td>
					</tr>					
					</c:if>
					<c:if test="${user.roles.role == 'FACULTY'}">
					<tr>
						<td>
						<a href="../../user/editFaculty//${user.facultyId}/${user.name}"> edit</a> 
						</td>
					</tr>
					</c:if>

				</table>
			</div>
		</div>
	</fieldset>
	<div class="clearfix"></div>	
	<a style="color: white;"href="/student/find"> Home</a>
	</div>
</div>

</body>

<%@include file="/WEB-INF/views/template/footer.jsp"%>