
# Projet Java "2024_Olympics_Ticket_Office" (Sujet du Bloc3 du Bachelor Developpeur JAVA)

## L'API en images 

Comme une image vaut mille mots, voici à quoi ressemble l'application :

![Alt text](./Frontend/public/img/Accueil.png)

![Alt text](./Frontend/public/img/Games.png)

![Alt text](./Frontend/public/img/Games-Modal.png)

![Alt text](./Frontend/public/img/Offers.png)

![Alt text](./Frontend/public/img/Informations.png)

![Alt text](./Frontend/public/img/Security-Policy)

![Alt text](./Frontend/public/img/Login.png)

![Alt text](./Frontend/public/img/Account.png)

![Alt text](./Frontend/public/img/Login.png)

![Alt text](./Frontend/public/img/Cart-to-complete.png)

![Alt text](./Frontend/public/img/Completed-Cart.png)

![Alt text](./Frontend/public/img/Completed-Cart-Selected.png)

![Alt text](./Frontend/public/img/Complete-Carte-step1.png)

![Alt text](./Frontend/public/img/Complete-Carte-step2.png)

![Alt text](./Frontend/public/img/Complete-Carte-step3.png)

![Alt text](./Frontend/public/img/Complete-Carte-Summary.png)

![Alt text](./Frontend/public/img/Completed-Cart-Selected.png)

![Alt text](./Frontend/public/img/Completed-Cart-Payment.png)

![Alt text](./Frontend/public/img/Completed-Cart-Offers-Soldpng.png)


## Structure du projet
- `Backend/`: Contient l'API Spring Boot Java.
- `Frontend/`: Contient l'application Angular qui écoute le Json en sortie de backend et gère son affichage.

## Présentation

La présente application est une billetterie en ligne pour sélectionner et acheter des places aux différents évènements programmés pendant les jeux olympiques de Paris. 

Cette application se compose d'un Backend JAVA (API) et d'un Frontend Angular. Le projet suit le pattern MVC (Modèle - Vue Contrôleur). 

Bonne visite de mon application !

## Navigation

La page d'accueil de l'API est la page "Games" qui charge les cérémonies et les jeux. Vous pouvez afficher tous les détails de chaque carte via le bouton "More Details".
Un filtre vous permet également de cibler un sport, une date, le nom d'un sportif, les jeux de type paralympique, etc.

La seconde page "Offers" présente les offres Solo, Duo et Family, qu'il est possible d'ajouter au panier. Le pourcentage spécifique à l'offre choisie sera pris en compte pour le calcul du montant à régler.

La troisième page est la page "Informations" qui donne quelques informations utiles.

La dernière page est "Account" qui sert à tous type de compte pour se connecter, voir son panier, modifier ses informations personnelles, accéder à son/ses tickets achetés (à présenter au personnel de sécurité des Jeux Olympiques pour accéder à votre place), et si vous avez les droits spécifiques (administrateur ou employés), vous pourrez modifier et créer d'autres offres, et consulter le nombre de ventes.

Les politiques de sécurités sont accessibles depuis :
- un lien dédié dans le Footer du site
- la page de création de compte (Register), puisqu'il est obligatoire de les accepter pour s'enregistrer.


## Fonctionnalités

Pour les visiteurs :
- Voir l'ensemble de tous les évènements (cérémonie, match, course, etc)
- filter les évènements via un filtre de recherche
- Ouvrir un évènement en particulier (cérémonie, match, course, etc)
- L'ajouter au panier
- Choisir une offre promotionnelle parmi les 3 proposées
- Modifier le panier
- Transformer le panier en achat
- Créer un compte pour consulter le QRcode et autres détails des billets achetés, et pouvoir gérer ses informations personnelles


Pour les Gestionnaires :
- se connecter à l'espace d'administration 
- Créer, Ajouter, modifier, supprimer les offres 
- Consulter les ventes


## Comment lancer le projet ?

### Lancement de l'application SANS docker :
Cela revient à lancer l'application comme le développeur le fait pour tester son code.

0) Base de données : soit vous avez un server MySQL lancé sur votre machine, et dans ce cas, créez une base "jo2024" avec les identifiants et mots de passe que vous trouverez dans le fichier application.properties dans le dossier Backend du Projet, soit vous vous crééz unede la même façon la base de données MySQL chez un hebergeur (ex : AlwaysData)

1) Ouvrir le projet avec votre IDE favori. Ici pour l'exemple ce sera à l'aide de VSCode.

   - ouvrir un terminal: dans VSCode c'est en tapant SHIFT+CTRL+Y (puis aller dans l'onglet TERMINAL).

   - taper : cd ./backend   (+ ENTER) : cela vous positionne dans le dossier Backend de votre projet. Pour vérifier votre emplacement taper : pwd (+ ENTER)

   - taper : mvn spring-boot:run "-Dspring.profiles.active=local"

<!-- OLD : 
   - taper : 
   mysql -u rootMyr25 -p < src/main/resources/init-database.sql + ENTER
   Puis :
   mvn clean spring-boot:run "-Dspring.profiles.active=local"
   sinon : 
   mvn spring-boot:run "-Dspring.profiles.active=local"  (+ ENTER) et attendre que la fin du BUILD SUCCESS indiqué par "Application started" -->

   - taper : cd .. (+ ENTER) pour remonter à la racine du projet
   - taper : cd ./frontend (+ ENTER) pour votre rendre dans le frontend
   - taper : ng serve (+ ENTER)  pour lancer Angular qui va construire le frontend.

