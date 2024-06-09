<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.ui.FiltroCRUD" %>
<%@ page import="es.uma.proyectogrupo18.entity.TipoEjercicioEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.EjercicioEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    FiltroCRUD filtro = (FiltroCRUD) request.getAttribute("filtroMod");
    List<TipoEjercicioEntity> tiposEj = (List<TipoEjercicioEntity>) request.getAttribute("tiposEj");
    List<EjercicioEntity> ejercicios = (List<EjercicioEntity>) request.getAttribute("ejercicios");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<!--
Autor:
Juan Manuel Porcuna Martín
-->
<form method="post" action="/admin/guardarCRUD">
    <input type="hidden" name="id" value="<%= filtro.getId()==null?-1:filtro.getId() %>">
    <input type="hidden" name="tipo" value="<%= filtro.getTipo() %>">
    <%
        if(filtro.getTipo() == 1){
            // comida
    %>
    <h1>Comida:</h1>
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="ComidaName" size="30" maxlength="30" value="<%= filtro.getComidaNombre() != null ? filtro.getComidaNombre() : "" %>" /></td>
        </tr>
        <tr>
            <td>KCal totales:</td>
            <td><input type="number" name="ComidaCal" size="5" maxlength="5" value="<%= filtro.getComidaCalorias() != null ? filtro.getComidaCalorias() : "" %>"/></td>
        </tr>
        <tr>
            <td colspan="2"><button>Enviar</button></td>
        </tr>
    </table>
    <%
    } else if (filtro.getTipo() == 2) {
        // ejercicio
    %>
    <h1>Ejercicio:</h1>
    <table border="0">
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="EjName" size="30" maxlength="30" value="<%= filtro.getejNombre() != null ? filtro.getejNombre() : "" %>" /></td>
        </tr>
        <tr>
            <td>Tipo:</td>
            <td><select name="EjTipo">
                <%
                    for (TipoEjercicioEntity tipoEj : tiposEj) {
                        String seleccionado = "";
                        if (tipoEj.getTipo().equals(filtro.getejTipo())) {
                            seleccionado = "selected";
                        }
                %>
                <option value="<%= tipoEj.getTipo() %>" <%= seleccionado %>><%= tipoEj.getTipo() %></option>
                <%
                    }
                %>
            </select></td>
        </tr>
        <tr>
            <td colspan="2"><button>Enviar</button></td>
        </tr>
    </table>
    <%
    } else if (filtro.getTipo() == 3) {
        // sesion
    %>
    <h1>Sesión de ejercicio</h1>
    <table border="0">
        <tr>
            <td>Repeticiones:</td>
            <td><input type="text" name="SeRep" size="5" maxlength="5" value="<%= filtro.getseRepeticiones() != null ? filtro.getseRepeticiones() : "" %>"/></td>
        </tr>
        <tr>
            <td>Cantidad:</td>
            <td><input type="number" name="SeCan" size="5" maxlength="5" value="<%= filtro.getseCantidad() != null ? filtro.getseCantidad() : "" %>" /></td>
        </tr>
        <tr>
            <td>Ejercicio:</td>
            <td><select name="SeEj">
                <%
                    for (EjercicioEntity ej : ejercicios) {
                        String seleccionado = "";
                        if (ej.getNombre().equals(filtro.getseEjercicio())) {
                            seleccionado = "selected";
                        }
                %>
                <option value="<%= ej.getId() %>" <%= seleccionado %>><%= ej.getNombre() %></option>
                <%
                    }
                %>
            </select></td>
        </tr>
        <tr>
            <td colspan="2"><button>Enviar</button></td>
        </tr>
    </table>
    <%
        }
    %>
</form>
</body>
</html>
