# jee-association
Site d'une association avec gestion des utilisateurs et de produits.

# Procedure de creation de la base de donnees

- Lancer Derby avec le fichier  ```startNetworkServer.bat``` (ou .sh sous linux).


- Lancer l'utilitaire ```ij.bat``` (.sh sous Linux) dans le dossier *bin* de Derby.
- Taper les commandes suivantes pour creer la base ainsi qu'un utilisateur :
```
CONNECT 'jdbc:derby:jee-association;create=true';
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.user.ADMIN', 'PASSWORD');
```
- Fermer l'utilitaire.


- Lancer Squirel et ajouter le driver de Derby avec la configuration suivante :
```
Name        : Derby driver
Exemple URL : jdbc:derby://localhost:1527/DBNAME
JAR         : derbyclient-10.11.1.1.jar (in folder "drivers-jdbc")
Class Name  : org.apache.derby.jdbc.ClientDriver
```
- Ajouter l'Alias suivant :
```
Name        : jee-association
Driver      : Derby driver
URL         : jdbc:derby://localhost:1527/jee-association
Username    : LOGIN
Password    : PASSWORD
```

Vous pouvez ensuite administrer la base depuis Squirel.
