<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
    Integer id = (Integer) session.getAttribute("usuarioid");
%>
<html>
<head>
    <title>CrearDieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<h1>Crear Dieta</h1>

<form:form method="post" action="/dietista/guardar" modelAttribute="dieta">

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

    Trabajador ID:
    <form:input path="trabajadorId" maxlength="10" size="10" type="number"/>

    <br/>

    Comidas:<br/>
    <%
        for(ComidaEntity c : comidas) {
    %>
    <form:checkbox path="dietaComidasByCodigo" value="<%=c.getId()%>"/>
    <%=c.getNombre()%>
    <a href="/dietista/menu?id=<%=c.getId()%>">Menú</a><br/>
    <%
        }
    %>

</form:form>

<a href="/dietista/volver?id=<%=id%>"><button>Atrás</button></a>

</body>
</html>
