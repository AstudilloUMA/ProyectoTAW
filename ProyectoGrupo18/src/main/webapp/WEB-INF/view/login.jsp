<%@ page import="es.uma.proyectogrupo18.ui.UsuarioUI" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioUI usuarioUI = (UsuarioUI) request.getAttribute("usuarioUI");
    String error = (String) request.getSession().getAttribute("error");
%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/login.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: url('${pageContext.request.contextPath}/images/fondoPrincipal.png') no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
<div style="text-align: center">
    <h1>Bienvenidos al login de Fit Score</h1>
    <div class="login-form">
        <form:form action="/login/autentica" modelAttribute="usuarioUI" method="post">
            <%
                if(error != null)
                {
            %>
                    <a><%=error%></a>
                    <br/>
                    <br/>
            <%
                }
            %>
            Usuario: <form:input path="user" cssStyle="margin-bottom: 1%"/>
            <br/>
            <br/>
            Contraseña: <form:password path="pwd" cssStyle="margin-bottom: 1%"/>
            <br/>
            <br/>
            <form:button htmlEscape="false"> Enviar </form:button>

        </form:form>
    </div>
</div>

</body>
</html>