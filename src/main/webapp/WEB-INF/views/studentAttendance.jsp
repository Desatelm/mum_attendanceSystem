<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                                <form action="../list" method="GET">
                                    
                                    <button type="submit" class="btn btn-primary">Back To List</button>
                                </form>
                            </td>

                        </tr>

                
                </table>
    <div class="panel panel-primary">
        <div class="panel-heading">My Attendance List</div>
        <div class="panel-body">

            <div class="col-sm-12" >
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>Student ID</th>
                        <th>Student Name</th>
                  <c:forEach items="${block.sessions}" var="session" >
                  
                        <th class="rotate"><div><span>${session.date}</span></div></th>
                        
                         </c:forEach>
                    </tr>

                    </thead>
                    <c:forEach items="${studentAttendance}" var="studentA" >
                  
                        <tr>
                            <td>${studentA.student.studentId}</td>
                            <td>${studentA.student.firstName}</td>
                      
                           
                            <c:forEach items="${studentA.attendance}" var="sttAtendance">
                            
                                   <td>
                                <c:if test="${sttAtendance==true}">
                                <span style="color:green" class="glyphicon glyphicon-ok"></span>
                                   <%-- <c:set var="present" value="${present + 1}" scope="page"/> --%>
                                </c:if>
                                <c:if test="${sttAtendance==false}">
                                   <%--  <c:set var="absent" value="${absent + 1}" scope="page"/> --%>
                                <span style="color:red"  class="glyphicon glyphicon-remove"></span>
                                </c:if>

                            </td>
 						 </c:forEach><td>  
 						 <form action="/studentAttendance/edit" method="GET">
                                    <input type="hidden" name="studentAtendance" value="${studentA}">
                                    <input type="hidden" name="block" value="${block}">
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
              
 						 </td>
                        </tr>
                        <tr>
                      
                        </tr>

                    </c:forEach>

                </table>

       

                <span class="label label-default">Attendance List</span>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>Session Date</th>
                        <th>Time Slot</th>
                        <th>Offering ID</th>
                        <th>Absent/Present</th>
                    </tr>

                    </thead>
                    <c:set var="present" value="0" scope="page" />
                    <c:set var="absent" value="0" scope="page" />

                    <c:forEach items="${tempSession}" var="tempsession">
                        <tr>
                            <td>${tempsession.sessiondate}</td>
                            <td>${tempsession.timeslot}</td>
                            <td>${tempsession.offeringid}</td>

                            <td>
                                <c:if test="${tempsession.attendance==true}">
                                <span style="color:green" class="glyphicon glyphicon-ok"></span>
                                   <c:set var="present" value="${present + 1}" scope="page"/>
                                </c:if>
                                <c:if test="${tempsession.attendance==false}">
                                    <c:set var="absent" value="${absent + 1}" scope="page"/>
                                <span style="color:red"  class="glyphicon glyphicon-remove"></span>
                                </c:if>

                            </td>

                        </tr>

                    </c:forEach>

                </table>
            <c:if test="${present!=0 || absent!=0}">
                <h1>Present: ${present}</h1>
                <h1>Absent: ${absent}</h1>
            </c:if>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/views/template/footer.jsp"%>