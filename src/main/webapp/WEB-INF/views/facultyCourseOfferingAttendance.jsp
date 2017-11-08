<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="row">
	<div class="col-md-10 col-md-offset-1">

		<table class="table ">
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

					<form action="/faculty/courseList" method="GET">

						<button type="submit" class="btn btn-primary">Back To
							List</button>
					</form>
				</td>

			</tr>


		</table>


		<div class="panel panel-primary">
			<div class="panel-heading">My Attendance s</div>
			<div class="panel-body panel-clear ">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Student ID</th>
						<th>Student Name</th> 
							<c:forEach items="${block.sessions}" var="session">
								<th class="verticaltext">
									${session.date.toString().substring(5)}</th>

							</c:forEach>
							<th>% <br> Points</th>
						</tr>

					</thead>
					<c:forEach items="${studentAttendance}" var="studentA">

						<tr>
							<td>${studentA.student.studentId}</td>
						<td>${studentA.student.firstName}</td> 


							<c:forEach items="${studentA.attendance}" var="sttAtendance" varStatus="iter">
								
								<td data-date="${block.sessions[iter.count - 1].date}" 
									class="attendance" 
									data-student="${studentA.student.studentId}"
									
									><c:if test="${sttAtendance==true}">
										<span style="color: green" class="glyphicon glyphicon-ok"></span>
										<%-- <c:set var="present" value="${present + 1}" scope="page"/> --%>
									</c:if> <c:if test="${sttAtendance==false}">
										<%--  <c:set var="absent" value="${absent + 1}" scope="page"/> --%>
										<span style="color: red" class="glyphicon glyphicon-remove"></span>
									</c:if></td>
							</c:forEach>
							<td>${studentA.meditaionPercentage} % <br> ${studentA.meditationExtraGrade } P</td>
						</tr>
						

					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>