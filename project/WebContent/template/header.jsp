<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>SUPINFO</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="/project/css/bootstrap.css" rel="stylesheet">
<link href="/project/css/jquery.jqplot.min.css" rel="stylesheet">

<link href="/project/css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="/project/ico/favicon.png">
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<a class="brand" href="/project/auth/listUrl">SUPINFO</a>
			
			
			<ul class="nav">
				<li><a href="/project/auth/listUrl">Accueil</a></li>

			</ul>
			<ul class="nav pull-right">
				<c:if test="${connected == true }">
				<li><a href="/project/logout">Déconnexion</a></li>
				</c:if>
				<c:if test="${empty connected}">
				<li><a href="/project/login.jsp">Login</a></li>
				<li><a href="/project/inscription.jsp">Inscription</a></li>
				</c:if>
				
			</ul>
		</div>
	</div>
	
	<div class="container">
	