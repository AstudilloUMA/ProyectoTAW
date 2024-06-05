<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<DietaEntity> dietas = (List<DietaEntity>) request.getAttribute("dietas");
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

<h1>Rutinas</h1>
<form:form method="post" action="/dietista/filtrar" modelAttribute="filtro">
    NºComidas: <form:input path="filtro1" type="number"/>
    y contiene la palabra: <form:input path="filtro2"  />
    <form:button>Filtrar</form:button>
    <form:hidden path="id" value="<%=id%>" />
</form:form>
<br/>

<div style="text-align: center">
    <%
        if (dietas == null || dietas.isEmpty()) {
    %>
    <h2>No hay rutinas</h2>
    <%
    } else {
    %>
        <div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td>
                <b>Código</b>
            </td>
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
            for (DietaEntity d : dietas) {
        %>
        <tr>
            <td>
                <%= d.getCodigo() %>
            </td>
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
                <a href="/dietista/ver?id=<%= d.getCodigo() %>"><button style="padding: 10px 15px">Ver</button></a>
                <a href="/dietista/modificar?id=<%= d.getCodigo() %>" style="margin-left: 25px"><button style="padding: 10px 15px">Modificar</button></a>
                <a href="eliminar?id=<%=d.getCodigo()%>" style="margin-left: 25px"><button style="padding: 10px 15px">Eliminar</button></a>
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
    <br/>
    <a href="/entrenador/crear"><button>Crear Rutina</button></a>
    <a href="/dietista/"><button>Volver</button></a>
</div>

</body>
</html>