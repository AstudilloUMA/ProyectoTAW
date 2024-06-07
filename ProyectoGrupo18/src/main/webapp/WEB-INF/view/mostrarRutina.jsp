<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="es.uma.proyectogrupo18.dao.SesionDeEjercicioRepository" %>
<%@ page import="es.uma.proyectogrupo18.ui.SesionEjercicio" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 05/06/2024
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaSemanalEntity rutina = (RutinaSemanalEntity) request.getAttribute("rutina");
    List<SesionEjercicio> ses = (List<SesionEjercicio>) request.getAttribute("sesiones");
    List<String> dias = new ArrayList<>(List.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"));
    String tipo = (String) request.getSession().getAttribute("tipo");
    String actionUrl;
    if("crosstrainer".equals(tipo)){
        actionUrl = "/crosstrainer/guardar";
    } else {
        actionUrl = "/bodybuilder/guardar";
    }
%>
<html>
<head>
    <title><%=rutina.getNombre()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>

<jsp:include page="navbarEntrenador.jsp"/>

<div class="advise">
    <h1><%=rutina.getNombre()%></h1>
</div>

<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td><b>Orden</b></td>
            <td><b>Día</b></td>
            <td><b>Nombre</b></td>
            <%
                if(tipo == "crosstrainer"){
            %>

            <td><b>Tipo</b></td>

            <%}%>
            <td><b>Repeticiones</b></td>
            <td><b>Series</b></td>
            <td><b>Video</b></td>
            <td></td>
            <td></td>

        </tr>
        <%
            for(SesionEjercicio sesion : ses){
                    SesionDeEjercicioEntity se = sesion.getSesion();
                    SesionDeEntrenamientoEntity sde = sesion.getSesionEntrenamiento();
                    EjercicioEntity ej = se.getEjercicioByEjercicioId();
        %>
            <div class="login-form">

                    <tr>
                        <form:form action="<%= actionUrl %>" modelAttribute="rutinaUi" method="post">
                            <form:input path="sesionId" value="<%= se.getId()%>" type="hidden"/>
                            <form:input path="ejercicioId" value="<%= ej.getId()%>" type="hidden"/>
                            <form:input path="rutinaId" value="<%= rutina.getId()%>" type="hidden"/>
                            <form:input path="sesionEntrenamientoId" value="<%= sde.getId()%>" type="hidden"/>
                            <td>
                            <form:input path="orden" value="<%= se.getOrden()%>" class="form-input"/>
                        </td>
                            <td>
                                <form:input path="dia" value="<%= sesion.getDia()%>" class="form-input"/>
                            </td>
                        <td>
                            <form:input path="nombre" value="<%= ej.getNombre()%>" class="form-input"/>
                        </td>
                        <%
                            if(tipo == "crosstrainer"){
                        %>
                        <td>
                            <form:input path="tipo" value="<%= ej.getTipo() %>" class="form-input"/>
                        </td>
                        <%}%>
                        <td>
                            <form:input path="repeticiones" value="<%= se.getRepeticiones() %>" class="form-input"/>
                        </td>
                        <td>
                            <form:input path="cantidad" value="<%= se.getCantidad() %>" class="form-input"/>
                        </td>
                        <td>
                            <form:input path="video" value="<%= ej.getVideo() %>" class="form-input"/>
                        </td>
                        <td>
                            <form:button htmlEscape="false"> Guardar </form:button>
                        </td>
                        </form:form>
                        <td>
                            <form action="/<%=tipo%>/borrar" method="post">
                                <input name="idRutina" hidden value="<%=rutina.getId()%>"/>
                                <input name="idEjercicio" hidden value="<%=ej.getId()%>"/>
                                <button style="margin-top: 17px">Borrar</button>
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
    <a href="rutinas"><button>Volver</button></a>
</div>
</body>
</html>
