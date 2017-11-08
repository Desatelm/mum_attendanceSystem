<%@include file="/WEB-INF/views/template/secureheader.jsp"%>
<div class="container">
    <!-- Example row of columns -->
    <div class="panel panel-primary">
        <div class="panel-heading">Edit Attendance</div>
            <div class="panel-body">

    <div class="row">
        <div class="col-sm-6">
            <form action="../../student/edit/${student.id}" method="POST" >
                <div class="form-group">
                    <label for="id">Student Id:</label>
                    <input type="text" class="form-control" id="id" name="studentID" value="${studentA.student.studentId}">
                </div>
                <div class="form-group">
                    <label for="fname">First Name:</label>
                    <input type="text" class="form-control" id="fname" name="firstName" value="${studentA.student.firstName}">
                </div>
                <div class="form-group">
                    <label for="lname">CourseOffering ID:</label>
                    <input type="text" class="form-control" id="lname" name="lastName" value="${studentA.courseOffering.id}">
                </div>
                 <c:forEach items="${studentA.attendance}" var="sttAtendance">
                <div class="form-group">
                    <label for="bcode">Student Barcode:</label>
                    <input type="text" class="form-control" id="bcode" name="barCode" value="${sttAtendance}">
                </div>
</c:forEach>
                <button type="submit" class="btn btn-default">Update Student</button>
            </form>
        </div>
    </div>


</div>
</div>
</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>
