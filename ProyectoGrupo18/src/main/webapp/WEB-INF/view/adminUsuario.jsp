<!--
Autor:
Juan Manuel Porcuna Martín
-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity usuarioUI = (UsuarioEntity) request.getAttribute("usuarioUI");
    boolean esEditar = (usuarioUI.getId() != -1);
    String usuarioName = "", Nombre = "", Apellidos = "", DNI="",sexo="",Rol="";
    Integer edad=0;
    List<String> roles = (List<String>) request.getAttribute("roles");

    if (esEditar) {
        usuarioName = usuarioUI.getUsuario();
        Nombre = usuarioUI.getNombre();
        Apellidos = usuarioUI.getApellidos();
        DNI = usuarioUI.getDni();
        sexo = usuarioUI.getSexo();
        edad = usuarioUI.getEdad();
        if(usuarioUI.getAdministrador()!=null){
            Rol = "Admin";
        }
        if(usuarioUI.getCliente()!=null){
            Rol = "Cliente";
        }
        if(usuarioUI.getTrabajador()!=null){
            Integer rolIddede = usuarioUI.getTrabajador().getRol().getId();
            Rol = rolIddede==1?"Dietista":rolIddede==2?"Entrenador Cross-training":"Entrenador Bodybuilding";
        }


    }
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<h1>Datos del usuarioUI</h1>
<form method="post" action="/admin/guardar">
    <input type="hidden" name="id" value="<%= usuarioUI.getId() %>">
    <input type="hidden" name="RolPre" value="<%=Rol%>">
    <table border="0">
        <tr>
            <td>Nombre de usuarioUI:</td>
            <td><input type="text" name="usuarioName" size="100" maxlength="100" value="<%= usuarioName %>" /> </td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="Nombre" size="100"  maxlength="100" value="<%= Nombre %>" /></td>
        </tr>
        <tr>
            <td>Apellidos:</td>
            <td><input type="text" name="Apellidos" size="100"  maxlength="100" value="<%= Apellidos %>" /></td>
        </tr>
        <tr>
            <td>DNI:</td>
            <td><input type="text" name="DNI" size="100"  maxlength="100" value="<%= DNI %>" /></td>
        </tr>
        <tr>
            <td>Sexo:</td>
            <td><input type="text" name="sexo" size="100"  maxlength="100" value="<%= sexo %>" /></td>
        </tr>
        <tr>
            <td>Edad:</td>
            <td><input type="number" name="edad" size="4"  maxlength="4" value="<%= edad %>" /></td>
        </tr>
        <tr>
            <td>Rol:</td>
            <td>
                <select name="Rol">
                    <%
                        for (String rolSelec: roles) {
                            String seleccionado = "Cliente";
                            if (esEditar && rolSelec.equals(Rol)) {
                                seleccionado = "selected";
                            }
                    %>
                    <option value="<%=rolSelec %>"  <%=seleccionado %>  ><%= rolSelec %></option>

                    <%
                        }
                    %>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form>


</body>
</html>
