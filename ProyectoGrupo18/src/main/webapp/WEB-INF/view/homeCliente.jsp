<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome Home <%=usuario.getNombre()%> <%=usuario.getApellidos()%></h1>
</body>
</html>
