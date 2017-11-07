<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%@ include file="../common/header.jsp" %>
	
	<div class="row" style="width: 90%; background-color: black;">
		<c:forEach var="achat" begin="0" items="${requestScope['achats']}">
			<div class="col-sm-4">
				<div class="card" style="margin: 10px;">
  					<div class="card-body">
    					<h4 class="card-title">${achat.id}</h4>
    					<h6 class="card-subtitle mb-2 text-muted">Stock : ${achat.quantite}</h6>
    					<%-- <h6 class="card-subtitle mb-2 text-muted">Prix  : ${achat.prix} €</h6> --%>
    					<%-- <p class="card-text">${achat.utilisateur2.identifiant}</p> --%>
    					<form action="catalogue" method="post">
    						<input type="number" min="0" max="${achat.quantite}" value="1" required>
    						<button  class="btn btn-primary" type="submit">Commander</button>
    					</form>
  					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>