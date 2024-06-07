<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
    Integer id = (Integer) session.getAttribute("usuarioid");
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
%>
<html>
<head>
    <title>Ver Dieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbarDietista.jsp"/>

<div style="text-align: center">

<div class="advise">
    <h1><%=dieta.getNombre()%>-Comidas</h1>
</div>

    <div class="rutinas">
    <input type="hidden" name="id" value="<%=id%>" />

    <table>
        <tr style="background-color: #222">
            <td><b>Nombre</b></td>
            <td><b>Kcal Totales</b></td>
            <td><b>Orden</b></td>
            <td></td>
        </tr>
    <% for(ComidaEntity c : comidas) { %>
        <tr>
            <td><%=c.getNombre()%></td>
            <td><%=c.getKilocaloriasTotales()%></td>
            <td><%=c.getOrden()%></td>
            <td><a href="/dietista/menu?id=<%=c.getId()%>&dietaid=<%=dieta.getId()%>">Menú</a></td>
        </tr>
    <% } %>
        </table>
    </div>
    <a href="/dietista/info?id=<%=id%>"><button>Atrás</button></a>
</div>
</body>
</html>
