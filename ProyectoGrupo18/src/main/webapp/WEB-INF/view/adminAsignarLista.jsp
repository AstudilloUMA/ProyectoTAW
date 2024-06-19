<!--
Autor:
Juan Manuel Porcuna Martín
-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroUsuario" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>

<jsp:include page="navbarAdmin.jsp"/>
<h1>Lista de Trabajadores</h1>

<div class="opcionesAdmin">
    <table>
        <tr>
            <th>USUARIO</th>
            <th>NOMBRE Y APELLIDOS</th>
            <th>DNI</th>
            <th>EDAD</th>
            <th>SEXO</th>
        </tr>

        <%
            for(Usuario usuarioUI : usuarios){
        %><tr>
        <td><%=usuarioUI.getUsuario()%></td>
        <td><%=usuarioUI.getNombre()%> <%=usuarioUI.getApellidos()%></td>
        <td><%=usuarioUI.getDni()%></td>
        <td><%=usuarioUI.getEdad()%></td>
        <td><%=usuarioUI.getSexo()%></td>
        <td><a href = "/admin/asignar?id=<%=usuarioUI.getId()%>">Asignar</a></td>

    </tr>
        <%
            }
        %>

    </table>
</div>

</body>
</html>

