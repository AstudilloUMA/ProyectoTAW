<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectogrupo18.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="es.uma.proyectogrupo18.dto.RutinaSemanal" %>
<%@ page import="es.uma.proyectogrupo18.dto.Ejercicio" %>
<%--
Created by IntelliJ IDEA.
User: pablo
Date: 05/06/2024
Time: 13:08
To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaSemanal rutina = (RutinaSemanal) request.getAttribute("rutina");
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
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
    <title>Nueva Sesión</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>

<jsp:include page="navbarEntrenador.jsp"/>

<div class="advise">
    <h1>Nueva sesión para la <%=rutina.getNombre()%></h1>
</div>

<div class="rutinas">
    <table>
        <tr style="background-color: #222">
            <td><b>Orden</b></td>
            <td><b>Día</b></td>
            <td><b>Nombre</b></td>
            <td><b>Repeticiones/Tiempo</b></td>
            <td><b>Series</b></td>
            <td><b>Peso/Velocidad</b></td>
            <td></td>

        </tr>
            <tr>
                <form action="/<%=tipo%>/crear" method="post">
                    <input name="rutinaId" value="<%= rutina.getId()%>" type="hidden"/>
                    <td>
                        <input type="number" name="orden" class="form-input"/>
                    </td>
                    <td>
                        <select name="dia" class="form-input" style="margin-top: 20px" required>
                            <%
                                for(String d : dias){
                            %>
                                <option value="<%=d%>"><%=d%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                    <td>
                        <select name="ejercicioId" class="form-input" style="margin-top: 20px">
                            <%
                                for(Ejercicio e : ejercicios){
                            %>
                                    <option value="<%= e.getId()%>"><%= e.getNombre()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                    <td>
                        <input name="repeticiones" class="form-input" required/>
                    </td>
                    <td>
                        <input type="number" name="cantidad" class="form-input" required/>
                    </td>
                    <td></td>
                    <td>
                        <button> Guardar </button>
                    </td>
                </form>
            </tr>
    </table>
</div>
<div style="text-align: center">
    <a href="/<%=tipo%>/tipo?id=<%=rutina.getId()%>"><button>Volver</button></a>
</div>
</body>
</html>

