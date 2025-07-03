<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion | Bibliothèque</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&family=Playfair+Display:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34495e;
            --accent-color: #3498db;
            --light-color: #ecf0f1;
            --text-color: #333;
            --text-light: #7f8c8d;
            --shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            --transition: all 0.3s ease;
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
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
        }

        .login-container {
            width: 100%;
            max-width: 450px;
            padding: 2.5rem;
            background: white;
            border-radius: 10px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            transform: translateY(-5%);
        }

        .login-header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .login-header h1 {
            font-family: 'Playfair Display', serif;
            font-weight: 700;
            color: var(--primary-color);
            font-size: 2.2rem;
            margin-bottom: 0.5rem;
        }

        .login-header p {
            color: var(--text-light);
            font-size: 0.95rem;
        }

        .login-icon {
            font-size: 3rem;
            color: var(--accent-color);
            margin-bottom: 1rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: var(--primary-color);
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

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 100%;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            font-weight: 500;
            text-decoration: none;
            transition: var(--transition);
            border: none;
            cursor: pointer;
            font-size: 1rem;
            margin-top: 0.5rem;
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

        .forgot-password {
            text-align: right;
            margin-top: -1rem;
            margin-bottom: 1.5rem;
        }

        .forgot-password a {
            color: var(--text-light);
            font-size: 0.85rem;
            text-decoration: none;
            transition: var(--transition);
        }

        .forgot-password a:hover {
            color: var(--accent-color);
        }

        .input-icon {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: var(--text-light);
        }

        .footer-links {
            margin-top: 2rem;
            text-align: center;
            font-size: 0.9rem;
            color: var(--text-light);
        }

        .footer-links a {
            color: var(--accent-color);
            text-decoration: none;
            transition: var(--transition);
        }

        .footer-links a:hover {
            text-decoration: underline;
        }

        @media (max-width: 576px) {
            .login-container {
                padding: 1.5rem;
                margin: 0 1rem;
            }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <div class="login-icon">
                <i class="fas fa-book-open"></i>
            </div>
            <h1>S'inscrire</h1>
            <p>Inscriver vous pour devenir membre</p>
        </div>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">Nom </label>
                <input type="text" id="username" name="name" class="form-control" placeholder="Entrez votre nom d'utilisateur" required>
                <i class="fas fa-user input-icon"></i>
            </div>
             <div class="form-group">
                <label for="username">Email </label>
                <input type="mail" id="username" name="email" class="form-control" placeholder="Entrez votre nom d'utilisateur" required>
                <i class="fas fa-user input-icon"></i>
            </div>
             <div class="form-group">
                <label for="username">Nom d'utilisateur </label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Entrez votre nom d'utilisateur" required>
                <i class="fas fa-user input-icon"></i>
            </div>

            <div class="form-group">
                <label for="password">Mot de passe</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Entrez votre mot de passe" required>
                <i class="fas fa-lock input-icon"></i>
            </div>

            <button type="submit" class="btn btn-primary">
                <i class="fas fa-sign-in-alt"></i>  S' inscrire
            </button>
        </form>

        <div class="footer-links">
            <p><a href="${pageContext.request.contextPath}/login">Se connecter</a></p>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Animation au chargement
            const loginContainer = document.querySelector('.login-container');
            loginContainer.style.opacity = '0';
            loginContainer.style.transform = 'translateY(20px)';
            
            setTimeout(() => {
                loginContainer.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
                loginContainer.style.opacity = '1';
                loginContainer.style.transform = 'translateY(-5%)';
            }, 100);

            // Effet hover sur le bouton
            const loginBtn = document.querySelector('.btn-primary');
            loginBtn.addEventListener('mouseenter', function() {
                this.style.transform = 'translateY(-4px)';
                this.style.boxShadow = '0 6px 12px rgba(0, 0, 0, 0.15)';
            });
            loginBtn.addEventListener('mouseleave', function() {
                this.style.transform = 'translateY(-2px)';
                this.style.boxShadow = 'var(--shadow)';
            });
        });
    </script>
</body>
</html>