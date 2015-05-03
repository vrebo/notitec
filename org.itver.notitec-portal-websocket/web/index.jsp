<%-- 
    Document   : index
    Created on : Apr 22, 2015, 1:58:20 AM
    Author     : vrebo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Sitver</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="shortcut icon" type="image/x-icon" href="imagenes/favicon.ico">
    </head>
    <body>
        <div class="container-fluid">

            <div class="row">
                <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                    <div class="form-group">
                        <img src="imagenes/logo5.png" class="logotipo" width="150" alt="Logotipo de SitVer">
                    </div>
                    
                    <form action="IniciarSesion" method="POST">
                        <div class="form-group input-group">
                            <span class="input-group-addon">Usuarios:</span>
                            <input id="usuario" name="usuario" type="text" class="form-control" placeholder="Número de control" name="user" required autofocus>
                        </div>

                        <div class="form-group input-group">
                            <span class="input-group-addon">Contraseña:</span>
                            <input id="contrasenia" name="contrasenia" type="password" class="form-control" placeholder="Contraseña" name="pass" required>
                        </div>

                        <div class="form-group checkbox">
                            <label> <input type="checkbox" >Recordar</label>
                        </div>

                        <div class="form-group col-md-4 col-md-offset-4">
                            <input type="submit" class="login btn btn-primary" value="Iniciar Sesión">
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>	
    </body>
</html>