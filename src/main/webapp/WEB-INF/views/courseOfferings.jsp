<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">Course List</div>
		<div class="panel-body">

			<div class="col-sm-12">
				<form action="../course/add" method="GET">
					<button type="submit" class="btn btn-success">Add New
						Course Offering</button>
				</form>
				<h2>List Of Course Offerings</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Course name</th>
							<th>Course period</th>
							<th>startDate</th>
							<th>Action</th>
						</tr>

					</thead>
					<c:forEach items="${courseOfferings}" var="courseOffering">
						<tr>
							<td>${courseOffering.course.name}</td>
							<td>${courseOffering.period}</td>
							<td>${courseOffering.startDate}</td>
							<td>
								<form action="/courseOffering/getrecord/${courseOffering.id}"
									method="GET">
									<button type="submit" class="btn btn-primary">get
										attendace record</button>
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