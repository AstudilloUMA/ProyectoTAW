<%--
Autores:
Pablo Astudillo Fraga:50%
Miguel Sánchez Hontoria:50%
--%>

<%@ page import="es.uma.proyectogrupo18.dto.Cliente" %>
<%@ page import="es.uma.proyectogrupo18.dto.FeedbackDieta" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    FeedbackDieta f = (FeedbackDieta) request.getAttribute("feedback");
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
<%
    if(f == null){
%>
<div style="text-align: center">
    <h2>No hay feedback aún</h2>
</div>
<%
    }else{
%>
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
    </table>
</div>
<%
    }
%>

<div style="text-align: center">
    <a href="clientes"><button>Volver</button></a>
</div>
</body>
</html>
