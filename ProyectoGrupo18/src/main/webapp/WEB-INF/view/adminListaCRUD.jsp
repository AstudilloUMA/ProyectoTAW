<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroComida" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroEjercicio" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroSesion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<FiltroComida> comidas = (List<FiltroComida>) request.getAttribute("comidas");
    List<FiltroEjercicio> ejercicios = (List<FiltroEjercicio>) request.getAttribute("ejercicios");
    List<FiltroSesion> sesiones = (List<FiltroSesion>) request.getAttribute("sesiones");
    Boolean ifEj = (Boolean) request.getAttribute("ifEj");
    Boolean ifComida = (Boolean) request.getAttribute("ifComida");
    Boolean ifSe = (Boolean) request.getAttribute("ifSe");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>

<jsp:include page="navbarAdmin.jsp"/>
<h1>Filtro de Datos</h1>
<<form method="GET" action="filtrarCRUD" modelAttribute="filtro">
    <!-- Checkbox para mostrar filtros de comida -->
    <input type="checkbox" name="ifComida" <%= ifComida ? "checked" : "" %> /> Mostrar Filtros de Comida

    <!-- Sección para los filtros de comida -->
    <fieldset>
        <legend>Filtrar Comidas</legend>
        <label for="comidaId">ID:</label>
        <form:input path="id" id="comidaId"></form:input>
        <label for="comidaNombre">Nombre:</label>
        <form:input path="nombre" id="comidaNombre"></form:input>
        <label for="comidaCalorias">Calorías:</label>
        <form:input path="calorias" id="comidaCalorias"></form:input>
    </fieldset>

    <!-- Checkbox para mostrar filtros de ejercicio -->
    <input type="checkbox" name="ifEj" <%= ifEj ? "checked" : "" %> /> Mostrar Filtros de Ejercicio

    <!-- Sección para los filtros de ejercicio -->
    <fieldset>
        <legend>Filtrar Ejercicios</legend>
        <label for="ejercicioId">ID:</label>
        <form:input path="id" id="ejercicioId"></form:input>
        <label for="ejercicioTipo">Tipo:</label>
        <form:input path="tipo" id="ejercicioTipo"></form:input>
        <label for="ejercicioNombre">Nombre:</label>
        <form:input path="nombre" id="ejercicioNombre"></form:input>
    </fieldset>

    <!-- Checkbox para mostrar filtros de sesión -->
    <input type="checkbox" name="ifSe" <%= ifSe ? "checked" : "" %> /> Mostrar Filtros de Sesión

    <!-- Sección para los filtros de sesión -->
    <fieldset>
        <legend>Filtrar Sesiones</legend>
        <label for="sesionId">ID:</label>
        <form:input path="id" id="sesionId"></form:input>
        <label for="sesionRepeticiones">Repeticiones:</label>
        <form:input path="repeticiones" id="sesionRepeticiones"></form:input>
        <label for="sesionCantidad">Cantidad:</label>
        <form:input path="cantidad" id="sesionCantidad"></form:input>
        <label for="sesionEjercicio">Ejercicio:</label>
        <form:input path="ejercicio" id="sesionEjercicio"></form:input>
        <label for="sesionTrabajo">Trabajo:</label>
        <form:input path="trabajo" id="sesionTrabajo"></form:input>
    </fieldset>

    <input type="submit" value="Filtrar">
</form>


<h2>Mostrar Tablas</h2>
<form method="GET" action="toggleTables">
    <input type="checkbox" name="ifEj" <%= ifEj ? "checked" : "" %> /> Mostrar Ejercicios
    <input type="checkbox" name="ifComida" <%= ifComida ? "checked" : "" %> /> Mostrar Comidas
    <input type="checkbox" name="ifSe" <%= ifSe ? "checked" : "" %> /> Mostrar Sesiones
    <input type="submit" value="Actualizar">
</form>

<% if (ifComida) { %>
<h2>Lista de Comidas</h2>
<div class="opcionesAdmin">
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Calorías</th>
        </tr>
        <%
            for (FiltroComida comida : comidas) {
        %>
        <tr>
            <td><%= comida.getId() %></td>
            <td><%= comida.getNombre() %></td>
            <td><%= comida.getCalorias() %></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<% } %>

<% if (ifEj) { %>
<h2>Lista de Ejercicios</h2>
<div class="opcionesAdmin">
    <table>
        <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Nombre</th>
        </tr>
        <%
            for (FiltroEjercicio ejercicio : ejercicios) {
        %>
        <tr>
            <td><%= ejercicio.getId() %></td>
            <td><%= ejercicio.getTipo() %></td>
            <td><%= ejercicio.getNombre() %></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<% } %>

<% if (ifSe) { %>
<h2>Lista de Sesiones</h2>
<div class="opcionesAdmin">
    <table>
        <tr>
            <th>ID</th>
            <th>Repeticiones</th>
            <th>Cantidad</th>
            <th>Ejercicio</th>
            <th>Trabajo</th>
        </tr>
        <%
            for (FiltroSesion sesion : sesiones) {
        %>
        <tr>
            <td><%= sesion.getId() %></td>
            <td><%= sesion.getRepeticiones() %></td>
            <td><%= sesion.getCantidad() %></td>
            <td><%= sesion.getEjercicio() %></td>
            <td><%= sesion.getTrabajo() %></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<% } %>

</body>
</html>
