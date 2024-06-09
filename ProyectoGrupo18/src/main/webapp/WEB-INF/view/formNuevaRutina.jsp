<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 08/06/2024
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String tipo = (String) request.getSession().getAttribute("tipo");
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


<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td><b>Nombre</b></td>
            <td><b>Fecha de Inicio</b></td>
            <td><b>Fecha de Fin</b></td>
            <td></td>
        </tr>
        <tr>
            <form action="/<%=tipo%>/creada" method="post">
                <td>
                    <input name="nombre" class="form-input"/>
                </td>
                <td>
                    <input name="inicio" class="form-input" type="date"/>
                </td>
                <td>
                    <input name="fin" class="form-input" type="date"/>
                </td>
                <td>
                    <button>Crear</button>
                </td>
            </form>
    </table>
</div>
<div style="text-align: center">
    <a href="/<%=tipo%>/rutinas"><button>Volver</button></a>
</div>
</body>
</html>
