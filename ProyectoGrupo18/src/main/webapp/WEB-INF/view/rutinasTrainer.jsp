<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="ch.qos.logback.core.net.server.Client" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroRutina" %>
<%@ page import="es.uma.proyectogrupo18.dto.RutinaSemanal" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 20/05/2024
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RutinaSemanal> rutinas = (List<RutinaSemanal>) request.getAttribute("rutinas");
    String tipo = (String) request.getSession().getAttribute("tipo");
    FiltroRutina filtro = (FiltroRutina) request.getAttribute("filtro");
    Boolean noResult = (Boolean) request.getAttribute("noResults");

    String actionUrl;
    if("crosstrainer".equals(tipo)){
        actionUrl = "/crosstrainer/filtrar";
    } else {
        actionUrl = "/bodybuilder/filtrar";
    }
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
        if(rutinas.isEmpty() && !noResult){
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
            <form:form modelAttribute="filtroRutina" action="<%=actionUrl%>" method="post">
                <tr>
                    <td>
                        <form:input path="nombre" cssClass="form-input" value='<%= (filtro == null ? "" : filtro.getNombre()) %>'/>
                    </td>
                    <td>
                        <form:input type="date" path="fechaInicio" cssClass="form-input" value='<%= (filtro == null ? "" : filtro.getFechaInicio()) %>'/>
                    </td>
                    <td>
                        <form:input type="date" path="fechaFin" cssClass="form-input" value='<%= (filtro == null ? "" : filtro.getFechaFin()) %>'/>
                    </td>
                    <td>
                        <form:button>Filtrar</form:button>
                    </td>
                </tr>
            </form:form>
        </table>
    </div>
    <%
        if(!noResult){
    %>
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
                    for(RutinaSemanal r : rutinas){
                %>
                    <tr>
                        <form method="post" action="/<%=tipo%>/actualizarAtributos">
                            <input value="<%=r.getId()%>" name="id" type="hidden" class="form-input">
                        <td>
                            <input value="<%= r.getNombre() %>" name="nombre" type="text" class="form-input">
                        </td>
                        <td>
                            <input value="<%= r.getFechaInicio() %>" name="fechaDeInicio" type="date" class="form-input">
                        </td>
                        <td>
                            <input value="<%= r.getFechaFin() %>" name="fechaDeFin" type="date" class="form-input">
                        </td>
                        <td>
                            <button style="margin-right: 25px">Guardar</button>
                        </form>
                            <a href="mostrar?id=<%=r.getId()%>"><button style="padding: 10px 15px">Ver</button></a>
                            <a href="eliminar?id=<%=r.getId()%>" style="margin-left: 25px"><button style="padding: 10px 15px">Eliminar</button></a>
                            <a href="/<%=tipo%>/duplicar?id=<%=r.getId()%>"><button style="margin-left: 25px">Duplicar</button></a>
                        </td>

                    </tr>
                <%
                    }
                %>
        </table>
    </div>
    <%
            }else{
    %>
    <div class="advise">
        <h1>No se han encontrado resultados con ese filtro</h1>
    </div>
    <%
            }
        }
    %>
    <a style="margin-right: 25px" href="/<%=tipo%>/nueva"><button>Crear Rutina</button></a>
    <a href="/<%=tipo%>/"><button>Volver</button></a>
</div>

</body>
</html>
