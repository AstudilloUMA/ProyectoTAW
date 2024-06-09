<%--
Autor:
Miguel Sánchez Hontoria:100%
--%>

<%@ page import="es.uma.proyectogrupo18.entity.DietaEntity" %>
<%@ page import="es.uma.proyectogrupo18.entity.ComidaEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ComidaEntity> comidas = (List<ComidaEntity>) request.getAttribute("comidas");
    Integer id = (Integer) session.getAttribute("usuarioid");
    DietaEntity dieta = (DietaEntity) request.getAttribute("dieta");
%>
<html>
<head>
    <title>Dieta</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <style>
        .comidas-columnas {
            display: block;
            column-count: 5;
            gap: 5px;
        }
    </style>
</head>
<body>

<jsp:include page="navbarDietista.jsp"/>

<%if(dieta.getNumComidas() != null){%>
<div class="advise">
    <h1>Modificar dieta<%=dieta.getNombre()%></h1>
</div>
<%}else{%>
<div class="advise">
    <h1>Crear dieta</h1>
</div>
<%}%>

<form method="post" action="/dietista/guardar">

    <div class="rutinas">
        <input type="hidden" name="id" value="<%=id%>">
        <input type="hidden" name="id2" value="<%=dieta.getId()%>">
        <b>Nombre:</b><input value="<%=dieta.getNombre()%>" name="nombre" maxlength="20" size="20" type="text"/>
        <b>NºComidas:</b><input value="<%=dieta.getNumComidas()%>" name="numComidas" maxlength="1" size="1" type="number"/>
        <b>Tipo:</b><input value="<%=dieta.getTipo()%>" name="tipo" maxlength="20" size="20" type="text"/>
        <b>FechaInicio:</b><input value="<%=dieta.getFechaInicio()%>" name="fechaInicio" maxlength="10" size="10" type="date"/>
        <b>FechaFin:</b><input value="<%=dieta.getFechaFin()%>" name="fechaFin" maxlength="10" size="10" type="date"/>
        <br/>
        <br/>
        <b>Comidas(seleccione las que estarán en la dieta):</b>
        <br/>
        <br/>
        <div class="comidas-columnas">
            <%for(ComidaEntity c : comidas){
                String esta = "";
                if(dieta.getComidas().contains(c)){
                    esta ="checked";
                }
            %>
                <input type="checkbox" name="comid" <%= esta %> value="<%=c.getId()%>"/> <%=c.getNombre()%><br/>

            <%
                }
            %>
        </div>
        <br/>
        <button>Guardar</button>
    </div>

</form>

<div style="text-align: center">
    <a href="/dietista/info?id=<%=id%>"><button>Atrás</button></a>
</div>

</body>
</html>