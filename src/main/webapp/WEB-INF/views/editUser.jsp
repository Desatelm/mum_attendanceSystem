<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="container">
	<!-- Example row of columns -->
	<div class="panel panel-primary">
		<div class="panel-heading">Existing user role is ${user.roles.role} </div>
		<div class="panel-body">

			<div class="row">
				<div class="col-sm-6">
					<c:if test="${user.roles.role == 'STUDENT'}">
						<form action="../../changTofacultyRole/${user.id}" method="POST">
							<div class="form-group">
								<label for="id">User ID:</label> <input type="text"
									class="form-control" id="id" name="id" value="${user.id}" readonly>
							</div>
							<div class="form-group">
								<label for="id">User Name:</label> <input type="text"
									class="form-control" id="name" name="username"
									value=" ${user.name}" readonly>
							</div>
							<div class="form-group">
								<select name="role">
									<option value="STUDENT">STUDENT</option>
									<option value="FACULTY">FACULTY</option>
									<option value="STAFF">STAFF</option>
								</select>
							</div>

							<div class="form-group">
								<label for="fname">First Name:</label> <input type="text"
									class="form-control" id="fname" name="firstName"
									value="${student.firstName}" readonly>
							</div>
							<div class="form-group">
								<label for="lname">Last Name:</label> <input type="text"
									class="form-control" id="lname" name="lastName"
									value="${student.lastName}" readonly>
							</div>
							<div class="form-group">
								<label for="bcode">Student Barcode:</label> <input type="text"
									class="form-control" id="bcode" name="barcode"
									value="${student.barcode}">
							</div>
							<button type="submit" class="btn btn-default">Update
								User</button>
						</form>
					</c:if>
					<c:if test="${user.roles.role == 'FACULTY'}">
						<form action="../../changeToStudentRole/${user.id}" method="POST">
							<div class="form-group">
								<label for="id">User ID:</label> <input type="text"
									class="form-control" id="id" name="id" value="${user.id}" readonly>
							</div>
							<div class="form-group">
								<label for="id">User Name:</label> <input type="text"
									class="form-control" id="name" name="name"
									value=" ${user.name}" readonly>
							</div>
							<div class="form-group">
								<select name="role">
									<option value="STUDENT">STUDENT</option>
									<option value="FACULTY">FACULTY</option>
									<option value="STAFF">STAFF</option>
								</select>
							</div>

							<div class="form-group">
								<label for="fname">First Name:</label> <input type="text"
									class="form-control" id="fname" name="firstName"
									value="${faculty.firstName}" readonly>
							</div>
							<div class="form-group">
								<label for="lname">Last Name:</label> <input type="text"
									class="form-control" id="lname" name="lastName"
									value="${faculty.lastName}" readonly>
							</div>
							<div class="form-group">
								<label for="bcode">Faculty ID:</label> <input type="text"
									class="form-control" id="bcode" name="facultyId"
									value="${faculty.id}" readonly>
							</div>
							<button type="submit" class="btn btn-default">Update
								User</button>
						</form>
					</c:if>



				</div>
			</div>


		</div>
	</div>

<div class="clearfix"></div>	
	<a href="/student/find"> Home</a>
	</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>
