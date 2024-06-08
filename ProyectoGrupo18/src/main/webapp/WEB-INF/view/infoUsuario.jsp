<%--
Autor:
Miguel Sánchez Hontoria:100%
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity user = (UsuarioEntity) request.getAttribute("user");
%>
<html>
<head>
    <title>Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/usuario.css">
</head>
<body>

<div class="content">
    <div class="rutinas">
    <h1>Información sobre usuario</h1>
        <table>
            <tr style="background-color: #222">
                <td><b>Nombre</b></td>
                <td><b>Nombre Usuario</b></td>
                <td><b>Contraseña</b></td>
                <td><b>Edad</b></td>
                <td><b>Sexo</b></td>
            </tr>
            <tr>
                <td><%=user.getNombre()%> <%=user.getApellidos()%></td>
                <td><%=user.getUsuario()%></td>
                <td><%=user.getContrasena()%></td>
                <td><b><%=user.getEdad()%></b></td>
                <td><b><%=user.getSexo()%></b></td>
            </tr>
        </table>

    <%if(user.getId() <= 3){%>
    <a href="/dietista/"><button>Atrás</button></a>
    <%}else if(user.getId()>=4 && user.getId()<=5){%>
    <a href="/bodybuilder/"><button>Atrás</button></a>
    <%}else if(user.getId()>=6 && user.getId()<=7){%>
    <a href="/crosstrainer/"><button>Atrás</button></a>
    <%}else{%>
    <a href="/customer/"><button>Atrás</button></a>
    <%}%>
    </div>
</div>

</body>
</html>