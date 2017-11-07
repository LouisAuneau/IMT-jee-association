<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

	<!--Include header (navbar + head)-->
	<%@ include file="../common/header.jsp" %>
	
	<div class="container">
	
	<h1 style="margin-bottom: 1em;">${requestScope['header']}</h1>
	
	<c:choose>
	<c:when test="${requestScope['commandeNonVide'] == true}">
	
	<div class="row" style="background-color: white;">
	
		<div class="container">
			<form action="commande" method="post">
				<input type="hidden" name="typeAction" value="1">
    			<button  class="btn btn-primary" type="submit">Abandonner la commande</button>
    		</form>
		</div>
    	
		<c:forEach var="achat" begin="0" items="${requestScope['achats']}">
			<div class="col-sm-4">
				<div class="card" style="margin: 10px;">
  					<div class="card-body">
    					<h4 class="card-title">${achat.article2.nom}</h4>
    					<h6 class="card-subtitle mb-2 text-muted">Quantité : ${achat.quantite}</h6>
    					<h6 class="card-subtitle mb-2 text-muted">Prix  : ${achat.article2.prix} €</h6>
    					<p class="card-text">${achat.article2.description}</p>
    					<form action="commande" method="post">
    						<input type="hidden" name="typeAction" value="0">
    						<input type="hidden" name="idAchat" value="${achat.id}">
    						<input type="hidden" name="codeArticle" value="${achat.article2.code}">
    						<input type="hidden" name="quantiteArticle" value="${achat.quantite}">
    						<button  class="btn btn-primary" type="submit">Retirer</button>
    					</form>
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