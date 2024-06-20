<%--
Autores:
Miguel Sánchez Hontoria:50%
Pablo Astudillo Fraga:50%
--%>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.dto.Cliente" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
%>
<html>
<head>
    <title>Clientes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<jsp:include page="navbarDietista.jsp"/>

<div class="advise">
    <h1>Clientes</h1>
</div>

<div style="text-align: center">
    <%
        if(clientes.isEmpty() || clientes == null){
    %>
    <h2>No hay clientes</h2>
    <%
    }else{
    %>
    <div class="rutinas">
        <table>
            <tr style="background-color: #222">
                <td>
                    <b>Nombre</b>
                </td>
                <td>
                    <b>Apellidos</b>
                </td>
                <td>
                    <b>DNI</b>
                </td>
                <td>
                    <b>Peso</b>
                </td>
                <td>
                    <b>Altura</b>
                </td>
                <td>
                    <b>Edad</b>
                </td>
                <td>
                    <b>Dieta</b>
                </td>
                <td></td>
            </tr>
            <%
                for(Cliente c : clientes){
                    Usuario u = c.getUsuario();
            %>
            <tr>
                <td>
                    <%= u.getNombre()%>
                </td>
                <td>
                    <%= u.getApellidos()%>
                </td>
                <td>
                    <%= u.getDni()%>
                </td>
                <td>
                    <%= c.getPeso()%>
                </td>
                <td>
                    <%= c.getAltura()%>
                </td>
                <td>
                    <%= c.getEdad()%>
                </td>
                <%
                    if(c.getDieta() == null || c.getDieta().getId() == null){
                %>
                <td>
                    Sin dieta
                </td>
                <td>
                    <a href="asignar?id=<%= u.getId()%>"><button style="padding: 10px 15px">Asignar Dieta</button></a>

                    <a href="desasignar?id=<%= u.getId()%>" type="disabled" style="margin-left: 25px"><button disabled style="padding: 10px 15px; opacity: 60%">Eliminar Dieta</button></a>

                    <a href="seguimiento?id=<%= u.getId()%>" type="disabled" style="margin-left: 25px"><button disabled style="padding: 10px 15px; opacity: 60%">Seguimiento</button></a>
                </td>
                <%
                }else{
                %>
                <td>
                    <%= c.getDieta().getNombre()%>
                </td>
                <td>
                    <a href="asignar?id=<%= u.getId()%>"><button disabled style="padding: 10px 15px; opacity: 60%">Asignar Dieta</button></a>

                    <a href="desasignar?id=<%= u.getId()%>&idDieta=<%=c.getDieta().getId()%>" type="disabled" style="margin-left: 25px"><button style="padding: 10px 15px">Eliminar Dieta</button></a>

                    <a href="seguimiento?id=<%= u.getId()%>&idDieta=<%=c.getDieta().getId()%>" type="disabled" style="margin-left: 25px"><button style="padding: 10px 15px;">Seguimiento</button></a>
                </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        }
    %>
    <a href="/dietista/"><button>Volver</button></a>
</div>

</body>
</html>
