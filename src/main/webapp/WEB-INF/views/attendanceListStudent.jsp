<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>

<div class="container">
	<span class="label label-default">Course Offering Information</span>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>Offering ID</th>
				<th>Course ID</th>
				<th>Course Name</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>View Attendance</th>
			</tr>

		</thead>

		<tr>
			<td>${studentAttendance.get(0).courseOffering.id}</td>
			<td>${studentAttendance.get(0).courseOffering.course.number}</td>
			<td>${studentAttendance.get(0).courseOffering.course.name}</td>

			<td>${block.beginDate}</td>
			<td>${block.endDate}</td>
			<td>
				<sec:authorize access="hasRole('ROLE_FACULTY')">
				<form action="/faculty/courseList" method="GET">

					<button type="submit" class="btn btn-primary">Back To List</button>
				</form>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_STAFF','ROLE_ADMIN')">
				<form action="/getallblocks" method="GET">

					<button type="submit" class="btn btn-primary">Back To List</button>
				</form>
				</sec:authorize>
				<form action="/courseOffering/getreport/${studentAttendance.get(0).courseOffering.id}" method="GET">

					<button type="submit" class="btn btn-primary">Print PDF Report</button>
				</form>
			</td>

		</tr>


	</table>

	<div class="panel panel-primary">
		<div class="panel-heading">My Attendance List</div>
		<div class="panel-body">

			<div class="col-sm-12">
				<table class="table table-hover table-bordered" id="table">
				
					<thead>
						<tr>
							<th>Student ID</th>
							<th>Student Name</th>
							<c:forEach items="${block.sessions}" var="session">
								<c:set var="sdate" value="${session.date}" />
								<th class="rotate"><div><span>${sdate}</span></div></th>

							</c:forEach>
							<th>% <br> Points</th>
						</tr>

					</thead>
					<tbody>
						<c:forEach items="${studentAttendance}" var="studentA"
							varStatus="iterstu">

							<tr>
								<td>${studentA.student.studentId}</td>
								<td>${studentA.student.firstName}</td>


								<c:forEach items="${studentA.attendance}" var="sttAtendance"
									varStatus="iteratt">

									<td class="attendance"
										data-id="${iteratt.index}id${iterstu.index}"
										id-student="${iteratt.index}studentId${iterstu.index}"
										id-session="${iteratt.index}sessionId${iterstu.index}"
										data-student="${studentAttendance[iterstu.index].student.studentId}"
										data-date="${block.sessions[iteratt.index].date}"><c:if
											test="${sttAtendance==true}">
											<span style="color: green" class="glyphicon glyphicon-ok"></span>

										</c:if> <c:if test="${sttAtendance==false}">

											<span style="color: red" class="glyphicon glyphicon-remove"></span>
										</c:if>
										
										<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')">
										<form action="/attendance/update" method="GET">
										<input type="text" class="hidden" name="atendanceType" value="many">
											<input type="text" class=" hidden form-control"
												id="${iteratt.index}OfferingId${iterstu.index}" name="offeringId"
												value="${studentAttendance.get(0).courseOffering.id}">
											<input type="text" class=" hidden form-control"
												id="${iteratt.index}studentId${iterstu.index}" name="studentId" value=""> <input
												type="text" class=" hidden form-control" id="${iteratt.index}sessionId${iterstu.index}"
												name="recordDate" value="">


											<button type="submit" class=" hidden btn btn-xs btn-success"
												id="${iteratt.index}id${iterstu.index}">
												<span class="glyphicon glyphicon-pencil"></span>
											</button>


										</form>
										
										</sec:authorize>
										</td>

								</c:forEach>
								<td>${studentA.meditaionPercentage} % <br> ${studentA.meditationExtraGrade } P</td>
							</tr>
							
						</c:forEach>
					</tbody>
				</table>




			</div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>