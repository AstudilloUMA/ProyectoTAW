<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: url('${pageContext.request.contextPath}/images/fondoCustomer2.png') no-repeat center center fixed;
            background-size: cover;
        }
    </style>
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
