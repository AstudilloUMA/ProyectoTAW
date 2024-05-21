<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
    Integer id = (Integer) session.getAttribute("usuarioid");
%>
<html>
<head>
    <title>CrearDieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>

<h1>Crear Dieta</h1>

Código:<input type="number" maxlength="3" size="3" name="codigo"/>
NºComidas:<input type="number" maxlength="1" size="1" name="num_comidas"/>
Tipo:<input type="text" maxlength="1" size="1" name="num_comidas"/>
FechaInicio:<input type="text" maxlength="10" size="10" name="fecha_inicio"/>
FechaFin:<input type="text" maxlength="10" size="10" name="fecha_fin"/>
<a href="/dietista/guardar"><button>Crear Dieta</button></a>
<br/>

    Comidas (elige las comidas que quiere en la dieta):<br/>
    <%
        for(ComidaEntity c : comidas){
    %>
        <input type="checkbox" name="comida" value="<%=c.getId()%>>"/><%=c.getNombre()%><br/>
    <%
        }
    %>

</table>

    <a href="/dietista/volver?id=<%=id%>"><button>Atrás</button></a>

</body>
</html>
