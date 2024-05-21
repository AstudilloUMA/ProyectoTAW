<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<DietaEntity> dietas = (List<DietaEntity>) request.getAttribute("dietas");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<h1>Dietas</h1>

<table border="1">
        <tr>
            <th>Código</th>
            <th>Nº Comidas</th>
            <th>Tipo</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
        </tr>
    <%
        for(DietaEntity d : dietas){
    %>
        <tr>
            <td><%=d.getCodigo()%></td>
            <td><%=d.getNumComidas()%></td>
            <td><%=d.getTipo()%></td>
            <td><%=d.getFechaInicio()%></td>
            <td><%=d.getFechaFin()%></td>
        </tr>
    <%
        }
    %>

    
</table>

</body>
</html>
