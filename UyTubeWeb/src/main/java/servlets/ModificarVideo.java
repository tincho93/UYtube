package servlets;

import publicadores.DtFecha;
import publicadores.DtUsuarioWeb;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "ModificarVideo" , value = "/ModificarVideo")
public class ModificarVideo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CVideoPublishService serviceVideo = new publicadores.CVideoPublishService();
        publicadores.CVideoPublish portVideo = serviceVideo.getCVideoPublishPort();

        HttpSession s = request.getSession();
        DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");

        if(usr != null){
            String nomOriginal = request.getParameter("nomOriginal");
            String nomVideo = request.getParameter("nomVid");
            int duracion = Integer.parseInt(request.getParameter("dur"));
            String url = request.getParameter("url");
            String descripcion = request.getParameter("desc");
            String catVideo = request.getParameter("categoria");
            String fPub = request.getParameter("fPub");

            //CODIGO PARA EXTRAER LA FECHA
            Date date = null;
            Calendar cal = Calendar.getInstance();
            try {
                date = new SimpleDateFormat("MM/dd/yyyy").parse(fPub);
                cal.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DtFecha fecha = new DtFecha();
            fecha.setAnio(cal.get(Calendar.YEAR));
            fecha.setMes(cal.get(Calendar.MONTH));
            fecha.setDia(cal.get(Calendar.DAY_OF_MONTH));
            //FIN DE CODIGO PARA EXTRAER LA FECHA

            Boolean publico;
            if(request.getParameter("publico") == null){
                publico = false;
            }else{
                publico = true;
            }
            portVideo.setUsr(usr.getNickname());
            portVideo.setVid(nomOriginal);
            portVideo.modificarInfoVideo(nomVideo, descripcion, fecha, duracion, url, publico);
            if(!catVideo.isEmpty()) {
                portVideo.agregarCategoria(catVideo);
            }
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/index.jsp");
            String message = "Se ha modificado el video <strong>" + nomVideo + "</strong>";
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
