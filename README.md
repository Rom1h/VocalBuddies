
# **Jeu VocalBuddies**

## **Présentation**
**VocalBuddies** est un jeu innovant où les joueurs utilisent leur voix pour contrôler un personnage dans un labyrinthe. Ce projet combine reconnaissance vocale et reconnaissance du locuteur pour offrir une expérience interactive unique. L’objectif principal est de sortir d’un labyrinthe en donnant des commandes vocales précises.

Le jeu a été développé en utilisant Java et diverses bibliothèques, avec des outils avancés tels que **Whisper AI** et **ALIZE** pour la reconnaissance vocale.

---

## **Fonctionnalités Principales**
- **Contrôle vocal :** Déplacez votre personnage dans le labyrinthe en donnant des commandes vocales telles que "go down" ou "go left".
- **Reconnaissance du locuteur :** Identification des joueurs à partir de leurs caractéristiques vocales.
- **Enregistrement vocal :** Création d’un modèle vocal personnalisé pour chaque joueur.
- **Interface graphique intuitive :** Basée sur les bibliothèques Swing et AWT.
- **Menu interactif :** Choisissez entre jouer, configurer un modèle vocal ou consulter les instructions.

---

## **Compilation et Exécution**
Après avoir téléchargé et décompressé le dépôt, procédez comme suit :

### Étape 1 : Installation de Whisper AI
Si Whisper AI n’est pas encore installé sur votre machine, exécutez la commande suivante :
```bash
./instal.sh
```

### Étape 2 : Lancement du jeu
Pour démarrer le jeu, exécutez :
```bash
./program.sh
```

---

## **Instructions de Jeu**
- Accédez à la section **Instructions** depuis le menu principal pour une explication détaillée du jeu.
- **Création de modèle vocal :** Sur la page "S'enregistrer", il est nécessaire d’enregistrer 30 minutes de données vocales pour créer un modèle précis.
  - Parlez jusqu’à ce que la jauge de progression soit remplie.
- **Commandes vocales :**
  - Donnez des commandes claires comme "va en haut" ou "va en bas" pour déplacer votre personnage.

---

## **Technologies Utilisées**
- **Langage :** Java
- **Reconnaissance vocale :** Whisper AI
- **Reconnaissance du locuteur :** ALIZE, avec modèles GMM
- **Interface graphique :** Java Swing et AWT
- **Enregistrement vocal :** Alsa et Java Sound API

---

## **Problèmes Rencontrés et Résolutions**
- **Reconnaissance vocale parfois imprécise :** Utilisation de phrases plus complètes comme "va en haut" pour améliorer la précision.
- **Temps d’exécution élevé de Whisper AI :** Optimisation du modèle pour réduire le temps de latence.
- **Difficulté avec ALIZE :** Manque de documentation externe résolu par une collaboration étroite en équipe et une exploration approfondie des outils.

---

## **Crédits**
**Membres de l’équipe :**
- Romain Houard
- Melissa Bouloufa
- Malone Sayarath
- Jules Cotrez
