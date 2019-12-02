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
  <link href="./assets/img/brand/favicon.png" rel="icon" type="image/png">
  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- Icons -->
  <link href="./assets/js/plugins/nucleo/css/nucleo.css" rel="stylesheet" />
  <link href="./assets/js/plugins/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="./assets/css/argon-dashboard.css?v=1.1.0" rel="stylesheet" />
</head>

<body class="">
  <nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white" id="sidenav-main">
    <div class="container-fluid">
      <!-- Toggler -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#sidenav-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <!-- Brand -->
      <a class="navbar-brand pt-0" href="./index.jsp">
        <img src="./assets/img/brand/logo.png" class="navbar-brand-img" alt="...">
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
        <% }else {%>
        <li class="nav-item dropdown">
          <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <div class="media align-items-center">
               <span class="avatar avatar-sm rounded-circle">
                  <% if (usr.getFoto().equals("src/main/resources/img/default.png")) {%>
                  <img alt="Image placeholder" src="./img/default.png">
                  <% } else { %>
                  <img alt="Image placeholder" src="<%=usr.getFoto()%>">
                  <% } %>
                </span>
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
            <a href="./CerrarSesion" class="dropdown-item">
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
              <a href="./index.jsp">
                <img src="./assets/img/brand/logo.png">
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
            <input type="hidden" name="canales" value="on">
            <input type="hidden" name="videos" value="on">
            <input type="hidden" name="listas" value="on">
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
        <%--<%
          IUsuario iU = UFactory.getInstancia().getIUsuario();
          iU.eliminarUsuario("pepe2");
          List<String> todosUsuarios = iU.listarUsuarios();
          for(String sss:todosUsuarios){
            System.out.println(sss);
          }
        %>--%>
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
            List<String> lista = portListaRep.listarListasDeUsuario(usr.getNickname()).getItem();
            for(String l: lista){ %>
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
            List<String> listaCat = portCategoria.listarCategorias().getItem();
            for(String cat: listaCat){ %>
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
                  <img alt="Image placeholder" src="./img/default.png">
                  <% } else { %>
                  <img alt="Image placeholder" src="<%=usr.getFoto()%>">
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
              <a href="./CerrarSesion" class="dropdown-item">
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
          <%
            String message = (String) request.getAttribute("message");
            System.out.println(message);
            if(message != null){
          %>
          <div class="col-md-4">
            <div class="modal fade" id="modal-notification" tabindex="-1" role="dialog" aria-labelledby="modal-notification" aria-hidden="true">
              <div class="modal-dialog modal-light modal-dialog-centered modal-" role="document">
                <div class="modal-content bg-gradient-green">
                  <div class="modal-body">
                    <div class="py-1 text-center">
                      <i class="ni ni-check-bold ni-5x"></i>
                      <p><%=message%></p>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-link text-white ml-auto" data-dismiss="modal">Cerrar</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <%}%>

          <%
            //obtener categorias con mas de 3 elementos
            List<String> todasCategorias = portCategoria.listarCategorias().getItem();
            List<String> nomCats3 = new ArrayList<String>();
            for(String c : todasCategorias){
              List<DtElementoWeb> vidsCat = portCategoria.listarVideosPublicosCategoria(c).getItem();
              if(vidsCat.size() >= 3){
                nomCats3.add(c);
              }

            }




            //PARA OBTENER VIDEOS RANDOM
            List<DtElementoWeb> totalVideosPub = portVideo.listarVideosPublicosWeb().getItem();
            List<DtElementoWeb> videos = new ArrayList<DtElementoWeb>();
            Random rand = new Random();
            if(totalVideosPub.size() >= 3){

              for (int i = 0; i < 3; i++) {
                int randomIndex = rand.nextInt(totalVideosPub.size());
                DtElementoWeb randomElement = totalVideosPub.get(randomIndex);
                videos.add(randomElement);
                totalVideosPub.remove(randomIndex);
              }
          %>

          <br>

            <h1><span class="badge badge-secondary">Videos que podrian gustarte</span></h1>
            <br>
            <div class="card-deck">
                <%for(DtElementoWeb vids : videos){%>
                    <div class="card">
                      <img src="http://img.youtube.com/vi/<%=vids.getUrl()%>/0.jpg" class="card-img" alt="...">
                        <a  href="<%= request.getContextPath() %>/module/visualizarVideo.jsp?u=<%=vids.getNickname()%>&v=<%=vids.getNombreE()%>">
                        <div class="card-body">
                            <p class="card-text"><small class="text-muted"></small><strong><%=vids.getNombreE()%></strong></p>
                            <h5 class="card-title text-muted">Subido por: <%=vids.getNickname()%></h5>
                        </div>
                        </a>
                    </div>
                <%}%>
            </div>

            <br><br>
          <%
            }

            //Para obtener categorias random
            String nomCat3 = null;
            Random rand2 = new Random();
            if(!nomCats3.isEmpty()){
              List<DtElementoWeb> vidsCategoria = new ArrayList<DtElementoWeb>();
              while (0 < nomCats3.size() && vidsCategoria.size() < 3){
                int randomIndex = rand2.nextInt(nomCats3.size());
                nomCat3 = nomCats3.get(randomIndex);
                vidsCategoria = portCategoria.listarVideosPublicosCategoria(nomCat3).getItem();
                nomCats3.remove(randomIndex);
              }
              if(!(vidsCategoria.size() < 3)){
                rand = new Random();
                List<DtElementoWeb> videosC = new ArrayList<DtElementoWeb>();
                for (int i = 0; i < 3; i++) {
                  int randomIndex = rand.nextInt(vidsCategoria.size());
                  DtElementoWeb randomElement = vidsCategoria.get(randomIndex);
                  videosC.add(randomElement);
                  vidsCategoria.remove(randomIndex);
                }


          %>
                <h1><span class="badge badge-secondary"><%=nomCat3%> - Categoria</span></h1>
                <br>
                <div class="card-deck">
                    <%for(DtElementoWeb vc : videosC){%>
                        <div class="card">
                          <img src="http://img.youtube.com/vi/<%=vc.getUrl()%>/0.jpg" class="card-img" alt="...">
                            <a  href="<%= request.getContextPath() %>/module/visualizarVideo.jsp?u=<%=vc.getNickname()%>&v=<%=vc.getNombreE()%>">
                                <div class="card-body">
                                    <p class="card-text"><small class="text-muted"></small><strong><%=vc.getNombreE()%></strong></p>
                                    <h5 class="card-title text-muted">Subido por: <%=vc.getNickname()%></h5>
                                </div>
                            </a>
                        </div>
                    <%}%>
                </div>
            <%}
            }%>

          <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
        </div>
      </div>
    </div>
    
  </div>
  <!--   Core   -->
  <script src="./assets/js/plugins/jquery/dist/jquery.min.js"></script>
  <script src="./assets/js/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!--   Optional JS   -->
  <script src="./assets/js/plugins/chart.js/dist/Chart.min.js"></script>
  <script src="./assets/js/plugins/chart.js/dist/Chart.extension.js"></script>
  <!--   Argon JS   -->
  <script src="./assets/js/argon-dashboard.min.js?v=1.1.0"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-dashboard-free"
      });
  </script>
  <% if(message != null){ %>
  <script type="text/javascript">
    $(window).on('load',function(){
      $('#modal-notification').modal('show');
    });
  </script>
  <%  }  %>
</body>

</html>