<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 14/05/2024
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  UsuarioEntity usuario = (UsuarioEntity) request.getSession().getAttribute("usuario");
  String tipo = (String) request.getSession().getAttribute("tipo");
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
        <a href="rutinas"><button>Rutinas</button></a>
      </td>
      <td>
        <a><button>Clientes</button></a>
      </td>
    </tr>
  </table>
</div>

</body>
</html>
