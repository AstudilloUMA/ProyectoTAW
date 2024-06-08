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
    FiltroCRUD filtro = (FiltroCRUD) request.getAttribute("filtro");
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
            <td> <label for="ifComida">Mostrar Comidas:</label>
                <form:checkbox path="ifComida" id="ifComida"  /></td>
            <td> <label for="ifEj">Mostrar Ejercicios:</label>
                <form:checkbox path="ifEj" id="ifEj" /></td>
            <td> <label for="ifSe">Mostrar Sesiones:</label>
                <form:checkbox path="ifSe" id="ifSe" /></td>
        </tr>
        <tr>
            <td>
                <%if(filtro.getIfComida()){
                %>
                <label for="comidaNombre">Nombre:</label>
                <form:input path="comidaNombre" id="comidaNombre" value="${filtroCRUD.comidaNombre != null ? filtroCRUD.comidaNombre : ''}"></form:input>
                <label for="comidaCalorias">Calorias:</label>
                <form:input path="comidaCalorias" id="comidaCalorias" value="${filtroCRUD.comidaCalorias != null ? filtroCRUD.comidaCalorias : ''}"></form:input>
                <%
                    }else{
                    %>Sin Comidas
                <%
                    }
                %>
            </td>
            <td>
                <%if(filtro.getIfEj()){
                %>
                <label for="ejTipo">Tipo:</label>
                <form:input path="ejTipo" id="ejTipo" value="${filtroCRUD.ejTipo != null ? filtroCRUD.ejTipo : ''}"></form:input>
                <label for="ejNombre">Nombre:</label>
                <form:input path="ejNombre" id="ejNombre" value="${filtroCRUD.ejNombre != null ? filtroCRUD.ejNombre : ''}"></form:input>
                <%
                    }else{
                %>Sin Ejercicios
                <%
                    }
                %>

            </td>
            <td>
                <%if(filtro.getIfSe()){
                %>
                <label for="seRepeticiones">Rep:</label>
                <form:input path="seRepeticiones" id="seRepeticiones" value="${filtroCRUD.seRepeticiones != null ? filtroCRUD.seRepeticiones : ''}"></form:input>
                <label for="seCantidad">Cantidad:</label>
                <form:input path="seCantidad" id="seCantidad" value="${filtroCRUD.seCantidad != null ? filtroCRUD.seCantidad : ''}"></form:input>
                <label for="seEjercicio">Ej:</label>
                <form:input path="seEjercicio" id="seEjercicio" value="${filtroCRUD.seEjercicio != null ? filtroCRUD.seEjercicio : ''}"></form:input>
                <%
                    }else{
                %>Sin Sesiones
                <%
                    }
                %>

            </td>
        </tr>
       <tr>
           <td>
               <%if(filtro.getIfComida()){

               %>
               <table>
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
                   <td><a href="/admin/modificarCRUD?id=<%=comida.getId()%>&tipo=<%=comida.getTipo()%>">Modificar</a></td>
                   <td><a href="/admin/eliminarCRUD?id=<%=comida.getId()%>&tipo=<%=comida.getTipo()%>">Eliminar</a></td>
               </tr>
               <%
                   }
                   %></table></td>
                   <%
                   }else{
                   %>Sin Comidas
           <%
               }
           %>


           <td>
               <%if(filtro.getIfEj()){

               %>
               <table>
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
                   <td><a href="/admin/modificarCRUD?id=<%=ejercicio.getId()%>&tipo=<%=ejercicio.getTipo()%>">Modificar</a></td>
                   <td><a href="/admin/eliminarCRUD?id=<%=ejercicio.getId()%>&tipo=<%=ejercicio.getTipo()%>">Eliminar</a></td>
               </tr>
                   <%
                       }
                   %></table></td>
                   <%
                   }else{
                   %>Sin Ejercicios
                   <%
               }
           %>
           <td>
               <%if(filtro.getIfSe()){

               %>
               <table>
               <tr>
                   <th>Repeticiones</th>
                   <th>Cantidad</th>
                   <th>Ejercicio</th>
               </tr>
               <%
                   for (FiltroCRUD sesion : sesiones) {
               %>
               <tr>
                   <td><%= sesion.getseRepeticiones() %></td>
                   <td><%= sesion.getseCantidad() %></td>
                   <td><%= sesion.getseEjercicio() %></td>
                   <td><a href="/admin/modificarCRUD?id=<%=sesion.getId()%>&tipo=<%=sesion.getTipo()%>">Modificar</a></td>
                   <td><a href="/admin/eliminarCRUD?id=<%=sesion.getId()%>&tipo=<%=sesion.getTipo()%>">Eliminar</a></td>
               </tr>
                   <%
                       }
                   %></table></td>
<%
}else{
%>Sin Sesiones
<%
    }
%>

       </tr>
        <input type="submit" value="Filtrar">
    </form:form>
</table>



</body>
</html>
