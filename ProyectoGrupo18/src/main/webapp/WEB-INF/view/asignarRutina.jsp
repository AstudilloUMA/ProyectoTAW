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
    RutinaSemanalEntity rutina = (RutinaSemanalEntity) request.getAttribute("rutina");
    List<ClienteEntity> clientes = (List<ClienteEntity>) request.getAttribute("clientes");
%>
<html>
<head>
    <title>Asignar Rutina</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="advise">
    <h1>Asigna la rutina <%=rutina.getNombre()%> - Inicio: <%=rutina.getFechaInicio()%> - Final: <%=rutina.getFechaFin()%></h1>
</div>
<div style="text-align: center">
    <%
        if(clientes.isEmpty() || clientes == null){
    %>
    <h2 style="text-align: center">No hay usuarios registrados</h2>
    <a href="rutinas"><button>Volver</button></a>
    <%
        }else{
    %>
        <div class="form-container">
            <h2 style="text-align: center">Selecciona el usuario al que quieres asignar la rutina</h2>
            <form method="post" action="asignada">
                <input type="hidden" name="idRutina" value="<%=rutina.getId()%>">
                <select name="id">
                    <%
                        for(ClienteEntity c : clientes){
                            UsuarioEntity usuario = c.getUsuarioByUsuarioId();
                    %>
                        <option value="<%=usuario.getId()%>"><%=usuario.getNombre()%> <%=usuario.getApellidos()%></option>
                    <%
                        }
                    %>
                </select>
                <br/>
                <button>Asignar</button>
            </form>
            <a href="rutinas"><button>Volver</button></a>
        </div>
    <%
        }
    %>
</div>
</body>
</html>
