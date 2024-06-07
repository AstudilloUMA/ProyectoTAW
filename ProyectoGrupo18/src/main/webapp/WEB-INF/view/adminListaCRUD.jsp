<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>

<jsp:include page="navbarAdmin.jsp"/>
<h1>Filtro de Datos</h1>
<form method="GET" action="filtrarCRUD" modelAttribute="filtro">
    <!-- Checkbox para mostrar filtros de comida -->
    <input type="checkbox" name="ifComida" value="1" <%= ifComida==1 ? "checked" : "" %> /> Mostrar Filtros de Comida

    <!-- Sección para los filtros de comida -->
    <fieldset>
        <legend>Filtrar Comidas</legend>
        <label for="comidaNombre">Nombre:</label>
        <form:input path="comidaNombre" id="comidaNombre"></form:input>
        <label for="comidaCalorias">Calorías:</label>
        <form:input path="comidaCalorias" id="comidaCalorias"></form:input>
    </fieldset>

    <!-- Checkbox para mostrar filtros de ejercicio -->
    <input type="checkbox" name="ifEj" value="1" <%= ifEj==1 ? "checked" : "" %> /> Mostrar Filtros de Ejercicio

    <!-- Sección para los filtros de ejercicio -->
    <fieldset>
        <legend>Filtrar Ejercicios</legend>
        <label for="ejTipo">Tipo:</label>
        <form:input path="ejTipo" id="ejTipo"></form:input>
        <label for="ejNombre">Nombre:</label>
        <form:input path="ejNombre" id="ejNombre"></form:input>
    </fieldset>

    <!-- Checkbox para mostrar filtros de sesión -->
    <input type="checkbox" name="ifSe" value="1" <%= ifSe==1 ? "checked" : "" %> /> Mostrar Filtros de Sesión

    <!-- Sección para los filtros de sesión -->
    <fieldset>
        <legend>Filtrar Sesiones</legend>
        <label for="seRepeticiones">Repeticiones:</label>
        <form:input path="seRepeticiones" id="seRepeticiones"></form:input>
        <label for="seCantidad">Cantidad:</label>
        <form:input path="seCantidad" id="seCantidad"></form:input>
        <label for="seEjercicio">Ejercicio:</label>
        <form:input path="seEjercicio" id="seEjercicio"></form:input>
        <label for="seTrabajador">Trabajador:</label>
        <form:input path="seTrabajador" id="seTrabajador"></form:input>
    </fieldset>

    <!-- Botón para aplicar el filtro -->
    <button type="submit">Filtrar</button>
</form>

<!-- Mostrar resultados de las consultas -->
<h2>Resultados</h2>
<h3>Comidas</h3>
<ul>
    <c:forEach var="comida" items="${comidas}">
        <li>${comida.nombre} - ${comida.kilocaloriasTotales} calorías</li>
    </c:forEach>
</ul>

<h3>Ejercicios</h3>
<ul>
    <c:forEach var="ejercicio" items="${ejercicios}">
        <li>${ejercicio.tipo} - ${ejercicio.nombre}</li>
    </c:forEach>
</ul>

<h3>Sesiones de Ejercicio</h3>
<ul>
    <c:forEach var="sesion" items="${sesiones}">
        <li>${sesion.repeticiones} repeticiones - ${sesion.cantidad} - ${sesion.ejercicio} - ${sesion.trabajador}</li>
    </c:forEach>
</ul>

</body>
</html>
