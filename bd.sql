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
    cotisation DECIMAL(10,2) DEFAULT 0.00,
    penalite DECIMAL(10,2) DEFAULT 0.00,
    actif BOOLEAN DEFAULT TRUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    numero_adherent VARCHAR(20) UNIQUE,
    max_prets INT DEFAULT 5,
    prets_en_cours INT DEFAULT 0,
    date_dernier_pret DATE
);



-- Table Emprunt
CREATE TABLE Emprunt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_adherent INT NOT NULL,
    id_exemplaire INT NOT NULL,
    date_emprunt DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_retour_prevue DATETIME,
    date_retour_effectif DATETIME,
    statut VARCHAR(20) DEFAULT 'en cours',
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



CREATE TABLE ABONNEMENTS (
    ID_ABONNEMENT INT PRIMARY KEY,
    ADHERENT VARCHAR(100) NOT NULL,
    DATE_DEBUT DATE NOT NULL,
    DATE_FIN DATE NOT NULL,
    VALIDITE_ABONNEMENT BOOLEAN
);

CREATE TABLE retour_pret (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pret_id INT NOT NULL,
    date_retour_effectif DATE NOT NULL,
    etat_livre ENUM('BON', 'ABIME', 'DETERIORE', 'PERDU') NOT NULL,
    commentaire TEXT,
    amende DECIMAL(10,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (pret_id) REFERENCES pret(id) ON DELETE CASCADE
);

-- Optionnel : Table pour suivre les amendes si besoin d'un système plus complexe
CREATE TABLE amende (
    id INT AUTO_INCREMENT PRIMARY KEY,
    retour_pret_id INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    raison VARCHAR(255) NOT NULL,
    reglee BOOLEAN DEFAULT FALSE,
    date_reglement DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (retour_pret_id) REFERENCES retour_pret(id) ON DELETE CASCADE
);

-- Création d'un index sur le username pour des recherches plus rapides
CREATE INDEX idx_admin_username ON admin(username);

