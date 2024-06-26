<%@ page import="es.uma.proyectogrupo18.dto.Menu" %>
<%@ page import="es.uma.proyectogrupo18.dto.Dieta" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Menu menu = (Menu) request.getAttribute("menuA");
    Integer id = (Integer) session.getAttribute("usuarioid");
    Dieta dieta = (Dieta) request.getAttribute("dieta");
    String from = (String) request.getAttribute("from");
%>
<html>
<head>
    <title>menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>

<div class="advise">
    <h1>Menú <%=menu.getId()%></h1>
</div>

Ingredientes:
<br/>
<textarea name="ingredientes" rows="3" cols="50">
<%=menu.getIngredientes()%>
</textarea>
<br/>
Preparación:
<br/>
<textarea name="ingredientes" rows="8" cols="50">
<%=menu.getPreparacion()%>
</textarea>
<br/>
<br/>
<% if ("modificar".equals(from)) { %>
<a href="/dietista/modificar?id=<%= dieta.getId() %>"><button>Atrás</button></a>
<% } else { %>
<a href="/dietista/ver?id=<%= dieta.getId() %>"><button>Atrás</button></a>
<% } %>

</body>
</html>
