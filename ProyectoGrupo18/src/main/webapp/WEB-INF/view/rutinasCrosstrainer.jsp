<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="ch.qos.logback.core.net.server.Client" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 20/05/2024
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RutinaSemanalEntity> rutinas = (List<RutinaSemanalEntity>) request.getAttribute("rutinas");
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
                <td>
                    <b>Cliente</b>
                </td>
                <td></td>
            </tr>
                <%
                    for(RutinaSemanalEntity r : rutinas){
                        ClienteEntity c = r.getCliente();
                        UsuarioEntity u = c.getUsuario();
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
                            <%= u.getNombre() %> <%= u.getApellidos() %>
                        </td>
                        <td>
                            <form style="margin-top: 17px" class="form-cell">
                                <input type="hidden" name="rutinaId" value="<%= r.getId() %>">
                                <button>Asignar</button>
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
