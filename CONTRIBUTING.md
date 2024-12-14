# Guide de Contribution 🛠️

Merci de votre intérêt pour contribuer au **Projet POTI** ! Ce guide vous aidera à comprendre comment vous pouvez contribuer efficacement.

---

## Comment contribuer 🤝

1. **Forkez le dépôt :**
   - Créez une copie du projet sur votre compte GitHub en cliquant sur le bouton "Fork".

2. **Clonez le dépôt :**
   - Clonez votre fork sur votre machine locale :
     ```bash
     git clone https://github.com/votre-utilisateur/nom-du-projet.git
     cd nom-du-projet
     ```

3. **Créez une branche :**
   - Créez une nouvelle branche pour vos modifications :
     ```bash
     git checkout -b nom-de-la-branche
     ```

4. **Apportez vos modifications :**
   - Modifiez le code, corrigez les bugs ou ajoutez des fonctionnalités. Assurez-vous de suivre les normes du projet (voir plus bas).

5. **Ajoutez vos modifications :**
   - Ajoutez et commitez vos changements :
     ```bash
     git add .
     git commit -m "Description claire de la modification"
     ```

6. **Poussez votre branche :**
   - Envoyez vos modifications sur votre fork :
     ```bash
     git push origin nom-de-la-branche
     ```

7. **Créez une Pull Request (PR) :**
   - Allez sur la page GitHub de votre fork.
   - Cliquez sur "Compare & pull request".
   - Décrivez vos modifications dans la Pull Request.
   - Soumettez votre PR !

---

## Normes de contribution 📏

### Écriture du code :
- **Frontend :**
  - Respectez la structure des composants React (fonctionnels ou basés sur des hooks).
  - Utilisez **React-Bootstrap** pour les éléments UI.
  - Respectez la convention de nommage des fichiers : camelCase pour les fichiers JavaScript et PascalCase pour les composants (`MonComposant.js`).
- **Backend :**
  - Respectez les conventions de nommage Java.
  - Structurez vos packages clairement :
    ```
    src/main/java/
      ├── controller/
      ├── service/
      ├── repository/
      ├── model/
    ```

### Bonnes pratiques :
- Fournissez une description claire de vos modifications dans les commits (pas de messages comme "fix" ou "update").
- Les branches doivent avoir un nom explicite : `feature/nom-fonctionnalité` ou `bugfix/nom-bug`.

### Tests :
- **Frontend** : Ajoutez des tests unitaires si possible (par exemple, avec Jest).
- **Backend** : Ajoutez des tests unitaires ou d'intégration pour toute nouvelle fonctionnalité.

---

## Suggestions et bugs 🐛
- Ouvrez une [issue](https://github.com/T1WiLLi/POTI) pour signaler un bug ou proposer une idée.
- Fournissez des détails clairs, y compris des étapes pour reproduire le problème ou des exemples pour illustrer votre idée.
