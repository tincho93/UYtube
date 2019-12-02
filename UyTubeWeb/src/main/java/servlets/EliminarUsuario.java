package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EliminarUsuario", value = "/EliminarUsuario")
public class EliminarUsuario extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();
        HttpSession s = request.getSession();
        String nick = request.getParameter("nick");
        String pass = request.getParameter("pass");
        port.eliminarUsuario(nick);
        s.removeAttribute("usuario");
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/CerrarSesion");
        String message = "El usuario fue eliminado correctamente";
        request.setAttribute("message", message);
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        s.removeAttribute("usuario");
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}
