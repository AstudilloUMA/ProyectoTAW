<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 14/05/2024
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTORES -->
  --> Pablo Astudillo Fraga 70%
  --> Alvaro Morales Perujo 30%

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
  <style>
    body {
      margin: 0;
      padding: 0;
      height: 100vh;
      background: url('${pageContext.request.contextPath}/images/fondoEntrenadores.png') no-repeat center center fixed;
      background-size: cover;
    }
  </style>
</head>
<body>

<jsp:include page="navbarEntrenador.jsp"/>

<div class="opcionesAdmin">
  <table>
    <tr>
      <td>
        <a href="rutinas"><button>Rutinas</button></a>
      </td>
      <td>
        <a href="clientes"><button>Clientes</button></a>
      </td>
    </tr>
  </table>
</div>

</body>
</html>
