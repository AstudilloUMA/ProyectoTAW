<%--
Autores:
Pablo Astudillo Fraga:90%
Miguel Sánchez Hontoria:10%
--%>

<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String tipo = (String) request.getSession().getAttribute("tipo");
%>
<div class="navbar">
    <div class="navbar-left">
        <a href="/<%=tipo%>/"><button>Inicio</button></a>

    </div>
    <h1 class="navbar-title"><a href="/<%=tipo%>/" class="navbar-h1-a">FIT SCORE</a></h1>
    <div class="navbar-right">
        <a href="/infoUsuario?id=<%=usuario.getId()%>&tipo=admin"><button><%=usuario.getNombre()%></button></a>
        <a href="/logout"><button>Log Out</button></a>
    </div>
</div>

