<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Membres | Bibliothèque</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&family=Playfair+Display:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34495e;
            --accent-color: #3498db;
            --success-color: #27ae60;
            --danger-color: #e74c3c;
            --warning-color: #f39c12;
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

        /* Main Content Styles */
        .main-content {
            margin-left: var(--sidebar-width);
            width: calc(100% - var(--sidebar-width));
            padding: 2rem;
            transition: var(--transition);
        }

        .container {
            max-width: 1400px;
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
            padding: 0.6rem 1.2rem;
            border-radius: 4px;
            font-weight: 500;
            text-decoration: none;
            transition: var(--transition);
            border: none;
            cursor: pointer;
            font-size: 0.9rem;
            gap: 0.5rem;
        }

        .btn-sm {
            padding: 0.4rem 0.8rem;
            font-size: 0.8rem;
        }

        .btn-primary {
            background-color: var(--accent-color);
            color: white;
        }

        .btn-success {
            background-color: var(--success-color);
            color: white;
        }

        .btn-danger {
            background-color: var(--danger-color);
            color: white;
        }

        .btn-warning {
            background-color: var(--warning-color);
            color: white;
        }

        .btn-outline {
            background-color: transparent;
            border: 1px solid var(--accent-color);
            color: var(--accent-color);
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: var(--shadow);
        }

        .btn-primary:hover {
            background-color: #2980b9;
        }

        .card {
            background-color: white;
            border-radius: 8px;
            box-shadow: var(--shadow);
            overflow: hidden;
            margin-bottom: 2rem;
        }

        .card-header {
            padding: 1.5rem;
            border-bottom: 1px solid #eee;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .card-header h2 {
            font-size: 1.5rem;
            color: var(--primary-color);
            display: flex;
            align-items: center;
            gap: 0.8rem;
        }

        .table-responsive {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 1rem 1.5rem;
            text-align: left;
            vertical-align: middle;
        }

        thead {
            background-color: var(--primary-color);
            color: white;
        }

        th {
            font-weight: 500;
            text-transform: uppercase;
            font-size: 0.8rem;
            letter-spacing: 0.5px;
        }

        tbody tr {
            border-bottom: 1px solid #eee;
            transition: var(--transition);
        }

        tbody tr:last-child {
            border-bottom: none;
        }

        tbody tr:hover {
            background-color: #f5f5f5;
        }

        .badge {
            display: inline-block;
            padding: 0.35rem 0.65rem;
            border-radius: 50px;
            font-size: 0.75rem;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .badge-success {
            background-color: #d4edda;
            color: #155724;
        }

        .badge-danger {
            background-color: #f8d7da;
            color: #721c24;
        }

        .badge-warning {
            background-color: #fff3cd;
            color: #856404;
        }

        .badge-info {
            background-color: #d1ecf1;
            color: #0c5460;
        }

        .text-muted {
            color: var(--text-light);
            font-size: 0.9rem;
        }

        .actions {
            display: flex;
            gap: 0.5rem;
        }

        .empty-state {
            text-align: center;
            padding: 3rem;
            color: var(--text-light);
        }

        .empty-state i {
            font-size: 3rem;
            margin-bottom: 1rem;
            color: #ddd;
        }

        .search-filter {
            display: flex;
            gap: 1rem;
            margin-bottom: 1.5rem;
            align-items: center;
        }

        .search-filter .form-group {
            flex: 1;
        }

        .form-control {
            width: 100%;
            padding: 0.75rem 1rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            transition: var(--transition);
        }

        .form-control:focus {
            border-color: var(--accent-color);
            outline: none;
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
        }

        .pagination {
            display: flex;
            justify-content: center;
            padding: 1.5rem;
            gap: 0.5rem;
        }

        .pagination a {
            padding: 0.5rem 0.8rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            color: var(--accent-color);
            text-decoration: none;
        }

        .pagination a.active {
            background-color: var(--accent-color);
            color: white;
            border-color: var(--accent-color);
        }

        .pagination a:hover:not(.active) {
            background-color: #f1f1f1;
        }

        .member-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: var(--accent-color);
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
            text-transform: uppercase;
        }

        .member-info {
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        .member-name {
            font-weight: 500;
        }

        .member-email {
            font-size: 0.8rem;
            color: var(--text-light);
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
            
            th, td {
                padding: 0.75rem;
            }
            
            .actions {
                flex-direction: column;
                gap: 0.25rem;
            }

            .search-filter {
                flex-direction: column;
                align-items: stretch;
            }
        }

        /* Toggle button for mobile */
        .sidebar-toggle {
            display: none;
            position: fixed;
            top: 1rem;
            left: 1rem;
            z-index: 1100;
            background: var(--primary-color);
            color: white;
            border: none;
            border-radius: 4px;
            padding: 0.5rem;
            cursor: pointer;
        }

        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1200;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
        }

        .modal-content {
            background-color: white;
            margin: 5% auto;
            padding: 2rem;
            border-radius: 8px;
            width: 50%;
            max-width: 600px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
        }

        .modal-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1.5rem;
            padding-bottom: 1rem;
            border-bottom: 1px solid #eee;
        }

        .modal-footer {
            margin-top: 1.5rem;
            display: flex;
            justify-content: flex-end;
            gap: 1rem;
        }

        .close {
            color: #aaa;
            font-size: 1.5rem;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: var(--danger-color);
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
            <h3><i class="fas fa-user-shield"></i> Administration</h3>
        </div>
        <nav class="sidebar-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="active">
                        <i class="fas fa-tachometer-alt"></i> Tableau de bord
                    </a>
                </li>
                <li class="has-submenu">
                    <a href="#">
                        <i class="fas fa-book"></i> Gestion des prêts
                        <i class="fas fa-chevron-down ml-auto"></i>
                    </a>
                    <ul class="submenu">
                        <li><a href="${pageContext.request.contextPath}/admin/pretEnCours"><i class="fas fa-list"></i> Liste des prêts</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/pret/new"><i class="fas fa-plus"></i> Ajouter un prêts</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/rendu"><i class="fas fa-copy"></i> rendre un prêts</a></li>
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
                        <span class="badge" style="background: var(--danger-color); color: white;">3</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/statistiques">
                        <i class="fas fa-chart-bar"></i> Statistiques
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/parametres">
                        <i class="fas fa-cog"></i> Paramètres
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
                <h1><i class="fas fa-users"></i> Gestion des Membres</h1>
                <div class="user-info">
                    Connecté en tant que <strong>${admin.prenom} ${admin.nom}</strong>
                </div>
            </header>

            <!-- Search and Filter Section -->
            <div class="search-filter">
                <div class="form-group" style="flex: 2;">
                    <input type="text" class="form-control" placeholder="Rechercher un membre (nom, prénom, email...)">
                </div>
                <div class="form-group">
                    <select class="form-control">
                        <option value="">Tous les statuts</option>
                        <option value="actif">Actif</option>
                        <option value="inactif">Inactif</option>
                        <option value="suspendu">Suspendu</option>
                    </select>
                </div>
                <div class="form-group">
                    <select class="form-control">
                        <option value="">Trier par</option>
                        <option value="nom">Nom (A-Z)</option>
                        <option value="date_inscription">Date d'inscription</option>
                        <option value="prets">Nombre de prêts</option>
                    </select>
                </div>
                <a href="${pageContext.request.contextPath}/admin/membres/ajouter" class="btn btn-primary">
                    <i class="fas fa-plus"></i> Ajouter un membre
                </a>
            </div>

            <!-- Members Table -->
            <div class="card">
                <div class="card-header">
                    <h2><i class="fas fa-list"></i> Liste des membres</h2>
                    <span class="text-muted">${totalMembres} membres trouvés</span>
                </div>
                <div class="table-responsive">
                    <table>
                        <thead>
                            <tr>
                                <th>Membre</th>
                                <th>Contact</th>
                                <th>Adhésion</th>
                                <th>Statut</th>
                                <th>Prêts en cours</th>
                                <th>Pénalités</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty membres}">
                                    <c:forEach items="${membres}" var="membre">
                                        <tr>
                                            <td>
                                                <div class="member-info">
                                                    <div class="member-avatar">
                                                        ${membre.prenom.charAt(0)}${membre.nom.charAt(0)}
                                                    </div>
                                                    <div>
                                                        <div class="member-name">${membre.prenom} ${membre.nom}</div>
                                                        <div class="member-id">ID: ${membre.id}</div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div>${membre.email}</div>
                                                <div class="text-muted">${membre.telephone}</div>
                                            </td>
                                            <td>
                                                <div>${membre.dateInscription}</div>
                                                <div class="text-muted">
                                                    <c:choose>
                                                        <c:when test="${membre.actif}">
                                                            Abonnement valide
                                                        </c:when>
                                                        <c:otherwise>
                                                            Abonnement expiré
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </td>
                                            <td>  
                                                 <c:choose>         
                                                    <c:when test="${membre.actif}">
                                                        <span class="badge badge-success">Actif</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                                Inactif
                                                    </c:otherwise>
                                                </c:choose> 
                                            </td>
                                            <td>
                                                ${membre.pretsEnCours} / ${membre.maxPrets}
                                                <c:if test="${membre.pretsEnCours >= membre.maxPrets}">
                                                    <span class="badge badge-warning">Quota atteint</span>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${membre.penalite > 0}">
                                                        <span class="badge badge-danger">${membre.penalite} €</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge badge-success">Aucune</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="actions">
                                                <a href="${pageContext.request.contextPath}/admin/membres/${membre.id}" 
                                                   class="btn btn-sm btn-outline" 
                                                   title="Voir le profil">
                                                    <i class="fas fa-eye"></i>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/admin/membres/modifier/${membre.id}" 
                                                   class="btn btn-sm btn-primary" 
                                                   title="Modifier">
                                                    <i class="fas fa-edit"></i>
                                                </a>
                                                
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="7">
                                            <div class="empty-state">
                                                <i class="fas fa-user-slash"></i>
                                                <h3>Aucun membre trouvé</h3>
                                                <p>Essayez de modifier vos critères de recherche</p>
                                            </div>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination -->
                <div class="pagination">
                    <c:if test="${page > 1}">
                        <a href="${pageContext.request.contextPath}/admin/membres?page=${page-1}">&laquo;</a>
                    </c:if>
                    
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="${pageContext.request.contextPath}/admin/membres?page=${i}" 
                           class="${i == page ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    
                    <c:if test="${page < totalPages}">
                        <a href="${pageContext.request.contextPath}/admin/membres?page=${page+1}">&raquo;</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <!-- Confirmation Modal -->
    <div id="confirmationModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Confirmer la suppression</h3>
                <span class="close" onclick="closeModal()">&times;</span>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr de vouloir supprimer le membre <strong id="membreNom"></strong> ?</p>
                <p class="text-muted">Cette action est irréversible et supprimera toutes les données associées à ce membre.</p>
            </div>
            <div class="modal-footer">
                <button onclick="closeModal()" class="btn btn-outline">Annuler</button>
                <button id="confirmDeleteBtn" class="btn btn-danger">Confirmer la suppression</button>
            </div>
        </div>
    </div>

    <script>
        // Script pour améliorer l'interactivité
        document.addEventListener('DOMContentLoaded', function() {
            // Effets hover sur les boutons
            const buttons = document.querySelectorAll('.btn');
            buttons.forEach(button => {
                button.addEventListener('mouseenter', function() {
                    this.style.transform = 'translateY(-2px)';
                    this.style.boxShadow = '0 6px 12px rgba(0, 0, 0, 0.15)';
                });
                button.addEventListener('mouseleave', function() {
                    this.style.transform = 'translateY(0)';
                    this.style.boxShadow = 'var(--shadow)';
                });
            });
        });

        // Fonction pour toggle la sidebar en mobile
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }

        // Fonctions pour la gestion des membres
        let currentMembreId = null;

        function confirmSuppression(id, nom) {
            currentMembreId = id;
            document.getElementById('membreNom').textContent = nom;
            document.getElementById('confirmationModal').style.display = 'block';
        }

        function closeModal() {
            document.getElementById('confirmationModal').style.display = 'none';
            currentMembreId = null;
        }

        document.getElementById('confirmDeleteBtn').addEventListener('click', function() {
            if (currentMembreId) {
                window.location.href = '${pageContext.request.contextPath}/admin/membres/supprimer/' + currentMembreId;
            }
        });

        function suspendreMembre(id) {
            if (confirm('Êtes-vous sûr de vouloir suspendre ce membre ?')) {
                window.location.href = '${pageContext.request.contextPath}/admin/membres/suspendre/' + id;
            }
        }

        function reactiverMembre(id) {
            if (confirm('Êtes-vous sûr de vouloir réactiver ce membre ?')) {
                window.location.href = '${pageContext.request.contextPath}/admin/membres/reactiver/' + id;
            }
        }

        // Fermer la modal si on clique en dehors
        window.onclick = function(event) {
            const modal = document.getElementById('confirmationModal');
            if (event.target == modal) {
                closeModal();
            }
        }
    </script>
</body>
</html>