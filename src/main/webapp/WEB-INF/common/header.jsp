<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Jee-association</title>
    <link type="text/css" rel="stylesheet" href="../../css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="../../css/bootstrap-grid.min.css" />
    <link type="text/css" rel="stylesheet" href="../../css/bootstrap-reboot.min.css" />
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="margin-bottom: 40px">
        <button class="navbar-toggler navbar-toggler-right"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
  		<a class="navbar-brand" href="home">Associ'active</a>

  		<div class="collapse navbar-collapse" id="navbarSupportedContent">
    		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">

				<c:choose>
                    <c:when test="${sessionType == 'LOGGED_IN_SESSION'}">

                        <c:choose>
                            <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/'
                                            && requestScope['javax.servlet.forward.request_uri'] != '/home'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="home">Accueil </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item active">
                                    <a class="nav-link" href="home">Accueil <span class="sr-only">(current)</span></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/catalogue'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="catalogue">Catalogue </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item active">
                                    <a class="nav-link" href="catalogue">Catalogue <span class="sr-only">(current)</span></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/commande'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="commande">Commande </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item active">
                                    <a class="nav-link" href="commande">Commande <span class="sr-only">(current)</span></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                    </c:when>
                    <c:otherwise>

                        <c:choose>
                            <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/login'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="login">Connexion </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item active">
                                    <a class="nav-link" href="login">Connexion  <span class="sr-only">(current)</span></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${requestScope['javax.servlet.forward.request_uri'] != '/register'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="register">Inscription </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item active">
                                    <a class="nav-link" href="register">Inscription <span class="sr-only">(current)</span></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                    </c:otherwise>
                </c:choose>
    		</ul>
  		</div>

	</nav>
</body>
</html>