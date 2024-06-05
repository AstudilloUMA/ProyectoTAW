<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.FeedbackEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 24/05/2024
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    FeedbackEntity feedback = cliente.getFeedbacksByUsuarioId();
%>
<html>
<head>
    <title>Seguimiento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbarEntrenador.jsp"/>
<div class="advise">
    <h1>Página en desarrollo</h1>
    <div style="text-align: center">
        <a href="clientes"><button>Volver</button></a>
    </div>
</div>
</body>
</html>
