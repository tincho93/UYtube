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

@WebServlet(name = "CrearLista", value = "/CrearLista")
public class CrearLista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        if (usr != null){
            publicadores.CListaRepPublishService serviceListaRep = new publicadores.CListaRepPublishService();
            publicadores.CListaRepPublish portListaRep = serviceListaRep.getCListaRepPublishPort();

            String nomLista = request.getParameter("nomList");
            String categoria = request.getParameter("categoria");
            Boolean esPublica;
            if(request.getParameter("esPublica") == null){
                esPublica = false;
            }else{
                esPublica = true;
            }
            if (nomLista != null && usr != null){
                if(portListaRep.existeListaParticular(usr.getNickname(), nomLista)){
                    RequestDispatcher rd;
                    rd = request.getRequestDispatcher("/module/nuevaLista.jsp");
                    String message = "Ya existe una lista con ese nombre";
                    request.setAttribute("message", message);
                    rd.forward(request, response);
                } else {
                    if(categoria.equals("")) {
                        portListaRep.agregarListaParticular(nomLista, esPublica);
                    } else {
                        portListaRep.agregarListaParticularCategoria(nomLista, esPublica, categoria);
                    }
                    RequestDispatcher rd;
                    rd = request.getRequestDispatcher("/index.jsp");
                    String message = "Se ha creado la Lista de Reproduccion <strong>" + nomLista + "</strong>";
                    request.setAttribute("message", message);
                    rd.forward(request, response);
                }
            } else {
                response.getWriter().append("Parametros invalidos");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
    }
}
