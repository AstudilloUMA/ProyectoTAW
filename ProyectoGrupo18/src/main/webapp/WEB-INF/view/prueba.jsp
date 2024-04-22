<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Esta página es de prueba</h1>
<%
    for(ComidaEntity c : comidas){
%>
    <%=c.getNombre()%>
    <%=c.getId()%>
    <%=c.getKilocaloriasTotales()%>
    <br>
<%
    }
%>
</body>
</html>