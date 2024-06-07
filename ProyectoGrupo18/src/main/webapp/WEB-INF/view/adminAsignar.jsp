<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    List<UsuarioEntity> entrenadores = (List<UsuarioEntity>) request.getAttribute("entrenadores");
    List<UsuarioEntity> dietistas = (List<UsuarioEntity>) request.getAttribute("diestistas");

%>
<html>
<head>
    <title>Asignar trabajador</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Asignar Entrenador/Dietista</h1>
<form method="post" action="/admin/autenticado">
    <input type="hidden" name="id" value="<%= cliente.getUsuarioId()%>">
    <table border="0">
        <tr>
            <td>Entrenador:
                <select name="entrenador">
                    <option value="Sin Asignar">Sin Asignar</option>
                    <%
                        for (UsuarioEntity entrenador : entrenadores) {
                            String seleccionado = "";
                            if (entrenador.equals(cliente.getEntrenador().getUsuarioByUsuarioId())) {
                                seleccionado = "selected";
                            }
                    %>
                    <option value="<%=entrenador.getId() %>" <%=seleccionado %>>
                        <%=entrenador.getNombre() %> <%=entrenador.getApellidos() %> -
                        <%=entrenador.getTrabajadorById().getRolId() == 1 ? "Dietista" :
                                entrenador.getTrabajadorById().getRolId() == 2 ? "Entrenador Cross-training" :
                                        "Entrenador Bodybuilding" %>
                    </option>
                    <%
                        }
                    %>
                </select>

            </td>
            <td>Dietista:
                <select name="dietista">
                    <option value="Sin Asignar">Sin Asignar</option>
                    <%
                        for (UsuarioEntity dietista: dietistas) {
                            String seleccionado = "";
                            if (dietista.equals(cliente.getDietista().getUsuarioByUsuarioId())) {
                                seleccionado = "selected";
                            }
                    %>
                    <option value="<%=dietista %>"  <%=seleccionado %>  ><%=dietista.getNombre()%> <%=dietista.getApellidos()%></option>

                    <%
                        }
                    %>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form>


</body>
</html>
