<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jee-association</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!--Conteneur principal-->
<div class="container">

    <!--Titre de la page-->
    <h1>Création d'un compte</h1>

    <!--Affichage des messages d'erreurs-->
    <c:choose>
        <c:when test="${noUsernameGiven}">
            <div class="alert alert-danger" role="alert">
                Erreur lors de la création de compte, merci d'indiquer votre identifiant.
            </div>
        </c:when>
        <c:when test="${noPasswordGiven}">
            <div class="alert alert-danger" role="alert">
                Erreur lors de la création de compte, merci d'indiquer votre mot de passe.
            </div>
        </c:when>
        <c:when test="${noPassword2Given}">
            <div class="alert alert-danger" role="alert">
                Erreur lors de la création de compte, merci d'indiquer votre mot de passe de confirmation.
            </div>
        </c:when>
        <c:when test="${noFirstNameGiven}">
            <div class="alert alert-danger" role="alert">
                Erreur lors de la création de compte, merci d'indiquer votre prénom.
            </div>
        </c:when>
        <c:when test="${noSecondNameGiven}">
            <div class="alert alert-danger" role="alert">
                Erreur lors de la création de compte, merci d'indiquer votre nom de famille.
            </div>
        </c:when>
        <c:when test="${noPasswordMatch}">
            <div class="alert alert-danger" role="alert">
                Erreur lors de la création de compte, les mots de passe ne correspondent pas.
            </div>
        </c:when>
    </c:choose>

    <!--Formulaire de connexion-->
    <form action="register" method="post">
        <div class="form-group">
            <label for="usernameInput">Identifiant <span class="badge badge-primary">* (Champ requis)</span></label>
            <input class="form-control" type="text" id="usernameInput" name="username" placeholder="noémie" value="${username}"/>
        </div>
        <div class="form-group">
            <label for="passwordInput">Mot de passe <span class="badge badge-primary">*</span></label>
            <input class="form-control" type="password" id="passwordInput" name="password" placeholder="mdp1234"/>
        </div>
        <div class="form-group">
            <label for="passwordInput2">Mot de passe (confirmation) <span class="badge badge-primary">*</span></label>
            <input class="form-control" type="password" id="passwordInput2" name="password2" placeholder="mdp1234"/>
        </div>
        <div class="form-group">
            <label for="secondName">Nom de famille <span class="badge badge-primary">*</span></label>
            <input class="form-control" type="text" id="secondName" name="secondName" placeholder="Favrelière" value="${secondName}"/>
        </div>
        <div class="form-group">
            <label for="firstName">Prénom <span class="badge badge-primary">*</span></label>
            <input class="form-control" type="text" id="firstName" name="firstName" placeholder="Noémie" value="${firstName}"/>
        </div>
        <div class="form-group">
            <label for="address">Adresse (rue)</label>
            <input class="form-control" type="text" id="address" name="address" placeholder="2 avenue des champs bleus" value="${address}"/>
        </div>
        <div class="form-group">
            <label for="postalCode">Code Postal</label>
            <input class="form-control" type="text" id="postalCode" name="postalCode" placeholder="77400" value="${postalCode}"/>
        </div>
        <div class="form-group">
            <label for="city">Ville</label>
            <input class="form-control" type="text" id="city" name="city" placeholder="Saint-Martin-de-Saint-Maixent" value="${city}"/>
        </div>
        <div class="form-group">
            <label for="country">Pays</label>
            <select class="form-control" id="country" name="country" value="${country}">
                <option></option>
                <c:forEach items="${countryList}" var="country">
                    <option><c:out value="${country}" /></option>
                </c:forEach>
            </select>
        </div>
        <button  class="btn btn-primary" type="submit">S'enregister</button>
    </form>

    <!--Lien d'accès à la page de login-->
    <div class="alert alert-primary" role="alert">
        Vous êtes déjà enregistré ? <a href="login">Connectez-vous</a>
    </div>

</div>
</body>
</html>
