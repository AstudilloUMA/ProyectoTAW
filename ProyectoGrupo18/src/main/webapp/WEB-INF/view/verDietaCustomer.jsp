<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 06/06/2024
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DietaEntity d = (DietaEntity) request.getAttribute("dieta");
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
%>
<html>
<head>
    <title><%=d.getNombre()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="advise">
    <h1><%=d.getNombre()%></h1>
</div>
<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td><b>Orden</b></td>
            <td><b>Nombre</b></td>
            <td><b>Kcal Totales</b></td>
        </tr>
        <%
            for(ComidaEntity c : comidas){
        %>
        <div>
            <tr>
                <td>
                    <%= c.getOrden()%>
                </td>
                <td>
                    <%= c.getNombre()%>
                </td>
                <td>
                    <%= c.getKilocaloriasTotales() %>
                </td>
            </tr>
        </div>
        <%
            }
        %>
    </table>
</div>

<div style="text-align: center">
    <a href="/customer/"><button>Volver</button></a>
    <a href="/customer/actualizarProgresoDieta"><button>Actualizar progreso</button></a>
</div>

</body>
</html>
