<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.dto.Cliente" %>
<%@ page import="es.uma.proyectogrupo18.dto.RutinaSemanal" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 20/05/2024
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    List<RutinaSemanal> rutinas = (List<RutinaSemanal>) request.getAttribute("rutinas");
%>
<html>
<head>
    <title>Asignar Rutina</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbarEntrenador.jsp"/>

<div class="advise">
    <h1>Asignar al cliente <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%></h1>
</div>
<div style="text-align: center">
    <%
        if(rutinas.isEmpty() || rutinas == null){
    %>
    <h2 style="text-align: center">No hay rutinas registradas</h2>
    <a href="clientes"><button>Volver</button></a>
    <%
        }else{
    %>
        <div class="form-container">
            <h2 style="text-align: center">Selecciona la rutina que quieres asignar</h2>
            <form method="post" action="personalizar">
                <input type="hidden" name="id" value="<%=cliente.getId()%>">
                <select name="rutinaId">
                    <%
                        for(RutinaSemanal r : rutinas){
                    %>
                        <option value="<%=r.getId()%>"><%=r.getNombre()%> - <%=r.getFechaInicio()%> - <%=r.getFechaFin()%></option>
                    <%
                        }
                    %>
                </select>
                <br/>
                <button>Asignar</button>
            </form>
            <a href="clientes"><button>Volver</button></a>
        </div>
    <%
        }
    %>
</div>
</body>
</html>
