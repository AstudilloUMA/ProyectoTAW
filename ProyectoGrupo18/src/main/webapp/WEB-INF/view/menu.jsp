<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ComidaEntity comida = (ComidaEntity) request.getAttribute("comida");
    Integer id = (Integer) session.getAttribute("usuarioid");
%>
<html>
<head>
    <title>menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<h1>Menu<%=comida.getId()%></h1>

Ingredientes:
<br/>
<textarea name="ingredientes" rows="3" cols="70">
Holaaaa
</textarea>
<br/>
Preparación:
<br/>
<textarea name="ingredientes" rows="20" cols="70">
The cat was playing in the garden.
</textarea>
<br/>
<br/>
<a href="/dietista/crear?id=<%=id%>"><button>Atrás</button></a>

</body>
</html>
