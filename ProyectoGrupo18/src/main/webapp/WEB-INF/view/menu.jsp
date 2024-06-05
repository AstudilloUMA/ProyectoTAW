<%@ page import="es.uma.proyectogrupo18.entity.MenuEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MenuEntity menu = (MenuEntity) request.getAttribute("menuA");
    Integer id = (Integer) session.getAttribute("usuarioid");
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
%>
<html>
<head>
    <title>menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<h1>Menu<%=menu.getId()%></h1>

Ingredientes:
<br/>
<textarea name="ingredientes" rows="3" cols="50">
<%=menu.getIngredientes()%>
</textarea>
<br/>
Preparación:
<br/>
<textarea name="ingredientes" rows="8" cols="50">
<%=menu.getPreparacion()%>
</textarea>
<br/>
<br/>
<a href="/dietista/ver?id=<%= dieta.getCodigo() %>"><button>Atrás</button></a>

</body>
</html>
