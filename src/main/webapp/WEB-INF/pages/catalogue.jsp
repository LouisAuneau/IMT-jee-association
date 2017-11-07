<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Associ'active - Catalogue</title>
</head>
<body>
	
	<%@ include file="../common/header.jsp" %>

	<div class="row" style="width: 90%; background-color: black;">
		<c:forEach var="article" begin="0" items="${requestScope['articles']}">
			<div class="col-sm-4">
				<div class="card" style="margin: 10px;">
  					<div class="card-body">
    					<h4 class="card-title">${article.nom}</h4>
    					<h6 class="card-subtitle mb-2 text-muted">Stock : ${article.quantite}</h6>
    					<h6 class="card-subtitle mb-2 text-muted">Prix  : ${article.prix} €</h6>
    					<p class="card-text">${article.description}</p>
    					<form action="catalogue" method="post">
    						<input type="hidden" name="id" value="${article.code}">
    						<input type="hidden" name="stock" value="${article.quantite}">
    						<input type="number" name="quantiteComm" min="0" max="${article.quantite}" value="1" required>
    						<button  class="btn btn-primary" type="submit">Commander</button>
    					</form>
  					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
</body>
</html>