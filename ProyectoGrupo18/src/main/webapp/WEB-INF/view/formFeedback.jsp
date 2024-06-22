
<%@ page import="es.uma.proyectogrupo18.dto.SesionDeEjercicio" %>
<%@ page import="es.uma.proyectogrupo18.dto.Feedback" %>
<%@ page import="es.uma.proyectogrupo18.dto.Ejercicio" %>
<%@ page import="es.uma.proyectogrupo18.dto.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pablo Astudillo Fraga (80%) -> Iniciado y corregido
  User: Andres Santaella (20%) -> Modificado
  Date: 06/06/2024
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%
    SesionDeEjercicio sesion = (SesionDeEjercicio) request.getAttribute("sesion");
    Feedback feedback = (Feedback) request.getAttribute("feedback");
    Ejercicio e = sesion.getEjercicio();
    Cliente c = (Cliente) request.getAttribute("cliente");
%>
<html>
<head>
    <title>Feedback</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="advise">
    <h1>Añadir Feedback a <%=e.getNombre()%></h1>
</div>

<div class="rutinas">
    <form action="/customer/guardarFeedback" method="post">
        <table>
            <tr style="background-color: #222">
                <td><b>Repeticiones/Tiempo <br/> (Entrenador : Realizadas)</b></td>
                <td><b>Series <br/> (Entrenador : Realizadas)</b></td>
                <td><b>Peso/Velocidad <br/> (Entrenador : Realizado) </b></td>
                <td><b>Calificación (0-10)</b></td>
                <td><b>Estado físico</b></td>
                <td><b>Comentarios</b></td>
            </tr>
            <div class="login-form">
                <tr>
                        <input name="clienteId" value="<%= c.getId() %>" type="hidden"/>
                        <input name="feedbackId" value="<%= feedback.getId() == null ? (-1) : feedback.getId() %>" type="hidden"/>
                        <input name="sesionId" value="<%= sesion.getId() %>" type="hidden"/>
                        <td width="250px">
                            <%= sesion.getRepeticiones() %> : <input required name="repeticiones" value="<%= feedback.getRepeticiones() == null ? "" : feedback.getRepeticiones() %>" class="form-input"/>
                        </td>
                        <td width="250px">
                            <%= sesion.getCantidad() %> : <input required name="series" value="<%= feedback.getSeries() == null ? "" : feedback.getSeries() %>" class="form-input"/>
                        </td>
                        <td width="250px">
                            <%= sesion.getPeso() %> : <input required name="peso" value="<%= feedback.getPeso() == null ? "" : feedback.getPeso() %>" class="form-input"/>
                        </td>
                        <td>
                            <input required type="number" name="calificacion" value="<%= feedback.getCalificacion() == null ? "" : feedback.getCalificacion() %>" class="form-input"/>
                        </td>
                        <td width="180px">
                            <input required name="estado" value="<%= feedback.getEstadoDelCliente() == null ? "" : feedback.getEstadoDelCliente() %>" class="form-input"/>
                        </td>
                        <td width="370px">
                            <textarea required name="comentarios" class="form-input" style="font-family: Arial, sans-serif"><%= feedback.getComentarios() == null ? "" : feedback.getComentarios() %></textarea>
                        </td>
                </tr>
            </div>
        </table>
        <div style="text-align: center">
            <button> Guardar </button>
        </div>
    </form>
</div>
</body>
</html>
