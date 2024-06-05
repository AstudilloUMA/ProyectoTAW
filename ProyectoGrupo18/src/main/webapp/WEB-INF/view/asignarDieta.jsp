<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 20/05/2024
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    List<DietaEntity> dietas = (List<DietaEntity>) request.getAttribute("dietas");
%>
<html>
<head>
    <title>Asignar Dieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbarDietista.jsp"/>

<div class="advise">
    <h1>Asignar al cliente <%=cliente.getUsuarioByUsuarioId().getNombre()%> <%=cliente.getUsuarioByUsuarioId().getApellidos()%></h1>
</div>
<div style="text-align: center">
    <%
        if(dietas.isEmpty() || dietas == null){
    %>
    <h2 style="text-align: center">No hay dietas registradas</h2>
    <a href="clientes"><button>Volver</button></a>
    <%
    }else{
    %>
    <div class="form-container">
        <h2 style="text-align: center">Selecciona la dieta que quieres asignar</h2>
        <form method="post" action="asignada">
            <input type="hidden" name="id" value="<%=cliente.getUsuarioId()%>">
            <select name="dietaId">
                <%
                    for(DietaEntity d : dietas){
                %>
                <option value="<%=d.getCodigo()%>"><%=d.getNombre()%> - <%=d.getFechaInicio()%> - <%=d.getFechaFin()%></option>
                <%
                    }
                %>
            </select>
            <br/>
            <button>Asignar</button>
        </form>
        <a href="clientes"><button>Volver</button></a>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
