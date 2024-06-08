<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 08/06/2024
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    String tipo = (String) request.getSession().getAttribute("tipo");
    RutinaSemanalEntity rutina = (RutinaSemanalEntity) request.getAttribute("rutina");

    List<SesionDeEjercicioEntity> sesiones = (List<SesionDeEjercicioEntity>) request.getAttribute("sesiones");
%>
<html>
<head>
    <title>Personalizar rutina</title>
</head>
<body>
<body>
<jsp:include page="navbarEntrenador.jsp"/>

<div class="advise">
    <h1>Personalizar rutina de <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%></h1>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</div>


<div class="rutinas">
    <%
        if(!sesiones.isEmpty()){
            SesionDeEjercicioEntity s = sesiones.get(0);
            EjercicioEntity ej = s.getEjercicio();
    %>
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
            <td></td>

        </tr>
        <div class="login-form">

            <tr>
                <form action="/crosstrainer/personalizada" method="post">
                    <input name="sesionId" value="<%= s.getId()%>" type="hidden"/>
                    <input name="clienteId" value="<%= cliente.getId()%>" type="hidden"/>
                    <input name="sesiones" value="<%= sesiones%>" type="hidden"/>
                    <td>
                        <%= s.getOrden()%>
                    </td>
                    <td>
                        <%=s.getDia()%>
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
                    <%
                        }
                    %>
                    <td>
                        <input name="repeticiones" value="<%= s.getRepeticiones() %>" class="form-input"/>
                    </td>
                    <td>
                        <input type="number" name="cantidad" value="<%= s.getCantidad() %>" class="form-input"/>
                    </td>
                    <td>
                        <input name="peso" value="<%= s.getPeso() %>" class="form-input"/>
                    </td>
                    <td>
                        <button> Guardar </button>
                    </td>
                </form>
            </tr>
        </div>
        <%
            }
        %>
    </table>
</div>
    <%
        if(sesiones.isEmpty()){
    %>
    <div style="text-align: center">
        <a href="/crosstrainer/clientes"><button>Finalizar</button></a>
    </div>
    <%
        }
    %>
</body>
</body>
</html>
