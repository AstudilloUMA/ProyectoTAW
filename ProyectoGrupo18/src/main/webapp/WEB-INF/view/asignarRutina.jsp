<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 20/05/2024
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    List<RutinaSemanalEntity> rutinas = (List<RutinaSemanalEntity>) request.getAttribute("rutinas");
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
                        for(RutinaSemanalEntity r : rutinas){
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
