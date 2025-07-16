<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nouveau Prêt | Bibliothèque</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&family=Playfair+Display:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34495e;
            --accent-color: #3498db;
            --success-color: #27ae60;
            --danger-color: #e74c3c;
            --light-color: #ecf0f1;
            --dark-color: #2c3e50;
            --text-color: #333;
            --text-light: #7f8c8d;
            --shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            --transition: all 0.3s ease;
            --sidebar-width: 250px;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Roboto', sans-serif;
            color: var(--text-color);
            background-color: #f9f9f9;
            line-height: 1.6;
            display: flex;
            min-height: 100vh;
        }

        /* Sidebar Styles */
        .sidebar {
            width: var(--sidebar-width);
            background-color: var(--primary-color);
            color: white;
            height: 100vh;
            position: fixed;
            transition: var(--transition);
            z-index: 1000;
        }

        .sidebar-header {
            padding: 1.5rem;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }

        .sidebar-header h3 {
            color: white;
            font-size: 1.2rem;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .sidebar-menu {
            padding: 1rem 0;
        }

        .sidebar-menu ul {
            list-style: none;
        }

        .sidebar-menu li {
            margin-bottom: 0.2rem;
        }

        .sidebar-menu a {
            display: flex;
            align-items: center;
            padding: 0.8rem 1.5rem;
            color: var(--light-color);
            text-decoration: none;
            transition: var(--transition);
            gap: 0.8rem;
        }

        .sidebar-menu a:hover, .sidebar-menu a.active {
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
        }

        .sidebar-menu a i {
            width: 20px;
            text-align: center;
        }

        .sidebar-menu .submenu {
            padding-left: 2.5rem;
            display: none;
        }

        .sidebar-menu .has-submenu.active .submenu {
            display: block;
        }

        /* Main Content Styles */
        .main-content {
            margin-left: var(--sidebar-width);
            width: calc(100% - var(--sidebar-width));
            padding: 2rem;
            transition: var(--transition);
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 1px solid #eee;
        }

        h1 {
            font-family: 'Playfair Display', serif;
            font-weight: 700;
            color: var(--primary-color);
            font-size: 2.2rem;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            font-weight: 500;
            text-decoration: none;
            transition: var(--transition);
            border: none;
            cursor: pointer;
            font-size: 0.9rem;
            gap: 0.5rem;
        }

        .btn-primary {
            background-color: var(--accent-color);
            color: white;
        }

        .btn-primary:hover {
            background-color: #2980b9;
            transform: translateY(-2px);
            box-shadow: var(--shadow);
        }

        .btn-success {
            background-color: var(--success-color);
            color: white;
        }

        .btn-danger {
            background-color: var(--danger-color);
            color: white;
        }

        /* Styles spécifiques au formulaire */
        .form-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: var(--shadow);
            padding: 2rem;
            margin-bottom: 2rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
        }

        select, input {
            width: 100%;
            padding: 0.75rem 1rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-family: inherit;
            font-size: 1rem;
            transition: var(--transition);
        }

        select:focus, input:focus {
            outline: none;
            border-color: var(--accent-color);
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
        }

        .form-actions {
            display: flex;
            justify-content: flex-end;
            gap: 1rem;
            margin-top: 2rem;
        }

        .info-text {
            font-size: 0.9rem;
            color: var(--text-light);
            margin-top: 0.5rem;
        }

        .small-input {
            width: 100px !important;
            display: inline-block;
            margin-right: 10px;
        }

        @media (max-width: 992px) {
            .sidebar {
                transform: translateX(-100%);
            }
            .sidebar.active {
                transform: translateX(0);
            }
            .main-content {
                margin-left: 0;
                width: 100%;
            }
            .sidebar-toggle {
                display: block !important;
            }
        }

        @media (max-width: 768px) {
            .container {
                padding: 1rem;
            }
            .form-actions {
                flex-direction: column;
            }
            .btn {
                width: 100%;
                justify-content: center;
            }
        }
    </style>
