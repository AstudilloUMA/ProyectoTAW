<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 14/05/2024
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) request.getSession().getAttribute("usuario");
    String tipo = (String) request.getSession().getAttribute("tipo");
%>
<div class="navbar">
    <div class="navbar-left">
        <a href="/<%=tipo%>/"><button>Inicio</button></a>

    </div>
    <h1 class="navbar-title"><a href="/<%=tipo%>/" class="navbar-h1-a">FIT SCORE</a></h1>
    <div class="navbar-right">
        <a><button><%=usuario.getNombre()%></button></a>
        <a href="/logout"><button>Log Out</button></a>
    </div>
</div>
