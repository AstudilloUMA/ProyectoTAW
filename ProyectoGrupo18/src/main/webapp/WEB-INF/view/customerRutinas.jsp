<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %>
<%@ page import="es.uma.proyectogrupo18.dto.RutinaSemanal" %>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioUI");
    String tipo = (String) request.getSession().getAttribute("tipo");
    List<RutinaSemanal> rutinas = (List<RutinaSemanal>) request.getAttribute("rutinas");
%>
<html>
<head>
    <title>Rutinas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div>
    <%
        if(rutinas.isEmpty()){
    %>
    <div class="advise">
        <h1>No existen rutinas</h1>
    </div>
    <%
    }else{
    %>
    <div class="rutinas">
        <table>
            <tr style="background-color: #222">
                <td>
                    <b>ID</b>
                </td>
                <td>
                    <b>Fecha de Inicio</b>
                </td>
                <td>
                    <b>Fecha de Fin</b>
                </td>
                <td></td>
            </tr>
            <%
                for(RutinaSemanal r : rutinas){
            %>
            <tr>
                <td>
                    <%= r.getId() %>
                </td>
                <td>
                    <%= r.getFechaInicio() %>
                </td>
                <td>
                    <%= r.getFechaFin() %>
                </td>
                <td>
                    <form class="form-cell">
                        <input type="hidden" name="rutinaId" value="<%= r.getId() %>">
                        <button>Ver</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
