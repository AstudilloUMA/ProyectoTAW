<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %><%--
  Created by IntelliJ IDEA.
  User: ansag
  Date: 07/06/2024
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="advise">
    <h1>Actualizar progreso</h1>
</div>

<h3>Sobre la dieta <%=dieta.getNombre()%></h3>

Calificación: <input type="number" name="calificacion" size="10" max="2">
<br/>
Comentarios: <textarea cols="40" rows="10"></textarea>


</body>
</html>
