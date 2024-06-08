<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: ansag
  Date: 07/06/2024
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
  ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");%>
<html>

<head>
    <title><%=dieta.getNombre()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="advise">
    <h1>Actualizar progreso</h1>
</div>

<div class="feedbackDieta" style="display: flex; flex-direction: column; align-items: center; justify-content: center;">
    <h3>Sobre la dieta: <%=dieta.getNombre()%></h3>
    <form action="${pageContext.request.contextPath}/customer/guardarProgresoDieta" method="post">
        <input type="hidden" name="dietaId" value="<%=dieta.getId()%>">
        <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">
        <p>
            Calificación: <input type="number" name="calificacion" min="1" max="10">
            <br/>
            <label for="comentarios">Comentarios:</label>
            <textarea id="comentarios" name="comentarios" cols="30" rows="5"></textarea>
        </p>
        <button type="submit">Guardar progreso</button>
    </form>
</div>

</body>
</html>
