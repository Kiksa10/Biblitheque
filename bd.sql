-- Création de la base de données
CREATE DATABASE bibliotheque;
USE bibliotheque;

-- Table Livre
CREATE TABLE Livre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    annee_publication INT,
    editeur VARCHAR(100),
    genre VARCHAR(50),
    nbrExemplaire INT DEFAULT 0,
    resume TEXT,
    date_ajout DATE DEFAULT CURRENT_DATE
);

-- Table Exemplaire
CREATE TABLE Exemplaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT NOT NULL,
    code_barre VARCHAR(50) UNIQUE,
    date_acquisition DATE DEFAULT CURRENT_DATE,
    prix DECIMAL(10,2),
    emplacement VARCHAR(50),
    FOREIGN KEY (id_livre) REFERENCES Livre(id) ON DELETE CASCADE
);

-- Table Status
CREATE TABLE Status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    etat ENUM('disponible', 'emprunté', 'réservé', 'en réparation', 'perdu') NOT NULL,
    date_changement DATETIME DEFAULT CURRENT_TIMESTAMP,
    notes TEXT
);

-- Table Exemplaire_Status (table de liaison)
CREATE TABLE Exemplaire_Status (
    id_exemplaire INT NOT NULL,
    id_status INT NOT NULL,
    date_association DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_exemplaire, id_status),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id) ON DELETE CASCADE,
    FOREIGN KEY (id_status) REFERENCES Status(id) ON DELETE CASCADE
);

-- Table Adherent
CREATE TABLE Adherent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_naissance DATE,
    adresse TEXT,
    telephone VARCHAR(20),
    email VARCHAR(255) UNIQUE,
    date_inscription DATE DEFAULT CURRENT_DATE,
    categorie ENUM('enfant', 'adulte', 'senior', 'etudiant', 'professionnel') NOT NULL,
    cotisation DECIMAL(10,2) DEFAULT 0,
    penalite DECIMAL(10,2) DEFAULT 0,
    actif BOOLEAN DEFAULT TRUE
);

ALTER TABLE Adherent 
ADD COLUMN username VARCHAR(50) UNIQUE,
ADD COLUMN password VARCHAR(255);

-- Table Emprunt
CREATE TABLE Emprunt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherent INT NOT NULL,
    id_exemplaire INT NOT NULL,
    date_emprunt DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_retour_prevue DATETIME,
    date_retour_effectif DATETIME,
    statut ENUM('en cours', 'retourné', 'en retard', 'perdu') DEFAULT 'en cours',
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id) ON DELETE CASCADE,
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id) ON DELETE CASCADE
);

-- Table Reservation
CREATE TABLE Reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherent INT NOT NULL,
    id_livre INT NOT NULL,
    date_reservation DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_expiration DATETIME,
    statut ENUM('EN_ATTENTE', 'HONOREE', 'ANNULEE', 'EXPIREE') DEFAULT 'EN_ATTENTE',
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id) ON DELETE CASCADE,
    FOREIGN KEY (id_livre) REFERENCES Livre(id) ON DELETE CASCADE
);

-- Table Auteur (optionnelle pour normalisation)
CREATE TABLE Auteur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100),
    date_naissance DATE,
    nationalite VARCHAR(50),
    biographie TEXT
);

-- Table Livre_Auteur (table de liaison pour relation many-to-many)
CREATE TABLE Livre_Auteur (
    id_livre INT NOT NULL,
    id_auteur INT NOT NULL,
    PRIMARY KEY (id_livre, id_auteur),
    FOREIGN KEY (id_livre) REFERENCES Livre(id) ON DELETE CASCADE,
    FOREIGN KEY (id_auteur) REFERENCES Auteur(id) ON DELETE CASCADE
);

CREATE TABLE admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    date_naissance DATE,
    adresse VARCHAR(255),
    telephone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    date_embauche DATE NOT NULL,
    role ENUM('SUPER_ADMIN', 'BIBLIOTHECAIRE', 'GESTIONNAIRE') NOT NULL,
    actif BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Création d'un index sur le username pour des recherches plus rapides
CREATE INDEX idx_admin_username ON admin(username);

