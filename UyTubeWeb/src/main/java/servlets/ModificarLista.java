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


@WebServlet(name = "ModificarLista", value = "/ModificarLista")
public class ModificarLista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CListaRepPublishService serviceListaRep = new publicadores.CListaRepPublishService();
        publicadores.CListaRepPublish portL = serviceListaRep.getCListaRepPublishPort();

        String nomLista = request.getParameter("nomL");
        Boolean esPublica;
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        if(usr != null) {
            if (request.getParameter("esPublica") == null) {
                esPublica = false;
            } else {
                esPublica = true;
            }

            portL.setuList(usr.getNickname());
            portL.setLista(nomLista);
            portL.modificarInfoLista(nomLista, esPublica);

            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/module/consultaLista.jsp?id=" + nomLista);
            String message = "Cambios realizados";
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
