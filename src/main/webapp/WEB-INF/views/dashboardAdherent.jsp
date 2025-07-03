<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espace Adhérent | Bibliothèque</title>
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

        .card {
            background-color: white;
            border-radius: 8px;
            box-shadow: var(--shadow);
            overflow: hidden;
            margin-bottom: 2rem;
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
            padding: 0.25rem 0.5rem;
            border-radius: 50px;
            font-size: 0.75rem;
            font-weight: 500;
            background-color: var(--light-color);
            color: var(--text-color);
            margin-right: 0.5rem;
            margin-bottom: 0.5rem;
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

        .status-available {
            background-color: #d4edda;
            color: #155724;
        }

        .status-unavailable {
            background-color: #fff3cd;
            color: #856404;
        }

        .status-late {
            background-color: #f8d7da;
            color: #721c24;
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

        /* Dashboard cards */
        .dashboard-cards {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 1.5rem;
            margin-bottom: 2rem;
        }

        .dashboard-card {
            background: white;
            border-radius: 8px;
            padding: 1.5rem;
            box-shadow: var(--shadow);
            transition: var(--transition);
        }

        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }

        .dashboard-card h3 {
            color: var(--text-light);
            font-size: 1rem;
            margin-bottom: 0.5rem;
        }

        .dashboard-card .value {
            font-size: 2rem;
            font-weight: 700;
            color: var(--primary-color);
        }

        .dashboard-card .icon {
            font-size: 2.5rem;
            color: var(--accent-color);
            margin-bottom: 1rem;
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
            <h3><i class="fas fa-user-circle"></i> Mon Espace</h3>
        </div>
        <nav class="sidebar-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/adherent/dashboard" class="active">
                        <i class="fas fa-tachometer-alt"></i> Tableau de bord
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/adherent/exemplaires">
                        <i class="fas fa-book"></i> Catalogue des livres
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/adherent/mes-prets">
                        <i class="fas fa-exchange-alt"></i> Mes prêts
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/adherent/mes-reservations">
                        <i class="fas fa-calendar-check"></i> Mes réservations
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/adherent/abonnement">
                        <i class="fas fa-id-card"></i> Mon abonnement
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/adherent/historique">
                        <i class="fas fa-history"></i> Mon historique
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
                <h1><i class="fas fa-tachometer-alt"></i> Mon Tableau de bord</h1>
                <div class="user-info">
                    Bonjour, <strong>${adherent.prenom} ${adherent.nom}</strong>
                </div>
            </header>

            <!-- Dashboard Cards -->
            <div class="dashboard-cards">
                <div class="dashboard-card">
                    <div class="icon"><i class="fas fa-book-open"></i></div>
                    <h3>Livres empruntés</h3>
                    <div class="value">${nombrePretsEnCours}</div>
                </div>
                <div class="dashboard-card">
                    <div class="icon"><i class="fas fa-clock"></i></div>
                    <h3>En attente</h3>
                    <div class="value">${nombreReservations}</div>
                </div>
                <div class="dashboard-card">
                    <div class="icon"><i class="fas fa-calendar-day"></i></div>
                    <h3>Prochain retour</h3>
                    <div class="value">${prochainRetour}</div>
                </div>
                <div class="dashboard-card">
                    <div class="icon"><i class="fas fa-exclamation-triangle"></i></div>
                    <h3>Retards</h3>
                    <div class="value" style="color: var(--danger-color);">${nombreRetards}</div>
                </div>
            </div>

            <!-- Section Mes prêts en cours -->
            <div class="card">
                <div class="card-header">
                    <h2><i class="fas fa-exchange-alt"></i> Mes prêts en cours</h2>
                </div>
                <div class="table-responsive">
                    <table>
                        <thead>
                            <tr>
                                <th>Titre</th>
                                <th>Date d'emprunt</th>
                                <th>Date de retour</th>
                                <th>Statut</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty prets}">
                                    <c:forEach items="${prets}" var="pret">
                                        <tr>
                                            <td>
                                                <strong>${pret.livre.titre}</strong><br>
                                                <span class="text-muted">${pret.livre.auteur}</span>
                                            </td>
                                            <td>${pret.dateEmprunt}</td>
                                            <td>${pret.dateRetourPrevue}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${pret.enRetard}">
                                                        <span class="badge status-late">En retard</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge status-available">En cours</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="actions">
                                                <c:if test="${!pret.enRetard && !pret.prolonge}">
                                                    <a href="${pageContext.request.contextPath}/adherent/prolonger/${pret.id}" class="btn btn-primary btn-sm">
                                                        <i class="fas fa-calendar-plus"></i> Prolonger
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5">
                                            <div class="empty-state">
                                                <i class="fas fa-book-open"></i>
                                                <h3>Aucun prêt en cours</h3>
                                                <p>Consultez notre catalogue pour trouver votre prochaine lecture</p>
                                                <a href="${pageContext.request.contextPath}/adherent/exemplaires" class="btn btn-primary mt-2">
                                                    <i class="fas fa-search"></i> Chercher un livre
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
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
    </script>
</body>
</html>