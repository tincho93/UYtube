<%@ page import="java.util.List" %>
<%@ page import="publicadores.DtUsuarioWeb" %>
<%@ page import="publicadores.DtElementoWeb" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="publicadores.DtCanalWeb" %>

<!--   Core   -->
<script src="<%= request.getContextPath() %>/assets/js/plugins/jquery/dist/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<!--   Optional JS   -->
<script src="<%= request.getContextPath() %>/assets/js/plugins/chart.js/dist/Chart.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/plugins/chart.js/dist/Chart.extension.js"></script>
<!--   Argon JS   -->
<script src="<%= request.getContextPath() %>/assets/js/argon-dashboard.min.js?v=1.1.0"></script>
<script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
    publicadores.CUsuarioPublishService serviceUsuario = new publicadores.CUsuarioPublishService();
    publicadores.CUsuarioPublish portUsuario = serviceUsuario.getCUsuarioPublishPort();

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
      <!-- User -->
      <ul class="nav align-items-center d-md-none">
        <% if (s.getAttribute("usuario") == null){ %>
        <li class="nav-item">
          <a class="nav-link nav-link-icon" href="<%= request.getContextPath() %>/module/iniciarSesion.jsp">
            <i class="fas fa-sign-in-alt"></i>
            <span class="nav-link-inner--text">Entrar</span>
          </a>
        </li>
        <% }else {
          usr = (DtUsuarioWeb) s.getAttribute("usuario");%>
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
            <a class="nav-link" href="<%= request.getContextPath() %>/module/nuevoVideo.jsp">
              <i class="ni ni-fat-add text-blue"></i> Subir video
            </a>
          </li>
          <% } %>
          <li class="nav-item">
            <a class="nav-link  active" href="<%= request.getContextPath() %>/module/verVideos.jsp">
              <i class="ni ni-button-play text-blue"></i> <strong> Ver videos </strong>
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
            <a class="nav-link " href="<%= request.getContextPath() %>/module/nuevaLista.jsp">
              <i class="ni ni-fat-add text-blue"></i> Crear lista
            </a>
          </li>
          <%
            List<String> lis = portListaRep.listarListasDeUsuario(usr.getNickname()).getItem();
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
          <%
            List<String> lC = portCategoria.listarCategorias().getItem();
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
            Boolean mostrarVid = false;
            Boolean mostrarLis = false;
            Boolean mostrarCan = false;
            Boolean ordFecha = false;
            Integer cantRes = 0;

            List<DtCanalWeb> listaC = new ArrayList<DtCanalWeb>();
            List<DtElementoWeb> listaV = new ArrayList<DtElementoWeb>();
            List<DtElementoWeb> listaL = new ArrayList<DtElementoWeb>();

            if (query == null){
              query = "";
            }
            if (request.getParameter("canales") == null && request.getParameter("videos") == null && request.getParameter("listas") == null ){
              mostrarVid = mostrarLis = mostrarCan = true;
            }else {
              mostrarCan = request.getParameter("canales") != null;
              mostrarVid = request.getParameter("videos") != null;
              mostrarLis = request.getParameter("listas") != null;
            }

            ordFecha = request.getParameter("orden") != null && request.getParameter("orden").equals("f");

            if(mostrarCan){
              listaC = portUsuario.busqueda(query, ordFecha).getItem();
              cantRes = cantRes + listaC.size();
            }
            if(mostrarVid){
              listaV = portVideo.busqueda(query,ordFecha).getItem();
              cantRes = cantRes + listaV.size();
            }
            if(mostrarLis){
              listaL =  portListaRep.busqueda(query, ordFecha).getItem();
              cantRes = cantRes + listaL.size();
            }

          %>

          <form name="buscar" action="buscar.jsp" method="get">
          <div class="row justify-content-center">
            <div class="col-xl-10 order-xl-1">
              <!-- inicio de filtrado -->
              <div class="card bg-secondary shadow ">
                <div class="card-body px-lg-5 py-lg-5">
                  <h3>Buscador</h3>
                    <div class="d-inline-flex" style="width: 100%">
                      <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Buscar..." value="<%=query%>">
                        <div class="input-group-append">
                          <button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="d-sm-inline-flex" style="width: 100%">
                      <p>&nbsp;&nbsp;Mostrar: &nbsp;&nbsp;&nbsp;&nbsp;</p>
                      <div class="custom-control custom-checkbox mb-3">
                        <input class="custom-control-input" id="canales" name="canales" type="checkbox" <%=mostrarCan ? "checked" : " "%>>
                        <label class="custom-control-label" for="canales">Canales &nbsp;&nbsp;</label>
                      </div>
                      <div class="custom-control custom-checkbox mb-3">
                        <input class="custom-control-input" id="videos" name="videos" type="checkbox" <%=mostrarVid ? "checked" : " "%>>
                        <label class="custom-control-label" for="videos">Videos &nbsp;</label>
                      </div>
                      <div class="custom-control custom-checkbox mb-3">
                        <input class="custom-control-input" id="listas" name="listas" type="checkbox" <%=mostrarLis ? "checked" : " "%>>
                        <label class="custom-control-label" for="listas">Listas de reproduccion</label>
                      </div>
                    </div>
                  <!-- fin de filtrado -->
                  <hr>
                  <!-- inicio de resultados -->
                  <div class="d-sm-inline-flex row" style="width: 100%">
                    <div class="col-sm">
                      <h3><%=cantRes%> resultados</h3>
                    </div>
                    <div class="col-sm text-sm-right">
                      <div class="form-group d-sm-inline-flex align-self-center">
                        <label for="orden" style="white-space: nowrap;">Ordenar por &nbsp;</label>
                        <div class="input-group input-group-sm mb-3">
                          <select class="form-control" id="orden" name="orden">
                            <option value="a">Alfabeticamente (A-Z)</option>
                            <option value="f" <%=ordFecha ? "selected" : " "%>>Fecha (Descendente)</option>
                          </select>
                          <div class="input-group-append">
                            <button type="submit" class="btn btn-default btn-sm"><i class="fas fa-sync-alt"></i></button>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="container-fluid">
                      <div class="col col- justify-content-left">
                        <!-- Listado de Canales -->
                        <% for (DtCanalWeb c: listaC){ %>
                        <div class="card mb-3" style="max-width: 630px;">
                          <%if(usr != null && c.getNickname().contentEquals(usr.getNickname())){ %>
                            <a class="" href="<%= request.getContextPath() %>/module/miPerfil.jsp" >
                              <% }else{ %>
                            <a class="" href="<%= request.getContextPath() %>/module/consultaUsuario.jsp?nick=<%=c.getNickname()%>">
                                <% } %>
                            <div class="row no-gutters">
                              <div class="col-md-4 text-center">
                                <% if (c.getImgUsr().equals("src/main/resources/img/default.png")) {%>
                                <img src="<%= request.getContextPath()%>/img/default.png" class="avatar avatar-ramiro-lg rounded-circle" alt="...">
                                <% } else { %>
                                <img src="<%= request.getContextPath()%>/<%=c.getImgUsr()%>" class="avatar avatar-ramiro-lg rounded-circle" alt="...">
                                <% } %>
                              </div>
                              <div class="col-md-5">
                                <div class="card-body">
                                  <h5 class="card-title mb-0 text-lg"><%=c.getNomCanal()%></h5>
                                  <span class="badge badge-pill badge-info">Canal</span>
                                  <p class="card-text"><small class="text-muted">De <strong><%=c.getNickname()%></strong></small></p>
                                </div>
                              </div>
                            </div>
                          </a>
                        </div>
                        <%}%>
                        <!-- Fin listado de Canales -->

                        <!-- Listado de Videos -->
                        <% for (DtElementoWeb v: listaV){%>
                        <div class="card mb-3" style="max-width: 630px;">
                          <a href="<%= request.getContextPath() %>/module/visualizarVideo.jsp?u=<%=v.getNickname()%>&v=<%=v.getNombreE()%>">
                            <div class="row no-gutters">
                              <div class="col-md-4">
                                <img src="http://img.youtube.com/vi/<%=v.getUrl()%>/0.jpg" class="card-img" alt="...">
                              </div>
                              <div class="col-md-5">
                                <div class="card-body">
                                  <h5 class="card-title mb-0 text-lg"><%=v.getNombreE()%></h5>
                                  <span class="badge badge-pill badge-default">Video</span>
                                  <p class="card-text"><small class="text-muted">Por <strong><%=v.getNickname()%></strong></small></p>
                                </div>
                              </div>
                            </div>
                          </a>
                        </div>
                        <%}%>
                        <!-- Fin listado de Videos -->

                        <!-- Listado de Listas -->
                        <% for (DtElementoWeb l: listaL){%>
                        <div class="card mb-3" style="max-width: 630px;">
                          <a href="<%= request.getContextPath() %>/module/consultaLista.jsp?u=<%=l.getNickname()%>&id=<%=l.getNombreE()%>">
                            <div class="row no-gutters">
                              <div class="col-md-4">
                                <img src="http://img.youtube.com/vi/a/0.jpg" class="card-img" alt="...">
                              </div>
                              <div class="col-md-5">
                                <div class="card-body">
                                  <h5 class="card-title mb-0 text-lg"><%=l.getNombreE()%></h5>
                                  <span class="badge badge-pill badge-primary">Lista de reproduccion</span>
                                  <p class="card-text"><small class="text-muted">Por <strong><%=l.getNickname()%></strong></small></p>
                                </div>
                              </div>
                            </div>
                          </a>
                        </div>
                        <%}%>
                        <!-- Fin listado de Listas -->

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


  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
</body>

</html>