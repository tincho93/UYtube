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

@WebServlet(name = "ModificarImagen", value = "/ModificarImagen")
public class ModificarImagen extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();

        HttpSession s = request.getSession();
        DtUsuarioWeb usrS = (DtUsuarioWeb) s.getAttribute("usuario");
        if (usrS != null){
            String nickname = request.getParameter("nickname");
            String foto = request.getParameter("foto");
            String message = "";

            if(port.existeNickname(nickname)) {
                String fotoURL;
                if(!foto.equals("")) {
                    fotoURL = "img/usr/" + foto;
                    message = "IMAGEN DE USUARIO MODIFICADA";
                }else {
                    fotoURL = "src/main/resources/img/default.png";
                    message = "IMAGEN DE USUARIO ELIMINADA";
                }
                port.modificarImagen(fotoURL);
            }

            DtUsuarioWeb usr = port.obtenerUsuarioWebNick(nickname);
            s.setAttribute("usuario", usr);
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/module/miPerfil.jsp");
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
