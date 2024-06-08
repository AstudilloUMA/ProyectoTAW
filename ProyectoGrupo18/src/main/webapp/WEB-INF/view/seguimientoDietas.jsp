<%--
Autores:
Pablo Astudillo Fraga:50%
Miguel Sánchez Hontoria:50%
--%>

<%@ page import="java.util.Collection" %>
<%@ page import="es.uma.proyectogrupo18.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    Collection<FeedbackdietaEntity> feedbacks = cliente.getFeedbackdietas();
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
%>
<html>
<head>
    <title>Seguimiento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbarDietista.jsp"/>
<div class="advise">
    <h1>Feedback de <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%></h1>
</div>
<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td>
                <b>Dieta</b>
            </td>
            <td>
                <b>Calificación</b>
            </td>
            <td>
                <b>Comentario</b>
            </td>
        </tr>
        <%
            for(FeedbackdietaEntity f : feedbacks){
                if(f.getDietaCodigo().getId() == dieta.getId()){
        %>
        <tr>
            <td>
                <%= f.getDietaCodigo().getNombre()%>
            </td>
            <td>
                <%= f.getCalificacion()%>
            </td>
            <td>
                <%= f.getComentarios()%>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>

<div style="text-align: center">
    <a href="clientes"><button>Volver</button></a>
</div>
</body>
</html>
