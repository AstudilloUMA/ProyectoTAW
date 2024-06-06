<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
    Integer id = (Integer) session.getAttribute("usuarioid");
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
%>
<html>
<head>
    <title>Dieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>

<jsp:include page="navbarDietista.jsp"/>

<%if(dieta != null){%>
<div class="advise">
    <h1>Modificar <%=dieta.getNombre()%></h1>
</div>
<%}else{%>
<div class="advise">
    <h1>Crear dieta</h1>
</div>
<%}%>

<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td><b>Código</b></td>
            <td><b>Nombre</b></td>
            <td><b>Kcal Totales</b></td>
            <td><b>Orden</b></td>
            <td><b>Menú</b></td>
        </tr>
        <%
            for(ComidaEntity c : comidas){
        %>
        <div class="login-form">
            <tr>
                <form:form action="/dietista/guardar" modelAttribute="dietaUi" method="post">
                    <form:input path="comidaId" value="<%= c.getId()%>" type="hidden"/>
                    <form:input path="dietaId" value="<%= dieta.getCodigo()%>" type="hidden"/>
                    <td>
                        <form:input path="codigo" value="<%= c.getId()%>" class="form-input"/>
                    </td>
                    <td>
                        <form:input path="nombre" value="<%= c.getNombre()%>" class="form-input"/>
                    </td>
                    <td>
                        <form:input path="kcal" value="<%= c.getKilocaloriasTotales() %>" class="form-input"/>
                    </td>
                    <td>
                        <form:input path="orden" value="<%= c.getOrden()%>" class="form-input"/>
                    </td>
                    <td>
                        <a href="/dietista/menu?id=<%=c.getId()%>&dietaid=<%=dieta.getCodigo()%>&from=modificar">Ver menú</a>
                    </td>
                    <td>
                        <form:button htmlEscape="false"> Guardar </form:button>
                    </td>
                </form:form>
            </tr>
        </div>
        <%
            }
        %>
    </table>
</div>
<div style="text-align: center">
    <a href="/dietista/"><button>Atrás</button></a>
</div>

</body>
</html>