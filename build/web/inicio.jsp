<%-- 

    Document   : inicio
    Created on : 26/11/2016, 10:56:22
    Author     : lki
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog</title>
        <style>
            .espacio {
                margin-bottom: 2%;
            }
        </style>
    </head>
    <body>
          <p style="text-align: center;font-size:150%;">
            <c:out value="Bienvenido al blog ${usuarioSesion.nombre}">Usted no esta registrado</c:out>
            <hr/>
            </p>


        <c:forEach items="${listaDePublicaciones}" var="publicacion">
            <div class="panel panel-primary" >

                <div class="panel-body">
                    <b class="">Titulo de la publicacion: ${publicacion.titulo}</b>

                    <p>${publicacion.descripcion}</p>
                </div>
                <div class="panel-footer">
                    <strong>Fecha de publicacion: ${publicacion.fecha}</strong>
                </div>
                <hr>
            </div>
            <div class="espacio"></div>
        </c:forEach>

    </body>
</html>
