<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<DietaEntity> dietas = (List<DietaEntity>) request.getAttribute("dietas");
    String filtro1 = request.getParameter("filtro1");
    String filtro2 = request.getParameter("filtro2");
    if (filtro1 == null) filtro1 = "";
    if (filtro2 == null) filtro2 = "";
    Integer id = (Integer) session.getAttribute("usuarioid");
%>
<html>
<head>
    <title>Dietas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<h1>Dietas</h1>
<form:form method="post" action="/dietista/filtrar" modelAttribute="filtro">
    NºComidas: <form:input path="filtro1" type="number"/>
    y contiene la palabra: <form:input path="filtro2"  />
    <form:button>Filtrar</form:button>
    <form:hidden path="id" value="<%=id%>" />
</form:form>
<br/>

<table border="1">
        <tr>
            <th>Código</th>
            <th>Nº Comidas</th>
            <th>Tipo</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
    <%
        for(DietaEntity d : dietas){
    %>
        <tr>
            <td><%=d.getCodigo()%></td>
            <td><%=d.getNumComidas()%></td>
            <td><%=d.getTipo()%></td>
            <td><%=d.getFechaInicio()%></td>
            <td><%=d.getFechaFin()%></td>
            <td><a href="/dietista/borrar?id=<%=d.getCodigo()%>">Borrar</a></td>
            <td><a href="/dietista/modificar?id=<%=d.getCodigo()%>">Modificar Dieta</a></td>
            <td><a href="/dietista/Ver?id=<%=d.getCodigo()%>">Ver Dieta</a></td>
        </tr>
    <%
        }
    %>

</table>

<br/>

<a href="/dietista/atras"> <button>Atrás</button></a>
<a href="/dietista/crear"> <button>Crear Dieta</button></a>
<a href="/dietista/asignar"> <button>Usuarios</button></a>


</body>
</html>
