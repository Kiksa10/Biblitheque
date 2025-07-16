INSERT INTO Livre (titre, auteur, isbn, annee_publication, editeur, genre, nbrExemplaire, resume, date_ajout)
VALUES ('Les Misérables', 'Victor Hugo', '9782070409189', 1862, 'Gallimard', 'Littérature classique', 3, NULL, CURRENT_DATE);

INSERT INTO Livre (titre, auteur, isbn, annee_publication, editeur, genre, nbrExemplaire, resume, date_ajout)
VALUES ('L''Étranger', 'Albert Camus', '9782070360022', 1942, 'Gallimard', 'Philosophie', 2, NULL, CURRENT_DATE);

INSERT INTO Livre (titre, auteur, isbn, annee_publication, editeur, genre, nbrExemplaire, resume, date_ajout)
VALUES ('Harry Potter à l''école des sorciers', 'J.K. Rowling', '9782070643026', 1997, 'Gallimard', 'Jeunesse / Fantastique', 1, NULL, CURRENT_DATE);


---------------
INSERT INTO Exemplaire (id_livre, code_barre, date_acquisition, prix, emplacement)
VALUES (1, 'MIS001', CURRENT_DATE, NULL, NULL);

INSERT INTO Exemplaire (id_livre, code_barre, date_acquisition, prix, emplacement)
VALUES (1, 'MIS002', CURRENT_DATE, NULL, NULL);

INSERT INTO Exemplaire (id_livre, code_barre, date_acquisition, prix, emplacement)
VALUES (1, 'MIS003', CURRENT_DATE, NULL, NULL);

INSERT INTO Exemplaire (id_livre, code_barre, date_acquisition, prix, emplacement)
VALUES (2, 'ETR001', CURRENT_DATE, NULL, NULL);

INSERT INTO Exemplaire (id_livre, code_barre, date_acquisition, prix, emplacement)
VALUES (2, 'ETR002', CURRENT_DATE, NULL, NULL);

INSERT INTO Exemplaire (id_livre, code_barre, date_acquisition, prix, emplacement)
VALUES (3, 'HAR001', CURRENT_DATE, NULL, NULL);
----------------------


INSERT INTO Status (etat, date_changement, notes)
VALUES ('disponible', CURRENT_TIMESTAMP, NULL);
-----------------
INSERT INTO exemplaire_status (id_exemplaire, id_status, date_association) VALUES
(1, 1, NOW()),
(2, 1, NOW()),
(3, 1, NOW()),
(4, 1, NOW()),
(5, 1, NOW()),
(6, 1, NOW());




INSERT INTO Adherent (
    nom, prenom, date_naissance, adresse, telephone, email, 
    date_inscription, categorie, cotisation, penalite, actif,
    username, password, numero_adherent, max_prets, prets_en_cours, date_dernier_pret
) VALUES
-- Adhérent standard actif
(
    'Amine', 'Bensaid', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    'ETU001', 'amine@gmail.com', 
    '2022-01-10', 'etudiant', 25.00, 10.00, TRUE,
    'amine', '12345', 'ETU001', 
    2, 0, NULL
),

-- Étudiant avec plusieurs prêts
(
    'Sarah', 'El Khattabi', '2003-07-22', 
    '45 Avenue des Champs, 69002 Lyon', 
    'ETU002', 'sarah@gmail.com', 
    '2022-02-15', 'etudiant', 15.00, 10.00, TRUE,
    'sarah', '12345', 'ETU002', 
    2, 0, NULL
),
(
    'Youssef', 'Moujahid', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    'ETU003', 'youssef@gmail.com', 
    '2022-01-10', 'etudiant', 25.00, 10.00, TRUE,
    'youssef', '12345', 'ETU003', 
    2, 0, NULL
),
(
    'Nadia', 'Benali', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    'ENS001', 'nadia@gmail.com', 
    '2022-01-10', 'adulte', 25.00, 10.00, TRUE,
    'nadia', '12345', 'ENS001', 
    2, 0, '2023-05-15'
),
(
    'Karim', 'Haddadi', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    'ENS002', 'karim@gmail.com', 
    '2022-01-10', 'adulte', 25.00, 10.00, TRUE,
    'karim', '12345', 'ENS002', 
    2, 0, '2023-05-15'
),
(
    'Salima', 'Touhami', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    'ENS003', 'salima@gmail.com', 
    '2022-01-10', 'adulte', 25.00, 10.00, TRUE,
    'salima', '12345', 'ENS003', 
    2, 0, '2023-05-15'
),
(
    'Rachid', 'El Mansouri', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    'PROF001', 'rachid@gmail.com', 
    '2022-01-10', 'professionnel', 25.00, 10.00, TRUE,
    'rachid', '12345', 'PROF001', 
    2, 0, '2023-05-15'
),
(
    'Amina', 'Zerouali', '2005-06-23', 
    '12 Rue des Fleurs, 75001 Paris', 
    'PROF002', 'amina@gmail.com', 
    '2022-01-10', 'professionnel', 25.00, 10.00, TRUE,
    'amina', '12345', 'PROF002', 
    2, 0, '2023-05-15'
);

-------------------