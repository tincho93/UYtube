<%@ page import="java.util.List" %>
<%@ page import="publicadores.DtUsuarioWeb" %>
<%@ page import="publicadores.DtUsuario" %>
<%@ page import="publicadores.DtCanal" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="publicadores.DtElementoWeb" %>
<%@ page import="net.java.dev.jaxb.array.StringArray" %>
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
    publicadores.CVideoPublish portV = serviceVideo.getCVideoPublishPort();

    publicadores.CListaRepPublishService serviceListaRep = new publicadores.CListaRepPublishService();
    publicadores.CListaRepPublish portL = serviceListaRep.getCListaRepPublishPort();

    publicadores.CCategoriaPublishService serviceCategoria = new publicadores.CCategoriaPublishService();
    publicadores.CCategoriaPublish portC = serviceCategoria.getCCategoriaPublishPort();

    publicadores.CUsuarioPublishService serviceUsuario = new publicadores.CUsuarioPublishService();
    publicadores.CUsuarioPublish portU = serviceUsuario.getCUsuarioPublishPort();
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
        <% }else { %>
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
                    <a class="nav-link " href="<%= request.getContextPath() %>/module/nuevaLista.jsp">
                        <i class="ni ni-fat-add text-blue"></i> Crear lista
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
                <%
                    List<String> lC = portC.listarCategorias().getItem();
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
                    <div class="row justify-content-center">
                        <div class="col-sm-10 order-xl-1">
                            <div class="card bg-secondary shadow ">
                                <div class="card-body px-lg-5 py-lg-5">
                                    <%
                                        String message = (String) request.getAttribute("message");
                                        if(message != null){
                                    %>
                                    <div class="alert alert-info" role="alert">
                                        <%=message%>
                                    </div>
                                    <%}%>
                                    <%
                                        DtUsuario usuario = portU.obtenerInfoUsuario(usr.getNickname());
                                        DtCanal canal = portU.obtenerInfoCanal();

                                        StringArray seguidoresSA = portU.listarSeguidores();
                                        List<String> seguidores = seguidoresSA.getItem();

                                        StringArray seguidosSA = portU.listarSeguidos();
                                        List<String> seguidos = seguidosSA.getItem();

                                        List<DtUsuarioWeb> listSeguidores = portU.listarNickFotoWeb(seguidoresSA).getItem();
                                        List<DtUsuarioWeb> listSeguidos = portU.listarNickFotoWeb(seguidosSA).getItem();

                                        List<DtElementoWeb> listVideos = portV.listarVideosDeUsuarioWeb(usuario.getNickname()).getItem();
                                        List<String> listListasRep = portL.listarListasDeUsuario(usuario.getNickname()).getItem();
                                    %>
                                    <div class="row justify-content-center">
                                        <div class="col-sm-5 mb-4">
                                            <div class="text-muted text-center mt-2 mb-4">
                                                <h1>Datos del Usuario</h1>
                                            </div>

                                            <form name="cambioImagen" role="form" action="<%= request.getContextPath() %>/ModificarImagen" method="post">
                                                <%--Foto de perfil--%>
                                                <div class="row justify-content-center">
                                                    <span href="" class="avatar avatar-ramiro-lg rounded-circle">
                                                        <% if (usuario.getImagen().equals("src/main/resources/img/default.png")) {%>
                                                            <img alt="Image placeholder" src="<%= request.getContextPath() %>/img/default.png">
                                                        <% } else { %>
                                                            <img alt="Image placeholder" src="<%= request.getContextPath() %>/<%=usuario.getImagen()%>">
                                                        <% } %>
                                                    </span>
                                                </div>
                                                <%--Fin Foto de perfil--%>

                                                <input type="hidden" name="nickname" value="<%=usuario.getNickname()%>">

                                                <div style="text-align: center" class="mt-3">
                                                    <label class="btn-sm btn-outline-primary">
                                                        Modificar imagen <input type="file" name="foto" style="display: none;"  onchange="cambiarImagen()">
                                                    </label>
                                                    <label class="btn-sm btn-outline-primary">
                                                        Eliminar imagen <input type=button name="fotoEliminar" style="display: none;" data-toggle="modal" data-target="#eliminarImg">
                                                        <%--type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">--%>
                                                    </label>
                                                </div>
                                            </form>


                                            <%--Nickname--%>
                                            <div class="row justify-content-center mb-3">
                                                <span class="mb-xl-2 font-weight-bold text-xl">@<%=usuario.getNickname() %></span>
                                            </div>
                                            <%--Fin Nickname--%>

                                            <%--Email--%>
                                            <div style="text-align: center">
                                                <h4 class="mb-0"> Email: </h4>
                                                <p class="font-weight-bold mt--1" style="font-size: 17px;"> <%=usuario.getCorreoE()%></p>
                                            </div>
                                            <%--Fin Email--%>

                                            <%--Nombre--%>
                                            <div style="text-align: center">
                                                <h4 class="mb-0"> Nombre: </h4>
                                                <p class="font-weight-bold mt--1" style="font-size: 20px;"> <%=usuario.getNombre()%></p>
                                            </div>
                                            <%--Fin Nombre--%>

                                            <%--Apellido--%>
                                            <div style="text-align: center">
                                                <h4 class="mb-0"> Apellido: </h4>
                                                <p class="font-weight-bold mt--1" style="font-size: 20px;"> <%=usuario.getApellido()%></p>
                                            </div>
                                            <%--Fin Apellido--%>

                                            <%--Fecha Nacimiento--%>
                                            <div style="text-align: center">
                                                <%
                                                    Calendar calendar = usuario.getFNac().toGregorianCalendar();
                                                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                                    String fechaS = sdf.format(calendar.getTime());
                                                %>
                                                <h4 class="mb-0">Fecha de Nacimiento: </h4>
                                                <p style="font-size: 16px " class="font-weight-bold mt--1"><%=fechaS %></p>
                                            </div>
                                            <%--Fin Fecha nacimiento--%>
                                        </div>

                                        <div class="col-sm-7 px-4 mb-4" style="text-align: center">
                                            <div class="text-muted mt-2 mb-5">
                                                <h1>Datos del Canal</h1>
                                            </div>

                                            <div class="row mb-2 px--3">
                                                <%--Nombre de Canal--%>
                                                <div class="col">
                                                    <h4>Nombre: </h4>
                                                    <p class="mb-xl-2 font-weight-bold text-xl"><%=canal.getNombre() %></p>
                                                </div>
                                                <%--Fin Nombre de Canal--%>

                                                <%--Categoria de Canal--%>
                                                <div class="col">
                                                    <% if(canal.getCategoria() != null){ %>
                                                        <h4>Categoria: </h4>
                                                        <p class="mb-xl-2 font-weight-bold text-xl"><%= canal.getCategoria()%></p>
                                                    <% }else{ %>
                                                        <h4>Categoria: </h4>
                                                        <p class="mb-xl-2 font-weight-bold text-lg"> Sin Categoria </p>
                                                    <% } %>
                                                </div>
                                                <%--Fin Categoria de Canal--%>
                                            </div>


                                            <%--Descripcion de Canal--%>
                                            <div class="row justify-content-center my-2 mt-5">
                                                <h4>Descripción: <br></h4>
                                            </div>
                                            <div class="card small center">
                                                <div class="card-body p--2 mx--2 my--2 text-sm-left">
                                                    <span><%=canal.getDescripcion()%></span>
                                                </div>
                                            </div>
                                            <%--Fin Descripcion de Canal--%>

                                        </div>

                                        <div class="row">
                                            <div class="m-3">
                                                <button type="button" class="btn-sm btn-outline-primary" data-toggle="modal" data-target="#editar">
                                                    Modificar Datos
                                                </button>
                                            </div>
                                            <div class="m-3">
                                                <%--Boton eliminar usuario--%>
                                                <form name="eliminaUsr" role="form" action="<%= request.getContextPath() %>/EliminarUsuario" method="post">
                                                    <input type="hidden" name="nick" value="<%=usuario.getNickname()%>">
                                                    <%--<label class="btn-sm btn-outline-primary">
                                                        Eliminar Usuario <input type=button name="usuarioEliminar" style="display: none;" data-toggle="modal" data-target="#eliminarUsr">
                                                    </label>--%>
                                                    <button type="button" class="btn-sm btn-outline-primary" data-toggle="modal" data-target="#eliminarUsr">
                                                        Eliminar Usuario <%--<input type=button name="usuarioEliminar" style="display: none;" data-toggle="modal" data-target="#eliminarUsr">--%>
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>



                                    <%--Aqui comienzan las tabs con Seguidores, seguidos, videos y listas--%>
                                    <hr>
                                    <div class="nav-wrapper">
                                        <ul class="nav nav-pills nav-fill flex-column flex-md-row" id="tabs-icons-text" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link mb-sm-4 mb-md-0" id="tabs-icons-text-1-tab" data-toggle="tab" href="#tabs-icons-text-1" role="tab" aria-controls="tabs-icons-text-1" aria-selected="true"><i class="ni ni-cloud-upload-96 mr-2"></i> <%=seguidores.size()%> Seguidores <%--<span class="badge badge-white"><%=seguidores.size()%></span>--%></a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link mb-sm-4 mb-md-0" id="tabs-icons-text-2-tab" data-toggle="tab" href="#tabs-icons-text-2" role="tab" aria-controls="tabs-icons-text-2" aria-selected="false"><i class="ni ni-bell-55 mr-2"></i> <%=seguidos.size()%> Seguidos </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link mb-sm-4 mb-md-0 active" id="tabs-icons-text-3-tab" data-toggle="tab" href="#tabs-icons-text-3" role="tab" aria-controls="tabs-icons-text-3" aria-selected="false"><i class="fab fa-youtube mr-2"></i>Videos</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link mb-sm-4 mb-md-0" id="tabs-icons-text-4-tab" data-toggle="tab" href="#tabs-icons-text-4" role="tab" aria-controls="tabs-icons-text-4" aria-selected="false"><i class="fas fa-list-ul mr-2"></i>Listas</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card shadow">
                                        <div class="card-body">
                                            <div class="tab-content" id="myTabContent">

                                                <%--Comienzo muestra seguidores--%>
                                                <div class="tab-pane fade" id="tabs-icons-text-1" role="tabpanel" aria-labelledby="tabs-icons-text-1-tab">
                                                    <div class="row row- justify-content-right">
                                                        <% for(DtUsuarioWeb u:listSeguidores) { %>
                                                        <div class="col-sm-3">
                                                            <div class="card bg-secondary shadow ">
                                                                <a class="" href="<%= request.getContextPath() %>/module/consultaUsuario.jsp?nick=<%=u.getNickname()%>">
                                                                    <div class="card-body px-lg-3 py-lg-3">
                                                                        <div class="media align-items-center">
                                                                            <span class="avatar avatar-lg rounded-circle">
                                                                              <% if (u.getFoto().equals("src/main/resources/img/default.png")) {%>
                                                                                <img alt="Image placeholder" src="<%= request.getContextPath() %>/img/default.png">
                                                                              <% } else { %>
                                                                                <img alt="Image placeholder" src="<%= request.getContextPath() %>/<%=u.getFoto()%>">
                                                                              <% } %>
                                                                            </span>
                                                                            <div class="media-body">
                                                                                <span class="mb-0 text-lg  font-weight-bold"> @<%=u.getNickname()%></span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                            <br/>
                                                        </div>
                                                        <% } %>
                                                    </div>
                                                </div>
                                                <%--Fin muestra seguidores--%>

                                                <%--Comienzo muestra seguidos--%>
                                                <div class="tab-pane fade" id="tabs-icons-text-2" role="tabpanel" aria-labelledby="tabs-icons-text-2-tab">
                                                    <div class="row row- justify-content-right">
                                                        <% for(DtUsuarioWeb u:listSeguidos) { %>
                                                        <div class="col-sm-3">
                                                            <div class="card bg-secondary shadow ">
                                                                <a class="" href="<%= request.getContextPath() %>/module/consultaUsuario.jsp?nick=<%=u.getNickname()%>">
                                                                    <div class="card-body px-lg-3 py-lg-3">
                                                                        <div class="media align-items-center">
                                                                            <span class="avatar avatar-lg rounded-circle">
                                                                              <% if (u.getFoto().equals("src/main/resources/img/default.png")) {%>
                                                                                <img alt="Image placeholder" src="<%= request.getContextPath() %>/img/default.png">
                                                                              <% } else { %>
                                                                                <img alt="Image placeholder" src="<%= request.getContextPath() %>/<%=u.getFoto()%>">
                                                                              <% } %>
                                                                            </span>
                                                                            <div class="media-body">
                                                                                <span class="mb-0 text-lg  font-weight-bold"> @<%=u.getNickname()%></span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                            <br/>
                                                        </div>
                                                        <% } %>
                                                    </div>
                                                </div>
                                                <%--Fin muestra seguidos--%>

                                                <%--Comienzo muestra videos--%>
                                                <div class="tab-pane fade show active" id="tabs-icons-text-3" role="tabpanel" aria-labelledby="tabs-icons-text-3-tab">
                                                    <div class="container-fluid">
                                                        <div class="row row- justify-content-right">
                                                            <%
                                                                for(DtElementoWeb eu: listVideos){
                                                            %>
                                                            <div class="col-sm-4">
                                                                <a href="<%= request.getContextPath() %>/module/visualizarVideo.jsp?u=<%=eu.getNickname()%>&v=<%=eu.getNombreE()%>">
                                                                    <div class="card-body px-2 py-2 mb-4">
                                                                        <div class="card">
                                                                            <img src="http://img.youtube.com/vi/<%=eu.getUrl()%>/0.jpg" class="card-img-top" alt="..." href="<%= request.getContextPath() %>/module/consultaVideo.jsp?nomvVid=<%=eu.getNombreE()%>">
                                                                            <div class="card-body mx--2">
                                                                                <h5 class="card-title my--3 text-xs"><%=eu.getNombreE()%></h5>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                            <% } %>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%--Fin muestra videos--%>

                                                <%--Comienzo muestra listas--%>
                                                <div class="tab-pane fade" id="tabs-icons-text-4" role="tabpanel" aria-labelledby="tabs-icons-text-4-tab">
                                                    <div class="row row- justify-content-right">
                                                        <% for(String lr:listListasRep) { %>
                                                        <div class="col-sm-3">
                                                            <a class="" href="<%= request.getContextPath() %>/module/consultaLista.jsp?id=<%=lr%>">
                                                            <div class="card shadow-sm p-1 mb-4 card border-info rounded" style="text-align: center">
                                                                <div class="card-body p-2 text-lg-center">
                                                                    <span class="mb-0 text-md-center font-weight-bold" style="color: #11b7d8"><%=lr%></span>
                                                                </div>
                                                            </div>
                                                            </a>
                                                        </div>
                                                        <% } %>
                                                    </div>
                                                </div>
                                                <%--Fin muestra listas--%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>



                            <%--Aqui empieza el formulario para cambio de datos del usuario--%>
                            <div class="modal fade" id="editar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Editar Datos de Usuario y Canal</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form name="consultaPerfil" role="form" action="<%= request.getContextPath() %>/ModificarDatosUsuario" method="post">
                                                <div class="text-muted text-center mt-2 mb-3">
                                                    <h3>Datos del Usuario</h3>
                                                </div>

                                                <%--Nombre--%>
                                                <div class="form-group">
                                                    <small>Nombre</small>
                                                    <input name="nomU" class="form-control form-control-sm" placeholder="Nombre" id="nomU" type="text" onkeyup="quitarInvalido(this)" value="<%=usuario.getNombre()%>">
                                                </div>
                                                <%--Fin Nombre--%>

                                                <%--Apellido--%>
                                                <div class="form-group">
                                                    <small>Apellido</small>
                                                    <input name="apellido" class="form-control form-control-sm" placeholder="Apellido" id="apellido" type="text" onkeyup="quitarInvalido(this)" value="<%=usuario.getApellido()%>">
                                                </div>
                                                <%--Fin Apellido--%>

                                                <%--Fecha Nacimiento--%>
                                                <div class="form-group">
                                                    <small>Fecha de Nacimiento</small>
                                                    <div class="input-group">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text form-control-sm"><i class="ni ni-calendar-grid-58"></i></span>
                                                        </div>
                                                        <input name="fNac" class="form-control datepicker form-control-sm" placeholder="Fecha de nacimiento" id="fNac" type="text" onkeyup="quitarInvalido(this)" value="<%=fechaS%>">
                                                    </div>
                                                </div>
                                                <%--Fin Fecha Nacimiento--%>


                                                <div class="text-muted text-center mt-2 mb-3">
                                                    <hr/>
                                                    <h3>Datos del Canal</h3>
                                                </div>

                                                <%--Nombre Canal--%>
                                                <div class="form-group">
                                                    <small>Nombre de Canal</small>
                                                    <input name="nomCan" class="form-control form-control-sm" placeholder="Nombre (opcional)" id="nomCan" type="text" onkeyup="quitarInvalido(this)" value="<%=canal.getNombre()%>">
                                                </div>
                                                <%--Fin Nombre Canal--%>

                                                <%--Categoria Canal--%>
                                                <div class="form-group">
                                                    <small>Categoria de Canal</small>
                                                    <%--<select class="form-control form-control-sm"> id="selCategoriaID" name="categoria">
                                                        <option value="" <%= (canal.getCategoria().equals("") )? "selected": "" %>> --Sin Categoria-- </option>
                                                        <%for(String cat: lC){ %>
                                                        <option value="<%=cat%>" <%= (canal.getCategoria().equals(cat)) ? "selected" : ""%>><%=cat%></option>
                                                        <% } %>
                                                    </select>--%>

                                                    <select class="form-control form-control-sm" id="inputGroupSelect01" name="categoria">
                                                        <option value="" <%= (canal.getCategoria() == null) ? "selected" : ""%>> --Sin Categoría-- </option>
                                                        <%
                                                            String categoria = "";
                                                            if(canal.getCategoria() != null){
                                                                categoria = canal.getCategoria();
                                                            }
                                                            List<String> lC2 = portC.listarCategorias().getItem();
                                                            for(String cat2: lC2){ %>
                                                        <option value="<%=cat2%>" <%= (categoria.equals(cat2)) ? "selected" : ""%>><%=cat2%></option>
                                                        <% } %>
                                                    </select>


                                                </div>
                                                <%--Fin Categoria Canal--%>

                                                <%--Descripcion Canal--%>
                                                <div class="form-group">
                                                    <small>Descripción de Canal</small>
                                                    <div class="input-group">
                                                        <textarea class="form-control" id="descripcion" rows="3" placeholder="Descripcion..." name="descripcion" onkeyup="quitarInvalido(this)"><%=canal.getDescripcion()%></textarea>
                                                    </div>
                                                </div>
                                                <%--Fin Descripcion Canal--%>

                                                <%--Canal publico--%>
                                                <div class="text-muted text-center mt-2 mb-3">
                                                    <div class="custom-control custom-checkbox mb-3">
                                                        <input class="custom-control-input" id="customCheck5" type="checkbox" name="publico" <%= canal.isPublico() ? "checked" : "" %>>
                                                        <label class="custom-control-label" for="customCheck5">Canal publico</label>
                                                    </div>
                                                </div>
                                                <%--Fin Canal publico--%>

                                                <input type="hidden" name="nickname" value="<%=usuario.getNickname()%>">

                                                <%--Mostrar mensaje de falta de datos para crear video--%>
                                                <div id="mensaje-error" class="alert alert-danger d-none" role="alert">
                                                    <!-- El texto del mensaje se genera en un script -->
                                                </div>
                                                <%--Fin mostrar mensaje de falta de datos para crear video--%>

                                                <%--Boton guardar cambios--%>
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary my-4" onclick="cambiar()">Guardar Cambios</button>
                                                </div>
                                                <%--Fin Boton guardar cambios--%>

                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <%--Aqui comienza el cartel si desea eliminar imagen--%>
                            <div class="modal fade" id="eliminarImg" tabindex="-1" role="dialog" aria-labelledby="ModalEliminarImagen" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            Desea eliminar su foto de perfil?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                            <button type="button" class="btn btn-primary" onclick="eliminarImagen()">Sí, deseo eliminar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%--Aqui comienza el cartel si desea eliminar el Usuario--%>
                            <div class="modal fade" id="eliminarUsr" tabindex="-1" role="dialog" aria-labelledby="ModalEliminarUsuario" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            Desea eliminar su usuario de UyTube?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                            <button type="button" class="btn btn-primary" onclick="eliminarUsuario()">Sí, deseo eliminar</button>
                                        </div>
                                    </div>
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
<script src="<%= request.getContextPath() %>/assets/js/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>


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
    function cambiar() {
        var nomU = document.forms["consultaPerfil"]["nomU"].value;
        var apellido = document.forms["consultaPerfil"]["apellido"].value;
        var fNac = document.forms["consultaPerfil"]["fNac"].value;
        var nomCan = document.forms["consultaPerfil"]["nomCan"].value;
        var descripcion = document.forms["consultaPerfil"]["descripcion"].value;
        if(nomU == "" || apellido == "" || fNac == "" || nomCan =="" || descripcion == "" ){
            $("#mensaje-error").html('<strong>Error!</strong> Falta completar algun campo obligatorio');
            $("#mensaje-error").removeClass("d-none");
            marcarCamposVacios(nomU, apellido, fNac, nomCan, descripcion);
        }else {
            document.forms["consultaPerfil"].submit();
        }
    }

    function quitarInvalido(arg){
        if(arg.value == ""){
            $(arg).addClass("is-invalid");
        }else {
            $(arg).removeClass("is-invalid");
        }
    }

    function marcarCamposVacios(nomU, apellido, fNac, nomCan, descripcion){
        if(nomU == "") {
            $("#nomU").addClass("is-invalid");
        }
        if(apellido == "") {
            $("#apellido").addClass("is-invalid");
        }
        if(fNac == "") {
            $("#fNac").addClass("is-invalid");
        }
        if(nomCan == "") {
            $("#nomCan").addClass("is-invalid");
        }
        if(descripcion == "") {
            $("#descripcion").addClass("is-invalid");
        }
    }

    function cambiarImagen() {
        document.forms["cambioImagen"].submit();
    }

    function eliminarImagen() {
        document.forms["cambioImagen"].submit();
    }

    function eliminarUsuario() {
        document.forms["eliminaUsr"].submit();
    }
</script>

</body>

</html>