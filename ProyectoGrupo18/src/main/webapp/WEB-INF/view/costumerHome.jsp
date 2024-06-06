<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity usuario = (UsuarioEntity) request.getSession().getAttribute("usuario");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="opcionesAdmin">
    <table>
        <tr>
            <td>
                <a href="/customer/rutina?id=<%=usuario.getId()%>"><button>Rutina</button></a>
            </td>
            <td>
                <a href="/customer/dieta?id=<%=usuario.getId()%>"><button>Dieta</button></a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
