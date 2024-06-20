<%--
Autores:
Miguel Sánchez Hontoria:50%
Pablo Astudillo Fraga:50%
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.dto.Cliente" %>
<%@ page import="es.uma.proyectogrupo18.dto.Dieta" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
%>
<html>
<head>
    <title>Asignar Dieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbarDietista.jsp"/>

<div class="advise">
    <h1>Asignar al cliente <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%></h1>
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
            <input type="hidden" name="id" value="<%=cliente.getId()%>">
            <select name="dietaId">
                <%
                    for(Dieta d : dietas){
                %>
                <option value="<%=d.getId()%>"><%=d.getNombre()%> - <%=d.getFechaInicio()%> - <%=d.getFechaFin()%></option>
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
