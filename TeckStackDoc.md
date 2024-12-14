## **Backend (Spring Boot)**

### **Dépendances Spring Boot**

1.  **`spring-boot-starter-web`**
    
    -   Fournit le support pour créer des applications web avec Spring MVC.
    -   Gère les requêtes HTTP, la sérialisation/désérialisation JSON et les API REST.
2.  **`spring-boot-starter-data-jpa`**
    
    -   Ajoute le support pour interagir avec des bases de données relationnelles à l’aide de JPA (Java Persistence API).
    -   Inclut Hibernate comme implémentation ORM par défaut.
3.  **`spring-boot-starter-security`**
    
    -   Ajoute des fonctionnalités de sécurité comme l’authentification, l’autorisation et la protection contre les attaques CSRF.
    -   Fournit une page de connexion par défaut et permet la personnalisation des mécanismes d'authentification.
4.  **`spring-boot-starter-validation`**
    
    -   Active la validation côté serveur en utilisant des annotations comme `@NotNull`, `@Size` et `@Email`.
5.  **`spring-boot-starter-websocket`**
    
    -   Permet la prise en charge des communications WebSocket pour des interactions en temps réel dans l’application.
6.  **`spring-boot-devtools`**
    
    -   Fournit des outils pour le rechargement automatique pendant le développement.
    -   Simplifie le débogage et les tests en redémarrant automatiquement l’application après des modifications du code.
7.  **`org.postgresql:postgresql`**
    
    -   Pilote JDBC pour connecter l’application à une base de données PostgreSQL.
8.  **`org.projectlombok:lombok`**
    
    -   Simplifie le code Java répétitif, comme les getters, setters, constructeurs, et méthodes `toString`.
9.  **`spring-boot-starter-test`**
    
    -   Inclut des bibliothèques de test comme JUnit, Mockito et Spring TestContext pour écrire des tests unitaires et d’intégration.
10.  **`spring-security-test`**
    
    -   Fournit des utilitaires pour tester les configurations de sécurité et simuler des authentifications dans les tests.
11.  **`com.h2database:h2`** _(Optionnel)_
    
    -   Fournit une base de données H2 en mémoire pour les tests ou le développement rapide.

----------

### **Plugins Maven Spring**

1.  **`maven-compiler-plugin`**
    
    -   Compile le code Java avec la version spécifiée (`java.version`).
2.  **`spring-boot-maven-plugin`**
    
    -   Offre des fonctionnalités spécifiques à Spring Boot comme la création d’un fichier JAR ou WAR exécutable.
    -   Simplifie le déploiement de l’application.
3.  **`frontend-maven-plugin`** _(Optionnel)_
    
    -   Gère Node.js et npm pour construire la partie frontend (React) pendant le cycle de vie Maven.
    -   Exécute les commandes `npm install` et `npm run build`.

----------

## **Frontend (React)**

### **Dépendances principales**

1.  **`react`**
    
    -   Bibliothèque principale pour créer des interfaces utilisateur avec une architecture basée sur les composants.
2.  **`react-dom`**
    
    -   Fournit des méthodes spécifiques au DOM pour afficher des composants React dans le navigateur.
3.  **`react-scripts`**
    
    -   Inclut des configurations et des scripts pour compiler, tester et exécuter l’application React.

----------

### **Styling et composants**

4.  **`bootstrap`**
    
    -   Un framework CSS populaire pour concevoir des sites web responsifs et adaptés aux mobiles.
    -   Fournit des composants pré-stylisés comme des boutons, des formulaires et des grilles.
5.  **`react-bootstrap`**
    
    -   Un wrapper React pour les composants Bootstrap, offrant des API adaptées à React.

----------

### **Routing et gestion de l'état**

6.  **`react-router-dom`**
    
    -   Permet de gérer la navigation multi-pages dans l'application React.
    -   Gère les URLs dynamiques et la navigation conditionnelle.
7.  **`@reduxjs/toolkit`**
    
    -   Simplifie la gestion de l’état avec Redux en réduisant le code répétitif.
    -   Fournit des outils comme `createSlice` et `configureStore` pour gérer l’état global.
8.  **`react-redux`**
    
    -   Intègre Redux avec React, permettant aux composants d’accéder et de déclencher des actions sur le store Redux.

----------

### **Communication avec le backend**

9.  **`axios`**
    
    -   Un client HTTP basé sur des Promises pour effectuer des requêtes API vers le backend.
    -   Simplifie la gestion des requêtes, des réponses et des erreurs.
10.  **`@tanstack/react-query`** _(Optionnel)_
    
    -   Bibliothèque pour gérer l'état côté serveur (fetching, caching, synchronisation des données avec le backend).

----------

### **Développement et débogage**

11.  **React Developer Tools (Extension Navigateur)**
    
    -   Fournit des outils de débogage pour inspecter les composants React dans le navigateur.
12.  **Redux DevTools (Extension Navigateur)**
    
    -   Outil de débogage pour inspecter l’état et les actions Redux pendant le développement.

----------

### **Outils supplémentaires**

13.  **Fichiers `.env` (React)**
    -   Permet de stocker des informations sensibles comme l’URL de l’API backend.
    -   Les variables doivent être préfixées par `REACT_APP_` pour être utilisées dans l’application.

----------

### **Outils de build frontend (Optionnels)**

1.  **Node.js**
    
    -   Runtime JavaScript nécessaire pour le développement et les processus de build React.
2.  **npm**
    
    -   Gestionnaire de paquets pour installer des bibliothèques et dépendances JavaScript.