<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container">
    <div class="row">
    
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            
            <div class="account-wall"
			style="background: rgba(0, 0, 0, 0.3); color: white; border-radius: 5px;">
            	<h1 class="text-center login-title" style="color: white;" >Sign in to continue to Fire-TimeSheet</h1>
            	<br/>
                <img class="profile-img" src="/static/fire.png?sz=120"
                     alt="">
                <form id="login-form" action="<c:url value="/login" />" method="post" class="form-signin">
                	
                    <c:if test="${not empty error}">
                        <div class="error" style="color: #ff0000;">${error}</div>
                    </c:if>

                    <input name= "username" type="text" class="form-control" placeholder="Username" required autofocus
                           value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'>
                    <br>
                    <input name="password" type="password" class="form-control" placeholder="Password" required>
                    <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>--%>
                    <input class="btn btn-primary" type="submit" name="yt0" value="Login">
                    <input class="btn btn-danger btnClear" name="yt1" type="button" value="Clear" onclick="$('#login-form').trigger('reset');">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <p></p>
                    <p></p>
                    <p></p>
                    <label class="checkbox pull-left">
                        <input type="checkbox" value="remember-me">
                        Remember me
                    </label>
                    <a href="#" class="pull-right need-help" style="color: white;">Need help? </a><span class="clearfix"></span>
                    <a href="#" class="text-center new-account" style="color: white;">Contact system admin to get an account </a>
                </form>
            </div>
            
        </div>
    </div>
</div>

<footer style="margin-top:150px; ">
		<p align="center"><span style="background: rgba(0,0,0,0.4);color:white; border-radius:5px;padding:5px;">&copy; 2017 TeamFire, Inc.</span></p>
	</footer>

</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"><\/script>')
</script>
<script src="<c:url value="/static/js/custome.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
</body>
</html>
