-- Insertion des Auteurs
INSERT INTO auteur (nom, prenom, date_naissance, nationalite, biographie) VALUES
('Rowling', 'J.K.', '1965-07-31', 'Britannique', 'Auteur de la célèbre série Harry Potter'),
('Martin', 'George R.R.', '1948-09-20', 'Américain', 'Auteur de la série Le Trône de Fer'),
('Tolkien', 'J.R.R.', '1892-01-03', 'Britannique', 'Auteur du Seigneur des Anneaux'),
('Orwell', 'George', '1903-06-25', 'Britannique', 'Auteur de 1984 et La Ferme des Animaux'),
('Christie', 'Agatha', '1890-09-15', 'Britannique', 'Reine du roman policier');

-- Insertion des Livres
INSERT INTO livre (titre, auteur, isbn, annee_publication, editeur, genre, nbrExemplaire, resume, date_ajout) VALUES
('Harry Potter à l école des sorciers', 'J.K. Rowling', '9782070584628', 1997, 'Gallimard', 'Fantasy', 5, 'Un jeune sorcier découvre son héritage magique', '2020-01-15'),
('Le Trône de Fer', 'George R.R. Martin', '9782253124846', 1996, 'Pygmalion', 'Fantasy', 3, 'Intrigues politiques dans un royaume médiéval', '2019-05-22'),
('Le Seigneur des Anneaux', 'J.R.R. Tolkien', '9782266282362', 1954, 'Pocket', 'Fantasy', 4, 'Quête épique pour détruire un anneau maléfique', '2021-03-10'),
('1984', 'George Orwell', '9782070368228', 1949, 'Gallimard', 'Dystopie', 2, 'Une société totalitaire sous surveillance constante', '2020-11-05'),
('Dix Petits Nègres', 'Agatha Christie', '9782253006327', 1939, 'Le Masque', 'Policier', 3, 'Dix invités sur une île, morts les uns après les autres', '2021-02-18');

-- Insertion des relations Livre-Auteur
INSERT INTO livre_auteur (id_livre, id_auteur) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);

-- Insertion des Exemplaires
INSERT INTO exemplaire (id_livre, code_barre, date_acquisition, prix, emplacement) VALUES
(1, 'EXMPL0001', '2020-01-20', 15.99, 'Rangement A1'),
(1, 'EXMPL0002', '2020-01-20', 15.99, 'Rangement A2'),
(1, 'EXMPL0003', '2020-06-15', 16.50, 'Rangement A3'),
(2, 'EXMPL0004', '2019-06-10', 22.50, 'Rangement B1'),
(2, 'EXMPL0005', '2020-03-05', 22.50, 'Rangement B2'),
(3, 'EXMPL0006', '2021-03-15', 19.99, 'Rangement C1'),
(3, 'EXMPL0007', '2021-04-02', 19.99, 'Rangement C2'),
(4, 'EXMPL0008', '2020-11-10', 12.99, 'Rangement D1'),
(5, 'EXMPL0009', '2021-02-20', 14.50, 'Rangement E1'),
(5, 'EXMPL0010', '2021-02-20', 14.50, 'Rangement E2');

-- Insertion des Statuts
INSERT INTO status (etat, date_changement, notes) VALUES
('disponible', NOW(), NULL),
('emprunté', NOW(), NULL),
('réservé', NOW(), NULL),
('en réparation', NOW(), 'Page 45 détachée'),
('perdu', NOW(), 'Non retourné depuis 6 mois');

-- Insertion des Statuts des Exemplaires
INSERT INTO exemplaire_status (id_exemplaire, id_status, date_association) VALUES
(1, 1, NOW()),
(2, 2, NOW()),
(3, 1, NOW()),
(4, 1, NOW()),
(5, 3, NOW()),
(6, 1, NOW()),
(7, 4, NOW()),
(8, 1, NOW()),
(9, 1, NOW()),
(10, 5, NOW());

-- Insertion des Adhérents

-- Insertion des Emprunts
-- Insertion d'adhérents avec des données variées et réalistes
INSERT INTO Adherent (
    nom, prenom, date_naissance, adresse, telephone, email, 
    date_inscription, categorie, cotisation, penalite, actif,
    username, password, numero_adherent, max_prets, prets_en_cours, date_dernier_pret
) VALUES
-- Adhérent standard actif
(
    'Lambert', 'Francky', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    '0345221160', 'francky.dupont@email.com', 
    '2022-01-10', 'etudiant', 25.00, 0.00, TRUE,
    'kiksa', '12345', 'ADH-2022-001', 
    5, 2, '2023-05-15'
),

-- Étudiant avec plusieurs prêts
(
    'Rakoto', 'Didier', '2003-07-22', 
    '45 Avenue des Champs, 69002 Lyon', 
    '0343921167', 'didier@gmail.com', 
    '2022-02-15', 'adulte', 15.00, 0.00, TRUE,
    'didier', '12345', 'ADH-2022-002', 
    5, 4, '2023-06-10'
),

-- Adhérent senior avec pénalité
(
    'Bernard', 'Marie', '1952-11-30', 
    '8 Rue du Temple, 13008 Marseille', 
    '0498765432', 'marieBernard@email.com', 
    '2021-11-05', 'senior', 20.00, 5.50, TRUE,
    'mbernard', '12345', 'ADH-2021-003', 
    5, 1, '2023-04-22'
),

-- Enfant (moins de 12 ans)
(
    'Petit', 'Lucas', '2015-05-18', 
    '32 Boulevard Voltaire, 31000 Toulouse', 
    '0687654321', 'lucaspetit@parent.com', 
    '2023-01-20', 'enfant', 10.00, 0.00, TRUE,
    'lpetit', '12345', 'ADH-2023-004', 
    3, 0, NULL
),

