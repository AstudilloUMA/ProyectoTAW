<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
    Integer id = (Integer) session.getAttribute("usuarioid");
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
%>
<html>
<head>
    <title>Ver Dieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<h1>Ver Dieta</h1>

<!-- Formulario para eliminar -->
    <input type="hidden" name="id" value="<%=id%>" />

    Código:
    <input name="codigo" maxlength="3" size="3" type="number" value="<%=dieta.getCodigo()%>"/>

    NºComidas:
    <input name="numComidas" maxlength="1" size="1" type="number" value="<%=dieta.getNumComidas()%>"/>

    Tipo:
    <input name="tipo" maxlength="20" size="20" type="text" value="<%=dieta.getTipo()%>"/>

    FechaInicio:
    <input name="fechaInicio" maxlength="10" size="10" type="date" value="<%=dieta.getFechaInicio()%>"/>

    FechaFin:
    <input name="fechaFin" maxlength="10" size="10" type="date" value="<%=dieta.getFechaFin()%>"/>

    <br/>

Comidas:<br/>
<ul>
    <% for(ComidaEntity c : comidas) { %>
    <li>
        <%=c.getNombre()%>
        <a href="/dietista/menu?id=<%=c.getId()%>&dietaid=<%=dieta.getCodigo()%>">Menú</a>
    </li>
    <% } %>
</ul>
<br/>

<a href="/dietista/"><button>Atrás</button></a>
</body>
</html>
