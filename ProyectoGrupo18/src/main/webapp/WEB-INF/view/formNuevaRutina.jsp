<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 08/06/2024
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String tipo = (String) request.getSession().getAttribute("tipo");
    String actionUrl;
    if("crosstrainer".equals(tipo)){
        actionUrl = "/crosstrainer/creada";
    } else {
        actionUrl = "/bodybuilder/creada";
    }
%>
<html>
<head>
    <title>Nueva Rutina</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbarEntrenador.jsp"/>

<div class="advise">
    <h1>Nueva Rutina</h1>
</div>

<form:form method="post" action="<%=actionUrl%>" modelAttribute="rutina">
<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td><b>Nombre</b></td>
            <td><b>Fecha de Inicio</b></td>
            <td><b>Fecha de Fin</b></td>
            <td></td>
        </tr>
        <tr>

                <td>
                    <form:input path="nombre" class="form-input"/>
                </td>
                <td>
                    <form:input path="fechaInicio" class="form-input" type="date"/>
                </td>
                <td>
                    <form:input path="fechaFin" class="form-input" type="date"/>
                </td>
                <td>
                    <button>Crear</button>
                </td>

    </table>
</div>
</form:form>
<div style="text-align: center">
    <a href="/<%=tipo%>/rutinas"><button>Volver</button></a>
</div>
</body>
</html>
