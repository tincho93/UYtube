<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 13/09/2019
  Time: 16:46
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

<%
  //WEBSERVICES
  publicadores.CCategoriaPublishService serviceCategoria = new publicadores.CCategoriaPublishService();
  publicadores.CCategoriaPublish portCategoria = serviceCategoria.getCCategoriaPublishPort();
  //FIN WEBSERVICES
%>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    UyTube - Registrarse
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
      <a class="navbar-brand" href="<%= request.getContextPath() %>/index.jsp">
        <img src="<%= request.getContextPath() %>/assets/img/brand/logo.png" />
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse-main" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbar-collapse-main">
        <!-- Collapse header -->
        <div class="navbar-collapse-header d-md-none">
          <div class="row">
            <div class="col-6 collapse-brand">
              <a href="<%= request.getContextPath() %>/index.jsp">
                <img src="<%= request.getContextPath() %>/assets/img/brand/logo.png">
              </a>
            </div>
            <div class="col-6 collapse-close">
              <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle sidenav">
                <span></span>
                <span></span>
              </button>
            </div>
          </div>
        </div>
        <!-- Navbar items -->
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link nav-link-icon" href="<%= request.getContextPath() %>/module/iniciarSesion.jsp">
              <i class="ni ni-key-25"></i>
              <span class="nav-link-inner--text">Iniciar sesion</span>
            </a>
          </li>
          </li>
        </ul>
      </div>
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
      <div class="col-xl-10 order-xl-1">
        <div class="card bg-secondary shadow ">
          <div class="card-body px-lg-5 py-lg-5">
            <div class="text-muted text-center mt-2 mb-3">
              <h1>Registrarse</h1>
              <small>Datos del Usuario</small>
            </div>
            <%
              String message = (String) request.getAttribute("message");
              if(message != null){
            %>
            <div class="alert alert-danger" role="alert">
              <%=message%>
            </div>
            <%}%>
            <%
              String nickname = (request.getParameter("nickname") != null) ? request.getParameter("nickname") : "";
              String email = (request.getParameter("email") != null) ? request.getParameter("email") : "";
              String nomU = (request.getParameter("nomU") != null) ? request.getParameter("nomU") : "";
              String apellido = (request.getParameter("apellido") != null) ? request.getParameter("apellido") : "";
              String fNac = (request.getParameter("fNac") != null) ? request.getParameter("fNac") : "";
              String foto = (request.getParameter("foto") != null) ? request.getParameter("foto") : "";
              String nomC = (request.getParameter("nomC") != null) ? request.getParameter("nomC") : "";
              String categoria = (request.getParameter("categoria") != null) ? request.getParameter("categoria") : "";
              String descripcion = (request.getParameter("descripcion") != null) ? request.getParameter("descripcion") : "";
              Boolean publico = request.getParameter("publico") != null;
            %>
            <form name="registro" role="form" action="<%= request.getContextPath() %>/AltaUsuario" method="post">
              <div class="row">
                <div class="col">
                  <div id="grupo-nick" class="form-group mb-3">
                    <input name="nickname" id="nickname" class="form-control" placeholder="Nickname" type="text" value="<%=nickname%>" autocomplete="off" onkeyup="validarNick()">
                  </div>
                </div>
                <div class="col">
                  <div id="grupo-email" class="form-group ">
                    <input name="email" id="email" class="form-control" placeholder="ejemplo@email.com" type="email" value="<%=email%>"  autocomplete="off" onkeyup="validarEmail()">
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <div class="form-group">
                    <input name="nomU" id="nomU" class="form-control" placeholder="Nombre" type="text" value="<%=nomU%>" onkeyup="quitarInvalido(this)">
                  </div>
                </div>
                <div class="col">
                  <div class="form-group">
                    <input name="apellido" id="apellido" class="form-control" placeholder="Apellido" type="text" value="<%=apellido%>" onkeyup="quitarInvalido(this)">
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <div id="grupopass" class="form-group">
                    <input id="pass" name="pass" class="form-control" placeholder="Contrasena" type="password" onkeyup="coincidenPass()">
                  </div>
                </div>
                <div class="col">
                  <div id="grupopass2" class="form-group">
                    <input id="pass2" name="pass2" class="form-control" placeholder="Repetir contrasena" type="password" onkeyup="coincidenPass()">
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <div class="form-group">
                    <div class="input-group">
                      <div class="input-group-prepend">
                        <span class="input-group-text"><i class="ni ni-calendar-grid-58"></i></span>
                      </div>
                      <input name="fNac" id="fNac" autocomplete="off" class="form-control datepicker" placeholder="Fecha de nacimiento" type="text" value="<%=fNac%>" onkeyup="quitarInvalido(this)">
                    </div>
                  </div>
                </div>
                <div class="col">
                  <div class="form-group">
                    <div class="input-group">
                      <input type="file" class="custom-file-input" id="inputGroupFile01" name="foto" value="<%=foto%>">
                      <label class="custom-file-label" for="inputGroupFile01">Foto de perfil (opcional)</label>
                    </div>
                  </div>
                </div>

              </div>

              <div class="text-muted text-center mt-2 mb-3">
                <hr/>
                <small>Datos del Canal</small>
              </div>
              <div class="row">
                <div class="col">
                  <div class="form-group">
                    <input class="form-control" placeholder="Nombre (opcional)" type="text" name="nomC" value="<%=nomC%>">
                  </div>
                </div>
                <div class="col">
                  <div class="form-group">
                    <select class="custom-select" id="inputGroupSelect01" name="categoria" >
                      <option value="" <%= (categoria.equals("") )? "selected": "" %>> --Sin Categoria-- </option>
                      <%
                        List<String> lC = portCategoria.listarCategorias().getItem();
                        for(String cat: lC){ %>
                      <option value="<%=cat%>" <%= (categoria.equals(cat)) ? "selected" : ""%>><%=cat%></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="input-group">
                  <textarea class="form-control" id="descripcion" rows="3" placeholder="Descripcion..." onkeyup="quitarInvalido(this)" name="descripcion"><%=descripcion%></textarea>
                </div>
              </div>
              <div class="text-muted text-center mt-2 mb-3">
                <div class="custom-control custom-control-alternative custom-checkbox mb-3">
                  <input class="custom-control-input" id="customCheck5" type="checkbox" name="publico" <%= publico ? "checked" : "" %>>
                  <label class="custom-control-label" for="customCheck5">Canal publico</label>
                </div>
              </div>
              <div id="mensaje-error" class="alert alert-danger d-none" role="alert">
                <!-- El texto del mensaje se genera en un script -->
              </div>
              <div class="text-center">
                <button type="button" class="btn btn-primary my-4" onclick="continuar()">Registrarse</button>
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

    var nickValido = false;
    var emailValido = false;
    function continuar() {
        $("#mensaje-error").addClass("d-none");
        var nick = document.forms["registro"]["nickname"].value;
        var email = document.forms["registro"]["email"].value;
        var nomU = document.forms["registro"]["nomU"].value;
        var apellido = document.forms["registro"]["apellido"].value;
        var fNac = document.forms["registro"]["fNac"].value;
        var pass = document.forms["registro"]["pass"].value;
        var pass2 = document.forms["registro"]["pass2"].value;
        var descripcion = document.forms["registro"]["descripcion"].value;
        if(nick == "" || email == "" || nomU == "" || apellido == "" || fNac == "" || pass == "" || pass2 == "" || descripcion == "" ){
          $("#mensaje-error").html('<strong>Error!</strong> Falta completar algun campo obligatorio');
          $("#mensaje-error").removeClass("d-none");
          marcarCamposVacios(nick, email, nomU, apellido, fNac, pass, pass2, descripcion);
        } else if (pass != pass2) {
          $("#mensaje-error").html('<strong>Error!</strong> Las contraseñas no coinciden');
          $("#mensaje-error").removeClass("d-none");
        }else if (!nickValido){
          $("#mensaje-error").html('<strong>Error!</strong> El nickname ingresado no es valido');
          $("#mensaje-error").removeClass("d-none");
        }else if (!emailValido){
          $("#mensaje-error").html('<strong>Error!</strong> El email ingresado no es valido');
          $("#mensaje-error").removeClass("d-none");
        }else {
            document.forms[0].submit();
        }
    }

    function quitarInvalido(arg){
      if(arg.value == ""){
        $(arg).addClass("is-invalid");
      }else {
        $(arg).removeClass("is-invalid");
      }
    }

    function marcarCamposVacios(nick, email, nomU, apellido, fNac, pass, pass2, descripcion){
      if(nick == "") {
        $("#nickname").addClass("is-invalid");
      }
      if(email == "") {
        $("#email").addClass("is-invalid");
      }
      if(nomU == "") {
        $("#nomU").addClass("is-invalid");
      }
      if(apellido == "") {
        $("#apellido").addClass("is-invalid");
      }
      if(fNac == "") {
        $("#fNac").addClass("is-invalid");
      }
      if(pass == "") {
        $("#pass").addClass("is-invalid");
      }
      if(pass2 == "") {
        $("#pass2").addClass("is-invalid");
      }
      if(descripcion == "") {
        $("#descripcion").addClass("is-invalid");
      }
    }

    function validarNick() {
      var target = document.getElementById("nickname");
      if(target.value != ""){
        var url = "<%= request.getContextPath() %>/VerificarNick?nickname=" + encodeURIComponent(target.value);
        $.get(url, function (res) {
          if (res == "false") {
            nickValido = true;
            $("#nickname").removeClass("is-invalid");
            $("#nickname").addClass("is-valid");
            $("#grupo-nick").removeClass("has-danger");
            $("#grupo-nick").addClass("has-success");
          } else {
            nickValido = false;
            $("#nickname").removeClass("is-valid");
            $("#nickname").addClass("is-invalid");
            $("#grupo-nick").removeClass("has-success");
            $("#grupo-nick").addClass("has-danger");
          }
        });
      } else {
        //aca se tiene que poner en rojo
        nickValido = false;
        $("#nickname").removeClass("is-valid");
        $("#nickname").addClass("is-invalid");
        $("#grupo-nick").removeClass("has-success");
        $("#grupo-nick").addClass("has-danger");
      }
    }

    function validarEmail() {
      var target = document.getElementById("email");
      if(target.value != ""){
        var url = "<%= request.getContextPath() %>/VerificarEmail?email=" + encodeURIComponent(target.value);
        $.get(url, function (res) {
          if (res == "false") {
            emailValido = true;
            $("#email").removeClass("is-invalid");
            $("#email").addClass("is-valid");
            $("#grupo-email").removeClass("has-danger");
            $("#grupo-email").addClass("has-success");
          } else {
            emailValido = false;
            $("#email").removeClass("is-valid");
            $("#email").addClass("is-invalid");
            $("#grupo-email").removeClass("has-success");
            $("#grupo-email").addClass("has-danger");
          }
        });
      } else {
        //si el campo esta vacio
        emailValido = false;
        $("#email").removeClass("is-valid");
        $("#email").addClass("is-invalid");
        $("#grupo-email").removeClass("has-success");
        $("#grupo-email").addClass("has-danger");
      }

    }

    function coincidenPass() {
      var pass1 = document.getElementById("pass").value;
      var pass2 = document.getElementById("pass2").value;
      if (pass1 != pass2 || pass2 == "") {
        $("#pass2").removeClass("is-valid");
        $("#pass2").addClass("is-invalid");
        $("#grupo-pass2").removeClass("has-success");
        $("#grupo-pass2").addClass("has-danger");
      }else {
        $("#pass2").removeClass("is-invalid");
        $("#pass2").addClass("is-valid");
        $("#grupo-pass2").removeClass("has-danger");
        $("#grupo-pass2").addClass("has-success");
      }
    }
</script>
</body>

</html>