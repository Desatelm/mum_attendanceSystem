<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">Student List</div>
		<div class="panel-body">

			<div class="col-sm-12">
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Student ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Student Email</th>
							<th>Action</th>
						</tr>

					</thead>
					<c:forEach items="${students}" var="student">
						<tr>
							<td>${student.studentId}</td>
							<td>${student.firstName}</td>
							<td>${student.lastName}</td>
							<td>${student.emailaddress}</td>
							<td>
								
								<form action="/student/CourselistWithId/${student.studentId}"
									method="GET">

									<button type="submit" class="btn btn-primary">View
										Student Courses</button>

								</form>

							</td>

						</tr>

					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>