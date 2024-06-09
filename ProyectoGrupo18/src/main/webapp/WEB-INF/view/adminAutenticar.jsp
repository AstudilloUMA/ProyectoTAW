<!--
Autor:
Juan Manuel Porcuna Martín
-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<UsuarioEntity> usuarios = (List<UsuarioEntity>) request.getAttribute("usuarios");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>

<jsp:include page="navbarAdmin.jsp"/>
<h1>Lista de Usuarios</h1>

<%
    if(usuarios.size() == 0){
        %>
<h2>No hay usuarios para autenticar</h2>
<%
    }else{
        %>
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
            for(UsuarioEntity usuario : usuarios){
        %><tr>
        <td><%=usuario.getUsuario()%></td>
        <td><%=usuario.getNombre()%> <%=usuario.getApellidos()%></td>
        <td><%=usuario.getDni()%></td>
        <td><%=usuario.getEdad()%></td>
        <td><%=usuario.getSexo()%></td>
        <td><a href = "/admin/autenticacion?id=<%=usuario.getId()%>">Autenticar</a></td>

    </tr>
        <%
            }
        %>

    </table>
</div>
<%
    }
%>


</body>
</html>
