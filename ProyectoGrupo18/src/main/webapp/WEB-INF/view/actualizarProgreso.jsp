<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.ui.SesionEjercicio" %>
<%@ page import="org.springframework.web.client.RestTemplate" %><%--
  Created by IntelliJ IDEA.
  User: ansag
  Date: 07/06/2024
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EjercicioEntity ej = (EjercicioEntity) request.getAttribute("ejercicio");
    if(ej == null) {
        ej = new EjercicioEntity(); // or some default object
    }
%>
<html>
<head>
    <title><%=ej.getNombre()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="advise">
    <h1>Actualizar progreso de: <%=ej.getNombre()%></h1>
</div>

<div class="ejercicio">
    <table>
        <tr style="background-color: #222">
            <td>
                <b>Peso</b>
            </td>
            <td>
                <b>Repeticiones</b>
            </td>
            <td>
                <b>Series</b>
            </td>
            <td>
                <b>Calificación</b>
            </td>
            <td>
                <b>Comentario</b>
            </td>
        </tr>
        <tr>
            <td>
                <input type="number" name="peso" max="999"> kg
            </td>
            <td>
                <input type="number" name="repeticiones" max="99"> reps
            </td>
            <td>
                <input type="number" name="series" max="99"> series
            </td>
            <td>
                <input type="number" name="calificacion" min="1" max="10">
            </td>
            <td>
                <textarea name="comentario"></textarea>
            </td>
        </tr>
    </table>
</div>
</body>
</html>