<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.SesionDeEjercicioEntity" %><%--
  Created by IntelliJ IDEA.
  User: ansag
  Date: 07/06/2024
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SesionDeEjercicioEntity se = (SesionDeEjercicioEntity) request.getAttribute("sesionDeEjercicio");
    EjercicioEntity ej = (EjercicioEntity) request.getAttribute("ejercicio");
%>
<html>
  <head>
    <title><%=ej.getNombre()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
  </head>
  <body>
  <jsp:include page="navbar.jsp"/>

  <div class="advise">
      <h1>Progreso de: <%=ej.getNombre()%></h1>
  </body>

    <div>
        <h2>Tu entrenador te mandó: </h2>
        <p>
            Series: <%=se.getCantidad()%> </br>
            Repeticiones: <%=se.getRepeticiones()%>
        </p>
    </div>

  <div>
      <h2>Tu progreso: </h2>
      <p>
          Series: <%=se.getSeriesCompletadas()%> </br>
          Repeticiones: <%=se.getRepeticionesCompletadas()%>
      </p>
  </div>
</html>
