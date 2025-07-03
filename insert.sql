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
INSERT INTO adherent (nom, prenom, date_naissance, adresse, telephone, email, date_inscription, categorie, cotisation, penalite, actif) VALUES
('Dupont', 'Jean', '1985-05-15', '12 Rue de la Paix, Paris', '0612345678', 'jean.dupont@email.com', '2020-02-10', 'adulte', 30.00, 0.00, true),
('Martin', 'Sophie', '1992-11-22', '34 Avenue des Champs, Lyon', '0698765432', 'sophie.martin@email.com', '2020-03-15', 'adulte', 30.00, 5.50, true),
('Petit', 'Lucas', '2008-07-30', '8 Boulevard Voltaire, Marseille', '0654321789', 'lucas.petit@email.com', '2021-01-05', 'enfant', 15.00, 0.00, true),
('Durand', 'Marie', '1978-09-12', '56 Rue de Rivoli, Lille', '0678912345', 'marie.durand@email.com', '2019-11-20', 'adulte', 30.00, 0.00, false),
('Leroy', 'Thomas', '1995-04-18', '22 Allée des Tilleuls, Bordeaux', '0687654321', 'thomas.leroy@email.com', '2021-02-28', 'etudiant', 20.00, 0.00, true);

-- Insertion des Emprunts
INSERT INTO emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue, date_retour_effectif, statut) VALUES
(1, 2, '2021-05-10 14:30:00', '2021-06-09 14:30:00', NULL, 'en cours'),
(2, 5, '2021-04-15 10:15:00', '2021-05-15 10:15:00', NULL, 'en cours'),
(3, 7, '2021-03-20 16:45:00', '2021-04-19 16:45:00', '2021-04-18 11:20:00', 'retourné'),
(4, 10, '2020-12-01 09:00:00', '2020-12-31 09:00:00', NULL, 'en retard'),
(5, 1, '2021-06-01 13:20:00', '2021-07-01 13:20:00', NULL, 'en cours');

-- Insertion des Réservations
INSERT INTO reservation (id_adherent, id_livre, date_reservation, date_expiration, statut) VALUES
(2, 3, '2021-05-20 15:00:00', '2021-05-27 15:00:00', 'en attente'),
(5, 4, '2021-04-10 11:30:00', '2021-04-17 11:30:00', 'honorée'),
(1, 5, '2021-06-05 09:45:00', '2021-06-12 09:45:00', 'en attente'),
(3, 1, '2021-03-15 14:00:00', '2021-03-22 14:00:00', 'expirée');


INSERT INTO Adherent (nom, prenom, username, password, email, categorie)
VALUES (
    'Dupont', 
    'Jean', 
    'jdupont', 
    '12345', -- mot de passe: adherent123
    'jeandupont@email.com', 
    'adulte'
);

-- Insertion d'un administrateur par défaut (mot de passe: admin123)
INSERT INTO admin (nom, prenom, username, password, date_embauche, role)
VALUES ('NIAINA', 'Francky', 'kiksa', '12345', CURDATE(), 'SUPER_ADMIN');