<%@ page import="es.uma.proyectogrupo18.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectogrupo18.dto.Cliente" %>
<%@ page import="es.uma.proyectogrupo18.dto.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 23/05/2024
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    String tipo = (String) request.getSession().getAttribute("tipo");
%>
<html>
<head>
    <title>Clientes</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<jsp:include page="navbarEntrenador.jsp"/>

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
                        <b>Rutina</b>
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
                        <%
                            if(c.getRutinaSemanal() == null){
                        %>
                            <td>
                                Sin rutina
                            </td>
                            <td>
                                <a href="asignar?id=<%= u.getId()%>" style="margin-left: 25px"><button style="padding: 10px 15px">Asignar Rutina</button></a>
                            </td>
                            <%
                                }else{
                            %>
                                <td>
                                    <%= c.getRutinaSemanal().getNombre()%>
                                </td>
                                <td>
                                    <a href="ver?id=<%= u.getId()%> " style="margin-left: 25px" ><button style="padding: 10px 15px;">Ver Rutina</button></a>

                                    <a href="desasignar?id=<%= u.getId()%>" style="margin-left: 25px"><button style="padding: 10px 15px">Eliminar Rutina</button></a>

                                    <a href="seguimiento?id=<%= u.getId()%>" type="disabled" style="margin-left: 25px"><button style="padding: 10px 15px;">Seguimiento</button></a>
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
    <a href="/<%=tipo%>/"><button>Volver</button></a>
</div>

</body>
</html>
