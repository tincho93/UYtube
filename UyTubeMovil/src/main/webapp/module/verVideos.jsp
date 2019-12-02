<%@ page import="java.util.List" %>
<%@ page import="publicadores.DtUsuarioWeb" %>
<%@ page import="publicadores.DtElementoWeb" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="interfaces.IUsuario" %>
<%@ page import="interfaces.UFactory" %>
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

        <%
          String query = request.getParameter("q");
          Integer cantRes = 0;

          List<DtElementoWeb> listaV = new ArrayList<DtElementoWeb>();

          if (query == null){
            query = "";
          }
          listaV = portVideo.busqueda(query,false).getItem();
          cantRes =listaV.size();
        %>

        <form name="buscar" action="verVideos.jsp" method="get">
          <div class="row justify-content-center">
            <div class="col-xl-10 order-xl-1">
              <!-- inicio de filtrado -->
              <div class="card bg-secondary shadow ">
                <div class="card-body px-lg-5 py-lg-5">
                  <h3>Videos</h3>
                  <div class="d-inline-flex" style="width: 100%">
                    <div class="input-group">
                      <input type="text" name="q" class="form-control" placeholder="Buscar..." value="<%=query%>">
                      <div class="input-group-append">
                        <button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
                      </div>
                    </div>
                  </div>
                  <br/>

                  <hr>
                  <!-- inicio de resultados -->
                  <div class="d-sm-inline-flex row" style="width: 100%">
                    <div class="col-sm">
                      <h3><%=cantRes%> resultados</h3>
                    </div>
                    <div class="container-fluid">
                      <div class="col col- justify-content-left">
                        <!-- Listado de Videos -->
                        <%
                          if ( listaV.size() == 0 ){
                        %>
                        <div class="alert alert-info" role="alert">
                          No hay videos
                        </div>
                        <%
                          }
                          for (DtElementoWeb v: listaV){
                        %>
                        <div class="card mb-3" style="max-width: 630px;">
                          <a href="<%= request.getContextPath() %>/module/visualizarVideo.jsp?u=<%=v.getNickname()%>&v=<%=v.getNombreE()%>">
                            <div class="row no-gutters">
                              <div class="col-md-4">
                                <img src="http://img.youtube.com/vi/<%=v.getUrl()%>/0.jpg" class="card-img" alt="...">
                              </div>
                              <div class="col-md-5">
                                <div class="card-body">
                                  <h5 class="card-title mb-0 text-lg"><%=v.getNombreE()%></h5>
                                  <p class="card-text"><small class="text-muted">Por <strong><%=v.getNickname()%></strong></small></p>
                                </div>
                              </div>
                            </div>
                          </a>
                        </div>
                        <%}%>
                        <!-- Fin listado de Videos -->
                      </div>


                    </div>

                  </div>
                  <!-- fin de resultados -->
                </div>
              </div>
            </div>
          </div>
        </form>


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