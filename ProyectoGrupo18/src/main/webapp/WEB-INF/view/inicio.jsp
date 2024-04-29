<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fit Score</title>
</head>
<body>
<div>
    <h1 style="text-align: center;">Bienvenido a Fit Score</h1>
    <div style="text-align: center">
        <h3>Elige un rol de usuario:</h3>
        <form action="/login" method="post">
            <input type="radio" name="rol" value="admin" style="margin-bottom: 1%"/>Administrador
            <input type="radio" name="rol" value="cliente" style="margin-left: 1%"/>Cliente
            <br/>
            <button type="submit">Aceptar</button>
        </form>
    </div>
</div>
</body>
</html>

