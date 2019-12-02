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

@WebServlet(name = "SeguirUsuario" , value = "/SeguirUsuario")
public class SeguirUsuario extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String seguido = request.getParameter("u");
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        if (seguido != null && usr != null){
            publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
            publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();

            String seguidor = usr.getNickname();
            port.seguirUsuario(seguidor, seguido);
            RequestDispatcher rd;
            String path = "/module/consultaUsuario.jsp?nick=" + seguido;
            rd = request.getRequestDispatcher(path);
            String message = "Se siguio al usuario " + seguido;
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }
    }
}
