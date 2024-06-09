<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="ch.qos.logback.core.net.server.Client" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroRutina" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 20/05/2024
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RutinaSemanalEntity> rutinas = (List<RutinaSemanalEntity>) request.getAttribute("rutinas");
    String tipo = (String) request.getSession().getAttribute("tipo");
    FiltroRutina filtro = (FiltroRutina) request.getAttribute("filtro");
%>
<html>
<head>
    <title>Rutinas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbarEntrenador.jsp"/>

<div style="text-align: center">
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
            <form:form modelAttribute="filtro" action="/crosstrainer/filtrar" method="post">
                <tr>
                    <td>
                        Nombre: <form:input path="nombre" value="<%= (filtro == null ? "" : filtro.getNombre()) %>"/>
                    </td>
                    <td>
                        Fecha de Inicio: <form:input path="fechaInicio" value="<%= (filtro == null ? "yyyy-mm-dd" : filtro.getFechaInicio()) %>"/>
                    </td>
                    <td>
                        Fecha de Fin: <form:input path="fechaFin" value="<%= (filtro == null ? "yyyy-mm-dd" : filtro.getFechaFin()) %>"/>
                    </td>
                    <td>
                        <form:button>Filtrar</form:button>
                    </td>
                </tr>
            </form:form>
        </table>
    </div>

    <div class="rutinas">
        <table>
            <tr style="background-color: #222">
                <td>
                    <b>Nombre</b>
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
                    for(RutinaSemanalEntity r : rutinas){
                %>
                    <tr>
                        <td>
                            <%= r.getNombre() %>
                        </td>
                        <td>
                            <%= r.getFechaInicio() %>
                        </td>
                        <td>
                            <%= r.getFechaFin() %>
                        </td>
                        <td>
                            <a href="mostrar?id=<%=r.getId()%>"><button style="padding: 10px 15px">Ver</button></a>
                            <a href="eliminar?id=<%=r.getId()%>" style="margin-left: 25px"><button style="padding: 10px 15px">Eliminar</button></a>
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
    <a style="margin-right: 25px" href="/<%=tipo%>/nueva"><button>Crear Rutina</button></a>
    <a href="/<%=tipo%>/"><button>Volver</button></a>
</div>

</body>
</html>
