<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Modifier un film</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 40px 20px;
            background-color: #f5f5f5;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }

        form {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 20px;
            color: #2c3e50;
            font-weight: 500;
        }

        input[type="text"], input[type="date"], select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-family: inherit;
        }

        input[type="checkbox"] {
            margin-left: 10px;
            transform: scale(1.2);
        }

        button {
            background-color: navy;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-family: inherit;
            font-weight: 500;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #2980b9;
        }

        .form-group {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<h1>Modifier un film</h1>
<form action="${pageContext.request.contextPath}/films/update" method="post">
    <input type="hidden" name="id" value="${film.id}"/>
    <div class="form-group">
        <label>
            Titre:
            <input type="text" name="titre" value="${film.titre}" required/>
        </label>
    </div>
    <div class="form-group">
        <label>
            Date de sortie:
            <input type="date" name="dateSortie" value="${film.dateSortie}" required/>
        </label>
    </div>
    <div class="form-group">
        <label>
            Type:
            <select name="type.id" required>
                <option value="">Sélectionner un type</option>
                <c:forEach items="${types}" var="type">
                    <option value="${type.id}" ${film.type.id == type.id ? 'selected' : ''}>${type.nom}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div class="form-group">
        <label>
            Catégories:
            <c:forEach items="${categories}" var="categorie">
                <label>
                    <input type="checkbox" name="categorieIds" value="${categorie.id}"
                           <c:if test="${film.categories.contains(categorie)}">checked</c:if>/> ${categorie.nom}
                </label>
            </c:forEach>
        </label>
    </div>
    <button type="submit">Mettre à jour</button>
</form>
</body>
</html>