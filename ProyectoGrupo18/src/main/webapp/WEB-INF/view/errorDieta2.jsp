<%--
Autor:
Miguel Sánchez Hontoria:100%
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String opc = (String) request.getAttribute("opc");
    Integer id = (Integer) request.getAttribute("id");
%>

<html>
<head>
    <title>Error Dieta 2</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<div class="advise">
    <h1>Error en las fechas de la dieta</h1>
</div>
<div style="text-align: center">
    <h3>Las dietas son semanales</h3>
</div>
<div style="text-align: center">
    <%if(opc.equals("crear")){%>
    <a href="/dietista/crear"><button>Atrás</button></a>
    <%}else{%>
    <a href="/dietista/modificar?id=<%= id %>"><button>Atrás</button></a>
    <%}%>

</div>

</body>
</html>