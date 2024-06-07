<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.AdministradorEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 14:23
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

    <jsp:include page="navbarAdmin.jsp"/>

    <div class="opcionesAdmin">
        <table>
            <tr>
                <td>
                    <a href="/admin/Usuarios" class="button"><button>Administración de Usuarios</button></a>
                </td>
                <td>
                    <a href="/admin/asignarLista" class="button"><button>Asociar entrenador/dietista</button></a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/admin/autenticar" class="button"><button>Autenticar Usuario</button></a>
                </td>
                <td>
                    <a><button>Administración de Ejercicios, Alimentos...</button></a>
                </td>
            </tr>
        </table>
    </div>

</body>
</html>

