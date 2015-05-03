<%-- 
    Document   : noticias
    Created on : Apr 14, 2015, 1:20:10 AM
    Author     : vrebo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Noticias</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="shortcut icon" type="image/x-icon" href="imagenes/favicon.ico">
        <script src="js/websocket.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/header.jspf"/>
            <div class="container">
                <div class="row">
                    <c:forEach var="noticia" items="${listaNoticias}">
                        <div class="col-xs-12 col-sm-12 col-md-6">
                            <div class="thumbnail">
                                <!--<img src="http://lorempixel.com/400/200/sports/1" alt="">-->
                                <div class="caption">
                                    <h3>${noticia.titulo}</h3>
                                    <h4>Subtítlo de la noticia</h4>
                                    <br><br>
                                    <p>${noticia.contenido}</p>
                                    <h5>${noticia.autor} ${noticia.fechaPublicacion}</h5>
                                    <div>
                                        <a href="EditarNoticia?id_noticia=${noticia.id}" class="btn btn-primary">Editar</a>
                                        <a href="EliminarNoticia?id_noticia=${noticia.id}" class="btn btn-danger">Eliminar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-xs-12 col-sm-12 col-md-6">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>Título de la noticia</h3>
                                <h4>Subtítlo de la noticia</h4>
                                <p>Contenido de la noticia...
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quam ullam rerum, necessitatibus deleniti enim eveniet explicabo dolor vero unde impedit voluptas, quod quis quaerat nulla ut laudantium dignissimos, voluptatibus sunt.			Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quam ullam rerum, necessitatibus deleniti enim eveniet explicabo dolor vero unde impedit voluptas, quod quis quaerat nulla ut laudantium dignissimos, voluptatibus sunt.</p>
                                <h5>autor... y fecha de publicacion</h5>
                                <button class="btn btn-primary">Editar</button>
                                <a href="#delete" class="btn btn-danger" data-toggle="modal">Eliminar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>	
    </body>
</html>
