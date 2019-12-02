<%@ page import="java.util.List" %>
<%@ page import="publicadores.DtUsuarioWeb" %>
<%@ page import="publicadores.DtElementoWeb" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="interfaces.IUsuario" %>
<%@ page import="interfaces.UFactory" %>
<%@ page import="publicadores.DtListaRep" %>
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
    publicadores.CVideoPublishService serviceVideo = new publicadores.CVideoPublishService();
    publicadores.CVideoPublish portVideo = serviceVideo.getCVideoPublishPort();

    publicadores.CListaRepPublishService serviceListaRep = new publicadores.CListaRepPublishService();
    publicadores.CListaRepPublish portListaRep = serviceListaRep.getCListaRepPublishPort();

    publicadores.CCategoriaPublishService serviceCategoria = new publicadores.CCategoriaPublishService();
    publicadores.CCategoriaPublish portCategoria = serviceCategoria.getCCategoriaPublishPort();
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
        <!-- User  Movil-->
        <ul class="nav align-items-center d-md-none">
            <% if (s.getAttribute("usuario") != null){ %>
            <li class="nav-item dropdown">
                <div class="media align-items-center">
             <span class="avatar avatar-sm rounded-circle">
                <% if (usr.getFoto().equals("src/main/resources/img/default.png")) {%>
                <img alt="Image placeholder" src="<%= request.getContextPath() %>/img/default.png">
                <% } else { %>
                <img alt="Image placeholder" src="<%= request.getContextPath() %>/<%=usr.getFoto()%>">
                <% } %>
              </span>
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
            <!-- Navigation -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link " href="<%= request.getContextPath() %>/module/verVideos.jsp">
                        <i class="ni ni-button-play text-blue"></i> Ver videos
                    </a>
                </li>
            </ul>
            <ul  class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/module/verListas.jsp">
                        <i class="ni ni-books text-blue"></i> Ver Listas
                    </a>
                </li>
            </ul>
            <ul  class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link " href="<%= request.getContextPath() %>/module/favoritos.jsp">
                        <i class="fas fa-star text-blue"></i> Mis Favoritos
                    </a>
                </li>
            </ul>
            <!-- Divider -->
            <hr class="my-3">
            <ul  class="navbar-nav">
                <li class="nav-item">
                    <a href="<%= request.getContextPath() %>/CerrarSesion" class="nav-link ">
                        <i class="ni ni-user-run text-blue"></i> Cerrar sesion
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main-content">
    <!-- Navbar -->
    <nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
        <div class="container-fluid">

            <!-- Form - Buscador -->
            <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto" action="<%= request.getContextPath() %>/module/verVideos.jsp" method="get">
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
            <% }else {%>
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
                <!-- Contenido aqui -------------------------------------------------------------------------------------------------------------------------------->
                <div class="card shadow">
                    <div class="card-body">
                        <%--            empieza contenido de la tab de videos--%>
                        <%
                            String lista = request.getParameter("id");
                            String usuario = request.getParameter("u");
                            if(usuario == null && usr != null){
                                usuario = usr.getNickname();
                            }
                            if(usuario == null){
                                response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
                            } else {
                                portListaRep.setuList(usuario);
                                List<DtElementoWeb> videoLista = portListaRep.listarVideosListaWeb(lista).getItem();
                                DtListaRep infoLista = portListaRep.obtenerListaDeUsuario(lista);
                        %>
                        <div class="container-fluid">
                            <div class="col col- ">
                                <div class="row">
                                    <div class="col">
                                        <h1><%=lista%></h1>
                                    </div>
                                    <div class="col col-md-6">
                                        <%--                                    PARA MOSTRAR SI ES PUBLICA--%>
                                        <% if (infoLista.isPublico()){%>
                                        <i class="fas fa-globe"></i><small> Publico</small>
                                        <%} else {%>
                                        <i class="fas fa-user-lock"></i><small> Privado</small>
                                        <%}%>
                                        <%--PARA MOSTRAR LA CATEGORIA--%>
                                        <% if(infoLista.isEsParticular()){ %>
                                        <% if (infoLista.getCategoria().isEmpty()){%>
                                        <span class="badge badge-pill badge-primary">Sin categoria</span>
                                        <%}else{%>
                                        <span class="badge badge-pill badge-primary"><%=infoLista.getCategoria()%></span>
                                        <%}%>
                                        <%}%>
                                    </div>
                                </div>

                                <br><br>
                                <%
                                    if(!videoLista.isEmpty()){
                                        for(DtElementoWeb vl: videoLista){
                                %>
                                <div class="card mb-3" style="max-width: 630px;">

                                    <div class="row no-gutters">
                                        <div class="col-md-4">
                                            <a href="<%= request.getContextPath() %>/module/visualizarVideo.jsp?u=<%=vl.getNickname()%>&v=<%=vl.getNombreE()%>">
                                                <img src="http://img.youtube.com/vi/<%=vl.getUrl()%>/0.jpg" class="card-img" alt="..." href="<%= request.getContextPath() %>/module/consultaVideo.jsp?nomvVid=<%=vl.getNombreE()%>">
                                            </a>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="card-body">
                                                <h5 class="card-title mb-0 text-lg"><%=vl.getNombreE()%> </h5>
                                                <br>
                                                <p class="card-text"><small class="text-muted">Uploaded by: <strong><%=vl.getNickname()%></strong></small></p>
                                                <%--                                            <button type="button" class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="right" title="Quitar video" onclick="quitarVideoLista()"><i class="far fa-trash-alt"></i></button>--%>

                                                <%if(usr != null && usuario.equals(usr.getNickname())){%>
                                                <!-- Button trigger modal -->
                                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#exampleModal">
                                                    <i class="far fa-trash-alt"></i>
                                                </button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel"><strong>Esta seguro que desea quitar el video de la lista <%=lista%>? </strong></h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                                <a class="btn btn-primary" href="<%= request.getContextPath() %>/QuitarVideodeLista?uv=<%=vl.getNickname()%>&v=<%=vl.getNombreE()%>&l=<%=lista%>">Confirmar</a>
                                                                <%
                                                                    String message = (String) request.getAttribute("message");
                                                                %>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%}%>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <% } %>
                                <% } %>
                            </div>
                        </div>
                        <%}%>


                    </div>
                </div>
                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                <!-- Fin contenido -------------------------------------------------------------------------------------------------------------------------------->
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
</body>

</html>