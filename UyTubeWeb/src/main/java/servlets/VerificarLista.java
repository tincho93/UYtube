package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VerificarLista", value = "/VerificarLista")
public class VerificarLista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /////////////WEB SERVICE/////////////////
        publicadores.CListaRepPublishService service = new publicadores.CListaRepPublishService();
        publicadores.CListaRepPublish port = service.getCListaRepPublishPort();
        //////////FIN WEBSERVICE///////////
        String targetId = request.getParameter("nomList");
        if ((targetId != null) && !port.existeLista(targetId)) {
            response.setContentType("text/plain");
            response.getWriter().write("false");
        } else {
            response.setContentType("text/plain");
            response.getWriter().write("true");
        }
    }
}
