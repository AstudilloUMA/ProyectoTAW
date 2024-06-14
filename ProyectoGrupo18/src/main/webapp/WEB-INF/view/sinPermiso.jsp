<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 14/05/2024
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String tipo = (String) request.getSession().getAttribute("tipo");
%>
<html>
<head>
    <title>Sin Permisos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <div class="advise">
        <h1>No tiene permisos para estar aqui</h1>
    </div>

    <%
        if(usuario != null)
        {

    %>
        <div style="text-align: center">
            <a href="/<%=tipo%>/"><button>Inicio</button></a>
        </div>
    <%
        } else{
    %>
        <div style="text-align: center">
            <a href="/"><button>Inicio</button></a>
        </div>
    <%
        }
    %>
</body>
</html>