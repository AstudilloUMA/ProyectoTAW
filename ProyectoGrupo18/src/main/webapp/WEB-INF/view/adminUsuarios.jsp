<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<FiltroUsuario> usuarios = (List<FiltroUsuario>) request.getAttribute("usuarios");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>

<jsp:include page="navbar.jsp"/>
<h1>Lista de Usuarios</h1>
<form:form method="GET" action="filtrar" modelAttribute="filtro">
    <label for="id">ID:</label>
    <form:input path="ID" id="ID"></form:input>
    <label for="usuario">Usuario:</label>
    <form:input path="usuario" id="usuario"></form:input>
    <label for="Nombre">Nombre:</label>
    <form:input path="Nombre" id="Nombre"></form:input>
    <label for="Apellidos">Apellidos:</label>
    <form:input path="Apellidos" id="Apellidos"></form:input>
    <label for="DNI">DNI:</label>
    <form:input path="DNI" id="DNI"></form:input>
    <label for="Edad">Edad:</label>
    <form:input path="Edad" id="Edad"></form:input>
    <label for="Sexo">Sexo:</label>
    <form:select path="sexo">
        <form:option value="Cualquiera" label="Cualquiera"/>
        <form:option value="Masculino" label="Masculino"/>
        <form:option value="Femenino" label="Femenino"/>
    </form:select>
    <input type="submit" value="Filtrar">
</form:form>

<button onclick="window.location.href='/admin/Crear'">Añadir Usuario</button>

<div class="opcionesAdmin">
    <table>
        <tr>
            <th>USUARIO</th>
            <th>NOMBRE Y APELLIDOS</th>
            <th>DNI</th>
            <th>EDAD</th>
            <th>SEXO</th>
            <th>ROL</th>
        </tr>

            <%
                for(FiltroUsuario usuario : usuarios){
            %><tr>
            <td><%=usuario.getUsuario()%></td>
            <td><%=usuario.getNombre()%> <%=usuario.getApellidos()%></td>
            <td><%=usuario.getDNI()%></td>
            <td><%=usuario.getEdad()%></td>
            <td><%=usuario.getSexo()%></td>
            <td><%=usuario.getRol()%></td>
            <td><a href = "/admin/modificar?id=<%=usuario.getID()%>">Modificar</a></td>
            <td><a href = "/admin/eliminar?id=<%=usuario.getID()%>">Eliminar</a></td>

    </tr>
            <%
                }
            %>

    </table>
</div>

</body>
</html>

