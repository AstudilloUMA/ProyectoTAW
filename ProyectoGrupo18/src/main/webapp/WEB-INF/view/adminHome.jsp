<!--
Autores:
Juan Manuel Porcuna Martín 50%
Pablo Astudillo Fraga 50%
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/opcionesAdmin.css">
</head>
<body>

    <jsp:include page="navbarAdmin.jsp"/>

    <div class="opcionesAdmin">
        <table>
            <tr>
                <td>
                    <a href="/admin/Usuarios" class="button"><button>Administración de Usuarios</button></a>
                </td>
                <td>
                    <a href="/admin/asignarLista" class="button"><button>Asociar entrenador/dietista</button></a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/admin/autenticar" class="button"><button>Autenticar Usuario</button></a>
                </td>
                <td>
                    <a href="/admin/ListaCRUD" class="button"><button>Administración de Ejercicios, Alimentos...</button></a>
                </td>
            </tr>
        </table>
    </div>

</body>
</html>

