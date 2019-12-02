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

@WebServlet(name = "ValorarVideo", value = "/ValorarVideo")
public class ValorarVideo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CVideoPublishService serviceVideo = new publicadores.CVideoPublishService();
        publicadores.CVideoPublish portVideo = serviceVideo.getCVideoPublishPort();

        String uVid = request.getParameter("u");
        String nVid = request.getParameter("v");
        String gustaS = request.getParameter("g");
        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
        if(usr != null && uVid != null && nVid != null && gustaS != null && (gustaS.equals("si") || gustaS.equals("no"))){
            portVideo.setUsr(uVid);
            portVideo.setVid(nVid);
            portVideo.valorarVideo(usr.getNickname(), gustaS.equals("si"));
            String path = "/module/visualizarVideo.jsp?u=" + uVid +"&v=" + nVid + "&h=no";
            RequestDispatcher rd;
            rd = request.getRequestDispatcher(path);
            String message;
            if(gustaS.equals("si")){
                message = "Te gusta el video";
            } else {
                message = "No te gusta el video";
            }
            request.setAttribute("message", message);
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/module/invalido.jsp");
        }
    }
}