-- Professionnel avec cotisation élevée
(
    'Leblanc', 'Thomas', '1980-09-12', 
    '5 Rue de la République, 59000 Lille', 
    '0623456789', 'thomasLeblanc@entreprise.com', 
    '2022-03-25', 'professionnel', 50.00, 0.00, TRUE,
    'tleblanc', '12345', 'ADH-2022-005', 
    8, 3, '2023-06-05'
),

-- Adhérent inactif
(
    'Roux', 'Elodie', '1992-04-05', 
    '18 Avenue Foch, 33000 Bordeaux', 
    '0676543210', 'elodieRoux@email.com', 
    '2021-07-15', 'adulte', 0.00, 12.00, FALSE,
    'eroux', '12345678', 'ADH-2021-006', 
    5, 0, '2022-12-10'
),

-- Nouvel adhérent sans prêt
(
    'Garcia', 'Antoine', '1995-08-27', 
    '22 Rue de la Paix, 44000 Nantes', 
    '0634567890', 'antoineGarcia@email.com', 
    CURDATE(), 'adulte', 25.00, 0.00, TRUE,
    'agarcia', '123456', 'ADH-2023-007', 
    5, 0, NULL
),

-- Adhérent avec retard
(
    'Moreau', 'Camille', '1988-12-03', 
    '7 Rue de la Liberté, 67000 Strasbourg', 
    '0687654321', 'camilleMoreau@email.com', 
    '2022-05-18', 'adulte', 25.00, 8.75, TRUE,
    'cmoreau', '12345', 'ADH-2022-008', 
    5, 2, '2023-05-28'
);
-- Insertion des Réservations
INSERT INTO reservation (id_adherent, id_livre, date_reservation, date_expiration, statut) VALUES
(2, 3, '2021-05-20 15:00:00', '2021-05-27 15:00:00', 'en attente'),
(5, 4, '2021-04-10 11:30:00', '2021-04-17 11:30:00', 'honorée'),
(1, 5, '2021-06-05 09:45:00', '2021-06-12 09:45:00', 'en attente'),
(3, 1, '2021-03-15 14:00:00', '2021-03-22 14:00:00', 'expirée');


-- Insertion d'un administrateur par défaut (mot de passe: admin123)
INSERT INTO admin (nom, prenom, username, password,date_naissance,adresse,telephone,email, date_embauche, role)
VALUES ('NIAINA', 'Francky', 'madoda', '12345','2005-06-23','AKM I 10ter','0345221160','madoda@gmail.com', CURDATE(), 'SUPER_ADMIN');




-- Emprunt 1: En cours (normal)
INSERT INTO Emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue, statut)
VALUES (8,3,'2023-11-01 10:00:00', '2024-01-01 10:00:00','en cours');

-- Emprunt 2: En retard
INSERT INTO Emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue, statut)
VALUES (
    8, 
    5, 
    '2023-10-15 14:30:00', 
    '2024-02-15 14:30:00',  -- Date de retour dépassée
    'en cours'
);

-- Emprunt 3: Retourné à temps
INSERT INTO Emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue, date_retour_effectif, statut)
VALUES (
    8, 
    8, 
    '2023-11-05 11:20:00', 
    DATE_ADD('2023-11-05 11:20:00', INTERVAL 30 DAY),
    DATE_ADD('2023-11-05 11:20:00', INTERVAL 25 DAY),  -- Retourné 5 jours avant
    'retourné'
);

-- Emprunt 4: Livre perdu
INSERT INTO Emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue, statut)
VALUES (
    8, 
    3, 
    '2023-09-10 16:45:00', 
    DATE_ADD('2023-09-10 16:45:00', INTERVAL 30 DAY),
    'perdu'
);

-- Emprunt 5: En cours (proche de la date de retour)
INSERT INTO Emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue, statut)
VALUES (
    8, 
    7, 
    DATE_SUB(NOW(), INTERVAL 25 DAY),  -- Emprunté il y a 25 jours
    DATE_ADD(DATE_SUB(NOW(), INTERVAL 25 DAY), INTERVAL 30 DAY),  -- Retour dans 5 jours
    'en cours'
);


-- Insertion 1: Abonnement valide
INSERT INTO ABONNEMENTS (ID_ABONNEMENT, ADHERENT, DATE_DEBUT, DATE_FIN, VALIDITE_ABONNEMENT)
VALUES (1, '0345221160', '2023-01-15', '2023-12-31', TRUE);

-- Insertion 2: Abonnement expiré
INSERT INTO ABONNEMENTS (ID_ABONNEMENT, ADHERENT, DATE_DEBUT, DATE_FIN, VALIDITE_ABONNEMENT)
VALUES (2, '0343921167', '2022-06-01', '2022-12-31', FALSE);

-- Insertion 3: Abonnement valide récent
INSERT INTO ABONNEMENTS (ID_ABONNEMENT, ADHERENT, DATE_DEBUT, DATE_FIN, VALIDITE_ABONNEMENT)
VALUES (3, '0498765432', '2023-03-10', '2024-03-09', TRUE);

-- Insertion 4: Abonnement qui commence bientôt
INSERT INTO ABONNEMENTS (ID_ABONNEMENT, ADHERENT, DATE_DEBUT, DATE_FIN, VALIDITE_ABONNEMENT)
VALUES (4, '0687654321', '2023-06-01', '2024-05-31', TRUE); -- Pas encore valide


