<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.ui.SesionEjercicio" %>
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="es.uma.proyectogrupo18.ui.ObtenerIdYT" %>
<%@ page import="es.uma.proyectogrupo18.dto.RutinaSemanal" %>
<%@ page import="es.uma.proyectogrupo18.dto.SesionDeEjercicio" %>
<%@ page import="es.uma.proyectogrupo18.dto.Ejercicio" %><%--
  Created by IntelliJ IDEA.
  User: Pablo Astudillo Fraga (100%)
  Date: 06/06/2024
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaSemanal r = (RutinaSemanal) request.getAttribute("rutina");
    List<SesionDeEjercicio> sesiones = (List<SesionDeEjercicio>) request.getAttribute("sesiones");
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
    <h3>Inicio: <%=r.getFechaInicio()%> - Final: <%=r.getFechaFin()%></h3>
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

            <td><b>Repeticiones/Tiempo</b></td>
            <td><b>Series</b></td>
            <td><b>Peso/Velocidad</b></td>
            <td><b>Video</b></td>
            <td><b>Feedback</b></td>
        </tr>
        <%
            for(SesionDeEjercicio s : sesiones) {
                Ejercicio ej = s.getEjercicio();
                String idYT = ObtenerIdYT.obtenerIdYT(ej.getVideo());
        %>
        <div>
            <tr>
                    <td>
                        <%=s.getOrden()%>
                    </td>
                    <td>
                        <%=s.getDia()%>
                    </td>
                    <td>
                        <%=ej.getNombre()%>
                    </td>
                <%
                    if (r.getTrabajador().getRol().getRol().compareTo("crosstrainer") == 0) {
                %>
                    <td>
                        <%=ej.getTipo()%>
                    </td>
                <% } %>
                    <td>
                        <%=s.getRepeticiones()%>
                    </td>
                    <td>
                        <%=s.getCantidad()%>
                    </td>
                    <td>
                        <%= s.getPeso()%>
                    </td>
                    <td>
                        <iframe width="280" height="157" src="https://www.youtube.com/embed/<%=idYT%>" frameborder="1" allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </td>
                    <td>
                        <form action="/customer/feedback" method="post">
                            <input name="sesionId" hidden value="<%= s.getId() %>"/>
                            <input name="clienteId" hidden value="<%= s.getCliente().getId() %>"/>
                            <button>Feedback</button>
                        </form>
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
