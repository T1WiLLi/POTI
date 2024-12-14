# Projet POTI 🚀

Bienvenue sur le **Projet POTI** ! Ce dépôt héberge une application web développée avec **Spring Boot** et **React**, offrant une plateforme amusante et interactive pour l'ouverture de packs, l'échange de cartes, et bien plus encore.

---

## Fonctionnalités 🛠️
- **Ouverture de packs** : Ouvrez jusqu'à 2 packs toutes les 6 heures.
- **Comptes utilisateurs** : Système d'authentification utilisant les emails du cégep.
- **Marketplace** : Achetez et vendez des cartes avec d'autres joueurs en utilisant la monnaie interne, `POTI`.
- **Collections** :
  - **Cours** : Cartes liées aux cours techniques.
  - **Professeurs** : Cartes mettant en vedette les professeurs.
  - **Locaux** : Cartes prestigieuses des locaux importants du cégep.
- **Gambling** : Échangez vos cartes en double pour tenter de gagner des récompenses fabuleuses.
- **Gestion d'inventaire** : Organisez et visualisez vos cartes collectées.

---

## Stack technique 💻
### **Backend** (Spring Boot)
- **Spring Boot** : APIs REST, communication WebSocket et authentification.
- **PostgreSQL** : Base de données relationnelle pour la persistance.
- **H2** *(optionnel)* : Base de données en mémoire pour les tests locaux.
- **Spring Security** : Authentification et autorisation.
- **Hibernate** : ORM pour l'interaction avec la base de données.

### **Frontend** (React)
- **React** : Développement d'interface utilisateur basé sur des composants.
- **Redux Toolkit** : Gestion de l'état.
- **React-Router** : Navigation multi-pages.
- **Bootstrap & React-Bootstrap** : Style et composants UI.
- **Axios** : Communication avec les APIs.


### Installation 
**Pour le développment**

#### Backend 
- **Installer votre IDE favoris** : Je vous recommande VSCODE puisque le projet contient aussi la partie frontend. (IntelliJI devrait fonctionner également) 
- **Installer MAVEN** : Si vous n'avez pas maven d'installer veuillez l'installer, pour vérifier si vous avez maven, vous pouvez taper dans la console : <code>mvn --version</code>

#### Frontend
- **Installer votre IDE favoris** : Je vous recommande VSCODE, toutefois si vous souhaitez rester dans l'écosystème Jetbrains, WebStorm est très bien :) 
- **Installer NPM & Node.js** : Vérifier si vous avez npm d'installer, pour ce faire, vous pouvez taper dans la console : <code> npm --version</code> (Node.js installe automatiquement npm) 
- **Télécharger les dépendances** : Une fois le projet <strong>Forker</strong> et sur votre machine, vous aller devoir naviguer dans la partie client : 
    ```
    cd client
    npm install
    npm run dev
    ```
Ces commandes vous permettront de télécharger les dépendances du projet. (Faite la commande une seul fois) 
La commande ```npm run dev``` permet de partir votre projet en mode développment, (hot-reloading) et un serveur de test. 