</head>
<body>
    <!-- Sidebar Toggle Button (Mobile) -->
    <button class="sidebar-toggle" onclick="toggleSidebar()">
        <i class="fas fa-bars"></i>
    </button>

    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        <div class="sidebar-header">
            <h3><i class="fas fa-book-open"></i> Bibliothèque</h3>
        </div>
        <nav class="sidebar-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/dashboard">
                        <i class="fas fa-tachometer-alt"></i> Tableau de bord
                    </a>
                </li>
                <li class="has-submenu active">
                    <a href="#">
                        <i class="fas fa-book"></i> Gestion des prêts
                        <i class="fas fa-chevron-down ml-auto"></i>
                    </a>
                    <ul class="submenu">
                        <li><a href="${pageContext.request.contextPath}/admin/pretEnCours"><i class="fas fa-list"></i> Liste des prêts</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/pret/new" class="active"><i class="fas fa-plus"></i> Ajouter un prêt</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/rendu"><i class="fas fa-copy"></i> Rendre un prêt</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/membres">
                        <i class="fas fa-users"></i> Gestion des membres
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/reservations">
                        <i class="fas fa-calendar-check"></i> Réservations
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/retards">
                        <i class="fas fa-exclamation-triangle"></i> Retards
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/statistiques">
                        <i class="fas fa-chart-bar"></i> Statistiques
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/logout">
                        <i class="fas fa-sign-out-alt"></i> Déconnexion
                    </a>
                </li>
            </ul>
        </nav>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="container">
            <header>
                <h1><i class="fas fa-hand-holding"></i> Nouveau Prêt</h1>
                <a href="${pageContext.request.contextPath}/admin/pretEnCours" class="btn btn-primary">
                    <i class="fas fa-arrow-left"></i> Retour à la liste
                </a>
                <c:if test="${error != null}">
                    <h1>${error}</h1>                            
                </c:if>
                <c:if test="${succes != null}">
                    <h1>${success}</h1>
                </c:if>
                
            </header>

            <div class="form-container">
                <form action="${pageContext.request.contextPath}/adminSaveEmprunt" method="POST">
                    <div class="form-group">
                        <label for="adherent">Adhérent *</label>
                        <select name="adherentId" id="adherent" required>
                            <option value="">-- Sélectionner un adhérent --</option>
                            <c:forEach items="${adherents}" var="adherent">
                                <option value="${adherent.id}">${adherent.nom} ${adherent.prenom} (${adherent.telephone})</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="livre">Livre *</label>
                        <select name="livreId" id="livre" required>
                            <option value="">-- Sélectionner un livre --</option>
                            <c:forEach items="${livresDisponibles}" var="livre">
                                <option value="${livre.id}" data-exemplaires="${livre.nbrExemplaire}">
                                    ${livre.titre} (${livre.auteur}) 
                                    <c:if test="${livre.nbrExemplaire > 0}">
                                        - Disponible (${livre.nbrExemplaire} ex.)
                                    </c:if>
                                </option>
                            </c:forEach>
                        </select>
                        <p class="info-text" id="disponibilite"></p>
                    </div>
                    
                    <div class="form-group">
                        <label for="datePret">Date de prêt *</label>
                        <input type="date" name="datePret" id="datePret" required 
                            value="${param.datePret}"> <!-- Retirer max="${maxDate}" -->
                    </div>
                    
                    <div class="form-group">
                        <label for="dureePret">Durée du prêt (jours)</label>
                        <input type="number" name="dureePret" id="dureePret" min="1" max="90" 
                               value="15" class="small-input">
                        <p class="info-text">Durée par défaut: 15 jours (modifiable)</p>
                    </div>
                    
                    <div class="form-group">
                        <label for="dateRetourPrevue">Date de retour prévue *</label>
                        <input type="date" name="dateRetourPrevue" id="dateRetourPrevue" required
                               value="${param.dateRetourPrevue}">
                    </div>
                    
                    <div class="form-group">
                        <label for="commentaire">Commentaire</label>
                        <input type="text" name="commentaire" id="commentaire" 
                               placeholder="Facultatif">
                    </div>
                    
                    <div class="form-actions">
                        <button type="reset" class="btn btn-danger">
                            <i class="fas fa-times"></i> Annuler
                        </button>
                        <button type="submit" class="btn btn-success">
                            <i class="fas fa-save"></i> Enregistrer le prêt
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Configuration
            const DUREE_PRET_JOURS = 15;
            
            // Éléments du formulaire
            const datePretInput = document.getElementById('datePret');
            const dateRetourInput = document.getElementById('dateRetourPrevue');
            const dureePretInput = document.getElementById('dureePret');
            const livreSelect = document.getElementById('livre');
            const disponibiliteText = document.getElementById('disponibilite');
            
            // Calcul automatique de la date de retour
            function calculerDateRetour() {
                if (datePretInput.value) {
                    const datePret = new Date(datePretInput.value);
                    const duree = dureePretInput ? parseInt(dureePretInput.value) || DUREE_PRET_JOURS : DUREE_PRET_JOURS;
                    
                    datePret.setDate(datePret.getDate() + duree);
                    dateRetourInput.value = datePret.toISOString().split('T')[0];
                }
            }
            
            // Contrôle de la durée
            if (dureePretInput) {
                dureePretInput.addEventListener('change', calculerDateRetour);
                dureePretInput.addEventListener('input', function() {
                    if (!this.value) return;
                    this.value = Math.max(1, Math.min(90, this.value));
                });
            }
            
            // Affichage de la disponibilité
            livreSelect.addEventListener('change', function() {
                const selectedOption = this.options[this.selectedIndex];
                if (selectedOption.value) {
                    const exemplaires = selectedOption.getAttribute('data-exemplaires');
                    disponibiliteText.textContent = `Exemplaires disponibles ${exemplaires}`;
                    disponibiliteText.style.color = exemplaires <= 0 ? 'var(--danger-color)' : 'var(--success-color)';
                } else {
                    disponibiliteText.textContent = '';
                }
            });
            
            // Événements
            datePretInput.addEventListener('change', calculerDateRetour);
            
            // Initialisation
            //datePretInput.max = new Date().toISOString().split('T')[0];
            if (datePretInput.value && !dateRetourInput.value) {
                calculerDateRetour();
            }
            
            // Gestion des sous-menus
            const hasSubmenu = document.querySelectorAll('.has-submenu');
            hasSubmenu.forEach(item => {
                item.addEventListener('click', function(e) {
                    if (e.target.tagName === 'A') {
                        this.classList.toggle('active');
                    }
                });
            });
        });
        
        // Fonction pour toggle la sidebar en mobile
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }
    </script>
</body>
</html>