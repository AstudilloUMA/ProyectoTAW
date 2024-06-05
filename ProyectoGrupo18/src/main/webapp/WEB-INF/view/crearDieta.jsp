<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
    Integer id = (Integer) session.getAttribute("usuarioid");
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
%>
<html>
<head>
    <title>CrearDieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<h1>Crear Dieta</h1>

<form:form method="post" action="/dietista/modificar" modelAttribute="dieta">
    <input type="hidden" name="id" value="<%=id%>" />

    Código:
    <form:input path="codigo" maxlength="3" size="3" type="number"/>

    NºComidas:
    <form:input path="numComidas" maxlength="1" size="1" type="number"/>

    Tipo:
    <form:input path="tipo" maxlength="20" size="20" type="text"/>

    FechaInicio:
    <form:input path="fechaInicio" maxlength="10" size="10" type="date"/>

    FechaFin:
    <form:input path="fechaFin" maxlength="10" size="10" type="date"/>

    <button type="submit">Modificar Dieta</button>
    <br/>

    Comidas(seleccione las que estarán en la dieta):<br/>
    <form:checkboxes path="dietaComidasByCodigo" items="${comidas}" itemLabel="nombre" itemValue="id"/>

    <br/>

</form:form>

<a href="/dietista/"><button>Atrás</button></a>
</body>
</html>