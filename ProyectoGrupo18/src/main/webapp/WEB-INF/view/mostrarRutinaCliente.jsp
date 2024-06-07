<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.ui.SesionEjercicio" %>
<%@ page import="org.springframework.web.client.RestTemplate" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 06/06/2024
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaSemanalEntity r = (RutinaSemanalEntity) request.getAttribute("rutina");
    List<SesionEjercicio> ses = (List<SesionEjercicio>) request.getAttribute("sesiones");
%>
<html>
<head>
    <title><%=r.getNombre()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="advise">
    <h1><%=r.getNombre()%></h1>
</div>
<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td><b>Orden</b></td>
            <td><b>Día</b></td>
            <td><b>Nombre</b></td>

            <%
                if(r.getTrabajador().getRol().getRol().compareTo("crosstrainer")==0)
                {
            %>
                <td><b>Tipo</b></td>
            <% } %>

            <td><b>Repeticiones</b></td>
            <td><b>Series</b></td>
            <td><b>Video</b></td>
            <td><b>Progreso</b></td>
            <td></td>
        </tr>
        <%
            for(SesionEjercicio sesion : ses){
                SesionDeEjercicioEntity se = sesion.getSesion();
                EjercicioEntity ej = se.getEjercicio();
        %>
        <div>
            <tr>
                    <td>
                        <%= se.getOrden()%>
                    </td>
                    <td>
                        <%=sesion.getDia()%>
                    </td>
                    <td>
                        <%= ej.getNombre()%>
                    </td>
                <%
                    if(r.getTrabajador().getRol().getRol().compareTo("crosstrainer")==0)
                    {
                %>
                    <td>
                        <%= ej.getTipo() %>
                    </td>
                <% } %>
                    <td>
                        <%= se.getRepeticiones() %>
                    </td>
                    <td>
                        <%= se.getCantidad() %>
                    </td>
                    <td>
                        <iframe width="280" height="157" src="https://www.youtube.com/embed/<%=ej.getVideo()%>" frameborder="0" allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </td>
                <td>
                    <a href="${pageContext.request.contextPath}/customer/actualizarProgreso?sesionId=<%= se.getId() %>" target="_blank">
                        <button>Actualizar progreso</button>
                    </a>
                </td>

            </tr>
        </div>
        <%
            }
        %>
    </table>
</div>

<div style="text-align: center">
    <a href="/customer/"><button>Volver</button></a>
</div>

</body>
</html>
