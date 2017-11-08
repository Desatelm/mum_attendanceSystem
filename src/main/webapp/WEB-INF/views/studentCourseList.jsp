<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">Course List</div>
		<div class="panel-body panel-clear">

			<div class="col-sm-12">
				
				
				<c:if test="${param.attendance =='none'}">
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						Sorry no attendance records for the selected course s
					</div>
				</c:if>


				<table class="table table-hover">
					<thead>
						<tr>
							<th>Course name</th>
							<th>Course period</th>
							<th>startDate</th>
							<th>Action</th>
						</tr>

					</thead>
					<c:forEach items="${enrolledCourses}" var="enrolledCourse">
						<tr>
							<td>${enrolledCourse.offering.course}/
								${enrolledCourse.offering.course.name }</td>
							<td>${enrolledCourse.offering.period}</td>
							<td>${enrolledCourse.offering.startDate}</td>
							<td>
								<form
									action="../courseOffering/student/${enrolledCourse.offering.id}"
									method="GET">
									<button type="submit" class="btn btn-primary">view my attendace </button>
								</form>
							</td>
							<%--
            <form action="../course/delete/${course.courseId}" method="GET">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>


        </td> --%>

						</tr>

					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>