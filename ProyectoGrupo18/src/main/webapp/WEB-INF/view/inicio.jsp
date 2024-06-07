<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) request.getSession().getAttribute("usuario");
    String tipo;
    if(usuario != null){
        tipo = (String) request.getSession().getAttribute("tipo");
    }
    else {
        tipo = "login";
    }
%>
<html>
<head>
    <title>Fit Score</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: url('${pageContext.request.contextPath}/images/fondoPrincipal.png') no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
<div style="text-align: center">
    <h1>Bienvenido a Fit Score</h1>
    <a href="/<%=tipo%>/"><button>Iniciar Sesión</button></a>
</div>
</body>
</html>

