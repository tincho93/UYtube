<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 13/09/2019
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

=========================================================
* Argon Dashboard - v1.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        UyTube - Iniciar sesion
    </title>
    <!-- Favicon -->
    <link href="<%= request.getContextPath() %>/assets/img/brand/favicon.png" rel="icon" type="image/png">
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
    <!-- Icons -->
    <link href="<%= request.getContextPath() %>/assets/js/plugins/nucleo/css/nucleo.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/assets/js/plugins/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet" />
    <!-- CSS Files -->
    <link href="<%= request.getContextPath() %>/assets/css/argon-dashboard.css?v=1.1.0" rel="stylesheet" />
</head>

<body class="bg-default">
<div class="main-content">
    <!-- Navbar -->
    <nav class="navbar navbar-top navbar-horizontal navbar-expand-md navbar-dark">
        <div class="container px-4">
            <a class="navbar-brand" href="#">
                <img src="<%= request.getContextPath() %>/assets/img/brand/logo.png" />
            </a>
        </div>
    </nav>
    <!-- Header -->
    <div class="header bg-gradient-primary py-7 py-lg-8">
        <div class="separator separator-bottom separator-skew zindex-100">
            <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
                <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
            </svg>
        </div>
    </div>
    <!-- Page content -->
    <div class="container mt--8 pb-5">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-7">
                <div class="card bg-secondary shadow border-0">
                    <div class="card-body px-lg-5 py-lg-5">
                        <form role="form" action="<%= request.getContextPath() %>/IniciarSesion" method="post" name="login">
                            <div class="form-group mb-3">
                                <div class="text-muted text-center mt-2 mb-3">
                                    <h1>Iniciar sesion</h1>
                                </div>
                                <%
                                    String message = (String) request.getAttribute("message");
                                    if(message != null){
                                %>
                                <div class="alert alert-danger" role="alert">
                                    <%=message%>
                                </div>
                                <%}%>
                                <% String nickname = (request.getParameter("nick") != null) ? request.getParameter("nick") : ""; %>
                                <div class="input-group input-group-alternative">

                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="ni ni-email-83"></i></span>
                                    </div>
                                    <input class="form-control" placeholder="Nickname o email" type="email" name="nick" value="<%=nickname%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group input-group-alternative">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                                    </div>
                                    <input class="form-control" placeholder="Contrasena" type="password" name="pass">
                                </div>
                            </div>
                            <div class="text-muted text-center mt-2 mb-3">
                                <div class="custom-control custom-control-alternative custom-checkbox mb-3">
                                    <input class="custom-control-input" id="customCheck5" type="checkbox" name="recordar" >
                                    <label class="custom-control-label" for="customCheck5">Recordarme</label>
                                </div>
                            </div>
                            <div id="mensaje-error" class="alert alert-danger d-none" role="alert">
                                <!-- El texto del mensaje se genera en un script -->
                            </div>
                            <div class="text-center">
                                <button type="button" class="btn btn-primary my-4" onclick="continuar()">Iniciar Sesion</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="py-5">
        <div class="container">

        </div>
    </footer>
</div>
<!--   Core   -->
<script src="<%= request.getContextPath() %>/assets/js/plugins/jquery/dist/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<!--   Optional JS   -->
<!--   Argon JS   -->
<script src="<%= request.getContextPath() %>/assets/js/argon-dashboard.min.js?v=1.1.0"></script>
<script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
<script>
    window.TrackJS &&
    TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
    });
</script>

<script type="text/javascript">
    function continuar() {
        $("#mensaje-error").addClass("d-none");
        var nick = document.forms["login"]["nick"].value;
        var pass = document.forms["login"]["pass"].value;
        if(nick == "" || pass == ""){
            $("#mensaje-error").html('<strong>Error!</strong> Falta completar algun campo');
            $("#mensaje-error").removeClass("d-none");
        }else {
            document.forms[0].submit();
        }

    }
</script>

</body>

</html>