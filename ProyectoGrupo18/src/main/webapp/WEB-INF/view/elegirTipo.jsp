<%@ page import="es.uma.proyectogrupo18.entity.RutinaSemanalEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.TipoEjercicioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectogrupo18.dto.RutinaSemanal" %>
<%@ page import="es.uma.proyectogrupo18.dto.TipoEjercicio" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 08/06/2024
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%--
AUTOR --> Pablo Astudillo Fraga
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaSemanal rutina = (RutinaSemanal) request.getAttribute("rutina");
    List<TipoEjercicio> tipos = (List<TipoEjercicio>) request.getAttribute("tipos");

    String tipo = (String) request.getSession().getAttribute("tipo");
    String actionUrl;
    if("crosstrainer".equals(tipo)){
        actionUrl = "/crosstrainer/guardar";
    } else {
        actionUrl = "/bodybuilder/guardar";
    }
%>
<html>
<head>
    <title>Nueva Sesión</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<jsp:include page="navbarEntrenador.jsp"/>

<div class="advise">
    <h1><%=rutina.getNombre()%></h1>
</div>

    <div class="form-container">
        <h2 style="text-align: center">Selecciona el tipo de ejercicio a añadir</h2>
        <form method="post" action="elegido">
            <input type="hidden" name="id" value="<%=rutina.getId()%>">
            <select name="tipo">
                <%
                    for(TipoEjercicio t : tipos){
                %>
                <option value="<%=t.getId()%>"><%=t.getTipo()%></option>
                <%
                    }
                %>
            </select>
            <br/>
            <button>Continuar</button>
        </form>
        <a href="/<%=tipo%>/mostrar?id=<%=rutina.getId()%>"><button>Volver</button></a>
    </div>

</body>
</html>
