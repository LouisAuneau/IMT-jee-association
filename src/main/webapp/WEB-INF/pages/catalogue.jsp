<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

	<!--Include header (navbar + head)-->
	<%@ include file="../common/header.jsp" %>

	<div class="container">
	
	<h1 style="margin-bottom: 1em;">${requestScope['header']}</h1>
	
	<c:choose>
	<c:when test="${requestScope['catalogueVide'] == false}">
	
	<div class="row" style="background-color: white;">
		<c:forEach var="article" begin="0" items="${requestScope['articles']}">
			<div class="col-sm-4">
				<div class="card" style="margin: 10px;">
  					<div class="card-body">
    					<h4 class="card-title">${article.nom}</h4>
    					<h6 class="card-subtitle mb-2 text-muted">Stock : ${article.quantite}</h6>
    					<h6 class="card-subtitle mb-2 text-muted">Prix  : ${article.prix} €</h6>
    					<p class="card-text">${article.description}</p>
    					
    					<c:choose>
    					<c:when test="${article.quantite > 0}">
    						<form action="catalogue" method="post">
    							<input type="hidden" name="id" value="${article.code}">
    							<input type="hidden" name="stock" value="${article.quantite}">
    							<label>Quantité </label>
    							<input type="number" name="quantiteComm" min="1" max="${article.quantite}" value="1" required>
    							<button  class="btn btn-primary" type="submit">Commander</button>
    						</form>
    					</c:when>
    					<c:otherwise>
    						<p class="card-text" style="color: red;">Stock épuisé !</p>
    					</c:otherwise>
    					</c:choose>
    					
  					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	</c:when>
	</c:choose>
	
	</div>
	
</body>
</html>