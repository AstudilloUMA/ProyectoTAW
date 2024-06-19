<!--
Autor:
Juan Manuel Porcuna Martín
-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuarioUI = (Usuario) request.getAttribute("usuario");

%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<h1>Autenticar usuario</h1>
<form method="post" action="/admin/autenticado">
    <input type="hidden" name="id" value="<%= usuarioUI.getId() %>">
    <table border="0">
        <tr>
            <td>Nombre de usuario:</td>
            <td><%= usuarioUI.getUsuario() %></td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><input type="text" name="passw" size="100"  maxlength="100"  /></td>
        </tr>
        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form>


</body>
</html>
