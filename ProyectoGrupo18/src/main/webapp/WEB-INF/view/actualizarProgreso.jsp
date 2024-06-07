<%@ page import="es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SesionDeEjercicioEntity se = (SesionDeEjercicioEntity) request.getAttribute("sesionDeEjercicio");
    EjercicioEntity ej = (EjercicioEntity) request.getAttribute("ejercicio");
    if(ej == null) {
        ej = new EjercicioEntity();
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
    <h1>Tu entrenador te mandó: </h1>
</div>

<div class="mandados">
    Series: <%=se.getCantidad()%> </br>
    Repeticiones: <%=se.getRepeticiones()%>
</div>

<div class="advise">
    <h1>Actualizar progreso de: <%=ej.getNombre()%></h1>
</div>

<div class="ejercicio">
    <form action="${pageContext.request.contextPath}/customer/guardarProgreso" method="post">
        <input type="hidden" name="sesionId" value="<%=se.getId()%>">
        <table>
            <tr style="background-color: #222">
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
        <div style="text-align: center; margin-top: 20px;">
            <button type="submit">Guardar Progreso</button>
        </div>
    </form>
</div>
</body>
</html>
