<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 05/06/2024
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaSemanalEntity rutina = (RutinaSemanalEntity) request.getAttribute("rutina");

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
            <td><b>Nombre</b></td>
            <td><b>Tipo</b></td>
            <td><b>Repeticiones</b></td>
            <td><b>Series</b></td>
            <td><b>Video</b></td>
            <td></td>
        </tr>
        <%
            for(RutinaSemanalEntrenamientoEntity r : rutina.getRutinaSemanalEntrenamientosById()){
                SesionDeEntrenamientoEntity s = r.getSesionDeEntrenamientoBySesionDeEntrenamientoId();
                Collection<EntrenamientoEjercicioEntity> e = s.getEntrenamientoEjerciciosById();
                for(EntrenamientoEjercicioEntity ee : e){
                    SesionDeEjercicioEntity se = ee.getSesionDeEjercicioBySesionDeEjercicioId();
                    EjercicioEntity ej = se.getEjercicioByEjercicioId();
        %>
            <div class="login-form">
                <form:form action="/crosstrainer/guardar" modelAttribute="rutinaUi" method="post">
                    <tr>
                        <td>
                            <form:input path="orden" value="<%= se.getOrden()%>" class="form-input"/>
                        </td>
                        <td>
                            <form:input path="nombre" value="<%= ej.getNombre()%>" class="form-input"/>
                        </td>
                        <td>
                            <form:input path="tipo" value="<%= ej.getTipo() %>" class="form-input"/>
                        </td>
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
                    </tr>
                </form:form>
            </div>
        <%
            }}
        %>
    </table>
</div>
<div style="text-align: center">
    <a href="rutinas"><button>Volver</button></a>
</div>
</body>
</html>
