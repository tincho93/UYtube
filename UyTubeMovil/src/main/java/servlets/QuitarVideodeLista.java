package servlets;

import publicadores.DtUsuarioWeb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "QuitarVideodeLista", value = "/QuitarVideodeLista")
public class QuitarVideodeLista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CListaRepPublishService serviceListaRep = new publicadores.CListaRepPublishService();
        publicadores.CListaRepPublish portL = serviceListaRep.getCListaRepPublishPort();

        String usuario = request.getParameter("uv");
        String video = request.getParameter("v");
        String lista = request.getParameter("l");
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        if(usuario != null && video != null && lista != null) {
            portL.setuVid(usuario);
            portL.setVideo(video);
            portL.setuList(usr.getNickname());
            portL.setLista(lista);
            portL.eliminarVideoDeLista(usuario, video, lista);

            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/module/consultaLista.jsp?id=" + lista);
            String message = "Borrado exitoso";
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }
    }
}
