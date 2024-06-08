<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>
<%
    List<FiltroCRUD> comidas = (List<FiltroCRUD>) request.getAttribute("comidas");
    List<FiltroCRUD> ejercicios = (List<FiltroCRUD>) request.getAttribute("ejercicios");
    List<FiltroCRUD> sesiones = (List<FiltroCRUD>) request.getAttribute("sesiones");
    Integer ifEj = (Integer) request.getAttribute("ifEj");
    Integer ifComida = (Integer) request.getAttribute("ifComida");
    Integer ifSe = (Integer) request.getAttribute("ifSe");
%>
<jsp:include page="navbarAdmin.jsp"/>
<h1>Filtro de Datos</h1>
<table>
    <form:form method="GET" action="filtrarCRUD" modelAttribute="filtro">
        <tr>
           <th>COMIDAS</th>
            <th>EJERCICIOS</th>
            <th>SESIONES</th>
        </tr>
        <tr>
            <td>
                <label for="comidaNombre">Nombre:</label>
                <form:input path="comidaNombre" id="comidaNombre"></form:input>
                <label for="comidaCalorias">Calorias:</label>
                <form:input path="comidaCalorias" id="comidaCalorias"></form:input>
            </td>
            <td>
                <label for="ejTipo">Tipo:</label>
                <form:input path="ejTipo" id="ejTipo"></form:input>
                <label for="ejNombre">Nombre:</label>
                <form:input path="ejNombre" id="ejNombre"></form:input>
            </td>
            <td>
                <label for="seRepeticiones">Rep:</label>
                <form:input path="seRepeticiones" id="seRepeticiones"></form:input>
                <label for="seCantidad">Cantidad:</label>
                <form:input path="seCantidad" id="seCantidad"></form:input>
                <label for="seEjercicio">Ej:</label>
                <form:input path="seEjercicio" id="seEjercicio"></form:input>
                <label for="seTrabajo">Entrenador:</label>
                <form:input path="seTrabajo" id="seTrabajo"></form:input>
            </td>
        </tr>
       <tr>
           <td><table>
               <tr>
                   <th>Nombre</th>
                   <th>Calorías</th>
               </tr>
               <%
                   for (FiltroCRUD comida : comidas) {
               %>
               <tr>
                   <td><%= comida.getComidaNombre() %></td>
                   <td><%= comida.getComidaCalorias() %></td>
               </tr>
               <%
                   }
               %>
           </table></td>
           <td><table>
               <tr>
                   <th>Tipo</th>
                   <th>Nombre</th>
               </tr>
               <%
                   for (FiltroCRUD ejercicio : ejercicios) {
               %>
               <tr>
                   <td><%= ejercicio.getejTipo() %></td>
                   <td><%= ejercicio.getejNombre() %></td>
               </tr>
               <%
                   }
               %>
           </table></td>
           <td><table>
               <tr>
                   <th>Repeticiones</th>
                   <th>Cantidad</th>
                   <th>Ejercicio</th>
                   <th>Trabajo</th>
               </tr>
               <%
                   for (FiltroCRUD sesion : sesiones) {
               %>
               <tr>
                   <td><%= sesion.getseRepeticiones() %></td>
                   <td><%= sesion.getseCantidad() %></td>
                   <td><%= sesion.getseEjercicio() %></td>
                   <td><%= sesion.getseTrabajo() %></td>
               </tr>
               <%
                   }
               %>
           </table></td>

       </tr>
        <input type="submit" value="Filtrar">
    </form:form>
</table>



</body>
</html>
