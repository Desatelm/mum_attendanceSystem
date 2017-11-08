<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">Student List</div>
		<div class="panel-body">

			<div class="col-sm-12">
			<form action="../student/get" method="GET">
					<input type="search" class="form-control" id="id" name="studentId" placeholder="enter studentId to search...">
					<button type="submit" class="btn btn-success">Search
						Student By StudentID</button>
				</form>
				<%-- <form action="../user/get" method="GET">
					<input type="search" class="form-control" id="searchUser" name="userName" placeholder="enter username to search...">
					<span style='color:red' id = "error">${error}</span>
					<button type="submit" class="btn btn-success">Search
						User By UserName</button>
				</form>	 --%>
				  <sec:authorize access="hasRole('ROLE_ADMIN')">
					<input type="search" class="form-control" id="username" name="userName" placeholder="enter username to search...">
					<button class="btn btn-success" id="searchUser">Search User By UserName</button>
					<p style='color:red' id = "error">${error}</p>
					<div id = 'userInfo'></div>
                    <div id = 'details'></div>				
					</sec:authorize>
					
				<form action="../student/list" method="GET">
					<input type="search" class="form-control" id="id" name="entryDate" placeholder="enter entry date to search all the students in the entry ...">
					<button type="submit" class="btn btn-success">Search All
						Students By Entry</button>
				</form>
				
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Student ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Student Email</th>
							<th>Action</th>
						</tr>

					</thead>

					<tr>
						<td>${student.studentId}</td>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.emailaddress}</td>
						<td>
							<form action="/student/CourselistWithId/${student.studentId}" method="GET">
							
								<button type="submit" class="btn btn-primary">View Student Courses</button>
								
							</form>



						</td>

					</tr>



				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>