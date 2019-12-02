package servlets;

import interfaces.IUsuario;
import interfaces.UFactory;
import publicadores.DtFecha;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "AltaUsuario", value = "/AltaUsuario")
public class AltaUsuario extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /////////////WEB SERVICE/////////////////
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();
        //////////FIN WEBSERVICE///////////
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String nomU = request.getParameter("nomU");
        String apellido = request.getParameter("apellido");
        String fNac = request.getParameter("fNac");
        String pass = request.getParameter("pass");
        String nomC = request.getParameter("nomC");
        String foto = request.getParameter("foto");
        String desc = request.getParameter("descripcion");
        Boolean publico;
        if(request.getParameter("publico") == null){
            publico = false;
        }else{
            publico = true;
        }
        String categoria = request.getParameter("categoria");

        //CODIGO PARA EXTRAER LA FECHA
        Date date = null;
        Calendar cal = Calendar.getInstance();
        try {
            date = new SimpleDateFormat("MM/dd/yyyy").parse(fNac);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DtFecha fecha = new DtFecha();
        fecha.setAnio(cal.get(Calendar.YEAR));
        fecha.setMes(cal.get(Calendar.MONTH));
        fecha.setDia(cal.get(Calendar.DAY_OF_MONTH));
        //FIN DE CODIGO PARA EXTRAER LA FECHA

        if(port.existeNickname(nickname)){
            // existe nickname
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/module/registro.jsp");
            String message = "Ya existe un Usuario con el nickname <strong>" + nickname + "</strong>";
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else if (port.existeEmail(email)){
            // existe mail
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/module/registro.jsp");
            String message = "Ya existe un Usuario con el email <strong>" + email + "</strong>";
            request.setAttribute("message", message);
            rd.forward(request, response);
        }else{
            port.agregarUsuario(nickname, nomU, apellido, fecha, email);
            if(!foto.equals("")) {
                port.modificarImagen("img/usr/" + foto);
            }else {
                port.modificarImagen("src/main/resources/img/default.png");
            }
            port.modificarContrasena(pass);
            port.agregarCanal();
            if(!nomC.equals("")) {
                port.modificarInfoCanal(nomC, desc, publico);
            }else {
                port.modificarInfoCanal(nickname, desc, publico);
            }
            if(!categoria.equals("")){
                port.modificarCatCanal(nickname, categoria);
            }
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/index.jsp");
            String message = "Se ha creado el Usuario <strong>" + nickname + "</strong>";
            request.setAttribute("message", message);
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
    }
}


