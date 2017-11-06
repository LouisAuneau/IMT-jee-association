<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  		<a class="navbar-brand" href="#">Associ'active</a>
  		<button class="navbar-toggler" 
  				type="button" 
  				data-toggle="collapse" 
  				data-target="#navbarNavAltMarkup" 
  				aria-controls="navbarNavAltMarkup" 
  				aria-expanded="false" 
  				aria-label="Toggle navigation"
  		>
    		<span class="navbar-toggler-icon"></span>
  		</button>
  		<!-- <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    		<div class="navbar-nav">
      			<a class="nav-item nav-link active" href="#">Accueil <span class="sr-only">(current)</span></a>
      			<a class="nav-item nav-link" href="/jee-association/">Catalogue</a>
      			<a class="nav-item nav-link" href="#">Panier</a>
    		</div>
  		</div> -->
  		<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      			<li class="nav-item active">
        			<a class="nav-link" href="#">Accueil <span class="sr-only">(current)</span></a>
      			</li>
      			<li class="nav-item">
        			<a class="nav-link" href="#">Catalogue</a>
      			</li>
      			<li class="nav-item">
        			<a class="nav-link" href="#">Panier</a>
      			</li>
    		</ul>
    		<form class="form-inline my-2 my-lg-0">
      			<input class="form-control mr-sm-2" type="search" placeholder="Search">
      			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    		</form>
  		</div>
	</nav>
</body>
</html>