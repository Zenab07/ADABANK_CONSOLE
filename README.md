# PROJET ADABANK_CONSOLE

# Guide d'Installation et Configuration


ADABANK est une application Java console simulant la gestion d'une banque.  
Elle permet à un administrateur de gérer les banques, clients, comptes et transactions, avec une architecture 3-tiers et une base de données relationnelle.

---

## Architecture du projet

```
ada-bank-console/
│── src/main/java/
│   ├── ci/ada/dao/              # Couche DAO (accès à la base de données)
│   ├── ci/ada/factory/          # Centraliser la création d’objets Transaction
│   ├── ci/ada/interfaces/       # Interfaces sous menu du menu principal
│   ├── ci/ada/models/           # Entités métier (Bank, Customer, Account, Transaction)
│   ├── ci/ada/observer/         # Simulation de l'envoi d'email au client après une transaction
│   ├── ci/ada/services/         # Couche Service (logique métier)
│   ├── ci/ada/singleton/        # Singleton pour la connexion DB
│   ├── ci/ada/strategy/         # Pour gérer les différents types de transaction
│   └── ci/ada/App.java          # Point d'entrée principal
│
│── src/test/java/               # Tests unitaires JUnit
│── target/site/apidocs/         
│   ├── index.html               # Page html du javadoc
│── ADABANK_DB.sql               # Scripts SQL
│── pom.xml                      # Configuration Maven
│── README.md                    # Documentation
```
---

Avant de démarrer, assurez-vous d'avoir :

- **Java JDK** 17
- **Maven**  
- **PostgreSQL**  
- **Un IDE Java** : IntelliJ IDEA, Eclipse ou NetBeans

---

## Installation

1. **Cloner le projet**
   ```bash
   git clone git@gitlab.com:adabank_group/adabank_project.git
   cd ada-bank-console
   ```

2. **Configurer la base de données**

    - Créez une base de données :
      ```sql
      CREATE DATABASE adabank_db;
      ```
    - Importez le script fourni :
      ```bash
      psql -U postgres -d adabank_db -f ADABANK_DB.sql

      ```

3. **Configurer la connexion**

   
   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/adabank_db?currentSchema=public&sslmode=disable";
   private static final String USER = "postgre";
   private static final String PASSWORD = "password";
   ```

4. **Installer les dépendances**
   ```bash
   mvn clean install
   ```

---

## Lancement de l'application

1. **Depuis Maven**
   ```bash
   mvn exec:java -Dexec.mainClass="ci.ada.App"
   ```

2. **Depuis l’IDE**
    - Ouvrir `App.java`
    - Clic droit → **Run 'App.main()'**

---

## Connexion administrateur

- **Login :** `admin`
- **Mot de passe :** `admin123`

*(Les identifiants sont codés en dur dans le fichier `App.java`)*

---

## Lancer les tests unitaires

Le projet utilise **JUnit 5** pour les tests.

```bash
mvn test
```

Les tests couvrent :
- **Les models**

*(Pour cette première version, les tests couvrent seulement les models)*

---

## Fonctionnalités (le menu principal avec les différents sous menus)

**Gestion des banques**
- Enregistrer une nouvelle banque
- Afficher top 15 banques par clients
- Rechercher banques par pays/ville

**Gestion des clients**
- Inscription client
- Liste clients par banque
- Recherche multicritères clients

**Gestion des comptes**
- Ouvrir un compte
- Clôturer un compte
- Liste comptes par client
- Détails d'un compte

**Gestion des transactions**
- Enregistrer une transaction
- Historique des transactions
- Recherche multicritères transaction

---

## Technologies utilisées

- **Java 17**
- **Maven**
- **JDBC**
- **PostgreSQL**
- **JUnit 3.8.1**

---

## Auteur

Projet développé par **Maimouna Zenab COULIBALY** dans le cadre de la formation **Ada 2025**.

---


