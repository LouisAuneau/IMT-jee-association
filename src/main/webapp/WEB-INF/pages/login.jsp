<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

    <!--Include header (navbar + head)-->
	<%@ include file="../common/header.jsp" %>

    <!--Conteneur principal-->
    <div class="container">

        <!--Titre de la page-->
        <h1>Connexion</h1>

        <!--Affichage des messages d'erreurs-->
        <c:choose>
            <c:when test="${noUsernameGiven}">
                <div class="alert alert-danger" role="alert">
                    Erreur lors de la connexion, merci d'indiquer votre identifiant.
                </div>
            </c:when>
            <c:when test="${noPasswordGiven}">
                <div class="alert alert-danger" role="alert">
                    Erreur lors de la connexion, merci d'indiquer votre mot de passe.
                </div>
            </c:when>
            <c:when test="${connexionFails}">
                <div class="alert alert-danger" role="alert">
                    Erreur lors de la connexion, les informations fournies ne permettent pas de vous identifier.
                </div>
            </c:when>
        </c:choose>

        <!--Formulaire de connexion-->
        <form action="<%=Routes.LOGIN.getRoutePath()%>" method="post">
            <div class="form-group">
                <label for="usernameInput">Identifiant</label>
                <input class="form-control" type="text" id="usernameInput" name="username" placeholder="noémie" value="${username}"/>
            </div>
            <div class="form-group">
                <label for="passwordInput">Mot de passe</label>
                <input class="form-control" type="password" id="passwordInput" name="password" placeholder="mdp1234"/>
            </div>
            <button  class="btn btn-primary" type="submit">Se connecter</button>
        </form>

        <!--Lien d'accès à la page de création de compte-->
        <div class="alert alert-primary" role="alert">
            Pas encore enregistré ? <a href="<%=Routes.REGISTER.getRoutePath()%>">Créer votre compte</a>
        </div>

    </div>

</body>
</html>
