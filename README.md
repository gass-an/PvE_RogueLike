# PvE RogueLike

Une application console Java implémentant un mini-rogue-like en PvE (Player versus Environment).  
Le joueur incarne un personnage qui monte de niveau, évolue en Barbare ou Magicien, affronte jusqu’à 10 adversaires et peut acheter/équiper des objets tous les deux combats.

---

## 🏆 Fonctionnalités principales

- **Création de personnage**  
  - Choix du nom  
  - Répartition de 3 points de compétences (Force, Intelligence, Agilité)  
  - Début en tant que Paysan

- **Combat tour par tour**  
  - Affichage des PV et statistiques à chaque tour  
  - Choix d’action du joueur :  
    - ATTAQUE_MELEE (si la classe le permet)  
    - ATTAQUE_DISTANCE (si la classe le permet)  
    - PARADE_MELEE  
    - PARADE_DISTANCE  
  - L’adversaire choisit une action aléatoire parmi ses options  
  - Les parades sont appliquées avant les attaques  
  - Les dégâts sont calculés (prise en compte de l’agilité pour réduire les dégâts en défense)  
  - Affichage du choix de l’adversaire et des PV restants  

- **Système d’évolution du joueur**  
  - À chaque victoire, le joueur gagne un niveau (+5 PV max, +5 PV courants, +5 pièces d’or)  
  - Un point de compétence est attribué (à placer dans Force, Intelligence ou Agilité)  
  - Si la Force ≥ 6 ou l’Intelligence ≥ 6 (seuil défini), le joueur peut évoluer :  
    - Paysan → Barbare (force finale = force + intelligence, intelligence = 0)  
    - Paysan → Magicien (intelligence finale = intelligence + force, force = 0)  
  - L’équipement et l’inventaire sont conservés après évolution

- **Boutique (Shop) tous les 2 combats**  
  - Génération aléatoire de 3 objets  
  - Chaque objet possède :  
    - Type (`TETE`, `TORSE`, `BOTTE`, `ARME`)  
    - Nom (numéro 1, 2 ou 3)  
    - Prix aléatoire (entre 5 et 14 pièces d’or)  
    - Bonus : aléatoire, la somme des bonus est égale à 3  
  - Affichage des 3 objets avec leurs caractéristiques  
  - Le joueur peut entrer 1/2/3 pour acheter l’objet (ou 0 pour ignorer)  
  - L’objet est ajouté à l’inventaire et équipé, ses bonus sont appliqués dynamiquement  
  - Mise à jour du solde du joueur  

- **Système d’inventaire et d’équipement**  
  - Le joueur dispose d’un inventaire (`HashMap<String, Item>`)  
  - Un équipement par `TypeItem` (`HashMap<TypeItem, Item>`)  
  - Les statistiques `getForce()`, `getIntelligence()` et `getAgilite()` sont **override** pour inclure les bonus des objets équipés  
  - En cas d’évolution, l’inventaire et l’équipement sont copiés vers la nouvelle instance (`JoueurBarbare` ou `JoueurMagicien`)  

---   

## 🚀 Pré-requis

- **Java 21.0.6** (JDK)  
- Outil de ligne de commande : `javac`, `jar`, `java`  
- Optionnellement, **PowerShell** (pour Windows) ou **Git Bash** (pour Linux/macOS)  

---

## 🗂 Structure du projet
```powershell
PvE-RogueLike/
├── src/
│ ├── adversaires/
│ │ ├── Adversaire.java
│ │ ├── AdversaireBarbare.java
│ │ ├── AdversaireMagicien.java
│ │ ├── AdversairePaysan.java
│ │ └── AdversaireNom.java
│ ├── game/
│ │ ├── Action.java
│ │ ├── Combat.java
│ │ ├── CreationEntite.java
│ │ ├── EvolutionJoueur.java
│ │ ├── Game.java
│ │ ├── Input.java
│ │ └── Start.java
│ ├── interfacesPersonnages/
│ │ ├── Barbare.java
│ │ ├── Magicien.java
│ │ └── Paysan.java
│ ├── joueurs/
│ │ ├── Joueur.java
│ │ ├── JoueurBarbare.java
│ │ ├── JoueurMagicien.java
│ │ └── JoueurPaysan.java
│ └── model/
│   ├── Item.java
│   ├── Personnage.java
│   ├── Shop.java
│   └── TypeItem.java
├── bin/ ← Dossier de sortie des classes compilées
├── documentation/ ← Dossier contenant toute la documentation des classes
├── manifest.txt 
├── PvE-RogueLike.jar 
└── README.md 
```
---

## ▶️ Lancer le jeu

Exécute simplement :

```powershell
java -jar PvE-RogueLike.jar
```
Le gameplay démarre, demande le nom du joueur, la répartition des points, puis enchaîne les combats, évolutions et passages au shop.

**Si soucis il y a, voici la marche à suivre :**  

### 📦 Compilation (Windows PowerShell)

1. **Créer le dossier `bin/`** (s’il n’existe pas) :  
   ```powershell
   mkdir bin
   ```
2. **Compiler tous les fichiers `.java`** pour générer les `.class` dans `bin/` : 
    ```powershell
   javac -d bin (Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })
   ```

### 📦 Création du JAR exécutable

1. **Créer un fichier `manifest.txt`** à la racine du projet, contenant :  
    ```powershell
    Main-Class: game.Start
   ```
2. **Générer le JAR** : 

    ```powershell
    jar cfm PvE-RogueLike.jar manifest.txt -C bin .
   ```

---


## 👥 Auteurs

- **Adrien ROYER**  
- **Anthony GASS**  
Etudiants de l'UNC en dernière année de Licence MIAGE.