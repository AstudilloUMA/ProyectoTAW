<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 29/04/2024
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1 style="text-align: center">Bienvenidos al login de Fit Score como Cliente</h1>
<%--@elvariable id="usuario" type=""--%>
<div style="text-align: center">
    <form:form action="/autenticarCliente" modelAttribute="usuario" method="post">
        ${error}
        Usuario: <form:input path="user" cssStyle="margin-bottom: 1%"/>
            </br>
        Contraseña: <form:password path="password" cssStyle="margin-bottom: 1%"/>
            </br>
        <form:button> Enviar </form:button>

    </form:form>
</div>


</body>
</html>
