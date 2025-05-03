
# Projet Java "2024_Olympics_Ticket_Office" (Sujet du Bloc3 du Bachelor Developpeur JAVA)

## Structure du projet
- `Backend/`: Contient l'API Spring Boot Java.
- `Frontend/`: Contient l'application Angular qui écoute le Json en sortie de backend et gère son affichage.

## Présentation

La présente application est une billetterie en ligne pour sélectionner et acheter des places aux différents évènements programmés pendant les jeux olympiques de Paris. 

Cette applciation se compose d'un Backend JAVA (API) et d'un Frontend Angular. Le projet suit le pattern MVC (Modèle - Vue Contrôleur). 

## Pour votre information

_ Commentaires
    Les commentaires ont été volontairement rédigés en anglais, certaines parties sont doublées en français. L'idée étant de suivre les recommandations de codage.

_ Respect des règles de rédaction : gestion des contraintes.
Le kamelCase pour les variables a été respecté le plus possible, seuls quelques cas de noms de variables composés de noms abrégés ont été délibéremment rédigés tout en minuscule, principalement pour forcer la bonne prise en compte syntaxique lors de la génération des tables MySQL.
Vous trouverez donc des underscores pour les Id. En effet lorsque les majuscules sont interprétées par MySQL elles sont le plus souvent (mais pas systématiquement) remplacées par des minuscules précédées d'un underscore, faisaint redouter que cela ne se passe pas forcément de la même façon sur différents setups.
D'où  fait que le kamelCase n'ait pas été respecté partout, l'enjeu étant que les datas présentes dans le fichier import.sql du dossier "resources" puissent être insérées dans les tables de la base de données. Les INSERT INTO contiennent les noms des champs et leur syntaxe doit être exactement la même que celle des champs des tables générées lors du lancement de l'application. 



Afin de pouvoir lancer le projet en local, vous devez avoir ces outils sur votre machine : 
- pour la partie back
    - [Java JDK 17](https://adoptium.net/temurin/releases/)
    - [Maven](https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.7-bin.zip)
- pour la partie front
    - [nodeJS (LTS) version 22.14.0](https://nodejs.org/en/download/)
    - [yarn](https://classic.yarnpkg.com/lang/en/docs/install/)
    - [Angular CLI version 19.2.5](https://www.npmjs.com/package/@angular/cli/v/19.2.5)
- pour la partie base de données 
    - créez vous un compte sur [AlwaysData](https://www.alwaysdata.com/fr/)
    - créez une base de données Mysql, puis, un utilisateur avec les droits sur cette base de données
    - récupérez les informations de connexion à la base de données (nom de la base, nom de l'utilisateur, mot de passe, adresse de la base de données)
      - dans le fichier application.properties du projet back, remplacez les valeurs actuelles par les valeurs récupérées précédemment

      IMPORTANT : veillez à désactiver les bloqueurs de publicités et pop-up pour le locahost, sans quoi vous aurez sans doute des problèmes pour accéder au contenu.

Je vous conseille les IDEs suivants :
- [IntelliJ](https://www.jetbrains.com/fr-fr/idea/download/) (pour le back)
- [WebStorm](https://www.jetbrains.com/fr-fr/webstorm/download/) (pour le front)
- [Visual Studio Code](https://code.visualstudio.com/)



## Utilisation

### Lancement du projet

Pour lancer le projet, il faut lancer les commandes suivantes dans l'ordre :
- `mvn clean install` dans le dossier Backend
- `yarn install` dans le dossier Frontend
- `yarn start` dans le dossier Frontend
- `mvn spring-boot:run` dans le dossier Backend
- ouvrir votre navigateur sur l'adresse http://localhost:4200

### Fonctionnalités

Pour les visiteurs :
- Sélectionner un évènement (cérémonie, match, course, etc)
- L'ajouter au panier
- Choisir une offre promotionnelle parmi les 3 proposées
- Modifier le panier
- Transformer le panier en achat
- Créer un compte pour conserver QRcode, détails des billets, et pouvoir gérer ses informations personnelles
- Consulter le QRcode de son billet, dans son espace personnel pour le présenter au contrôleur et ainsi accéder aux places achetées.


Pour les Gestionnaires :
- se connecter à l'espace d'administration pour pouvoir ajouter, modifier, supprimer le contenu du site (gestion des challengers, des offres, des ventes, etc)

## Technologies utilisées

SpringBoot version :                3.4.4
Maven version :                     3.8.6
JAVA version :                      JAVA JDK 17 LTS
Node version :                      22.14.0
Angular version :                   19.2.5 (CLI)

Les QRcodes sont générés via la dépendance Zxing.
La base de données choisie est MySQL.
Hibernate est présent dans ce projet pour la communication vers la base de données depuis les classes JAVA (Pojo)


## Technologies utilisées

Ce projet utilise Angular 19.2.5 en front, Spring Boot 3.4.4 en back-end


![Alt text](./Frontend/public/img/Volley-ball-women.jpg)