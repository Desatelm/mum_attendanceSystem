<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <title>TeamFire TimeSheet Web Application</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/main.css"/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value="/static/css/jumbotron.css"/>"  rel="stylesheet">    
    <link href="<c:url value="/static/css/panels.css"/>"  rel="stylesheet">
    <link href="<c:url value="/static/css/profile.css"/>"  rel="stylesheet">

</head>

<body style="background-image: url(/static/home-min.jpg); height: 60vh; background-attachment: fixed; background-size: auto 100vh;background-repeat: no-repeat;margin-bottom:40px;">


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Fire-TimeSheet</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
         <form action="/login" class="navbar-form navbar-right">
            <!-- <form action="/courseOffering/list" class="navbar-form navbar-right"> -->
                <button type="submit" class="btn btn-success">Sign in</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>