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

@WebServlet(name = "AgregarALista", value = "/AgregarALista")
public class AgregarALista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CListaRepPublishService service = new publicadores.CListaRepPublishService();
        publicadores.CListaRepPublish port = service.getCListaRepPublishPort();

        String nomLis = request.getParameter("l");
        String usrVid = request.getParameter("vu");
        String nomVid = request.getParameter("vn");
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        if (nomLis != null && usrVid != null && nomVid != null && usr != null) {
            String usrLis = usr.getNickname();
            if (!port.existeListaParticular(usrLis, nomLis)) {
                port.agregarVideoListaPorDefecto(usrVid, nomVid, nomLis);
            } else {
                port.agregarVideoListaParticular(usrVid, nomVid, nomLis);
            }
            String path = "/module/visualizarVideo.jsp?u=" + usrVid + "&v=" + nomVid;
            RequestDispatcher rd;
            rd = request.getRequestDispatcher(path);
            String message = "Video agregado a la lista " + nomLis;
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }
    }
}
