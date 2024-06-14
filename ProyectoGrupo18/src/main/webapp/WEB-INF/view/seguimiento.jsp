<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.FeedbackEntity" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.dto.Cliente" %>
<%@ page import="es.uma.proyectogrupo18.dto.Feedback" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 24/05/2024
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
%>
<html>
<head>
    <title>Seguimiento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="navbarEntrenador.jsp"/>
<div class="advise">
    <h1>Feedback de <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%></h1>
</div>

<%
    if(feedbacks.isEmpty()){
%>
    <div style="text-align: center">
        <h2>No hay feedbacks</h2>
    </div>
<%
    }else{
%>
    <div class="rutinas">
            <table>
                <tr style="background-color: #222">
                    <td><b>Ejercicio</b></td>
                    <td><b>Repeticiones/Tiempo <br/> (Entrenador : Realizadas)</b></td>
                    <td><b>Series <br/> (Entrenador : Realizadas)</b></td>
                    <td><b>Peso/Velocidad <br/> (Entrenador : Realizado) </b></td>
                    <td><b>Calificación (0-10)</b></td>
                    <td><b>Estado físico</b></td>
                    <td><b>Comentarios</b></td>
                </tr>
                <%
                    for(FeedbackEntity feedback : feedbacks){
                        SesionDeEjercicioEntity sesion = feedback.getSesion();
                %>
                    <tr>
                        <td width="250px">
                            <%= sesion.getEjercicio().getNombre() %>
                        </td>
                        <td width="250px">
                            <%= sesion.getRepeticiones() %> : <%= feedback.getRepeticiones() %>
                        </td>
                        <td width="250px">
                            <%= sesion.getCantidad() %> : <%= feedback.getSeries() %>
                        </td>
                        <td width="250px">
                            <%= sesion.getPeso() %> : <%=feedback.getPeso() %>
                        </td>
                        <td>
                            <%= feedback.getCalificacion() %>
                        </td>
                        <td width="180px">
                            <%= feedback.getEstadoDelCliente() %>
                        </td>
                        <td width="370px">
                            <%= feedback.getComentarios() %>
                        </td>
                    </tr>
                <%
                    }
                %>
                </div>
            </table>
    </div>
<%
    }
%>

<div style="text-align: center">
    <a href="clientes"><button>Volver</button></a>
</div>
</body>
</html>
