<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.FeedbackdietaEntity" %><%--
  Created by IntelliJ IDEA.
  User: ansag
  Date: 07/06/2024
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    FeedbackdietaEntity feedback = (FeedbackdietaEntity) request.getAttribute("feedback");
%>

<html>

<head>
    <title>Feedback de dieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="advise">
    <h1>Feedback de <%=dieta.getNombre()%></h1>
</div>

<div class="rutinas">
    <form action="/customer/guardarProgresoDieta" method="post">
    <table>
        <tr style="background-color: #222">
            <td><b>Calificación</b></td>
            <td><b>Comentarios</b></td>
        </tr>
        <div class="login-form">
            <input type="hidden" name="dietaId" value="<%=dieta.getId()%>">
            <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">
            <tr>
                <td>
                    <input required type="number" name="calificacion" value="<%= feedback.getCalificacion() == null ? "" : feedback.getCalificacion() %>" class="form-input"/>
                <td>
                    <textarea required name="comentarios" class="form-input" style="font-family: Arial, sans-serif"><%= feedback.getComentarios() == null ? "" : feedback.getComentarios() %></textarea>
                </td>
            </tr>
        </div>
    </table>
        <div style="text-align: center">
            <button>Guardar</button>
        </div>
    </form>
</div>
</body>
</html>
