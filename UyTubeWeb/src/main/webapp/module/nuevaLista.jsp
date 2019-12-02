<%@ page import="java.util.List" %>
<%@ page import="publicadores.DtUsuarioWeb" %>

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
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    HttpSession s = request.getSession();
    DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
    //WEBSERVICES
    publicadores.CListaRepPublishService serviceListaRep = new publicadores.CListaRepPublishService();
    publicadores.CListaRepPublish portL = serviceListaRep.getCListaRepPublishPort();

    publicadores.CCategoriaPublishService serviceCategoria = new publicadores.CCategoriaPublishService();
    publicadores.CCategoriaPublish portC = serviceCategoria.getCCategoriaPublishPort();
    //FIN WEBSERVICES
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>
        UyTube
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

<body class="">
<nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white" id="sidenav-main">
    <div class="container-fluid">
        <!-- Toggler -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#sidenav-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!-- Brand -->
        <a class="navbar-brand pt-0" href="<%= request.getContextPath() %>/index.jsp">
            <img src="<%= request.getContextPath() %>/assets/img/brand/logo.png" class="navbar-brand-img" alt="...">
        </a>
        <!-- User -->
        <ul class="nav align-items-center d-md-none">
        <% if (s.getAttribute("usuario") == null){ %>
        <li class="nav-item">
          <a class="nav-link nav-link-icon" href="<%= request.getContextPath() %>/module/iniciarSesion.jsp">
            <i class="fas fa-sign-in-alt"></i>
            <span class="nav-link-inner--text">Entrar</span>
          </a>
        </li>
        <% }else { ;%>
        <li class="nav-item dropdown">
          <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <div class="media align-items-center">
               <span class="avatar avatar-sm rounded-circle">
                  <% if (usr.getFoto().equals("src/main/resources/img/default.png")) {%>
                  <img alt="Image placeholder" src="<%= request.getContextPath() %>/img/default.png">
                  <% } else { %>
                  <img alt="Image placeholder" src="<%= request.getContextPath() %>/<%=usr.getFoto()%>">
                  <% } %>
                </span>
            </div>
          </a>
          <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
            <div class=" dropdown-header noti-title">
              <h6 class="text-overflow m-0">Bienvenido</h6>
            </div>
            <a href=""<%= request.getContextPath() %>/module/miPerfil.jsp" class="dropdown-item">
              <i class="ni ni-single-02"></i>
              <span>Mi perfil</span>
            </a>
            <div class="dropdown-divider"></div>
            <a href=""<%= request.getContextPath() %>/CerrarSesion" class="dropdown-item">
              <i class="ni ni-user-run"></i>
              <span>Cerrar sesion</span>
            </a>
          </div>
        </li>
      <% } %>
      </ul>
        <!-- Collapse -->
        <div class="collapse navbar-collapse" id="sidenav-collapse-main">
            <!-- Collapse header -->
            <div class="navbar-collapse-header d-md-none">
                <div class="row">
                    <div class="col-6 collapse-brand">
                        <a href="<%= request.getContextPath() %>/index.jsp">
                            <img src="<%= request.getContextPath() %>/assets/img/brand/logo.png">
                        </a>
                    </div>
                    <div class="col-6 collapse-close">
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#sidenav-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle sidenav">
                            <span></span>
                            <span></span>
                        </button>
                    </div>
                </div>
            </div>
            <!-- Form -->
            <form class="mt-4 mb-3 d-md-none" action="<%= request.getContextPath() %>/module/buscar.jsp" method="get">
                <div class="input-group input-group-rounded input-group-merge" >
                    <input type="search" class="form-control form-control-rounded form-control-prepended" placeholder="Buscar..." aria-label="Search" name="q">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="fa fa-search"></span>
                        </div>
                    </div>
                </div>
            </form>
            <!-- Navigation -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link " href="<%= request.getContextPath() %>/module/verUsuarios.jsp">
                        <i class="ni ni-single-02 text-blue"></i> Ver usuarios
                    </a>
                </li>
            </ul>
            <!-- Divider -->
            <hr class="my-3">
            <!-- Heading -->
            <h6 class="navbar-heading text-muted">Videos</h6>
            <!-- Navigation -->
            <ul class="navbar-nav">
                <% if (s.getAttribute("usuario") != null){ %>
                <li class="nav-item">
                    <a class="nav-link " href="<%= request.getContextPath() %>/module/nuevoVideo.jsp">
                        <i class="ni ni-fat-add text-blue"></i> Subir video
                    </a>
                </li>
                <% } %>
                <li class="nav-item">
                    <a class="nav-link " href="<%= request.getContextPath() %>/module/verVideos.jsp">
                        <i class="ni ni-button-play text-blue"></i> Ver videos
                    </a>
                </li>
            </ul>
            <% if (s.getAttribute("usuario") != null){ %>
            <!-- Divider -->
            <hr class="my-3">
            <!-- Heading -->
            <h6 class="navbar-heading text-muted">Listas de Reproduccion</h6>
            <!-- Navigation -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="<%= request.getContextPath() %>/module/nuevaLista.jsp">
                        <i class="ni ni-fat-add text-blue"></i> <strong> Crear lista </strong>
                    </a>
                </li>
                <%
                    List<String> lis = portL.listarListasDeUsuario(usr.getNickname()).getItem();
                    for(String l: lis){ %>
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/module/consultaLista.jsp?id=<%=l%>">
                        <i class="ni ni-books text-blue"></i> <%=l%>
                    </a>
          </li>
          <% } %>
          <li class="nav-item">
            <a class="nav-link " href="<%= request.getContextPath() %>/module/favoritos.jsp">
              <i class="fas fa-star text-blue"></i> Mis Favoritos
            </a>
          </li>
        </ul>
        <% } %>
            <!-- Divider -->
            <hr class="my-3">
            <!-- Heading -->
            <h6 class="navbar-heading text-muted">Categorias</h6>
            <!-- Navigation -->
            <ul class="navbar-nav">
                <%  List<String> lC = portC.listarCategorias().getItem();
                    for(String cat: lC){ %>
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/module/consultaCategoria.jsp?id=<%=cat%>">
                        <i class="ni ni-books text-blue"></i> <%=cat%>
                    </a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
<div class="main-content">
    <!-- Navbar -->
    <nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
        <div class="container-fluid">

            <!-- Form - Buscador -->
            <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto"  action="<%= request.getContextPath() %>/module/buscar.jsp" method="get">
                <div class="form-group mb-0">
                    <div class="input-group input-group-alternative">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                        </div>
                        <input class="form-control" placeholder="Buscar" type="text" name="q">
                    </div>
                </div>
            </form>
            <!-- User -->
            <% if (s.getAttribute("usuario") == null){ %>
            <ul class="navbar-nav align-items-center d-none d-md-flex">
                <li class="nav-item">
                    <a class="nav-link nav-link-icon" href="<%= request.getContextPath() %>/module/registro.jsp">
                        <i class="ni ni-circle-08"></i>
                        <span class="nav-link-inner--text">Registrarse</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-icon" href="<%= request.getContextPath() %>/module/iniciarSesion.jsp">
                        <i class="ni ni-key-25"></i>
                        <span class="nav-link-inner--text">Iniciar sesion</span>
                    </a>
                </li>
            </ul>
            <% }else { %>
            <ul class="navbar-nav align-items-center d-none d-md-flex">
                <li class="nav-item dropdown">
                    <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <div class="media align-items-center">
                            <span class="avatar avatar-sm rounded-circle">
                              <% if (usr.getFoto().equals("src/main/resources/img/default.png")) {%>
                              <img alt="Image placeholder" src="<%= request.getContextPath() %>/img/default.png">
                              <% } else { %>
                              <img alt="Image placeholder" src="<%= request.getContextPath() %>/<%=usr.getFoto()%>">
                              <% } %>
                            </span>
                            <div class="media-body ml-2 d-none d-lg-block">
                                <span class="mb-0 text-sm  font-weight-bold">@<%=usr.getNickname() %></span>
                            </div>
                        </div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
                        <div class=" dropdown-header noti-title">
                            <h6 class="text-overflow m-0">Bienvenido</h6>
                        </div>
                        <a href="<%= request.getContextPath() %>/module/miPerfil.jsp" class="dropdown-item">
                            <i class="ni ni-single-02"></i>
                            <span>Mi perfil</span>
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="<%= request.getContextPath() %>/CerrarSesion" class="dropdown-item">
                            <i class="ni ni-user-run"></i>
                            <span>Cerrar sesion</span>
                        </a>
                    </div>
                </li>
            </ul>
            <% } %>
        </div>
    </nav>
    <!-- End Navbar -->
    <!-- Header -->
    <div class="header bg-gradient-primary pb-8 pt-5 pt-md-8">
        <div class="container-fluid">
            <div class="header-body">
                <!-- Contenido aqui TODO-->
                    <div class="row justify-content-center">
                        <div class="col-xl-10 order-xl-1">
                            <div class="card bg-secondary shadow ">
                                <div class="card-body px-lg-5 py-lg-5">
                                <form name="nuevaLista" id="nuevaLista" action="<%= request.getContextPath() %>/CrearLista" method="post">
                                    <div class="text-muted text-center mt-2 mb-3">
                                        <h1>Crear Lista de Reproduccion</h1>
                                    </div>
                                    <div class="container">
                                        <%
                                            String message = (String) request.getAttribute("message");
                                            if(message != null){
                                                %>
<%--                                                <div class="alert alert-danger" role="alert">--%>
<%--                                                    <strong>Error</strong> Nombre de Lista repetido--%>
<%--                                                </div>--%>
                                            <%}%>
                                            <div id="grupo-nombre" class="form-group">
                                                <input type="text" name="nomList" id="nomList" class="form-control" placeholder="Nombre de Lista" autocomplete="off" onkeyup="validarNom()">
                                            </div>
                                        <div class="form-group">
                                            <select class="custom-select" id="inputGroupSelect01" name="categoria" >
                                                <option value="" checked> --Sin Categoria-- </option>
                                                <%
                                                    for(String cat: lC){ %>
                                                <option value="<%=cat%>" ><%=cat%></option>
                                                <% } %>
                                            </select>
                                        </div>

                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="esPublica" value="" id="defaultCheck1">
                                            <label class="form-check-label" for="defaultCheck1">
                                                Lista publica
                                            </label>
                                        </div>

                                        <%--Mostrar mensaje de falta de datos para crear video--%>
                                        <div id="mensaje-error" class="alert alert-danger d-none mt-3" role="alert">
                                            <!-- El texto del mensaje se genera en un script -->
                                        </div>
                                        <%--Fin mostrar mensaje de falta de datos para crear video--%>

                                        <div class="text-center">
                                            <button type="button" class="btn btn-primary my-4" onclick="continuar()">Crear Lista</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>


                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
            </div>
        </div>
    </div>

</div>
<!--   Core   -->
<script src="<%= request.getContextPath() %>/assets/js/plugins/jquery/dist/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<!--   Optional JS   -->
<script src="<%= request.getContextPath() %>/assets/js/plugins/chart.js/dist/Chart.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/plugins/chart.js/dist/Chart.extension.js"></script>
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
    var nomValido = false;

    function continuar() {
        $("#mensaje-error").addClass("d-none");
        let nomList = document.forms["nuevaLista"]["nomList"].value;
        if (nomList == "") {
            $("#mensaje-error").html('<strong>Error!</strong> La lista debe tener un nombre');
            $("#mensaje-error").removeClass("d-none");
            marcarCamposVacios(nomList);
        } else if (!nomValido) {
            $("#mensaje-error").html('<strong>Error!</strong> Ese nombre de lista ya existe');
            $("#mensaje-error").removeClass("d-none");
            marcarCamposVacios(nomList);
        }else {
            document.forms["nuevaLista"].submit();
        }
    }

    function quitarInvalido(arg){
        if(arg.value == ""){
            $(arg).addClass("is-invalid");
        }else {
            $(arg).removeClass("is-invalid");
        }
    }

    function marcarCamposVacios(nomList) {
        if (nomList == "") {
            $("#nomList").addClass("is-invalid");
        }
    }

    function validarNom() {
        let target = document.getElementById("nomList");
        if(target.value != ""){
            var url = "<%= request.getContextPath() %>/VerificarLista?nomList=" + encodeURIComponent(target.value);
            $.get(url, function (res) {
                if (res == "false") {
                    nomValido = true;
                    $("#nomList").removeClass("is-invalid");
                    $("#nomList").addClass("is-valid");
                    $("#grupo-nombre").removeClass("has-danger");
                    $("#grupo-nombre").addClass("has-success");
                } else {
                    nomValido = false;
                    $("#nomList").removeClass("is-valid");
                    $("#nomList").addClass("is-invalid");
                    $("#grupo-nombre").removeClass("has-success");
                    $("#grupo-nombre").addClass("has-danger");
                }
            });
        } else {
            //aca se tiene que poner en rojo
            nomValido = false;
            $("#nomList").removeClass("is-valid");
            $("#nomList").addClass("is-invalid");
            $("#grupo-nombre").removeClass("has-success");
            $("#grupo-nombre").addClass("has-danger");
        }
    }

</script>
</body>

</html>
