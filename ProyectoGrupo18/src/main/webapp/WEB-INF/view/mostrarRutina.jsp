<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="es.uma.proyectogrupo18.dao.SesionDeEjercicioRepository" %>
<%@ page import="es.uma.proyectogrupo18.ui.SesionEjercicio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="es.uma.proyectogrupo18.ui.RutinaUi" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 05/06/2024
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaSemanalEntity rutina = (RutinaSemanalEntity) request.getAttribute("rutina");
    List<SesionDeEjercicioEntity> sesiones = (List<SesionDeEjercicioEntity>) request.getAttribute("sesiones");
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
            <td><b>Repeticiones/Tiempo</b></td>
            <td><b>Series</b></td>
            <td><b>Peso/Velocidad</b></td>
            <td><b>Video</b></td>
            <td></td>
            <td></td>

        </tr>
        <%
            for(SesionDeEjercicioEntity s : sesiones){
                EjercicioEntity ej = s.getEjercicio();
        %>
            <div class="login-form">

                    <tr>
                        <form action="<%= actionUrl %>" method="post">
                            <input name="sesionId" value="<%= s.getId()%>" type="hidden"/>
                            <input name="ejercicioId" value="<%= ej.getId()%>" type="hidden"/>
                            <input name="rutinaId" value="<%= rutina.getId()%>" type="hidden"/>
                            <td>
                            <input type="number" name="orden" value="<%= s.getOrden()%>" class="form-input"/>
                        </td>
                            <td>
                                <select name="dia" class="form-input" style="margin-top: 20px">
                                    <%
                                        for(String d : dias){
                                            if (s.getDia() == null){
                                    %>
                                                <option value="null" disabled>-</option>
                                    <%
                                            }
                                            if(d.equals(s.getDia())){
                                    %>
                                                <option selected value="<%=d%>"><%=d%></option>
                                    <%
                                            } else {
                                    %>
                                                <option value="<%=d%>"><%=d%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                        <td>
                            <%=ej.getNombre()%>
                        </td>
                        <%
                            if(tipo == "crosstrainer"){
                        %>
                        <td>
                            <%= ej.getTipo().getTipo() %>
                        </td>
                        <%}%>
                        <td>
                            <input name="repeticiones" value="<%= s.getRepeticiones() %>" class="form-input"/>
                        </td>
                        <td>
                            <input type="number" name="cantidad" value="<%= s.getCantidad() %>" class="form-input"/>
                        </td>
                            <td></td>
                        <td>
                            <input name="video" value="<%= ej.getVideo() %>" class="form-input"/>
                        </td>
                        <td>
                            <button> Guardar </button>
                        </td>
                        </form>
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