2) Aller dans votre navigateur à l'adresse :
   http://localhost:4200

   L'application va s'affichier, vous serez automatiquement redirigé sur http://localhost:4200/games
     
### Lancement de l'application AVEC docker :
Cela permet d'être certain que l'application va se lancer, puisqu'avec Docker nous sommes affranchis des problèmatiques de version des dépendances, des applications et du système d'exploitation.
Pour cela :

- Si vous avez congiguré un server MySQL ou tout autre server de bases de données sur l'ordinateur que vous utilisez, pensez à stopper le service MySQL ou autre (Xampp, Wamp, etc), qui utiliserait déjà le port 3306. Pour se faire, par exemple sur windows dans une fenêtre de commande tapez "services.msc" et dans la la fenêtre qui s'ouvre, allez à la ligne MySQL, faites clic-droit et choisissez "Arrêter".  
Sinon si vous n'y arrivez pas, pour libérer le port 3306 vous devezouvrir un terminal en mode administrateur sur votre machine et taper :
netstat -ano | findstr :3306
Si le port est utilisé, un code PID vous est indiqué, il suffira de taper ensuite : 
kill <PID>.
Si vous rejouez la commande précédente netstat -ano | findstr :3306, elle ne renverra cette fois aucune information.

- Docker Desktop doit être lancé/ouvert

- ouvrir un terminal à la racine du projet : dans VSCode c'est en tapant SHIFT+CTRL+Y (puis aller dans l'onglet TERMINAL).

- taper :
"docker-compose up -d; docker-compose logs -f startup-check"  (sans les guillemets bien sûr)

Attendre la fin de toutes les étapes de build de l'application

- aller dans votre navigateur à l'adresse :
   http://localhost:80 ou http://localhost
   L'application va s'afficher


### Les outils indispensables :

Afin de pouvoir lancer le projet en local, vous devez avoir ces outils sur votre machine. Je vous détaille ici les versions utilisées : 
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

      IMPORTANT (2): ne pas modifier ou supprimer la secret key définie dans applciations.properties (dossier "resources" du backend), car sans elle les comptes utilisateurs, employés et administrateurs créés ne seront plus accessibles. D'ailleurs il faut également ne pas supprimer ni modifier le fichier import.sql qui se trouve dans le même dossier, pour les mêmes raisons.

Je vous conseille les IDEs suivants :
- [IntelliJ](https://www.jetbrains.com/fr-fr/idea/download/) (pour le back)
- [WebStorm](https://www.jetbrains.com/fr-fr/webstorm/download/) (pour le front)
- [Visual Studio Code](https://code.visualstudio.com/)



### Mots de passe des comptes sur l'API :

Si vous consultez le fichier import.sql qui contient les INSERT INTO qui alimentent la base de données au démarrage, vous constaterez que les mots de passe utilisateurs sont cryptés. C'est bien le cas, via PasswordEncoder.

Voici donc les mots de passe en clair pour pouvoir utiliser l'application :

- Compte Administrateur global : (role_id : 1)
Username : Superad-Min
Password : p@sswordnot$ecur3dAdmin

- Compte Employé : (role_id : 2)
Username : Empl-Oyee
Password : passwordnotsecuredemployee

- Comptes utilisateurs sans provilèges : (role_id : 3)
Username : Bond-James
Password : p@s$wordn0tseQrd007

Username : Fournier-Myriam
Password : passwordnotsecured1



### Commandes de lancement :

Pour lancer le projet, il faut lancer les commandes suivantes dans l'ordre :
- `mvn clean install` dans le dossier Backend
- `mvn spring-boot:run` dans le dossier Backend
- `yarn install` dans le dossier Frontend
- `yarn start` dans le dossier Frontend
- ouvrir votre navigateur sur l'adresse http://localhost:4200



## Technologies utilisées

SpringBoot version :                3.4.4
Maven version :                     3.8.6
JAVA version :                      JAVA JDK 17 LTS
Node version :                      22.14.0
Angular version :                   19.2.5 (CLI)

Les QRcodes sont générés via la dépendance Zxing.
La base de données choisie est MySQL.
Hibernate est présent dans ce projet pour la communication vers la base de données depuis les classes JAVA (Pojo)


## Pour votre information

### Commentaires dans le code :

    Les commentaires ont été volontairement rédigés en anglais, certaines parties sont doublées en français. L'idée étant de suivre les recommandations de codage.

### Respect des règles de rédaction : gestion des contraintes.

Le kamelCase pour les variables a été respecté le plus possible, seuls quelques cas de noms de variables composés de noms abrégés ont été délibéremment rédigés tout en minuscule, principalement pour forcer la bonne prise en compte syntaxique lors de la génération des tables MySQL.
Vous trouverez donc des underscores pour les Id. En effet lorsque les majuscules sont interprétées par MySQL elles sont le plus souvent (mais pas systématiquement) remplacées par des minuscules précédées d'un underscore, faisaint redouter que cela ne se passe pas forcément de la même façon sur différents setups.
D'où  fait que le kamelCase n'ait pas été respecté partout, l'enjeu étant que les datas présentes dans le fichier import.sql du dossier "resources" puissent être insérées dans les tables de la base de données. Les INSERT INTO contiennent les noms des champs et leur syntaxe doit être exactement la même que celle des champs des tables générées lors du lancement de l'application. 


Je vous remercie d'avoir consulté mon travail et espère qu'il vous aura plu !


       <      @Myriam Fournier - 2025      >


![Alt text](./Frontend/public/img/Volley-ball-women.jpg)
