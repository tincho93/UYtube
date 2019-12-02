package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VerificarNick", value = "/VerificarNick")
public class VerificarNick extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /////////////WEB SERVICE/////////////////
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();
        //////////FIN WEBSERVICE///////////
        String targetId = request.getParameter("nickname");
        if ((targetId != null) && !port.existeNickname(targetId)) {
            response.setContentType("text/plain");
            response.getWriter().write("false");
        } else {
            response.setContentType("text/plain");
            response.getWriter().write("true");
        }
    }
}
