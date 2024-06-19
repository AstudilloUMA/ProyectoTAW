<%--
Autor:
Miguel Sánchez Hontoria:100%
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="es.uma.proyectogrupo18.dto.Dieta" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
    String filtro1 = request.getParameter("filtro1");
    String filtro2 = request.getParameter("filtro2");
    if (filtro1 == null) filtro1 = "";
    if (filtro2 == null) filtro2 = "";
    Integer id = (Integer) session.getAttribute("usuarioid");
%>
<html>
<head>
    <title>Rutinas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbarDietista.jsp"/>

<div class="advise">
    <h1>Dietas</h1>
</div>

<div class="rutinas">
<table>
    <tr style="background-color: #222">
        <td>
            <b>Número de comidas</b>
        </td>
        <td>
            <b>Contiene la palabra</b>
        </td>
        <td></td>
    </tr>
    <form:form method="post" action="/dietista/filtrar" modelAttribute="filtro">
        <form:hidden path="id" value="<%=id%>" />
        <tr>
            <td>
                <form:input path="filtro1" cssClass="form-input"/>
            </td>
            <td>
                <form:input path="filtro2" cssClass="form-input"/>
            </td>
            <td>
                <form:button>Filtrar</form:button>
            </td>
        </tr>
    </form:form>
</table>
</div>


<br/>

<div style="text-align: center">
    <%
        if (dietas == null || dietas.isEmpty()) {
    %>
    <h2>No hay dietas</h2>
    <%
    } else {
    %>
        <div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td>
                <b>NºComidas</b>
            </td>
            <td>
                <b>Tipo</b>
            </td>
            <td>
                <b>Fecha Inicio</b>
            </td>
            <td>
                <b>Fecha Fin</b>
            </td>
            <td></td>
        </tr>
        <%
            for (Dieta d : dietas) {
        %>
        <tr>
            <td>
                <%=  d.getNumComidas() %>
            </td>
            <td>
                <%= d.getTipo() %>
            </td>
            <td>
                <%= d.getFechaInicio() %>
            </td>
            <td>
                <%=  d.getFechaFin() %>
            </td>
            <td>
                <a href="/dietista/ver?id=<%= d.getId() %>"><button style="padding: 10px 15px">Ver</button></a>
                <a href="/dietista/modificar?id=<%= d.getId() %>" style="margin-left: 25px"><button style="padding: 10px 15px">Modificar</button></a>
                <a href="/dietista/eliminar?id=<%=d.getId()%>" style="margin-left: 25px"><button style="padding: 10px 15px">Eliminar</button></a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
        </div>
    <%
        }
    %>
    <a href="/dietista/crear"><button>Crear Dieta</button></a>
    <a href="/dietista/"><button>Atrás</button></a>
</div>

</body>
</html>