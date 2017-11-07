<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

	<!--Include header (navbar + head)-->
	<%@ include file="../common/header.jsp" %>

	<div class="container">

        <h1>Accueil</h1>

        <p>Bienvenu sur votre espace personnel ! <br/>
            Vous pouvez :
        </p>

		<a href="<%=Routes.CATALOG.getRoutePath()%>">Consulter les articles disponibles</a><br/>
		<a href="<%=Routes.COMMAND.getRoutePath()%>">Consulter les articles disponibles</a><br/>
        <a href="#">Se déconnecter</a><br/>
	</div>

</body>
</html>