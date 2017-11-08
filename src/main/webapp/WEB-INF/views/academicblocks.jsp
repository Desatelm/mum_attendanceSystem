<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">Course List</div>
		<div class="panel-body">

			<div class="col-sm-12">
				<form action="../course/add" method="GET">
					<button type="submit" class="btn btn-success">Add New
						Block </button>
				</form>
				<h2>List Of Blocks</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Block ID</th>
							<th>Block Name</th>
							<th>Block StartDate</th>
							<th>Block EndDate</th>
							<th>Semester</th>
							<th>Action</th>
						</tr>

					</thead>
					<c:forEach items="${blocks}" var="block">
						<tr>
							<td>${block.id}</td>
							<td>${block.name}</td>
							<td>${block.beginDate}</td>
							<td>${block.endDate}</td>
							<td>${block.semester}</td>
							
							<td>
								<form action="../courseOffering/list/${block.beginDate}"
									method="GET">
									<button type="submit" class="btn btn-primary">get
										Course Offerings</button>
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