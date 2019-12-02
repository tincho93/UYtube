package servlets;

import publicadores.DtUsuarioWeb;
import interfaces.IUsuario;
import interfaces.UFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DejarSeguirUsuario", value = "/DejarSeguirUsuario")
public class DejarSeguirUsuario extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();

        String seguido = request.getParameter("u");
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        if (seguido != null && usr != null){
            String seguidor = usr.getNickname();

            port.dejarDeSeguirUsuario(seguidor, seguido);
            RequestDispatcher rd;
            String path = "/module/consultaUsuario.jsp?nick=" + seguido;
            rd = request.getRequestDispatcher(path);
            String message = "Se dejo seguir al usuario " + seguido;
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }
    }
}
