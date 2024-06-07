<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.FeedbackEntity" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 24/05/2024
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    Collection<FeedbackEntity> feedbacks = cliente.getFeedbacks();
%>
<html>
<head>
    <title>Seguimiento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbarEntrenador.jsp"/>
<div class="advise">
    <h1>Feedback de <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%></h1>
</div>
<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td>
                <b>Ejercicio</b>
            </td>
            <td>
                <b>Tipo</b>
            </td>
            <td>
                <b>Calificación</b>
            </td>
            <td>
                <b>Estado del Cliente</b>
            </td>
            <td>
                <b>Comentario</b>
            </td>
        </tr>
        <%
            for(FeedbackEntity f : feedbacks){
                EjercicioEntity e = f.getEjercicio();
        %>
        <tr>
            <td>
                <%= e.getNombre()%>
            </td>
            <td>
                <%= e.getTipo() != null ? e.getTipo() : "Sin tipo"%>
            </td>
            <td>
                <%= f.getCalificacion() %>
            </td>
            <td>
                <%= f.getEstadoDelCliente()%>
            </td>
            <td>
                <%= f.getComentarios()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

<div style="text-align: center">
    <a href="clientes"><button>Volver</button></a>
</div>
</body>
</html>
