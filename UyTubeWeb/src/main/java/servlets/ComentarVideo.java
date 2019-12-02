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

@WebServlet(name = "ComentarVideo", value = "/ComentarVideo")
public class ComentarVideo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uVid = request.getParameter("uVid");
        String nVid = request.getParameter("nVid");
        String comentario = request.getParameter("comentario");
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");

        if (usr != null && uVid != null && nVid != null && comentario != null && !comentario.isEmpty()){
            publicadores.CVideoPublishService serviceVideo = new publicadores.CVideoPublishService();
            publicadores.CVideoPublish portVideo = serviceVideo.getCVideoPublishPort();

            portVideo.setUsr(uVid);
            portVideo.setVid(nVid);
            portVideo.realizarComentario(usr.getNickname(), new DtFecha(), comentario);
            String path = "/module/visualizarVideo.jsp?u=" + uVid +"&v=" + nVid  + "&h=no";
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
        response.getWriter().append("Parametros invalidos");
    }
}
