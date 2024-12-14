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
