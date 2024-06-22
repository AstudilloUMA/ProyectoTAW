
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.dto.Comida" %>
<%@ page import="es.uma.proyectogrupo18.dto.Dieta" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 06/06/2024
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Dieta d = (Dieta) request.getAttribute("dieta");
    List<Comida> comidas = (List<Comida>) request.getAttribute("comidas");
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
            for(Comida c : comidas){
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
    <a href="/customer/actualizarPDieta" style="margin-left: 25px"><button>Feedback</button></a>
</div>

</body>
</html>
