<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Ajouter un film</title>
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

        .category-checkboxes {
            display: flex;
            flex-direction: column;
            margin-top: 10px;
        }

        .category-item {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<h1>Ajouter un film</h1>
<form:form action="${pageContext.request.contextPath}/films" method="post" modelAttribute="film">
    <div class="form-group">
        <label>
            Titre:
            <form:input path="titre" required="required"/>
        </label>
    </div>
    <div class="form-group">
        <label>
            Date de sortie:
            <form:input path="dateSortie" type="date" required="required"/>
        </label>
    </div>
    <div class="form-group">
        <label>
            Type:
            <select name="typeId" required>
                <option value="">Sélectionner un type</option>
                <c:forEach items="${types}" var="type">
                    <option value="${type.id}">${type.nom}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div class="form-group">
        <label>Catégories:</label>
        <c:forEach items="${categories}" var="categorie">
            <div>
                <input type="checkbox" name="categorieIds" value="${categorie.id}">
                <label>${categorie.nom}</label>
            </div>
        </c:forEach>
    </div>
    <button type="submit">Ajouter</button>
</form:form>
</body>
</html>

