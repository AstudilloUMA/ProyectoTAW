<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 14/05/2024
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error Dieta 1</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<div class="advise">
    <h1>Error en el número de comidas de la dieta</h1>
</div>
<div style="text-align: center">
    <h3>Las comidas deben ser de 3 a 5</h3>
</div>

<div style="text-align: center">
    <a href="/dietista/crear"><button>Atrás</button></a>
</div>

</body>
</html>