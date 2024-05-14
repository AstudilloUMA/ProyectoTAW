<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.AdministradorEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) request.getSession().getAttribute("usuario");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<div class="navbar">
    <div class="navbar-left">
        <a href="/admin/"><button>Inicio</button></a>
        <a href="/admin/"><button>Rutinas</button></a>
        <a href="/admin/"><button>Dietas</button></a>
    </div>
    <h1 class="navbar-title">FIT SCORE</h1>
    <div class="navbar-right">
        <a href="/admin/"><button><%=usuario.getNombre()%></button></a>
        <a href="/logout"><button>Log Out</button></a>
    </div>
</div>

</body>
</html>

