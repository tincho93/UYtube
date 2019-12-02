package servlets;

import publicadores.DtFecha;
import publicadores.DtUsuarioWeb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ResponderComentario", value = "/ResponderComentario")
public class ResponderComentario extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idC = request.getParameter("id");
        String comentario = request.getParameter("respuesta");
        String path = request.getParameter("path")  + "&h=no";
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        //WEBSERVICES
        publicadores.CVideoPublishService serviceVideo = new publicadores.CVideoPublishService();
        publicadores.CVideoPublish portVideo = serviceVideo.getCVideoPublishPort();
        //FIN WEBSERVICES
        if (usr != null && idC != null && comentario != null && !comentario.isEmpty()){
            portVideo.responderComentario(Integer.parseInt(idC), usr.getNickname(), new DtFecha(), comentario);
            RequestDispatcher rd;
            rd = request.getRequestDispatcher(path);
            String message = "Comentario Agregado";
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
    }
}
