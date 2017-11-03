CREATE TABLE Utilisateur (
    identifiant VARCHAR(255) NOT NULL PRIMARY KEY,
    password varchar(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    adresse LONG VARCHAR,
    city VARCHAR(255),
    code_postal VARCHAR(64),
    pays VARCHAR(255)
);

CREATE TABLE Article (
    code CHAR(2) NOT NULL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prix FLOAT(2),
   	description LONG VARCHAR NOT NULL,
   	quantite INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT CODE_CK CHECK (code LIKE '[A-Z][0-9]')
);

CREATE TABLE Achat (
    id INTEGER NOT NULL 
        PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
        (START WITH 1, INCREMENT BY 1),
    article CHAR(2) NOT NULL,
    utilisateur VARCHAR(255) NOT NULL,
    quantite INTEGER NOT NULL DEFAULT 1,
    CONSTRAINT UTILISATEUR_FK
        FOREIGN KEY (utilisateur)
        REFERENCES Utilisateur(identifiant),
    CONSTRAINT ARTICLE_FK
        FOREIGN KEY (article)
        REFERENCES Article(code),
     CONSTRAINT QUANTITE_CK CHECK (quantite > 0)
);